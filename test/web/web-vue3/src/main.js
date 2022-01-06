import {createApp} from 'vue'
import App from './App.vue'
import router from './router/router'
import store from './store'
// arco-design 组件库引入
import ArcoVue from '@arco-design/web-vue'
import '@arco-design/web-vue/dist/arco.css'

createApp(App).use(store).use(router).use(ArcoVue).mount('#app')
