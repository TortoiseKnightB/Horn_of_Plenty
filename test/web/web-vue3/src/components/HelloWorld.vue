<template>
  <h2>HelloWorld</h2>
  <button v-on:click="test_sum">SUM</button>
  <button v-on:click="test_actions">ACTIONS</button>
  <button v-on:click="test_mutations">MUTATIONS</button>
  <button v-on:click="test_getters()">GETTERS</button>
  <h4>STORE_DATA: {{ data }}</h4>
  <h4>GETTERS_DATA: {{ getters_data }}</h4>
  <h4>HERO_NUMBER: {{ heroList.length }}</h4>
</template>

<script>
import {useStore} from 'vuex'
import {computed, reactive, toRefs} from "vue";

export default {
  name: "HelloWorld",
  setup() {
    const store = useStore();

    // computed
    const data = reactive({
      // state
      data: computed(() => store.state.helloworldStore.storeData),
      sum: computed(() => store.state.helloworldStore.storeSum),
      heroList: computed(() => store.state.heroStore.heroList),
      // getters
      getters_data: computed(() => store.getters["helloworldStore/testGetters"])
    })

    // methods
    const methods = reactive({
      test_sum: () => {
        console.log(store.state.helloworldStore.storeData)
      },
      test_actions: () => store.dispatch("helloworldStore/testActions", "11"),
      test_mutations: () => store.commit("helloworldStore/TEST_MUTATIONS", "22"),
      test_getters: () => console.log(store.getters["helloworldStore/testGetters"])
    })

    return {
      ...toRefs(data),
      ...toRefs(methods)
    }
  }
}
</script>

<style scoped>

</style>
