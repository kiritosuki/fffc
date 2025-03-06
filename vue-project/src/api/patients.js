import request from "../utils/request"


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


export function DelPatient(ids){

    return request({
        url:`/patients?ids=${ids}`,
        method:"DELETE"
    })
}



export function AddPatient(data){
    return request({
        url:"/patients",
        method:"POST",
        data
    })
}




export default {
    CheckPatient,
    DelPatient,
    AddPatient
}