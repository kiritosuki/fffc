import axios from 'axios'

const request = axios.create({
  baseURL: 'https://localhost:8080',    // 请求基础URL
  timeout: 1000
})

export default request