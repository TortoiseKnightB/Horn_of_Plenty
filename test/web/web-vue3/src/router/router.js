import {createRouter, createWebHashHistory} from 'vue-router'
// 引入路由组件
import router1 from "@/views/router1";
import router2 from "@/views/router2";
import router2_1 from "@/views/router2_1";
import router2_2 from "@/views/router2_2";

const routes = [
    {
        name: 'r1',
        path: '/router1',
        component: router1,
        meta: {title: '路由_1'},
        beforeEnter: (to, from, next) => {
            console.log('独享路由守卫', from, to)
            next()
        }
    },
    {
        name: 'r2',
        path: '/router2',
        component: router2,
        meta: {title: '路由_2'},
        children: [
            {
                name: 'r2_1',
                path: 'router2_1',
                component: router2_1,
                meta: {title: '路由_2_1'},
                // 向下一个路由页面传参
                props($route) {
                    return {
                        id: $route.query.id,
                        message: $route.query.message
                    }
                }
            },
            {
                name: 'r2_2',
                path: 'router2_2',
                component: router2_2,
                meta: {title: '路由_2_2', isAuthenticated: true},
                props($route) {
                    return {
                        id: $route.query.id,
                        message: $route.query.message
                    }
                }
            }
        ]
    }

]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

//全局前置路由守卫————初始化的时候被调用、每次路由切换之前被调用
router.beforeEach((to, from, next) => {
    console.log('前置路由守卫', from, to)
    if (to.meta.isAuthenticated) { //判断是否需要鉴权
        if (localStorage.getItem('password') === '123456') {
            next()
        } else {
            alert('请在 Application 中加入 localStorage：{password,123456} ')
        }
    } else {
        next()
    }
})

//全局后置路由守卫————初始化的时候被调用、每次路由切换之后被调用
router.afterEach((to, from) => {
    console.log('后置路由守卫', from, to)
    document.title = to.meta.title || 'VUE_TEST_DEMO'
})

export default router
