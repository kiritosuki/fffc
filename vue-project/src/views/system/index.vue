<!-- <script>

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
      rows: [],
      totalNum: '',
      // addData: {
      //   name: '',
      //   age: '',
      //   gender: '',
      //   phone: '',
      //   leftImg: '',
      //   rightImg: '',
      // },
    }
  },
  methods: {
    CheckPatient(name, gender, begin, end, page = 1, pageSize = 10) {
      this.$api.CheckPatient(this.name, this.gender, this.begin, this.end, this.page, this.pageSize).then(res => {
        this.rows = res.data.data.rows
        this.totalNum = res.data.total
        this.maxPage = this.totalNum / this.pageSize

      })
    },
    DelPatient(ids) {
      this.$api.DelPatient(ids).then(res => {
        console.log(res);
        this.buttontask()
      })
      ids = [];
    },
    
    //   buttontask() {
    //      this.$api.CheckPatient("","","","",1,10).then(res => {
    //       // let data =  res.data.data.rows
    //       // this.rows = data
    //       this.rows = res.data.data.rows
    //     },)
    // },



  },
  computed: {
    comMaxPage() {
      return Math.ceil(this.totalNum / this.pageSize)
    }
  },
  mounted() {
    this.CheckPatient("","","","", this.page, this.pageSize)
  }

} -->

<!-- </script> -->
 <script setup>
import { ref, onMounted, computed, onBeforeMount } from 'vue'
import api from '../../api/index.js'



const name = ref('')
const gender = ref('')
const begin = ref('')
const end = ref('')
const page = ref(1)
const pageSize = ref(10)
const deleteIds = ref([])
const rows = ref([])
const totalNum = ref('')
function CheckPatient(name, gender, begin, end, page = 1, pageSize = 10) {
  api.CheckPatient(name, gender, begin, end, page, pageSize).then(res => {
    rows.value = res.data.data.rows
    totalNum.value = res.data.total

  })
}

function DelPatient(ids) {
  api.DelPatient(ids).then(res => {
    console.log(res);
    CheckPatient("", "", "", "", page.value, pageSize.value)
  })
  ids = [];
}


const comMaxPage = computed(() => {
  return Math.ceil(totalNum.value / pageSize.value)
})


onBeforeMount(() => {
  CheckPatient("", "", "", "", page.value, pageSize.value)
})



</script>




<template>


  <h1>查询功能
  </h1>
  <input type="text" v-model="name" placeholder="name"><br>
  <input type="text" v-model="gender" placeholder="gender"><br>
  <input type="text" v-model="begin" placeholder="begin"><br>
  <input type="text" v-model="end" placeholder="end"><br>








  <button @click="CheckPatient(name, gender, begin, end, page, pageSize)">查询
  </button><br>







  <h1>总览与删除功能</h1>

  <!-- <button @click="buttontask()">查询全部</button> -->

  <div>
    <select v-model="pageSize">
      <option disabled value="">10</option>
      <option>10</option>
      <option>15</option>
      <option>20</option>
    </select>
    <button @click="page--" v-if="page > 1" class="page">上一页</button>
    <button v-for="n in comMaxPage" @click="page = n" class="page">{{ n }}</button>
    <button @click="page++" v-if="page < comMaxPage" class="page"></button>
  </div>

  <div v-for="item in rows" :key="item.id" class="patient">
    <input type="checkbox" :id="item.id" :value="item.id" v-model="deleteIds">
    <span> {{ item.name }} {{ item.gender ? "女" : "男" }} {{ item.createTime }} {{ item.updataTime }}</span>

  </div>




  <h1>查询全部并显示</h1>

  <button @click="DelPatient(deleteIds)">一键删除</button>


</template>
<style>
.patient {
  margin: 10px;
  display: block;
}

.page {
  margin: 10px;
}
</style>