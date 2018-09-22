<template>
  <div>
    <div class="loading" v-if="loading">
      Loading...
    </div>
    <div v-if="!loading">
      <el-row :gutter="20">
        <el-col :span="24">
          <h1>{{ customer.name }}</h1>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="24">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>Customer Details</span>
            </div>
            <el-form ref="form" :model="customer" label-width="120px">
              <el-row :gutter="40">
                <el-col :span="12">
                  <el-form-item label="ID">
                    <el-input v-model="customer.id" disabled="disabled"></el-input>
                  </el-form-item>
                  <el-form-item label="Created">
                      <el-date-picker v-model="customer.created"
                                      type="datetime"
                                      disabled="disabled"></el-date-picker>
                  </el-form-item>
                  <el-form-item label="Status">
                    <el-select v-model="customer.status">
                      <el-option label="prospective" value="PROSPECTIVE"></el-option>
                      <el-option label="current" value="CURRENT"></el-option>
                      <el-option label="non-active" value="NON_ACTIVE"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="Email">
                    <el-input v-model="customer.email" placeholder="" type="email"></el-input>
                  </el-form-item>
                  <el-form-item label="Telefone number">
                    <el-input v-model="customer.telefoneNumber" placeholder="" type="string"></el-input>
                  </el-form-item>
                  <el-form-item label="Mobile number">
                    <el-input v-model="customer.mobileNumber" placeholder="" type="string"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-button type="primary" @click="saveCustomerDetails">Save</el-button>
                </el-col>
              </el-row>
            </el-form>
          </el-card>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>Notes</span>
            </div>
            <el-row>
              <el-col :span="12">
                <el-form ref="form" :model="emptyNote" label-width="120px">
                  <el-form-item label="New Note">
                    <el-input v-model="emptyNote.content" type="textarea" :autosize="{ minRows: 4, maxRows: 8}"></el-input>
                  </el-form-item>
                  <el-form-item label="">
                    <el-button type="primary" @click="addNote">Add</el-button>
                  </el-form-item>
                </el-form>
              </el-col>
              <el-col :span="12">
                <article v-for="(note, index) in customer.notes" :key="index">
                  <el-form ref="form" :model="note" label-width="120px">
                    <div class="noteInfo">
                      <span><b>Created:</b> {{note.createdDate}}</span><span><b>Modified:</b> {{note.modifiedDate}}</span>
                    </div>
                    <el-form-item label="">
                      <el-input v-model="customer.notes[index].content" type="textarea" :autosize="{ minRows: 4, maxRows: 8}" ></el-input>
                    </el-form-item>
                    <el-form-item label="">
                      <el-button type="primary" @click="editNote(note)">Edit</el-button>
                    </el-form-item>
                  </el-form>
                </article>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import axios from "axios";
import { Notification } from "element-ui";
import CustomerDetails from "../model/CustomerDetails";
import CustomerExcerpt from "../model/CustomerExcerpt";
import Note from "../model/Note";
import CustomerStatus from "../model/CustomerStatus";
import { Getter } from "vuex-class";

@Component
export default class CustomerDetailsView extends Vue {
  customer: CustomerDetails = new CustomerDetails();
  loading: boolean = true;
  emptyNote: Note = new Note();
  customerStatus: Array<string> = Object.keys(CustomerStatus);

  @Getter getCustomerById;

  @Prop() id: string;

  saveCustomerDetails() {
    axios
      .post(`http://localhost:8080/rest/customer/${this.id}`, this.customer)
      .then(response => {
        this.notifySuccess("Saved");
      })
      .catch(e => {
        this.notifyError("Error", "Something went wrong");
      });
  }

  fetchData() {
    this.loading = true;
    const customerExcerpt: CustomerExcerpt = this.getCustomerById(
      Number(this.id)
    );

    console.log(customerExcerpt.linkToSelf);

    axios
      .get(customerExcerpt.linkToSelf)
      .then(response => {
        const c: CustomerDetails = new CustomerDetails();
        c.id = response.data.id;
        c.name = response.data.name;
        c.created = response.data.created;
        c.status = response.data.status;
        c.mobileNumber = response.data.mobileNumber;
        c.telefoneNumber = response.data.telefoneNumber;
        c.email = response.data.email;
        response.data.notes.forEach((note: Note) => {
          c.notes.push(note);
        });
        this.customer = c;
        this.loading = false;
      })
      .catch(e => {
        this.loading = false;
        this.notifyError("Error", "Something went wrong");
      });
  }

  addNote() {
    axios
      .post(
        `http://localhost:8080/rest/customer/${this.id}/note`,
        this.emptyNote
      )
      .then(response => {
        this.emptyNote = new Note();
        const note: Note = response.data;
        this.customer.notes.push(note);
        this.notifySuccess("New note added");
      })
      .catch(e => {
        this.notifyError("Error", "Something went wrong");
      });
  }

  notifySuccess(title: string, message: string = "") {
    this.$notify({
      title,
      message,
      type: "success"
    });
  }

  notifyError(title: string, message: string = "") {
    this.$notify({
      title,
      message,
      type: "error"
    });
  }

  editNote(note: Note) {
    axios
      .post(
        `http://localhost:8080/rest/customer/${this.id}/note/${note.id}`,
        note
      )
      .then(response => {
        note.modifiedDate = response.data.modifiedDate;
        this.notifySuccess("Saved");
      })
      .catch(e => {
        this.notifyError("Error", "Something went wrong");
      });
  }

  beforeMount() {
    console.log("data required");
    this.fetchData();
  }
}
</script>


<style scoped>
.noteInfo {
  font-size: 0.8rem;
  text-align: right;
}
.noteInfo span {
  margin-right: 0.5em;
}

.el-row {
  margin-bottom: 1.3em;
}
</style>
