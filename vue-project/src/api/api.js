// src/api/user.js
// import request from '../utils/request.js'

// export const changeUser = (data) => {
//   return request({
//     url: '/api/user/change',
//     method: 'POST',
//     data
//   })
// }
// export const changePassword = (data) => {
    //   return request({
    //     url: '/api/password/change',
    //     method: 'POST',
    //     data
    //   })
    // }

//     // 上传图片接口
// export const uploadImage = (file) => {
//     const formData = new FormData()
//     formData.append('file', file)
//     return request.post('/upload', formData, {
//       headers: {
//         'Content-Type': 'multipart/form-data'
//       }
//     })
//   }
  
//   // 添加病例接口
//   export const addPatient = (data) => {
//     return request.post('/patients', data)
//   }