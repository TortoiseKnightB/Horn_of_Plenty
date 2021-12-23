<template>
  <div>
    <h2>HelloWorld</h2>
    <button v-on:click="test_sum">SUM</button>
    <button v-on:click="test_actions">ACTIONS</button>
    <button v-on:click="test_mutations">MUTATIONS</button>
    <button v-on:click="test_getters">GETTERS</button>
    <h4>STORE_DATA: {{ data }}</h4>
    <h4>GETTERS_DATA: {{ getters_data }}</h4>
    <h4>HERO_NUMBER: {{ heroList.length }}</h4>
  </div>
</template>

<script>
import {mapState, mapGetters} from 'vuex'

export default {
  name: "HelloWorld",
  computed: {
    ...mapState("helloWorldStore", {data: "storeData", sum: "storeSum"}),
    ...mapState("heroStore", {heroList: "heroList"}),
    ...mapGetters("helloWorldStore", {getters_data: "testGetters"})
  },
  methods: {
    test_sum() {
      console.log(this.$store.state.helloWorldStore.storeData);
    },
    test_actions() {
      this.$store.dispatch("helloWorldStore/testActions", "11")
    },
    test_mutations() {
      this.$store.commit("helloWorldStore/TEST_MUTATIONS", "22")
    },
    test_getters() {
      console.log(this.$store.getters["helloWorldStore/testGetters"])
    }
  }
}
</script>

<style scoped>

</style>