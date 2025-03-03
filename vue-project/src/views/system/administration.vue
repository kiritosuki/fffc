<script>

export default {
  data() {
    return {
        deleteIds: [],
        rows: {}
    }
  },
  methods: {
    
    CheckPatient(name, gender, begin, end, page, pagesize) {
      this.$api.CheckPatient(this.name, this.gender, this.begin, this.end, this.page, this.pagesize).then(res => {
        console.log(res);
      })
    },
    DelPatient(ids) {
      this.$api.DelPatient(ids).then(res => {
        console.log(res);
      })
    },
    buttontest(){
      rows = CheckPatient("","","","",1,10).data.rows
    }
  },
//    beforeMount(){
//     let data = CheckPatient("","","","",1,10).data.rows
//   }
}

</script>




<template>

<h1>总览与删除功能</h1>

<button @click="buttontest()">查询全部</button>

<h1>查询全部并显示</h1>

<div v-for="item in rows":key="item.id">
<input type="checkbox" :id="item.id" :value="item.id" v-model="deleteIds">
<span>    {{ item.name }}         {{ item.gender ? "女" : "男" }}        {{ item.createTime }}      {{ item.updataTime }}</span> 

</div>



<button @click="DelPatient(deleteIds)">一键删除</button>

</template>