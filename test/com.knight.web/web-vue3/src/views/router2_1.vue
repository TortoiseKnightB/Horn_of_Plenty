<template>
  {{ id }}::{{ message }}
</template>

<script>
export default {
  name: "router2_1",
  // 接收上一个路由页面传过来的参数
  props: ["id", "message"]
}
</script>

<style scoped>

</style>
