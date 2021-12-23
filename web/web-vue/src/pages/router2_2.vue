<template>
  <div>
    {{ id }}::{{ message }}
  </div>
</template>

<script>
export default {
  name: "router2_2",
  props: ["id", "message"]
}
</script>

<style scoped>

</style>