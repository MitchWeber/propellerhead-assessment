<template>
  <div id="app">
    <header>
      <div>Propellerhead Technical Assessment</div>
      <router-link to="/">Overview</router-link>
    </header>
    <main>
        <router-view></router-view>
    </main>
    <footer>
        &copy; Michael Weber
    </footer>
  </div>
</template>

<script>
import CustomerOverviewView from './views/CustomerOverviewView.vue';

export default {
  name: 'app',
  components: {
    CustomerOverviewView,
  },
};
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

header {
  background-color: #d3d3d3;
  text-align: left;
  height: 5em;
}

main {
  height: 100%;
  padding-bottom: 2em;
}

footer {
  background-color:#d3d3d3;
  text-align: center;
  height: 4em;
  padding-top: 2em;
}
</style>
