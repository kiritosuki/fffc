<template>
  <div class="body">
    <div class="page-container" :class="{ 'page-visible': pageVisible }">
          
  <div class="upload-container">
<div class="divider">
          <div class="divider-text">提交区</div>
        </div>
            <!-- 操作按钮 -->
    <!-- 上传区域 -->
    <el-upload
  action="#"
  list-type="picture-card"
  :auto-upload="false"
  :multiple="true"
  :limit="10"
  v-model:file-list="fileList"
  :on-change="handleChange"
  :on-remove="handleRemove"
  class="upload-drag-area"
>
  <el-icon><Plus /></el-icon>
  <template #tip>
    <div class="upload-tip">可上传1-10张眼部图片（支持JPG/PNG格式）</div>
  </template>
</el-upload>
<div class="action-buttons">
      <el-button 
        type="primary" 
        :loading="uploading"
        @click="handleUpload"
        :disabled="fileList.length === 0"
      >
        <template #loading>
          <el-icon class="is-loading"><Loading /></el-icon>
          上传中...
        </template>
        一键上传
      </el-button>
      <el-button @click="handleClear">一键清除</el-button>
    </div>
<div class="divider" v-if="ifUploading">
    <div class="divider-text">诊断信息</div>
  </div>
<div id="table">
    <!-- 结果表格 -->
    <el-table :data="resultData" style="width: 100%" v-if="resultData.length > 0">
      <el-table-column prop="index" label="序号" width="80" />
      <el-table-column label="图片" width="180">
        <template #default="{ row }">
          <el-image 
            :src="row.img" 
            :preview-src-list="previewList"
            fit="cover"
            style="width: 100px; height: 80px"
          />
        </template>
      </el-table-column>
      <el-table-column prop="diag" label="眼部关键字" />
      <el-table-column prop="resInfo" label="诊断结果" />
      <el-table-column prop="createTime" label="创建时间" width="180" />
    </el-table>
  </div></div></div></div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { Plus, Loading } from '@element-plus/icons-vue'
import api from '@/api'

const fileList = ref([])
const resultData = ref([])
const uploading = ref(false)
const ifUploading = ref(0)

const pageVisible = ref(false)

onMounted(() => {
  // 页面加载完成后触发动画
  setTimeout(() => {
    pageVisible.value = true
  }, 100) // 添加微小延迟确保DOM已渲染
})

// 计算属性用于图片预览
const previewList = computed(() => 
  resultData.value.map(item => item.img)
)

// 文件变化处理
const handleChange = (file) => {
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
  return true
}

// 删除文件
const handleRemove = () => {
  // 删除逻辑由el-upload自动处理
}

const handleUpload = async () => {
  uploading.value = true;
  try {
    const results = [];
    for (const [index, file] of fileList.value.entries()) {  // 获取索引
      const formData = new FormData();
      if (!file.raw || !(file.raw instanceof File)) {
        throw new Error('无效的文件对象');
      }
      formData.append('file', file.raw); // 确保键名与后端一致
      const res = await api.uploadLotImage(formData);
      
      console.log('API 返回:', res);  // 打印 API 返回的完整数据，查看结构

      if (res.data.code === 1) {
        results.push({
          ...res.data.data,
          index: index + 1  // 给每个结果加上索引，序号从1开始
        });
      } else {
        // 如果返回的 code 不等于 1，说明上传失败，显示具体错误信息
        ElMessage.error(res.data.msg || '上传失败');
      }
    }
    resultData.value = results;
  } catch (error) {
    // 打印错误信息
    console.error('上传出错:', error);
    ElMessage.error(error.data || '上传失败');
  } finally {
    uploading.value = false;
    ifUploading.value = 1;
  }
}





// 清除所有

const handleClear = () => {
  fileList.value = []
  resultData.value = []
  ifUploading.value = 0;
}
</script>

<style scoped>
.divider {
  text-align: left;
  display: inline-block;
  width: 100%;
  height: 50px;
  border-radius: 7px;
  background-color: rgb(238, 237, 245);
  margin-bottom: 20px;
  position: relative;
}

.divider::before {
  position: absolute;
  display: block;
  content: "";
  background-color: rgb(96, 71, 169);
  width: 6px;
  height: 100%;
  border-radius: 5px 0 0 5px;
}

.divider-text {
  margin-top: 13px;
  margin-left: 23px;
  font-family: 'PingFang SC', 'Helvetica Neue', Arial, sans-serif;
  color: rgb(96, 71, 169);
  font-size: 20px;
}

.page-container {
  opacity: 0;
  transform: translateY(30px);
  transition: all 0.6s cubic-bezier(0.25, 0.1, 0.25, 1);
}

.page-visible {
  opacity: 1;
  transform: translateY(0);
}

.body {
  padding-top: 15px;
  text-align: center;
}

.page-container {
  width: 80%;
  min-height: 85vh;
  display: inline-block;
  text-align: center;
  padding: 20px;
  background: #fefefe;
  border-radius: 30px;
  box-shadow: 9px 9px 18px #c3c3c3,
    -9px -9px 18px #e6e6e6;
}

#table {
  width: 100%;
  height: 100%;
  overflow-y: auto;
  overflow-x: hidden;
}

.upload-container {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.action-buttons {
  margin: 0px 0px 20px 0;
  display: flex;
  flex-direction: row-reverse;
  gap: 15px;
}

.upload-tip {
  font-size: 12px;
  color: var(--el-text-color-secondary);
  margin-top: 8px;
}





:deep(.el-upload-list__item) {
  transition: all 0.3s;
}


</style>