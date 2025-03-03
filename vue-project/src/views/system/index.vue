<script>
export default {
  data() {
    return {
      name: '',
      gender: '',
      begin: '',
      end: '',
      page: '',
      pagesize: ''
    }
  },
  methods: {
    CheckPatient(name, gender, begin, end, page, pagesize) {
      this.$api.CheckPatient(this.name, this.gender, this.begin, this.end, this.page, this.pagesize).then(res => {
        console.log(res);
      })
    },

  },
}
</script>




<template>
    

    <h1>查询功能
</h1>
    <input type="text" v-model="name" placeholder="name"><br>
    <input type="text" v-model="gender" placeholder="gender"><br>
    <input type="text" v-model="begin" placeholder="begin"><br>
    <input type="text" v-model="end" placeholder="end"><br>
    <input type="text" v-model="page" placeholder="page"><br>
    <input type="text" v-model="pagesize" placeholder="pagesize"><br>
    
    <button @click="CheckPatient(name, gender, begin, end, page, pagesize)">查询
    </button><br>

</template>