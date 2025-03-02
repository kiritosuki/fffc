<script>
export default {
    data(){
        return {
            name: '',
            gender: '',
            begin: '',
            end: '',
            page: '',
            pageSize: '',
            Delids: [],
        }
    },
    methods: {
      CheckPatient(name, gender, begin, end, page, pageSize) {
          this.$api.CheckPatient(name, gender, begin, end, page, pageSize).then(res => {
              console.log(res);
          })
      }  ,
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
<input type="text" v-model="pageSize" placeholder="pageSize"><br>
<input type="text" v-model="Delids" placeholder="Ids"><br>
<button @click="CheckPatient(name, gender, begin, end, page = 1, pageSize = 10)">查询
</button><br>
<button @click="DelPatient(ids)">删除</button>

</div>
<router-view></router-view>

</template>

<style scoped>




</style>
