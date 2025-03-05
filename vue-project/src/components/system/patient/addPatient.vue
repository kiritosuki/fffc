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
  
        <!-- 诊断信息 -->
        <el-form-item label="诊断日期" prop="diagTime">
          <el-date-picker
            v-model="form.diagTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="选择日期"
          />
        </el-form-item>
  
        <el-form-item label="诊断结果" prop="resInfo">
          <el-input
            v-model="form.resInfo"
            type="textarea"
            :rows="4"
            placeholder="请输入诊断详情"
            show-word-limit
            maxlength="500"
          />
        </el-form-item>
  
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
            确认添加
          </el-button>
        </span>
      </template>
    </el-dialog>
  </template>
  
  <script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'

const visible = ref(false)
const submitting = ref(false)
const formRef = ref(null)
const leftImage = ref(null)
const rightImage = ref(null)

const form = reactive({
  name: '',
  age: null,
  gender: 1,
  phone: '',
  diagTime: '',
  resInfo: ''
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
  diagTime: [
    { required: true, message: '请选择诊断日期', trigger: 'change' }
  ],
  resInfo: [
    { required: true, message: '请输入诊断结果', trigger: 'blur' }
  ],
  leftImage: [
    { required: true, validator: validateImage('left'), trigger: 'change' }
  ],
  rightImage: [
    { required: true, validator: validateImage('right'), trigger: 'change' }
  ]
})

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

// 修改提交处理
const handleSubmit = async () => {
  try {
    // 先验证图片字段
    await formRef.value.validateField(['leftImage', 'rightImage'])
    // 验证其他字段
    await formRef.value.validate()

    submitting.value = true

    // 构造FormData
    const formData = new FormData()
    Object.entries(form).forEach(([key, value]) => {
      formData.append(key, value)
    })
    
    // 添加图片文件
    if (leftImage.value) {
      formData.append('leftEyeImage', leftImage.value)
    }
    if (rightImage.value) {
      formData.append('rightEyeImage', rightImage.value)
    }

    // 模拟API调用
    console.log('提交数据:', Object.fromEntries(formData))
    await new Promise(resolve => setTimeout(resolve, 1500))
    
    ElMessage.success('病例添加成功（演示模式）')
    handleCancel()
  } catch (error) {
    if (error) {
      ElMessage.error('请正确填写表单')
    }
  } finally {
    submitting.value = false
  }
}

// 修改取消处理
const handleCancel = () => {
  formRef.value.resetFields()
  leftImage.value = null
  rightImage.value = null
  visible.value = false
}
// 定义 handleOpen 函数
const handleOpen = () => {
  visible.value = true;
};

defineExpose({ handleOpen });
</script>
  
  <style scoped>
  .upload-tip {
    font-size: 12px;
    color: #909399;
    margin-top: 8px;
  }
  </style>