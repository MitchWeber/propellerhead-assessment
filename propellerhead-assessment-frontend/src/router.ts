import Vue from "vue";
import Router from "vue-router";
import CustomerOverviewView from "./views/CustomerOverviewView.vue";
import CustomerDetailsView from "./views/CustomerDetailsView.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      component: CustomerOverviewView
    },
    {
      path: "/customer/:id",
      component: CustomerDetailsView,
      props: true,
      name: "CustomerDetailsView"
    }
  ]
});
