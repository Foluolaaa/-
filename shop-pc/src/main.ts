import { createApp } from 'vue'
//import './style.css'
import App from './App.vue'
import router from './router/index'
import ElementPlus from 'element-plus'
import myconfirm from './utils/myconfirm'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
//引入Pinia构造函数
import { createPinia } from 'pinia'
// 实例化 Pinia
const pinia = createPinia()
const app = createApp(App);
// 使用Element Plus的库和图标库
app.use(ElementPlus,{
    locale:zhCn,
}).use(router).use(pinia).mount('#app')
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
//全局挂载 
app.config.globalProperties.$myconfirm = myconfirm;