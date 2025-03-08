// router.js
import { createRouter, createWebHistory } from 'vue-router'




import Home from '../views/system/index.vue'
import introduce from '../views/system/introduce.vue'
import AppLayout from '../components/layout/header.vue'
import Admin from '../views/system/administration.vue'
import Analyze from '../views/system/analyze.vue'
import personal from '../views/system/personalCenter.vue'


const routes = [
  {
  path: '/',
  component: AppLayout, // 父路由使用布局组件
  children: [
      { path: '/home', component: Home},
      { path: '/admin', component: Admin },
      { path: '/analyze', component: Analyze },
      { path: '/introduce', component: introduce },
      { path: '/personal', component: personal},
      {path: '/index',name: 'AddPatient',
        component: () => import('@/views/system/index.vue')
      }
    ]
  }
]

const router = createRouter({
  history:createWebHistory(),
  routes
})

export default router
// router.js
