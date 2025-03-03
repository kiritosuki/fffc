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
      deleteIds: [],
      rows: []
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
      ids = [];
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
    <input type="text" v-model="pagesize" placeholder="pagesize"><br>
    
    <button @click="CheckPatient(name, gender, begin, end, page, pagesize)">查询
    </button><br>







    <h1>总览与删除功能</h1>

<button @click="buttontest()">查询全部</button>

<h1>查询全部并显示</h1>

<div v-for="item in rows":key="item.id">
<input type="checkbox" :id="item.id" :value="item.id" v-model="deleteIds">
<span>    {{ item.name }}         {{ item.gender ? "女" : "男" }}        {{ item.createTime }}      {{ item.updataTime }}</span> 

</div>



<button @click="DelPatient(deleteIds)">一键删除</button>


</template>