import axios from 'axios'

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
  timeout: 10000
})

// 请求拦截器
service.interceptors.request.use(config => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

// 响应拦截器
service.interceptors.response.use(
  response => response.data,
  error => {
    if (error.response?.status === 401) {
      localStorage.removeItem('token')
      window.location.reload()
    }
    return Promise.reject(error)
  }
)

export default service