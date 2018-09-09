<template>
    <el-table :data="getCustomers" style="width: 100%">
        <el-table-column sortable prop="id" label="Id"></el-table-column>
        <el-table-column sortable prop="name" label="Name"></el-table-column>
        <el-table-column sortable prop="status" label="Status"></el-table-column>
        <el-table-column sortable prop="created" label="Date"></el-table-column>
        <el-table-column prop="link.self" label="">
          <template slot-scope="scope">
            <router-link :to='"/customer/" + customers[scope.$index].id'>View Details</router-link>
          </template>
        </el-table-column>
  </el-table>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import axios from "axios";
import CustomerExcerpt from "../model/CustomerExcerpt";

@Component
export default class CustomerOverviewView extends Vue {
  customers: Array<CustomerExcerpt> = [];

  get getCustomers() {
    return this.customers;
  }

  created() {
    axios
      .get("http://localhost:8080/rest/customer/all")
      .then(response => {
        response.data.forEach((element: CustomerExcerpt) => {
          this.customers.push(element);
        });
      })
      .catch(e => {});
  }
}
</script>


<style scoped>
</style>
