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
        class="add-patient-btn"
      >
        新增病例
      </el-button>
      
      <AddPatientDialog ref="addDialogRef" />
    </div>
  </template>
  <style scoped>
  .patient-management {
    padding-left: -10px;
    }
    .add-patient-btn {
      padding-left: 0px;
      padding-right:20px;
    }
  </style>
  

