<script setup>
import { ref } from 'vue'
import AddPatientDialog from '@/components/system/patient/addPatient.vue'
import { computed, onBeforeMount } from 'vue'
import api from '../../api/index.js'


const addDialogRef = ref(null)

const openAddDialog = () => {
  addDialogRef.value.handleOpen()
}





const name = ref('')
const gender = ref('')
const begin = ref('')
const end = ref('')
const page = ref(1)
const pageSize = ref(10)
const deleteIds = ref([])
const rows = ref([])
const totalNum = ref('')
function CheckPatient(name, gender, begin, end, page = 1, pageSize = 10) {
  api.CheckPatient(name, gender, begin, end, page, pageSize).then(res => {
    rows.value = res.data.data.rows
    totalNum.value = res.data.total

  })
}

function DelPatient(ids) {
  api.DelPatient(ids).then(res => {
    console.log(res);
    CheckPatient("", "", "", "", page.value, pageSize.value)
  })
  ids = [];
}


const comMaxPage = computed(() => {
  return Math.ceil(totalNum.value / pageSize.value)
})


onBeforeMount(() => {
  CheckPatient("", "", "", "", page.value, pageSize.value)
})

</script>

<template>

  <div class="patient-management">
    <el-button type="primary" icon="Plus" @click="openAddDialog" class="add-patient-btn">
      新增病例
    </el-button>

    <AddPatientDialog ref="addDialogRef" />
  </div>


  <h1>查询功能
  </h1>
  <input type="text" v-model="name" placeholder="name"><br>
  <input type="text" v-model="gender" placeholder="gender"><br>
  <input type="text" v-model="begin" placeholder="begin"><br>
  <input type="text" v-model="end" placeholder="end"><br>


  <button @click="DelPatient(deleteIds)">一键删除</button>






  <button @click="CheckPatient(name, gender, begin, end, page, pageSize)">查询
  </button><br>









  <!-- <button @click="buttontask()">查询全部</button> -->

  <div>
    <select v-model="pageSize">
      <option disabled value="">10</option>
      <option>10</option>
      <option>15</option>
      <option>20</option>
    </select>
    <button @click="page--" v-if="page > 1" class="page">上一页</button>
    <button v-for="n in comMaxPage" @click="page = n" class="page">{{ n }}</button>
    <button @click="page++" v-if="page < comMaxPage" class="page"></button>
  </div>

  <div v-for="item in rows" :key="item.id" class="patient">
    <input type="checkbox" :id="item.id" :value="item.id" v-model="deleteIds">
    <span> {{ item.name }} {{ item.gender ? "女" : "男" }} {{ item.createTime }} {{ item.updataTime }}</span>

  </div>








</template>
<style scoped>
.patient-management {
padding-left: -10px;
}

.add-patient-btn {
padding-left: 0px;
padding-right: 20px;
}
</style>

<style>
.patient {
margin: 10px;
display: block;
}

.page {
margin: 10px;
}
</style>
