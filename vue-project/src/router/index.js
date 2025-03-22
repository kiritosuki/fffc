// router.js
import { createRouter, createWebHistory } from 'vue-router'




import Home from '../views/system/index.vue'
import HomeResult from '../views/system/indexResult.vue'
import introduce from '../views/system/introduce.vue'
import AppLayout from '../components/layout/header.vue'
import Admin from '../views/system/administration.vue'
import Analyze from '../views/system/analyze.vue'
import personal from '../views/system/personalCenter.vue'
import newRecord from '../views/system/newRecord.vue'
import login from '../views/system/login.vue'
import history from '../views/system/history.vue'
import aLotOfPictures from '../views/system/aLotOfPictures.vue'

const routes = [
  {
  path: '/',
  component: AppLayout, // 父路由使用布局组件
  children: [
      { path: '/home', component: Home },
      { path: '/admin', component: Admin },
      { path: '/homeResult', component: HomeResult,props: (route) => ({ id: route.query.id }), props: true ,name: 'HomeResult'},
      { path: '/analyze', component: Analyze },
      { path: '/introduce', component: introduce },
      { path: '/personal', component: personal},
      { path: '/patients/info', component: newRecord,props: (route) => ({ id: route.query.id })},
      { path: '/patients/history', component: history, props: (route) => ({ id: route.query.id })},
      { path: '/login', component: login},
      { path: '/aLotOfPictures', component: aLotOfPictures},
    ]
  }
]

const router = createRouter({
  history:createWebHistory(),
  routes
})

export default router
// router.js
