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
        <el-form-item label="眼部照片">
          <el-upload
            action="#"
            list-type="picture-card"
            :auto-upload="false"
            :on-change="handleUpload"
            multiple
          >
            <el-icon><Plus /></el-icon>
          </el-upload>
          <div class="upload-tip">请分别上传左右眼照片（支持JPG/PNG格式）</div>
        </el-form-item>
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
  const imageFiles = ref([])
  
  const form = reactive({
    name: '',
    age: null,
    gender: 1,
    phone: '',
    diagTime: '',
    resInfo: ''
  })
  
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
    ]
  })
  
  // 暴露打开方法给父组件
  const handleOpen = () => {
    visible.value = true
  }
  
  const handleCancel = () => {
    formRef.value.resetFields()
    imageFiles.value = []
    visible.value = false
  }
  
  const handleUpload = (file) => {
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
    imageFiles.value.push(file.raw)
    return false // 手动上传
  }
  
  const handleSubmit = async () => {
    try {
      await formRef.value.validate()
      submitting.value = true
  
      // 构造FormData
      const formData = new FormData()
      Object.entries(form).forEach(([key, value]) => {
        formData.append(key, value)
      })
      imageFiles.value.forEach((file, index) => {
        formData.append(`eyeImages`, file)
      })
  
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
  
  defineExpose({ handleOpen })
  </script>
  
  <style scoped>
  .upload-tip {
    font-size: 12px;
    color: #909399;
    margin-top: 8px;
  }
  </style>