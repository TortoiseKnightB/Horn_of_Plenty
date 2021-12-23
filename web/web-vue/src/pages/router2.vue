<template>
  <div>
    <ul>
      <li v-for="r in routerList" v-bind:key="r.id">
        <router-link v-bind:to="{
          name: r.destination,
          query:{
            id:r.id,
            message: r.text
          }
        }">{{ r.text }}
        </router-link>
      </li>
    </ul>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "router2",
  data() {
    return {
      routerList: [
        {
          id: '001',
          destination: 'r2_1',
          text: '2_1'
        },
        {
          id: '002',
          destination: 'r2_2',
          text: '2_2'
        }
      ]
    }
  },
  activated() {
    console.log("router2 activated")
  },
  deactivated() {
    console.log("router2 deactivated")
  }
}
</script>

<style scoped>

</style>