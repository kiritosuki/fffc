
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

// // 创建 axios 实例
// const service = axios.create({
//     baseURL: 'your_api_base_url', // 替换为你的 API 基础 URL
//     timeout: 5000 // 请求超时时间
//   });
  
//   // 请求拦截器
//   service.interceptors.request.use(
//     config => {
//       // 在发送请求之前做些什么，例如添加 token
//       const token = localStorage.getItem('token');
//       if (token) {
//         config.headers['Authorization'] = `Bearer ${token}`;
//       }
//       return config;
//     },
//     error => {
//       // 处理请求错误
//       console.log(error);
//       return Promise.reject(error);
//     }
//   );
  
//   // 响应拦截器
//   service.interceptors.response.use(
//     response => {
//       // 对响应数据做点什么
//       return response.data;
//     },
//     error => {
//       // 处理响应错误
//       console.log('err' + error);
//       return Promise.reject(error);
//     }
//   );
  
//   export default service;