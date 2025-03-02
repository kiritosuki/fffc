<!-- src/components/UserDialog.vue -->
<template>
    <el-dialog
      v-model="visible"
      title="更换用户"
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
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="form.username"
            placeholder="请输入新用户名"
            clearable
          />
        </el-form-item>
  
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="form.password"
            type="password"
            placeholder="请输入密码"
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
//   import { changeUser } from '../../../api/api'
//   import { useUserStore } from '../../../utils/token'
  
//   const emit = defineEmits(['success'])
  const visible = ref(false)
//   const loading = ref(false)
  const formRef = ref(null)
  
  const form = reactive({
    username: '',
    password: ''
  })
  
  const rules = reactive({
    username: [
      { required: true, message: '用户名不能为空', trigger: 'blur' }
    ],
    password: [
      { required: true, message: '密码不能为空', trigger: 'blur' }
    ]
  })
  
//   const userStore = useUserStore()
  
  const handleOpen = () => {
    visible.value = true
  }
  
  const handleClose = (done) => {
    formRef.value.resetFields()
    done()
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
        username: form.username,
        password: form.password
      })
  
    //   userStore.setUser(data.userInfo)
    //   userStore.setToken(data.token)
      
      ElMessage.success('用户切换成功')
    //   emit('success')
      visible.value = false
    } catch (error) {
      if (error !== 'cancel') {
        ElMessage.error(error.message || '用户切换失败')
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