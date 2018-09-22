import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "./plugins/element";
import store from "./store";

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  beforeCreate() {
    store.dispatch("init");
  },
  render: h => h(App)
}).$mount("#app");
