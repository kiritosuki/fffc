<script>
export default {
  data() {
    return {
      name: '',
      gender: '',
      begin: '',
      end: '',
      page: '',
      pagesize: '',
      Delids: [],
    }
  },
  methods: {
    CheckPatient(name, gender, begin, end, page, pagesize) {
      this.$api.CheckPatient(name, gender, begin, end, page, pagesize).then(res => {
        console.log(res);
      })
    },
    DelPatient(ids) {
      this.$api.DelPatient(ids).then(res => {
        console.log(res);
      })
    }
  },
}
</script>


<template>

  <div id="app">


    <input type="text" v-model="name" placeholder="name"><br>
    <input type="text" v-model="gender" placeholder="gender"><br>
    <input type="text" v-model="begin" placeholder="begin"><br>
    <input type="text" v-model="end" placeholder="end"><br>
    <input type="text" v-model="page" placeholder="page"><br>
    <input type="text" v-model="pagesize" placeholder="pagesize"><br>
    <input type="text" v-model="Delids" placeholder="Ids"><br>
    <button :@click="CheckPatient(name, gender, begin, end, page, pagesize)">查询
    </button><br>
    <button @click="DelPatient(ids)">删除</button>

  </div>
  <router-view></router-view>


</template>

<style scoped>
/* src/assets/main.css */
.el-overlay {
  background-color: rgba(0, 0, 0, 0.5) !important;
  backdrop-filter: blur(2px);
}

.el-overlay .el-dialog {
  border-radius: 8px;
  box-shadow: 0 12px 32px rgba(0, 0, 0, 0.2);
}
</style>
