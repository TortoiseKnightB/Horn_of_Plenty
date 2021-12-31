<template>
  <div>
    <HelloWorld/>
    <Hero/>
    <div>
      <!-- Vue中借助router-link标签实现路由的切换 -->
      <router-link to="/router1">ROUTER_1</router-link>
      <router-link to="/router2">ROUTER_2</router-link>
    </div>
    <div>
      <!-- 指定组件的呈现位置 -->
      <router-view></router-view>
    </div>
    <br/>
    <Coin/>
    <br/>
    <TestController/>
  </div>
</template>

<script>
import HelloWorld from "./components/HelloWorld"
import Hero from "./components/Hero";
import Coin from "./components/Coin";
import TestController from "./components/TestController";

export default {
  name: 'App',
  components: {
    HelloWorld,
    Hero,
    Coin,
    TestController
  }
}
</script>
