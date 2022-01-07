<!-- 头像 -->
<template>
  <a-space size="large">
    <a-avatar :size="96" @click="click_event">
      <img
          alt="avatar"
          src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/3ee5f13fb09879ecb5185e440cef6eb9.png~tplv-uwbnlip3yd-webp.webp"
      />
    </a-avatar>
  </a-space>
</template>

<script>
import {reactive, toRefs} from "vue";

export default {
  name: "Avatar",
  setup() {

    // methods
    const methods = reactive({
      click_event: () => {
        console.log("Avatar click")
      }
    })

    return {
      ...toRefs(methods)
    }
  }
}
</script>

<style scoped>

</style>
