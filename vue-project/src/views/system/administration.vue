<script setup>
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import api from '../../api/index'
import { ref } from 'vue'

const drawer = ref(false)






</script>




<template>
<el-button @click="drawer = true" type="primary" style="margin-left: 16px;">
  搜索病例
</el-button>

<el-drawer
  title="我是标题"
  :visible.sync="drawer"
  direction="rtl"
  :before-close="handleClose">
  <span>我来啦!</span>
</el-drawer>


</template>