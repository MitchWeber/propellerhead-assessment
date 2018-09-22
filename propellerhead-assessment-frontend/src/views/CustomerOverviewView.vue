<template>
    <el-table :data="getCustomers" style="width: 100%">
        <el-table-column sortable prop="entityId" label="Id"></el-table-column>
        <el-table-column sortable prop="name" label="Name"></el-table-column>
        <el-table-column sortable 
                         prop="status" 
                         :filters="statusFilter"
                         :filter-method="filterTag" 
                         label="Status"></el-table-column>
        <el-table-column sortable prop="created" label="Date"></el-table-column>
        <el-table-column prop="link.self" label="">
          <template slot-scope="scope">
            <router-link :to='"/customer/" + getCustomers[scope.$index].entityId'>View Details</router-link>
          </template>
        </el-table-column>
  </el-table>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import CustomerExcerpt from "../model/CustomerExcerpt";
import ICustomerExcerpt from "../model/CustomerExcerpt";
import CustomerStatus from "../model/CustomerStatus";
import { mapGetters } from "vuex";
import { Row } from "element-ui";

@Component({
  computed: mapGetters(["getCustomers"])
})
export default class CustomerOverviewView extends Vue {
  filterTag(value: string, row: any) {
    return row.status === value;
  }

  get statusFilter(): Array<Object> {
    const filters = Array<Object>();
    for (status in CustomerStatus) {
      filters.push({ text: CustomerStatus[status], value: status });
    }
    return filters;
  }
}
</script>


<style scoped>
</style>
