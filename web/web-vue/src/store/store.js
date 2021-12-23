//引入Vue核心库
import Vue from 'vue'
//引入Vuex
import Vuex from 'vuex'
import helloWorld from "./helloworld";
import hero from "./hero";
//应用Vuex插件
Vue.use(Vuex)


//创建并暴露store
export default new Vuex.Store({
    modules: {
        helloWorldStore: helloWorld,
        heroStore: hero
    }
})