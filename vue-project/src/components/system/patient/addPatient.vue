<!-- src/components/patient/AddPatientDialog.vue -->
<template>
    <el-dialog
      v-model="visible"
      title="添加新病例"
      width="600px"
      :close-on-click-modal="false"
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="100px"
        label-position="right"
      >
        <!-- 基础信息 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="患者姓名" prop="name">
              <el-input
                v-model="form.name"
                placeholder="请输入患者姓名"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="年龄" prop="age">
              <el-input-number
                v-model="form.age"
                :min="1"
                :max="120"
                controls-position="right"
              />
            </el-form-item>
          </el-col>
        </el-row>
  
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-radio-group v-model="form.gender">
                <el-radio :label="1">男</el-radio>
                <el-radio :label="0">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号码" prop="phone">
              <el-input
                v-model="form.phone"
                placeholder="请输入手机号"
                clearable
              />
            </el-form-item>
          </el-col>
        </el-row>
  
        <!-- 图片上传 -->
      
        <el-form-item label="左眼照片" prop="leftImage">
          <el-upload
            action="#"
            list-type="picture-card"
            :auto-upload="false"
            :limit="1"
            :on-change="(file) => handleUpload(file, 'left')"
            :on-remove="() => handleRemove('left')"
          >
            <el-icon><Plus /></el-icon>
          </el-upload>
          <div class="upload-tip">支持JPG/PNG格式</div>
        </el-form-item>

        <el-form-item label="右眼照片" prop="rightImage">
          <el-upload
            action="#"
            list-type="picture-card"
            :auto-upload="false"
            :limit="1"
            :on-change="(file) => handleUpload(file, 'right')"
            :on-remove="() => handleRemove('right')"
          >
            <el-icon><Plus /></el-icon>
          </el-upload>
          <div class="upload-tip">支持JPG/PNG格式</div>
        </el-form-item>

        <!-- 提交按钮 -->
      </el-form>
  
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="handleCancel">取消</el-button>
          <el-button
            type="primary"
            :loading="submitting"
            @click="handleSubmit"
          >
          {{ submitting ? '提交中...' : '确认添加' }}
          </el-button>
        </span>
      </template>
    </el-dialog>
  </template>
  
  <script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
// import { uploadImage, addPatient } from '@/api/api.js'

const visible = ref(false)
const submitting = ref(false)
const formRef = ref(null)
const leftImage = ref(null)
const rightImage = ref(null)

const form = reactive({
  name: '',
  age: null,
  gender: 1,
  phone: ''
})

// 新增验证函数
const validateImage = (type) => {
  return (rule, value, callback) => {
    if ((type === 'left' && !leftImage.value) || 
        (type === 'right' && !rightImage.value)) {
      callback(new Error(`请上传${type === 'left' ? '左眼' : '右眼'}照片`))
    } else {
      callback()
    }
  }
}

const rules = reactive({
  name: [
    { required: true, message: '请输入患者姓名', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在2到10个字符', trigger: 'blur' }
  ],
  age: [
    { required: true, message: '请选择患者年龄', trigger: 'blur' }
  ],
  phone: [
    { required: true, message: '请输入手机号码', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '手机号格式不正确', trigger: 'blur' }
  ],
  leftImage: [
    { required: true, validator: validateImage('left'), trigger: 'change' }
  ],
  rightImage: [
    { required: true, validator: validateImage('right'), trigger: 'change' }
  ]
})

const handleOpen = () => {
  visible.value = true;
};

// 修改上传处理函数
const handleUpload = (file, type) => { // 添加type参数
  const isImage = file.raw.type.includes('image/')
  const isLt5M = file.raw.size / 1024 / 1024 < 5

  if (!isImage) {
    ElMessage.error('只能上传图片格式!')
    return false
  }
  if (!isLt5M) {
    ElMessage.error('图片大小不能超过5MB!')
    return false
  }
  
  // 清除已有文件
  if (type === 'left') {
    leftImage.value = file.raw
  } else {
    rightImage.value = file.raw
  }
  
  return false
}

// const handleSubmit = async () => {
//   try {
//     // 验证表单
//     await formRef.value.validateField(['leftImage', 'rightImage'])
//     await formRef.value.validate()

//     submitting.value = true

//     // 1. 上传左眼图片
//     const leftRes = await uploadImage(leftImage.value)
//     const leftImgUrl = leftRes.data.data
    
//     // 2. 上传右眼图片
//     const rightRes = await uploadImage(rightImage.value)
//     const rightImgUrl = rightRes.data.data

//     // 3. 提交病例数据
//     await addPatient({
//       name: form.name,
//       age: form.age,
//       gender: form.gender,
//       phone: form.phone,
//       leftImg: leftImgUrl,
//       rightImg: rightImgUrl
//     })

//     ElMessage.success('病例添加成功')
//     handleCancel()
//   } catch (error) {
//     if (error.response) {
//       const msgMap = {
//         400: '请求参数错误',
//         401: '身份验证失败',
//         500: '服务器错误'
//       }
//       ElMessage.error(msgMap[error.response.status] || '操作失败')
//     } else {
//       ElMessage.error('请求失败，请检查网络')
//     }
//   } finally {
//     submitting.value = false
//   }
// }
// 修改取消处理
const handleCancel = () => {
  formRef.value.resetFields()
  leftImage.value = null
  rightImage.value = null
  visible.value = false
}



defineExpose({ handleOpen });
</script>
  
  <style scoped>
  .upload-tip {
    font-size: 12px;
    color: #909399;
    margin-top: 8px;
  }
  </style>