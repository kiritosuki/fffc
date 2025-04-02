<!-- src/views/patient/AddPatientPage.vue -->
<template>
  <div class="page-container">
    <el-form ref="formRef" :model="form" :rules="rules" label-width="100px" label-position="right" class="patient-form">
      <!-- 基础信息 -->
      <el-row :gutter="24">
        <el-col :span="8">
          <el-form-item label="患者姓名" prop="name">
            <el-input v-model="form.name" placeholder="请输入患者姓名" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="年龄" prop="age">
            <el-input-number v-model="form.age" :min="1" :max="120" controls-position="right" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别" prop="gender">
            <el-radio-group v-model="form.gender">
              <el-radio :value="1">男</el-radio>
              <el-radio :value="2">女</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        
        <el-col :span="10">
          <el-form-item label="手机号码" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入手机号" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="身份证号" prop="idCard">
            <el-input v-model="form.idCard" placeholder="请输入身份证号" clearable>
              <template #append>
                <el-button @click="handleQueryPatient" :loading="queryLoading" icon="Search">
                  <template #loading>
                    <span class="custom-loading">
                      <el-icon class="is-loading">
                        <Loading />
                      </el-icon>
                      查询中...
                    </span>
                  </template>
                  查询</el-button>
              </template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="24">
      
        <el-col :span="12">
      <!-- 图片上传 -->
      <el-form-item label="左眼照片" prop="leftImage">
        <el-upload action="#" list-type="picture-card"
          :class="['custom-upload', { 'hide-border': leftImageList.length > 0 }]" :auto-upload="false" :limit="1"
          :file-list="leftImageList" :on-change="(file) => handleUpload(file, 'left')"
          :on-remove="() => handleRemove('left')" drag :multiple="false">
          <template #default>
            <div class="drag-area">
              <template v-if="leftImageList.length < 1">
                <el-icon class="drag-icon">
                  <Plus />
                </el-icon>
                <div class="drag-text">点击或拖拽文件到此区域</div>
              </template>
            </div>
          </template>
        </el-upload>
        <!-- <div class="upload-tip">支持JPG/PNG格式</div> -->
      </el-form-item>
    </el-col>
    <el-col :span="12">
      <el-form-item label="右眼照片" prop="rightImage">
        <el-upload action="#" list-type="picture-card"
          :class="['custom-upload', { 'hide-border': rightImageList.length > 0 }]" :auto-upload="false" :limit="1"
          :file-list="rightImageList" :on-change="(file) => handleUpload(file, 'right')"
          :on-remove="() => handleRemove('right')" drag :multiple="false">
          <template #default>
            <div class="drag-area">
              <template v-if="rightImageList.length < 1">
                <el-icon class="drag-icon">
                  <Plus />
                </el-icon>
                <div class="drag-text">点击或拖拽文件到此区域</div>
                <!-- <div class="upload-tip">支持JPG/PNG格式</div> -->
              </template>
            </div>
          </template>
        </el-upload>
        

      </el-form-item>
    </el-col>
    </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="过敏药物" prop="allergy">
            <el-input v-model="form.allergy" placeholder="请输入过敏药物" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="患者主诉" prop="complaint">
            <el-input v-model="form.complaint" placeholder="请输入患者主诉" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否初诊" prop="visit">
            <el-radio-group v-model="form.visit">
              <el-radio :value="1">初诊</el-radio>
              <el-radio :value="2">复诊</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="现病史" prop="presHistory">
            <el-input v-model="form.presHistory" placeholder="请输入现病史" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="既往史" prop="pastHistory">
            <el-input v-model="form.pastHistory" placeholder="请输入既往史" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="阳体特征" prop="posFeature">
            <el-input v-model="form.posFeature" placeholder="请输入阳体特征" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="阴体特征" prop="negFeature">
            <el-input v-model="form.negFeature" placeholder="请输入阴体特征" clearable />
          </el-form-item>
        </el-col>

      </el-row>

      <!-- 提交按钮 -->
      <div class="form-actions">
        <el-button @click="handleCancel">取消</el-button>
        <el-button @click="handleClearForm">清除</el-button>
        <el-button type="primary" :loading="submitting" @click="handleSubmit">
          {{ submitting ? '提交中...' : '确认添加' }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>
// 保持原有script部分内容不变，仅移除弹窗相关逻辑
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import api from '../../api/index'
import { useRouter } from 'vue-router'
import Loading from '../error/loading.vue'
const router = useRouter();
const submitting = ref(false)
const formRef = ref(null)

const leftImage = ref(new FormData())
const rightImage = ref(new FormData())
const leftImageList = ref([])
const rightImageList = ref([])

const queryLoading = ref(false)

const form = reactive({
  name: '',
  age: null,
  gender: 1,
  phone: '',
  idCard: '',
  leftImg: '',
  rightImg: '',
  allergy: '',
  complaint: '',
  visit: 1,
  presHistory: '',
  pastHistory: '',
  posFeature: '',
  negFeature: ''
})







// 验证函数
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

// formRef的数据验证规则
const rules = reactive({
  name: [
    { required: true, message: '请输入患者姓名', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在2到10个字符', trigger: 'blur' }
  ],
  age: [
    { required: true, message: '请选择患者年龄', trigger: 'blur' }
  ],
  gender: [

  ],
  phone: [
    { required: true, message: '请输入手机号码', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '手机号格式不正确', trigger: 'blur' }
  ],
  idCard: [
    { required: true, message: '请输入身份证号', trigger: 'blur' },
    { pattern: /^[1-9]\d{5}(18|19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2]\d|3[0-1])\d{3}[\dXx]$/, message: '身份证号格式不正确', trigger: 'blur' }
  ],
  leftImage: [
    { required: true, validator: validateImage('left'), trigger: 'change' }
  ],
  rightImage: [
    { required: true, validator: validateImage('right'), trigger: 'change' }
  ],
  allergy: [
  ],
  complaint: [
  ],
  visit: [

  ],
  presHistory: [

  ],
  pastHistory: [

  ],
  posFeature: [

  ],
  negFeature: [

  ]

})

const handleUpload = (file, type) => {
  // 限制文件格式和大
  const isImage = file.raw.type.includes('image/')
  const isLt5M = file.raw.size / 1024 / 1024 < 5

  if (!isImage) {
    ElMessage.error('只能上传图片格式!')
    if (type === 'left') leftImageList.value = []
    if (type === 'right') rightImageList.value = []
    return false
  }
  if (!isLt5M) {
    ElMessage.error('图片大小不能超过5MB!')
    if (type === 'left') leftImageList.value = []
    if (type === 'right') rightImageList.value = []
    return false
  }

  // 清除已有文件
  if (type === 'left') {
    leftImageList.value = []  // 先清空数组
    leftImageList.value.push(file)  // 添加新文件
    leftImage.value = file.raw
  } else {
    rightImageList.value = []
    rightImageList.value.push(file)
    rightImage.value = file.raw
  }

  return false
}

const handleSubmit = async () => {
  try {
    // 验证表单
    await formRef.value.validateField(['leftImage', 'rightImage']);
    await formRef.value.validate();

    submitting.value = true;

    // 1. 上传左眼图片
    const leftRes = await api.uploadImg(leftImage.value);
    const leftImgUrl = leftRes.data.data;

    // 2. 上传右眼图片
    const rightRes = await api.uploadImg(rightImage.value);
    const rightImgUrl = rightRes.data.data;

    // 3. 提交病例数据
    const res = await api.AddPatient({
      name: form.name,
      age: form.age,
      gender: form.gender,
      phone: form.phone,
      idCard: form.idCard,
      doctorId: "1",
      leftImg: leftImgUrl,
      rightImg: rightImgUrl,
      allergy: form.allergy,
      complaint: form.complaint,
      visit: form.visit,
      presHistory: form.presHistory,
      pastHistory: form.pastHistory,
      posFeature: form.posFeature,
      negFeature: form.negFeature
    });

    console.log('响应结果:', res.data.data);

    console.log('提交响应:', res.data)

    if (res.data.code === 1) {
      console.log('跳转参数:', {
        path: '/homeResult',
        query: { id: res.data.data }
      })
      router.push({
        path: '/homeResult',
        query: { id: res.data.data }
      }).then(() => {
        console.log('跳转成功')
      }).catch((err) => {
        console.error('跳转失败:', err)
      })
    }



    // 4. 重置表单
    formRef.value.resetFields();
    leftImage.value = null;
    rightImage.value = null;
    leftImageList.value = [];
    rightImageList.value = [];

    if (res.data.code === 1) {
      ElMessage.success('病例添加成功');
    } else {
      ElMessage.success(res.data.msg);
    }
    handleCancel();
  } catch (error) {
    if (error.data.response) {
      const msgMap = {
        400: '请求参数错误',
        401: '身份验证失败',
        500: error.data.msg,
      };
      ElMessage.error(msgMap[error.data.response.status] || '操作失败');
    } else {
      ElMessage.error(error.data.msg);
    }
  } finally {
    submitting.value = false;
  }
};



// 删除函数
const handleRemove = (type) => {
  if (type === 'left') {
    leftImage.value = null
    leftImageList.value = []  // 清空左眼图片列表
  } else {
    rightImage.value = null
    rightImageList.value = []  // 清空右眼图片列表
  }
}

const handleQueryPatient = async () => {
  if (!form.idCard) {
    ElMessage.warning('请输入身份证号')
    return
  }

  try {
    queryLoading.value = true
    const response = await api.getPatientInfo(form.idCard)

    // 填充表单（示例字段，需根据实际接口调整）
    if (response.data.code === 1) {
      const data = response.data.data
      Object.assign(form, {
        name: data.name || '',
        age: data.age || null,
        gender: data.gender || 1,
        phone: data.phone || '',
        allergy: data.allergy || '',
        complaint: data.complaint || '',
        visit: data.visit || 1,
        presHistory: data.presHistory || '',
        pastHistory: data.pastHistory || '',
        posFeature: data.posFeature || '',
        negFeature: data.negFeature || ''

      })
      ElMessage.success('患者信息加载成功')
    }
  } catch (error) {
    ElMessage.error(error.data.msg || '查询失败')
  } finally {
    queryLoading.value = false
  }
}

// 一键清除方法
const handleClearForm = async () => {
  try {
    await ElMessageBox.confirm('确定要清除所有内容吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    formRef.value.resetFields()
    leftImage.value = null
    rightImage.value = null
    leftImageList.value = []
    rightImageList.value = []

    // 手动重置非表单绑定的字段（如果有）
    form.name = ''
    form.age = null
    form.gender = 1
    form.phone = ''
    form.idCard = ''
    form.leftImg = ''
    form.rightImg = ''
    form.visit = 1
    form.allergy = ''
    form.complaint = ''
    form.presHistory = ''
    form.pastHistory = ''
    form.posFeature = ''
    form.negFeature = ''

    ElMessage.success('表单已重置')
  } catch {
    // 用户取消
  }
}

const handleCancel = () => {
  formRef.value.resetFields()
  leftImage.value = null
  rightImage.value = null
  leftImageList.value = []
  rightImageList.value = []
}

</script>

<style scoped>
.page-container {
  padding: 20px;
  background: #fefefe;
  min-height: 100vh;
  border-radius: 30px;
}

.patient-form {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.form-actions {
  margin-top: 30px;
  text-align: center;
}

.upload-tip {
  font-size: 12px;
  color: #909399;
  margin-top: 8px;
}

/* 去除默认边框 */
:deep(.custom-upload .el-upload--picture-card) {
  border: dashed 1px #d9d9d9;
  background: none;
  width: 14.5vw;
  height: 14.5vw;
}

/* 鼠标悬浮时的蓝色边框 */
:deep(.custom-upload .el-upload--picture-card:hover) {
  border-color: rgba(0, 120, 189, 0.363);
}

/* 隐藏上传按钮 */
:deep(.custom-upload.hide-border .el-upload--picture-card) {
  display: none;
}

/* 调整预览卡片样式 */
:deep(.custom-upload .el-upload-list--picture-card .el-upload-list__item) {
  border-radius: 6px;
  margin: 0;
}

/* 调整预览图片大小 */
:deep(.el-upload-list__item-thumbnail img) {
  object-fit: contain;
  object-position: center;
  width: 100%;
  height: 100%;
}

/* 优化拖拽区域的显示 */
:deep(.el-upload__drag) {
  border: dashed 2px #d9d9d9;
  padding: 20px;
  background-color: #f7f7f7;
  text-align: center;
  font-size: 14px;
  color: #666;
  cursor: pointer;
}

/* 鼠标悬停时的边框颜色 */
:deep(.el-upload__drag-hover) {
  border-color: #409eff;
  background-color: #e6f7ff;
}

/* 设置拖拽区域的样式 */
.drag-area {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.drag-icon {
  font-size: 2em;
  color: var(--el-color-primary);
  margin-bottom: 8px;
}

.drag-text {
  font-size: 12px;
  color: var(--el-text-color-secondary);
  text-align: center;
  line-height: 1.4;
}

:deep(.custom-upload .el-upload-dragger) {
  width: 100%;
  height: 100%;
  padding: 0;
  border: none;
  background: none;
}

:deep(.el-upload-dragger:hover) {
  border-color: var(--el-color-primary);
}

:deep(.custom-upload .el-upload-list--picture-card .el-upload-list__item) {
  transition: all 0.3s;
}





/* 其他样式保持不变... */

/* 固定上传卡片大小 */
:deep(.custom-upload .el-upload--picture-card),
:deep(.custom-upload .el-upload-list--picture-card .el-upload-list__item) {
  width: 300px;
  height: 200px;
  line-height: 200px;
  margin: 0;
}

/* 调整拖拽区域大小 */
:deep(.custom-upload .el-upload-dragger) {
  width: 300px;
  height: 200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

/* 图片预览样式 */
:deep(.custom-upload .el-upload-list__item-thumbnail) {
  width: 100%;
  height: 100%;
  object-fit: contain;
}

/* 移除响应式调整，保持固定大小 */
@media (max-width: 768px) {
  :deep(.custom-upload .el-upload--picture-card),
  :deep(.custom-upload .el-upload-list--picture-card .el-upload-list__item) {
    width: 300px;
    height: 200px;
  }
}


.el-input-group__append { 
  padding: 0 12px;
}

.custom-loading {
  display: flex;
  align-items: center;
  gap: 8px;
}
</style>