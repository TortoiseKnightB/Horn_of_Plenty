<template>
  router1
</template>

<script>
import {onMounted, onUnmounted} from "vue";

export default {
  name: "router1",
  setup() {
    onMounted(() => {
      console.log("router1 mounted")
    })

    onUnmounted(() => {
      console.log("router1 unmounted")
    })
  }
}
</script>

<style scoped>

</style>
