<template>
  <div class="page-container">
    <el-form ref="formRef" :model="form" :rules="rules" label-width="100px" class="patient-form">
      <el-form-item label="病例图片" prop="images">
        <el-upload
          action="#"
          list-type="picture-card"
          :class="['custom-upload', { 'hide-border': imageList.length > 0 }]"
          :auto-upload="false"
          :limit="10"
          :file-list="imageList"
          :on-change="(file) => handleAddFile(file)"
          :on-remove="(file) => handleRemove(file)"
          multiple
          drag
        >
          <template #default>
            <div class="drag-area">
              <el-icon class="drag-icon"><Plus /></el-icon>
              <div>点击或拖拽添加图片</div>
              <div class="upload-tip">支持JPG/PNG格式（1-10张）</div>
            </div>
          </template>

          <template #file="{ file }">
            <div class="image-item">
              <img class="el-upload-list__item-thumbnail" :src="file.url" />
              <div class="image-label">图{{ getImageIndex(file) }}</div>
              <div class="upload-status">
                <el-progress 
                  v-if="file.status === 'uploading'"
                  type="circle" 
                  :percentage="file.percent"
                  :width="60"
                />
                <el-icon v-else-if="file.status === 'success'" class="success-icon">
                  <CircleCheck />
                </el-icon>
                <el-icon v-else-if="file.status === 'error'" class="error-icon">
                  <CircleClose />
                </el-icon>
              </div>
            </div>
          </template>
        </el-upload>
      </el-form-item>

      <!-- 诊断结果表格 -->
      <el-table :data="tableData" v-loading="submitting">
        <el-table-column label="图片序号" width="100">
          <template #default="{ $index }">图{{ $index + 1 }}</template>
        </el-table-column>
        <el-table-column prop="eye" label="诊断关键字" />
        <el-table-column prop="info" label="诊断结果" />
        <el-table-column prop="time" label="时间" />
      </el-table>

      <div class="form-actions">
        <el-button @click="handleClearForm">重置</el-button>
        <el-button 
          type="primary" 
          :loading="submitting"
          :disabled="!canSubmit"
          @click="handleSubmit"
        >
          {{ submitText }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, CircleCheck, CircleClose } from '@element-plus/icons-vue'

const imageList = ref([])
const tableData = ref([])
const submitting = ref(false)
const formRef = ref(null)

// 验证规则
const validateImages = (rule, value, callback) => {
  if (imageList.value.length < 1) {
    callback(new Error('至少需要上传1张图片'))
  } else if (imageList.value.length > 10) {
    callback(new Error('最多只能上传10张图片'))
  } else {
    callback()
  }
}

const rules = reactive({
  images: [{ required: true, validator: validateImages, trigger: 'change' }]
})

// 获取图片序号
const getImageIndex = (file) => {
  return imageList.value.indexOf(file) + 1
}

// 计算属性
const canSubmit = computed(() => {
  return imageList.value.length > 0 &&
    !imageList.value.some(file => file.status === 'uploading')
})

const submitText = computed(() => {
  if (submitting.value) return '提交中...'
  const successCount = imageList.value.filter(f => f.status === 'success').length
  return successCount === imageList.value.length ? '重新提交' : '开始诊断'
})

// 文件管理逻辑
const handleAddFile = (file) => {
  file.status = 'pending'
  file.percent = 0
  file.url = URL.createObjectURL(file.raw)
  return true
}

const handleRemove = async (file) => {
  imageList.value = imageList.value.filter(f => f.uid !== file.uid)
}

// 核心上传逻辑（逐一上传）
const uploadFile = async (file) => {
  try {
    file.status = 'uploading'

    const formData = new FormData()
    formData.append('image', file.raw)
    formData.append('index', getImageIndex(file))

    const res = await api.uploadLotImage(formData, {
      onUploadProgress: e => {
        file.percent = Math.round((e.loaded * 100) / e.total)
      }
    })

    file.status = 'success'
    file.serverId = res.data.id
    return res.data

  } catch (err) {
    file.status = 'error'
    file.error = err.msg
    throw err
  }
}

// 提交按钮逻辑：逐张上传
const handleSubmit = async () => {
  submitting.value = true
  const results = []

  try {
    for (let i = 0; i < imageList.value.length; i++) {
      const file = imageList.value[i]

      // 跳过已经上传成功的图片
      if (file.status === 'success') continue

      const result = await uploadFile(file)

      // 填充表格数据
      results.push({
        eye: result.data.diag,
        info: result.data.resInfo,
        time: result.data.createTime
      })
    }

    // 更新表格数据
    tableData.value = results

    // 提示上传成功
    ElMessage.success('所有图片上传完成，诊断信息已更新')
  } catch (error) {
    ElMessage.error('上传过程中出现错误，请稍后重试')
  } finally {
    submitting.value = false
  }
}

// 清除表单
const handleClearForm = async () => {
  try {
    await ElMessageBox.confirm('确定要清除所有内容吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })

    imageList.value = []
    tableData.value = []
    formRef.value.resetFields()
    ElMessage.success('已重置')

  } catch (error) {
    // 取消操作
  }
}
</script>

  
  <style scoped>
  /* 新增图片标签样式 */
  .image-item {
    position: relative;
    width: 100%;
    height: 100%;
  }
  
  .image-label {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, 0.6);
    color: white;
    text-align: center;
    padding: 4px;
    font-size: 12px;
  }
  
  /* 调整上传区域高度 */
  :deep(.el-upload--picture-card) {
    width: 160px;
    height: 160px;
  }
  
  /* 响应式调整 */
  @media (max-width: 768px) {
    :deep(.el-upload--picture-card) {
      width: 100px;
      height: 100px;
    }
    
    .drag-text {
      font-size: 12px;
    }
  }
  
  .upload-tip {
    color: #666;
    font-size: 12px;
    margin-top: 8px;
  }

  .upload-status {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: rgba(255, 255, 255, 0.9);
  padding: 5px;
  border-radius: 50%;
}

.success-icon {
  color: #67c23a;
  font-size: 28px;
}

.error-icon {
  color: #f56c6c;
  font-size: 28px;
}
  </style>