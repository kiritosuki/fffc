import request from "../utils/request"



 //  获取特定的患者信息
export function CheckPatient(name, gender, begin, end, page = 1, pageSize = 10){
    let url = `/patients?page=${page}&pageSize=${pageSize}`
    if(name) url += `&name=${name}`
    if(gender) url += `&gender=${gender}`
    if(begin) url += `&begin=${begin}`
    if(end) url += `&end=${end}`
    
    return request({
        url: url,
        method:"GET"
    })
}



// 删除患者信息
export function DelPatient(ids){

    return request({
        url:`/patients?ids=${ids}`,
        method:"DELETE"
    })
}



// 添加患者信息1
export function AddPatient(data){
    return request({
        url:"/patients",
        method:"POST",
        data
    })
}



// 添加患者信息2
//   export const addPatient = (data) => {
//     return request.post('/patients', data)
//   }



// 个人账号修改
export const changeUser = (data) => {
  return request({
    url: '/api/user/change',
    method: 'POST',
    data
  })
}



// 找回密码
export const changePassword = (data) => {
      return request({
        url: '/api/password/change',
        method: 'POST',
        data
      })
    }



// 上传图片接口
export const uploadImage = (file) => {
    const formData = new FormData()
    formData.append('file', file)
    return request.post('/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }
  




export default {
    CheckPatient,
    DelPatient,
    AddPatient,
    uploadImage,
    changePassword,
    changeUser,
    

}