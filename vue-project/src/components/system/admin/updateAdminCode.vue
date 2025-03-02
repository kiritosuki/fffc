<template>
    <el-dialog
      v-model="visible"
      title="更改密码"
      width="30%"
      :close-on-click-modal="false"
      :show-close="false"
      :before-close="handleClose"
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="80px"
      >
        <el-form-item label="旧密码" prop="oldPassword">
          <el-input
            v-model="form.oldPassword"
            type="oldPassword"
            placeholder="请输入旧密码"
            show-password
            clearable
          />
        </el-form-item>
  
        <el-form-item label="新密码" prop="newPassword">
          <el-input
            v-model="form.newPassword"
            type="newPassword"
            placeholder="请输入新密码"
            show-password
            clearable
          />
        </el-form-item>

        <el-form-item label="确认密码" prop="confirmPassword">
        <el-input
          v-model="form.confirmPassword"
          type="password"
          placeholder="请再次输入新密码"
          show-password
          clearable
        />
      </el-form-item>
      </el-form>
  
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="handleCancel">取消</el-button>
          <el-button
            type="primary"
            :loading="loading"
            @click="handleConfirm">
            确定
          </el-button>
        </span>
      </template>
    </el-dialog>
  </template>
  
  <script setup>
  import { ref, reactive } from 'vue'
  import { ElMessage } from 'element-plus'
//   import { changePassword } from '../../../api/api'
//   import { useUserStore } from '../../../utils/token'
  
//   const emit = defineEmits(['success'])
  const visible = ref(false)
//   const loading = ref(false)
  const formRef = ref(null)
  
  const form = reactive({
    oldPassword: '',
    newPassword: '',
    confirmPassword: ''
  })
  
  const validatePassword = (rule, value, callback) => {
  const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/
  if (!regex.test(value)) {
    callback(new Error('需8-20位字母和数字组合'))
  } else {
    callback()
  }
}

const validateConfirm = (rule, value, callback) => {
  if (value !== form.newPassword) {
    callback(new Error('两次输入密码不一致'))
  } else {
    callback()
  }
}

  const rules = reactive({
    oldPassword: [
      { required: true, message: '旧密码不能为空', trigger: 'blur' }
    ],
    newPassword: [
      { required: true, message: '新密码不能为空', trigger: 'blur' },
      { validator: validatePassword, trigger: 'blur' }
    ],
    confirmPassword: [
      { required: true, message: '确认密码不能为空', trigger: 'blur' },
      { validator: validateConfirm, trigger: 'blur' } 
    ]
  })
  
//   const userStore = useUserStore()
  
  const handleOpen = () => {
    visible.value = true
  }
  

  
  const handleCancel = () => {
    visible.value = false
    formRef.value.resetFields()
  }
  
  const handleConfirm = async () => {
    try {
      await formRef.value.validate()
      loading.value = true
      
      const { data } = await changeUser({
        oldPassword: form.oldPassword,
        newPassword: form.password
      })
  
    //   const { data } = await changePassword({
    //   oldPassword: form.oldPassword,
    //   newPassword: form.newPassword
    // })
 
      
      ElMessage.success('密码重置成功')
    
      visible.value = false
    } catch (error) {
      if (error !== 'cancel') {
        ElMessage.error(error.message || '密码重置失败')
      }
    } finally {
      loading.value = false
    }
  }

  defineExpose({ handleOpen })
  </script>
  
  <style scoped>
  .dialog-footer {
    display: flex;
    justify-content: flex-end;
  }
  </style>