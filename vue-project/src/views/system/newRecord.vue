<!-- src/views/NewRecord.vue -->
<template>
    <div class="record-container">
      <!-- 加载状态 -->
      <el-skeleton v-if="loading" :rows="6" animated />
  
      <!-- 病历表单 -->
      <el-form 
        v-else 
        ref="formRef" 
        :model="medicalRecord" 
        label-width="120px"
        :rules="rules"
      >
        <el-form-item label="患者姓名">
          <el-input v-model="medicalRecord.name" disabled />
        </el-form-item>

        <el-form-item label="年龄">
          <el-input v-model="medicalRecord.age" disabled />
        </el-form-item>

        <el-form-item label="性别" prop="gender">
      <el-select 
        v-model="medicalRecord.gender"
        placeholder="请选择性别"
        clearable
        filterable
      >
        <el-option
          v-for="item in genderOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
    </el-form-item>

        <el-form-item label="手机号">
          <el-input v-model="medicalRecord.phone" disabled />
        </el-form-item>

        <el-form-item label="身份证号">
          <el-input v-model="medicalRecord.idCard" disabled />
        </el-form-item>

        <el-form-item label="诊断医师">
          <el-input v-model="medicalRecord.DoctorID" disabled />
        </el-form-item>

        <el-form-item label="左眼照片">
      <el-Img
        v-if="form.leftImg"
        :src="form.leftImg"
        :preview-src-list="[form.leftImg]"
        fit="contain"
        class="eye-img"
        :zoom-rate="1.2"
        :max-scale="7"
        :min-scale="0.2"
      >
        <!-- 加载状态 -->
        <template #placeholder>
          <div class="Img-loading">
            <el-icon class="loading-icon"><Loading /></el-icon>
          </div>
        </template>

        <!-- 错误状态 -->
        <template #error>
          <div class="Img-error">
            <el-icon><Picture /></el-icon>
            <div>图片加载失败</div>
          </div>
        </template>
      </el-Img>
      <div v-else class="no-Img">暂无照片</div>
    </el-form-item>

    <!-- 右眼照片 -->
    <el-form-item label="右眼照片">
      <el-Img
        v-if="form.rightImg"
        :src="form.rightImg"
        :preview-src-list="[form.rightImg]"
        fit="contain"
        class="eye-Img"
      >
        <!-- 复用相同的加载和错误模板 -->
      </el-Img>
      <div v-else class="no-Img">暂无照片</div>
    </el-form-item>

    <el-form-item label="左眼诊断信息关键字">
        <el-input v-model="medicalRecord.leftDiag" disabled />
    </el-form-item>

    <el-form-item label="右眼诊断信息关键字">
        <el-input v-model="medicalRecord.rightDiag" disabled />
    </el-form-item>

    <el-form-item label="诊断结果">
          <el-input 
            v-model="medicalRecord.diagnosis" 
            type="textarea" 
            :rows="4"
          />
        </el-form-item>
  
        <el-form-item label="诊断日期">
          <el-date-picker 
            v-model="medicalRecord.diagDate" 
            type="date"
            value-format="YYYY-MM-DD"
          />
        </el-form-item>
  
        <el-form-item label="创建时间">
          <el-input v-model="medicalRecord.createTime" disabled />
        </el-form-item>

        <el-form-item label="最后更新时间">
          <el-input v-model="medicalRecord.updateTime" disabled />
        </el-form-item>

        <el-form-item label="过敏药物">
          <el-input v-model="medicalRecord.allergy" disabled />
        </el-form-item>

        <el-form-item label="患者主诉">
          <el-input v-model="medicalRecord.complaint" disabled />
        </el-form-item>

        <el-form-item label="现病史">
          <el-input v-model="medicalRecord.preshistory" disabled />
        </el-form-item>

        <el-form-item label="既往史">
          <el-input v-model="medicalRecord.pastHistory" disabled />
        </el-form-item>

        <el-form-item label="阳体特征">
          <el-input v-model="medicalRecord.posFeature" disabled />
        </el-form-item>

        <el-form-item label="阴体特征">
          <el-input v-model="medicalRecord.negFeature" disabled />
        </el-form-item>

        <el-form-item label="左眼疾病" prop="leftStatusIllList">
      <el-select 
        v-model="medicalRecord.leftStatusIllList"
        multiple
        placeholder="请选择左眼疾病"
        clearable
        filterable
        collapse-tags
      >
        <el-option
        v-for="disease in diseaseOptions"
          :key="disease.id"
          :label="disease.name"
          :value="disease.id"
        />
      </el-select>
        </el-form-item>

      <el-form-item label="右眼疾病" prop="rightStatusIllList">
      <el-select 
        v-model="medicalRecord.rightStatusIllList"
        multiple
        placeholder="请选择右眼疾病"
        clearable
        filterable
        collapse-tags
      >
        <el-option
        v-for="disease in diseaseOptions"
          :key="disease.id"
          :label="disease.name"
          :value="disease.id"
        />
      </el-select>
    </el-form-item>

    <el-form-item label="左眼其他异常" v-if="showLeftIllInfo">
          <el-input v-model="medicalRecord.leftIllInfo" disabled />
        </el-form-item>

        <el-form-item label="右眼其他异常" v-if="showRightIllInfo">
          <el-input v-model="medicalRecord.rightIllInfo" disabled />
        </el-form-item>
  
    
  
        <div class="form-actions">
          <el-button @click="handleCancel">返回</el-button>
          <el-button 
            type="primary" 
            :loading="submitting" 
            @click="handleSubmit"
          >
            保存修改
          </el-button>
        </div>
      </el-form>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted,watch,computed } from 'vue'
  import { useRoute, useRouter } from 'vue-router'
  import { ElMessage } from 'element-plus'
  import api from '@/api'
  
  const route = useRoute()
  const router = useRouter()
  
  // 数据定义
  const loading = ref(true)
  const submitting = ref(false)

  const genderOptions = ref([
  { value: 1, label: '男性' },
  { value: 0, label: '女性' }
])

