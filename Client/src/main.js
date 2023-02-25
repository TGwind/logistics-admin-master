import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import global from '@/utils/global_val' //导入全局变量
import '../src/assets/global.css' //引入css

// ! element-plus vue3.0
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'dayjs/locale/zh-cn' //中文
import locale from 'element-plus/lib/locale/lang/zh-cn' //中文
import * as ElIconModules from '@element-plus/icons'// 统一导入el-icon图标\


const app=createApp(App)
for(let iconName in ElIconModules){
    app.component(iconName,ElIconModules[iconName])
}
app.use(store).use(router).use(ElementPlus,{locale}).mount('#app')




