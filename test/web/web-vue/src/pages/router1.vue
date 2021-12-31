<template>
  <div>
    router1
  </div>
</template>

<script>
export default {
  name: "router1",
  activated() {
    console.log("router1 activated")
  },
  deactivated() {
    console.log("router1 deactivated")
  }
}
</script>

<style scoped>

</style>