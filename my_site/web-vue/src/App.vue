<template>
<!--  <div>-->
<!--    &lt;!&ndash; Vue中借助router-link标签实现路由的切换 &ndash;&gt;-->
<!--    <router-link to="/router1">ROUTER_1</router-link>-->
<!--    <router-link to="/router2">ROUTER_2</router-link>-->
<!--  </div>-->
  <div>
    <!-- 指定组件的呈现位置 -->
    <router-view></router-view>
  </div>
</template>


<script>
import Home from "@/views/Home";

export default {
  name: 'App',
  components: {
    Home
  }
}
</script>


