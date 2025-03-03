<script>
export default {
  data() {
    return {
      name: '',
      gender: '',
      begin: '',
      end: '',
      page: 1,
      pageSize: 10,
      deleteIds: [],
      rows: []
    }
  },
  methods: {
    CheckPatient(name, gender, begin, end, page = 1, pageSize = 10) {
      this.$api.CheckPatient(this.name, this.gender, this.begin, this.end, this.page, this.pageSize).then(res => {
        console.log(res);
      })
    },
    DelPatient(ids) {
      this.$api.DelPatient(ids).then(res => {
        console.log(res);
      })
      ids = [];
    },
    buttontask() {
      this.CheckPatient("","","","",1,10)
    }


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
  <input type="text" v-model="pageSize" placeholder="pageSize"><br>

  <button @click="CheckPatient(name, gender, begin, end, page, pageSize)">查询
  </button><br>







  <h1>总览与删除功能</h1>

  <button @click="buttontask()">查询全部</button>

  <h1>查询全部并显示</h1>

  <div v-for="item in rows" :key="item.id" class="patient">
    <input type="checkbox" :id="item.id" :value="item.id" v-model="deleteIds">
    <span> {{ item.name }} {{ item.gender ? "女" : "男" }} {{ item.createTime }} {{ item.updataTime }}</span>

  </div>



  <button @click="DelPatient(deleteIds)">一键删除</button>


</template>
<style>
.patient {
  margin: 10px;
  display: block;
}
</style>