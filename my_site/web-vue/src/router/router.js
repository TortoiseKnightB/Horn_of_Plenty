import {createRouter, createWebHashHistory} from 'vue-router'
import Home from '../views/Home.vue'
import test from "@/views/test";

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: "/test",
        name: "test",
        component: test
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
