import {createRouter, createWebHashHistory} from 'vue-router'
import Main from "@/Layout/main";
import sendOut from "@/components/sendOut.vue"
import Login from "@/components/Login"
import Echarts from "@/components/Echart.vue"
import receive from  "@/components/receive.vue"
import tosend from "@/components/tosend.vue"

const routes = [
    {
        path: "/", // path设置为/表示页面最初始的地址 / ,redirect表示要被重定向的新地址，设置为一个路由即可
        // redirect:"/Login",
        name:"Login",
        component: Login,
    },
    {
        path: '/Main',
        name: 'Main',
        component: Main,
        children: [
            {
                path: '/sendOut',
                name: 'sendOut',
                component: sendOut,

            },
            {
                path: '/receive',
                name: 'receive',
                component: receive
            },
            {
                path: '/tosend',
                name: 'tosend',
                component: tosend
            },
            {
                path: '/Echarts',
                name: 'Echarts',
                component: Echarts,
            },
        ]
    },


]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
