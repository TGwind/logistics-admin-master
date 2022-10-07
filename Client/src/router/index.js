import {createRouter, createWebHashHistory} from 'vue-router'
import Main from "@/Layout/main";
import Message from "@/components/Message"
import Award from "@/components/Award"
import Status from "@/components/Status"
import Punish from "@/components/Punish"
import Login from "@/components/Login"

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
                path: '/Message',
                name: 'Message',
                component: Message
            },
            {
                path: '/Status',
                name: 'Status',
                component: Status
            },
            {
                path: '/Award',
                name: 'Award',
                component: Award
            },
            {
                path: '/Punish',
                name: 'Punish',
                component: Punish,
            },
        ]
    },


]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