const diseaseOptions = ref([
  { value: 1, label: '正常' },
  { value: 2, label: '糖尿病' },
  { value: 3, label: '青光眼' },
  { value: 4, label: '白内障' },
  { value: 5, label: 'AMD' },
  { value: 6, label: '高血压' },
  { value: 7, label: '近视' },
  { value: 8, label: '其他异常' },
])

  const medicalRecord = ref({
    name: '',
    age: '',
    gender: '',
    phone: '',
    idCard: '',
    DoctorID: '',
    leftImg: '',
    rightImg: '',
    leftDiag: '',
    rightDiag: '',
    diagnosis: '',
    diagDate: '',
    createTime: '',
    updateTime: '',
    allergy: '',
    complaint: '',
    preshistory: '',
    pastHistory: '',
    posFeature: '',
    negFeature: '',
    leftStatusIllList: [],
    rightStatusIllList: [],
    leftIllInfo: '',
    rightIllInfo: ''
  })

  const rules = reactive({
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' } 
  ],
  age: [
    { required: true, message: '请输入年龄', trigger: 'blur' }
  ],
  gender: [
    { required: true, message: '请选择性别', trigger: 'change' }
  ],
  phone: [
    { required: true, message: '请输入手机号', trigger: 'blur' }
  ],
  idCard: [
    { required: true, message: '请输入身份证号', trigger: 'blur' }
  ],
  DoctorID: [
    { required: true, message: '请输入诊断医师', trigger: 'blur' }
  ],
  leftImg: [
    { disabled: true, message: '请上传左眼照片', trigger: 'blur' }
  ],
  rightImg: [
    { disabled: true, message: '请上传右眼照片', trigger: 'blur' }
  ],
  leftDiag: [
    { required: true, message: '请输入左眼诊断信息关键字', trigger: 'blur' }
  ],
  rightDiag: [
    { required: true, message: '请输入右眼诊断信息关键字', trigger: 'blur' }
  ],
  diagDate: [
    { disabled: true, message: '请选择诊断日期', trigger: 'change' }
  ],
  createTime: [
    { disabled: true, message: '请输入创建时间', trigger: 'blur' } 
  ],
  updateTime: [
    { disabled: true, message: '请输入最后更新时间', trigger: 'blur' } 
  ],
  allergy: [
    { required: true, message: '请输入过敏药物', trigger: 'blur' }  
  ],
  complaint: [
    // { required: true, message: '请输入患者主诉', trigger: 'blur' }
  ],
  preshistory: [
    // { required: true, message: '请输入现病史', trigger: 'blur' } 
  ],
  pastHistory: [
    // { required: true, message: '请输入既往史', trigger: 'blur' } 
  ],
  posFeature: [
    // { required: true, message: '请输入阳体特征', trigger: 'blur' }
  ],
  negFeature: [
    // { required: true, message: '请输入阴体特征', trigger: 'blur' }
  ],
  leftStatusIllList: [
    { type:'array',required: true, message: '请选择左眼疾病', trigger: 'change' }
  ],
  rightStatusIllList: [
    { type:'array',required: true, message: '请选择右眼疾病', trigger: 'change' }
  ],
  leftIllInfo: [
    // 默认为非必填项，动态更新
    { required: false, message: '请输入左眼其他异常', trigger: 'blur' }
  ],
  rightIllInfo: [
    // 默认为非必填项，动态更新
    { required: false, message: '请输入右眼其他异常', trigger: 'blur' }
  ],
})
  
  // 获取病历数据
  const fetchData = async () => {
    try {
      const res = await api.getMedicalRecord(route.query.id)
      medicalRecord.value = res.data
    } catch (error) {
      ElMessage.error('获取病历失败')
    } finally {
      loading.value = false
    }
  }
  
  // 提交修改
  const handleSubmit = async () => {
    submitting.value = true
    try {
      await api.updateMedicalRecord(route.query.id, medicalRecord.value)
      ElMessage.success('修改成功')
    } catch (error) {
      ElMessage.error('修改失败')
    } finally {
      submitting.value = false
    }
  }
  
  // 返回列表页
  const handleCancel = () => {
    router.push('/administration')
  }
  
  onMounted(() => {
    if (!route.query.id) {
      ElMessage.error('无效的患者ID')
      router.back()
    } else {
      fetchData()
    }
  })

  // 左右眼疾病选择监控
