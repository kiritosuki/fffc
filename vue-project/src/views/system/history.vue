<script setup>
import { ref, onMounted } from 'vue'

const historyList = ref([])

const selectedListChange = (() => {

})


onMounted(() => {

})
</script>


<template>
<el-collapse v-model="historyList" @change="selectedListChange">
  <el-collapse-item title="一致性 Consistency" name="1">

  </el-collapse-item>
  <el-collapse-item title="反馈 Feedback" name="2">

  </el-collapse-item>
  <el-collapse-item title="效率 Efficiency" name="3">

  </el-collapse-item>
  <el-collapse-item title="可控 Controllability" name="4">

  </el-collapse-item>
</el-collapse>
</template>