// src/utils/errorHandler.js
import { ElMessage } from 'element-plus'

export const handleError = (error) => {
  if (error.response) {
    const status = error.response.status
    const code = error.response.data?.code
    const message = error.response.data?.msg || '请求失败'

    // 统一处理非1状态码
    if (code !== undefined && code !== 1) {
      ElMessage({
        message: `错误代码 ${code}: ${message}`,
        type: 'error',
        icon: 'CircleCloseFilled', // 使用叉号图标
        customClass: 'custom-error-message'
      })
    } else {
      ElMessage.error(message)
    }
  } else {
    ElMessage.error('网络连接异常，请检查网络设置')
  }
}