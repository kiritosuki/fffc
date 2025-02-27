// router.js
import Vue from 'vue'
import VueRouter from 'vue-router'
import AppLayout from '../components/layout/header.vue'
import Admin from '../views/administration.vue'
import Analyze from '../views/analyze.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: AppLayout, // 父路由使用布局组件
    children: [
      { path: '/admin', component: Admin  },
      { path: '/analyze', component: Analyze }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router