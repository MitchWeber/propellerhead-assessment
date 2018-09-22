import Vue from "vue";
import Vuex from "vuex";
import CustomerExcerpt from "./model/CustomerExcerpt";
import ICustomerExcerpt from "./model/CustomerExcerpt";
import axios from "axios";

Vue.use(Vuex);

interface State {
  customers: Array<CustomerExcerpt>;
}

const state: State = {
  customers: new Array<CustomerExcerpt>()
};

export default new Vuex.Store<State>({
  state,
  mutations: {
    addOrUpdateCustomer(state, customer: CustomerExcerpt) {
      const existingCustomer = state.customers.filter(
        c => c.entityId === customer.entityId
      );

      if (existingCustomer.length === 0) {
        state.customers.push(customer);
      } else {
        console.warn("The customer exists. Update note implemented");
        existingCustomer[0].links = customer.links;
      }
    },
    setCustomers(state, customers: Array<CustomerExcerpt>);
  },
  actions: {
    async init(context) {
      console.log("Store init start");
      const data: Array<ICustomerExcerpt> = [];
      axios
        .get("http://localhost:8080/rest/customer/all")
        .then(response => {
          response.data.forEach((element: ICustomerExcerpt) => {
            data.push(element);
          });
        })
        .catch(e => {
          console.error(e);
        });
      data.forEach(e =>
        context.commit("addOrUpdateCustomer", new CustomerExcerpt(e))
      );
      await console.log("Store init done");
    }
  },
  getters: {
    getCustomerById: state => (id: number) => {
      return state.customers.find((c: CustomerExcerpt) => c.entityId === id);
    },
    getCustomers: state => {
      return state.customers;
    }
  }
});
