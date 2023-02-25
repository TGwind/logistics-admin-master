import {createRouter, createWebHashHistory} from 'vue-router'
import Main from "@/Layout/main";
import Admin from "@/Layout/admin.vue"
import sendOut from "@/components/user/sendOut.vue"
import Login from "@/components/Login"
import userEcharts from "@/components/Echart.vue"
import adminEcharts from "@/components/Echart.vue"
import receive from "@/components/user/receive.vue"
import tosend from "@/components/user/toSend.vue"
import deliverMessage from "@/components/admin/deliverMessage.vue";
import checkBill from "@/components/admin/checkBill.vue";

const routes = [
    {
        path: "/", // path设置为/表示页面最初始的地址 / ,redirect表示要被重定向的新地址，设置为一个路由即可
        // redirect:"/Login",
        name: "Login",
        component: Login,
    },

    // 用户首页
    {
        path: '/Main',
        name: 'Main',
        component: Main,
        children: [
            {
                path: '/Main/sendOut',
                name: 'sendOut',
                component: sendOut,

            },
            {
                path: '/Main/receive',
                name: 'receive',
                component: receive
            },
            {
                path: '/Main/tosend',
                name: 'tosend',
                component: tosend
            },
            {
                path: '/Main/userEcharts',
                name: 'userEcharts',
                component: userEcharts, //这里使用的都是component下面的Echarts组件，要改成两个名字使用，否则路由错误
            },
        ]
    },

    // 管理员首页
    {
        path: '/admin',
        name: 'admin',
        component: Admin,
        children: [
            {
                path: '/admin/adminEcharts',
                name: 'adminEcharts',
                component: adminEcharts,
            },
            {
                path: '/admin/deliverMessage',
                name: 'deliverMessage',
                component: deliverMessage,
            },
            {
                path: '/admin/checkBill',
                name: 'checkBill',
                component: checkBill,
            },
        ]
    },


]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
