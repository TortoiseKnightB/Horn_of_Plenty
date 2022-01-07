<!-- 自我介绍卡片 -->
<template>
  <div :style="{ display: 'flex' }">
    <a-card :hoverable="true" :style="{ width: '100%' }" :bordered="false">
      ByteDance's core product, Toutiao ("Headlines"), is a content platform in
      China and around the world. Toutiao started out as a news recommendation
      engine and gradually evolved into a platform delivering content in various
      formats.
    </a-card>
  </div>
</template>

<script>
export default {
  name: "ProfileCard"
}
</script>

<style scoped>

</style>
