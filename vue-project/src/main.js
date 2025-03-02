// import Vue from 'vue'  //  vue2 的Vue创建
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import api from './api/index.js'
// import './assets/main.css'



const app = createApp(App)

app.config.globalProperties.$api = api

app.use(router)

app.mount('#app')