const handleDiseaseSelection = (type, newVal) => {
  const field = type === 'left' ? 'leftStatusIllList' : 'rightStatusIllList'
  const otherField = type === 'left' ? 'leftIllInfo' : 'rightIllInfo'
  
  // 选中"正常"时清除其他选项
  if (newVal.includes(1)) {
    medicalRecord.value[field] = [1]
    medicalRecord.value[otherField] = '' // 清空其他异常信息
    return
  }

  // 选中其他选项时清除"正常"
  if (newVal.some(v => v >= 2 && v <= 8)) {
    medicalRecord.value[field] = newVal.filter(v => v !== 1)
  }

  // 取消选择"其他异常"时清空输入
  if (!newVal.includes(8)) {
    medicalRecord.value[otherField] = ''
  }
}

// 监听左眼选择
watch(
  () => medicalRecord.value.leftStatusIllList,
  (newVal) =>{ handleDiseaseSelection('left', newVal)
  // 动态更新leftIllInfo的必填规则
  if (newVal.includes(8)) {
      // 如果选择了“其他异常”，则leftIllInfo必填
      rules.leftIllInfo = [{ required: true, message: '请输入左眼其他异常', trigger: 'blur' }]
    } else {
      // 否则，leftIllInfo非必填
      rules.leftIllInfo = [{ required: false, message: '请输入左眼其他异常', trigger: 'blur' }]
    }
  },
  { deep: true }
)

// 监听右眼选择  
watch(
  () => medicalRecord.value.rightStatusIllList,
  (newVal) => {handleDiseaseSelection('right', newVal)
  // 动态更新rightIllInfo的必填规则
  if (newVal.includes(8)) {
      // 如果选择了“其他异常”，则rightIllInfo必填
      rules.rightIllInfo = [{ required: true, message: '请输入右眼其他异常', trigger: 'blur' }]
    } else {
      // 否则，rightIllInfo非必填
      rules.rightIllInfo = [{ required: false, message: '请输入右眼其他异常', trigger: 'blur' }]
    }
  },
  { deep: true }
)

// 控制显示其他异常输入框
const showLeftIllInfo = computed(() => 
  medicalRecord.value.leftStatusIllList?.includes(8)
)
const showRightIllInfo = computed(() => 
  medicalRecord.value.rightStatusIllList?.includes(8)
)
  </script>
  
  <style scoped>
  .record-container {
    max-width: 800px;
    margin: 20px auto;
    padding: 20px;
    background: #fff;
  }
  
  .form-actions {
    margin-top: 30px;
    text-align: center;
  }

  /* 添加过渡动画 */
.el-form-item {
  transition: all 0.3s ease;
}

/* 突出显示必填项 */
.required-label::before {
  content: "*";
  color: #F56C6C;
  margin-right: 4px;
}
  </style>