<!-- src/views/NewRecord.vue -->
<template>
  <div class="page-container">
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
      <div class="divider">
          <div class="divider-text">基础信息</div>
        </div>
      <el-row :gutter="24" >
        <el-col :span="12">
        <el-form-item label="患者姓名">
          <el-input v-model="medicalRecord.name" disabled/>
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="年龄">
          <el-input v-model="medicalRecord.age" disabled/>
        </el-form-item>
        </el-col>
      </el-row>
<el-col :span="8">
        <el-form-item label="性别" prop="gender">
      <el-select 
        v-model="medicalRecord.gender"
        placeholder="请选择性别"
        clearable
        filterable
        disabled
      >
        <el-option
          v-for="item in genderOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
    </el-form-item>
        </el-col>
      <el-row :gutter="24" :justify="justify" :align="align">
        <el-col :span="12">
        <el-form-item label="手机号">
          <el-input v-model="medicalRecord.phone" disabled/>
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="身份证号">
          <el-input v-model="medicalRecord.idCard" disabled />
        </el-form-item>
        </el-col>
      </el-row>

       
      <div class="divider">
          <div class="divider-text">诊断图片</div>
        </div>
      <el-row :gutter="24">
  <el-col :span="12">
    <!-- 左眼照片 -->
    <el-form-item label="左眼照片">
      <el-image
        v-if="medicalRecord.leftImg"
        :src="medicalRecord.leftImg"
        :preview-src-list="[medicalRecord.leftImg]"
        fit="contain"
        class="eye-image"
      />
      <div v-else class="no-image">暂无照片</div>
    </el-form-item>
  </el-col>
  <el-col :span="12">
    <!-- 右眼照片 -->
    <el-form-item label="右眼照片">
      <el-image
        v-if="medicalRecord.rightImg"
        :src="medicalRecord.rightImg"
        :preview-src-list="[medicalRecord.rightImg]"
        fit="contain"
        class="eye-image"
      />
      <div v-else class="no-image">暂无照片</div>
    </el-form-item>
  </el-col>
  
</el-row>
<div class="divider">
          <div class="divider-text">诊断结果</div>
        </div>
      <el-row :gutter="24" :justify="justify" :align="align">
        <el-col :span="12">
        <el-form-item label="左眼关键字">
          <el-input v-model="medicalRecord.leftDiag" disabled/>
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="右眼关键字">
          <el-input v-model="medicalRecord.rightDiag"  disabled/>
        </el-form-item>
        </el-col>
      </el-row>
     
<el-row :gutter="24" :justify="justify" :align="align">
  <el-col :span="12">
        <el-form-item label="左眼疾病" prop="leftStatusIllList">
  <el-select 
    v-model="medicalRecord.leftStatusIllList"
    multiple
    placeholder="请选择左眼疾病"
    clearable
    filterable
   
    @change="handleDiseaseSelection('left', $event)"
    disabled
  >
    <el-option
      v-for="disease in diseaseOptions"
      :key="disease.value"
      :label="disease.label"
      :value="disease.value"
    />
  </el-select>
</el-form-item>

<el-form-item label="左眼其他异常" v-if="showLeftIllInfo">
        <el-input v-model="medicalRecord.leftIllInfo" :rules="leftIllRule" disabled/>
      </el-form-item>
</el-col>
<el-col :span="12">
<el-form-item label="右眼疾病" prop="rightStatusIllList">
  <el-select 
    v-model="medicalRecord.rightStatusIllList"
    multiple
    placeholder="请选择右眼疾病"
    clearable
    filterable
    
    @change="handleDiseaseSelection('right', $event)"
    disabled
  >
    <el-option
      v-for="disease in diseaseOptions"
      :key="disease.value"
      :label="disease.label"
      :value="disease.value"
    />
  </el-select>
</el-form-item>

<el-form-item label="右眼其他异常" v-if="showRightIllInfo">
        <el-input v-model="medicalRecord.rightIllInfo" :rules="rightIllRule" disabled/>
      </el-form-item>
  
</el-col>
</el-row>
    <el-row :gutter="24" :justify="justify" :align="align">
        <el-col :span="24">

    <el-form-item label="诊断结果">
          <el-input 
            v-model="medicalRecord.diagnosis" 
            type="textarea" 
            :rows="1"
            disabled
          />
        </el-form-item>
      </el-col>
    </el-row>
    <div class="divider">
          <div class="divider-text">其他信息</div>
      </div>
      <el-row :gutter="24">
             <el-col :span="12">
        <el-form-item label="诊断医师">
          <el-input v-model="medicalRecord.DoctorID" disabled/>
        </el-form-item>
        </el-col>
                <el-col :span="12">
        <el-form-item label="诊断日期">
          <el-date-picker
            v-model="medicalRecord.diagDate"
            type="date"
            value-format="YYYY-MM-DD"
            disabled
          />
        </el-form-item>
        </el-col>
      </el-row>
 
    <el-row :gutter="24" :justify="justify" :align="align">

        <el-col :span="12">
        <el-form-item label="创建时间">
          <el-input v-model="medicalRecord.createTime" disabled />
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="最后更新时间">
          <el-input v-model="medicalRecord.updateTime" disabled />
        </el-form-item>
        </el-col>
    </el-row>
        
