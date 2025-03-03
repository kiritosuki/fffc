  <script setup>
  import { ref } from 'vue'
  import AddPatientDialog from '@/components/system/patient/addPatient.vue'
  
  const addDialogRef = ref(null)
  
  const openAddDialog = () => {
    addDialogRef.value.handleOpen()
  }



  </script>
  
  <template>
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
  

