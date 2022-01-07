<template>
  <ul>
    <li v-for="hero in heroList" v-bind:key="hero.id">{{ hero.name }}</li>
  </ul>
  <button v-on:click="add_hero">add hero</button>
  <input type="text" placeholder="heroName" v-model="heroName"/>
</template>

<script>
import {useStore} from "vuex";
import {computed, ref} from "vue";

export default {
  name: "Hero",
  setup() {
    const store = useStore()

    // data
    // v-model 双向绑定用这个顶替
    const heroName = ref("")

    // computed
    // state
    const heroList = computed(() => store.state.heroStore.heroList)

    // methods
    const add_hero = () => {
      store.dispatch("heroStore/addHero", heroName.value)
      heroName.value = ""
    }

    return {
      heroName,
      heroList,
      add_hero
    }
  }
}
</script>

<style scoped>

</style>