<el-row :gutter="24" :justify="justify" :align="align">
  <el-col :span="12">
        <el-form-item label="过敏药物">
          <el-input v-model="medicalRecord.allergy" disabled/>
        </el-form-item>
  </el-col>
  <el-col :span="12">
        <el-form-item label="患者主诉">
          <el-input v-model="medicalRecord.complaint" disabled/>
        </el-form-item>
  </el-col>
</el-row>

<el-row :gutter="24" :justify="justify" :align="align">
  <el-col :span="12">


        <el-form-item label="现病史">
          <el-input v-model="medicalRecord.presHistory" disabled/>
        </el-form-item>
      </el-col>

      <el-col :span="12">
        
        <el-form-item label="既往史">
          <el-input v-model="medicalRecord.pastHistory"  disabled/>
        </el-form-item>
      </el-col>
</el-row>

<el-row :gutter="24" :justify="justify" :align="align">
  <el-col :span="12">
        <el-form-item label="阳体特征">
          <el-input v-model="medicalRecord.posFeature" disabled />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="阴体特征">
          <el-input v-model="medicalRecord.negFeature"  disabled/>
        </el-form-item>
      </el-col>
</el-row>




     

      
    
  
        <div class="form-actions">
          <el-button @click="handleCancel">返回</el-button>
          <!-- <el-button 
            type="primary" 
            :loading="submitting" 
            @click="handleSubmit"
          >
            保存修改
          </el-button> -->
          <el-button 
            @click="handleHistory"
          >
            历史记录
          </el-button>
        </div>
      </el-form>
    </div>
  </div>
  </template>
  
  <script setup>
  import { ref, onMounted,watch,computed } from 'vue'
  import { useRoute, useRouter } from 'vue-router'
  import { ElMessage } from 'element-plus'
  import api from '@/api'
  import { reactive } from 'vue'
  import { debounce } from 'lodash-es'
  
  const route = useRoute()
  const router = useRouter()
  
  // 数据定义
  const loading = ref(true)
  const submitting = ref(false)

  const loadData = async () => {
    try {
      // const res = await api.getMedicalRecord(route.params.id)
      // medicalRecord.value = res.data.data
    } catch (error) {
      ElMessage.error('数据加载失败')
    } finally {
      // 在数据加载完成后设置 loading 为 false
      loading.value = false
    }
  }

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

