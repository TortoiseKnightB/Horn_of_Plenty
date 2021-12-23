//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//引入store
import store from './store/store'
//引入VueRouter
import VueRouter from 'vue-router'
//引入路由器
import router from './router/router'
// vue-axios 是按照 vue 插件的方式去写的，尽量不去破坏原型链
import axios from 'axios'
import VueAxios from 'vue-axios'

//关闭Vue的生产提示
Vue.config.productionTip = false
//应用插件
Vue.use(VueRouter)
Vue.use(VueAxios, axios);

//创建vm
new Vue({
    render: h => h(App),
    store: store,
    router: router
}).$mount('#app')
