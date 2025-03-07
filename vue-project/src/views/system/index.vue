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



</script>



<template>
  
</template>
