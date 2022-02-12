<template>
  <div>
    <ul>
      <li v-for="hero in heroList" v-bind:key="hero.id">{{ hero.name }}</li>
    </ul>
    <button v-on:click="add_hero">add hero</button>
    <input type="text" v-model="heroName"/>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "Hero",
  data() {
    return {
      heroName: ""
    }
  },
  computed: {
    ...mapState("heroStore", {heroList: "heroList"})
  },
  methods: {
    add_hero() {
      this.$store.dispatch("heroStore/addHero", this.heroName);
      this.heroName = "";
    }
  }
}
</script>

<style scoped>

</style>