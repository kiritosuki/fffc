<!-- 在需要的位置添加触发按钮 -->
<template>
    <div class="user-area">
      
    <el-button type="text" @click="openUserDialog">
        <!-- 当前用户：{{ userStore.username }} -->
        SwitchUser
      </el-button>
      <user-dialog ref="userDialogRef"/>
      <!-- <user-dialog ref="userDialogRef" @success="handleUserChange" /> -->
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  // import { useUserStore } from '../../utils/token'
  import UserDialog from '../../components/system/admin/changeAdmin.vue'
  
//   const userStore = useUserStore()
  const userDialogRef = ref(null)
  
  const openUserDialog = () => {
    userDialogRef.value.handleOpen()
  }
  
  // const handleUserChange = () => {
  //   // 用户切换成功后的处理
  //   window.location.reload() // 或执行状态更新
  // }
  </script>