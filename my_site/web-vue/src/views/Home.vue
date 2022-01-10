<template>
  <div class="wrapper">
    <ul id="backtop-layout">
      <!--  header  -->
      <Header/>
      <!--  content  -->
      <Content/>
      <!--  footer  -->
      <Footer/>
    </ul>
    <!-- 返回顶部 -->
    <a-back-top target-container="#backtop-layout" :style="{position:'absolute'}" visible-height="10">
      <a-button>UP</a-button>
    </a-back-top>
  </div>
</template>


<script>
// 自定义
import Header from "@/components/module/header/Header";
import Content from "@/components/module/content/Content";
import Footer from "@/components/module/footer/Footer";

export default {
  name: "Home",
  components: {
    Header,
    Content,
    Footer,
  },
}
</script>


<style scoped lang="less">
// 返回顶部
.wrapper {
  position: relative;

  display: flex;
  flex-direction: column;

  height: calc(100vh);

  ul {
    height: calc(100vh);
    overflow-y: auto;
  }
}
</style>