// 创建疾病ID到名称的映射
const diseaseMap = computed(() => {
    return diseaseOptions.value.reduce((map, disease) => {
      map[disease.value] = disease.label
      return map
    }, {})
  })

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
    presHistory: '',
    pastHistory: '',
    posFeature: '',
    negFeature: '',
    leftStatusIllList: [],
    rightStatusIllList: [],
    leftIllInfo: '',
    rightIllInfo: ''
  })

  // 计算属性，映射疾病ID为名称
  const selectedLeftDiseases = computed(() => {
    return medicalRecord.value.leftStatusIllList.map(id => diseaseMap.value[id] || '')
  })

  const selectedRightDiseases = computed(() => {
    return medicalRecord.value.rightStatusIllList.map(id => diseaseMap.value[id] || '')
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
  presHistory: [
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
  leftIllInfo: {
      get() { return leftIllRule.value },
      set() {} // 防止直接修改
    },
    rightIllInfo: {
      get() { return rightIllRule.value },
      set() {}
    }
})
  
  // 获取病历数据
  // 修改后的fetchData函数
  const fetchData = async (force = false) => {
  try {
    loading.value = true
    const res = await api.getMedicalRecord(route.query.id)
    
    
    // 调试输出
    console.log('API响应状态:', res.status)
    console.log('原始图片路径:', {
      left: res.data.data.leftImg,
      right: res.data.data.rightImg
    })

    // URL处理函数
    const formatImageUrl = (path) => {
      if (!path) return ''
      return path.startsWith('http') 
        ? path 
        : `${api.defaults.baseURL}/${path.replace(/^\//, '')}`
    }

    const processedData = {
      ...res.data.data,
      leftImg: formatImageUrl(res.data.data.leftImg),
      rightImg: formatImageUrl(res.data.data.rightImg),
     
    }

console.log('处理后的图片URL:', {
  left: processedData.leftImg,
  right: processedData.rightImg
})
console.log('Left image URL:', medicalRecord.value.leftImg);
console.log('Right image URL:', medicalRecord.value.rightImg);

medicalRecord.value = processedData
} catch (error) {
ElMessage.error('数据加载失败')
console.error('加载错误详情:', error.response?.data || error.msg)
} finally {
loading.value = false
}

}
// 修改onMounted
onMounted(() => {
  if (!route.query.id) {
    ElMessage.error('无效的患者ID')
    return router.back()  // 添加return防止继续执行
  }
  // loadData()
  fetchData()
  
})

// 用于 watch 数据更新时，关闭加载
watch(() => medicalRecord.value, (newVal) => {
    if (newVal && newVal.name) {
      loading.value = false
    }
  })
  
  // 提交修改
  const handleSubmit = async () => {
  try {
    // 使用深拷贝避免响应式污染
    const submitData = JSON.parse(JSON.stringify(medicalRecord.value))
    
    // 转换疾病数组为字符串
    submitData.leftStatusIllList = submitData.leftStatusIllList.join(',')
    submitData.rightStatusIllList = submitData.rightStatusIllList.join(',')
    
    await api.updateMedicalRecord(route.query.id, submitData)
    ElMessage.success('修改成功')
    
    // 强制刷新数据
    await fetchData(true) // 传入强制刷新参数
  } catch (error) {
    // 错误处理...
  }
}
  
  // 返回列表页
  const handleCancel = () => {
    router.push('/admin')
  }
 
  const handleDiseaseSelection = (type, newVal) => {
    const field = `${type}StatusIllList`
    const otherField = `${type}IllInfo`
    const cloneVal = [...newVal]

    if (cloneVal.includes(1)) {
      medicalRecord.value[field] = Object.freeze([1]) // 冻结数组防止修改
      medicalRecord.value[otherField] = ''
      return
    }

    requestAnimationFrame(() => {
      medicalRecord.value[field] = cloneVal.filter(v => v !== 1)
      if (!cloneVal.includes(8)) {
        medicalRecord.value[otherField] = ''
      }
    })
  }

  // 左右眼疾病选择监控
  watch(
    () => [...medicalRecord.value.leftStatusIllList],
    debounce((newVal, oldVal) => {
      if (JSON.stringify(newVal) === JSON.stringify(oldVal)) return
      handleDiseaseSelection('left', newVal)
    }, 300)
  )

  watch(
    () => [...medicalRecord.value.rightStatusIllList],
    debounce((newVal, oldVal) => {
      if (JSON.stringify(newVal) === JSON.stringify(oldVal)) return
      handleDiseaseSelection('right', newVal)
    }, 300)
  )

  // 控制显示其他异常输入框
  const showLeftIllInfo = computed(() =>
    medicalRecord.value.leftStatusIllList && medicalRecord.value.leftStatusIllList.includes(8)
  )

  const showRightIllInfo = computed(() => 
    medicalRecord.value.rightStatusIllList && medicalRecord.value.rightStatusIllList.includes(8)
  )

const handleHistory = () => {
  router.push({
    path: '/patients/history',
    query: {
      id: route.query.id
    }
  })
}
  </script>
  
  <style scoped>
  .page-container {
  width: 85%;
  height: auto;
  display: inline-block;
  text-align: center;
  padding: 20px;
  background: #fefefe;
  border-radius: 30px;
  box-shadow: 9px 9px 18px #c3c3c3,
    -9px -9px 18px #e6e6e6;
}
.divider {
  text-align: left;
  display: inline-block;
  width: 95%;
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
  .record-container {
    max-width: 800px;
    margin: 20px auto;
    padding: 20px;
    background: #fefefe;
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

/* 添加加载状态提示 */
.validating-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(255,255,255,0.8);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

/* 添加图片容器样式 */
.eye-img {
  width: 300px;
  height: 200px;
  display: block;
  margin: 10px 0;
  border: 1px solid #eee;
  border-radius: 4px;
}

/* 图片容器基础样式 */
.eye-image {
  width: 100%;
  height: 200px;
  display: block;
  margin: 0 auto;
  border: 1px solid #eee;
  border-radius: 4px;
  background-color: #f5f5f5;
  
  /* 图片填充方式 */
  :deep(img) {
    width: 100%;
    height: 100%;
    object-fit: contain;
    object-position: center;
  }
}

/* 无图占位样式 */
.no-image {
  width: 100%;
  height: 200px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px dashed #ddd;
  color: #999;
  background-color: #fafafa;
}

/* 确保列布局均匀 */
/* .el-col {
  display: flex;
  flex-direction: column;
  
  .el-form-item {
    flex: 1;
    display: flex;
    flex-direction: column;
    
    :deep(.el-form-item__content) {
      flex: 1;
    }
  }
} */

/* 响应式调整 */
@media (max-width: 768px) {
  .el-col {
    width: 100%;
    
    .eye-image,
    .no-image {
      height: 180px;
    }
  }
}
  </style>