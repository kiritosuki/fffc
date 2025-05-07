import axios from 'axios'
import { handleError } from '@/utils/errorHandler'
// const request = axios.create({
//   baseURL: 'http://localhost:8080',    // 请求基础URL
//   timeout: 1000
// })

// export default request
// import { defineStore } from 'pinia'
// import axios from 'axios';


// export const useUserStore = defineStore('user', {
//   state: () => ({
//     token: localStorage.getItem('token') || '',
//     username: localStorage.getItem('username') || ''
//   }),
//   actions: {
//     setToken(token) {
//       this.token = token
//       localStorage.setItem('token', token)
//     },
//     setUser(username) {
//       this.username = username
//       localStorage.setItem('username', username)
//     }
//   }
// })


const service = axios.create({
  baseURL: 'http://localhost:8080', // 请求基础URL
  timeout: 2000
})

// 请求拦截器
service.interceptors.request.use(config => {
  console.log(config)
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

// 响应拦截器
service.interceptors.response.use(
  response => {
    if (response.data.code !== 1) {
      return Promise.reject(response) 
    }
    return response
  },
  error => {
    handleError(error)
    if (error.response?.status === 401) {
      localStorage.removeItem('token')
      window.location.reload()
    }
    return Promise.reject(error)
  }
)

export default service