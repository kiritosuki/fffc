  <script setup>
  import { ref } from 'vue'
  import AddPatientDialog from '@/components/system/patient/addPatient.vue'
  
  
  const addDialogRef = ref(null)
  
  const openAddDialog = () => {
    addDialogRef.value.handleOpen()
  }



  </script>
  
  <template>
    <h1>病例添加</h1>
    <div class="patient-management">
      <el-button 
        type="primary" 
        icon="Plus"
        @click="openAddDialog"
      >
        新增病例
      </el-button>
      
      <add-patient-dialog ref="addDialogRef" />
    </div>
  </template>
  

