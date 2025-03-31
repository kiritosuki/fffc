import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import api from './api/index.js'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './styles/message.scss'

import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// import Loading from './views/error/loading.vue'

// import './assets/main.css'

const app = createApp(App)

// Elment的所有图标注册
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }

app.use(router)

app.mount('#app')

app.config.globalProperties.$api = api

app.use(ElementPlus)

app.use(createPinia())

// app.component('Loading',Loading)




