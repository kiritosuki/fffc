<script setup>
import { ref } from 'vue';

// 控制 Drawer 显示/隐藏的状态
const isDrawerOpen = ref(false);

// 打开抽屉默认为精确查询
const activeName = ref("first")

const checkName = ref('');
const checkGender = ref('');
const checkIdCard = ref('');
const checkPhoneNumber = ref('');



const size = ref('30%'); // Drawer 的宽度或高度

// 日期快捷选项功能
// const pickerOptions = {
//   shortcuts: [{
//     text: '最近一周',
//     onClick(picker) {
//       const end = new Date();
//       const start = new Date();
//       start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
//       picker.$emit('pick', [start, end]);
//     }
//   }, {
//     text: '最近一个月',
//     onClick(picker) {
//       const end = new Date();
//       const start = new Date();
//       start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
//       picker.$emit('pick', [start, end]);
//     }
//   }, {
//     text: '最近三个月',
//     onClick(picker) {
//       const end = new Date();
//       const start = new Date();
//       start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
//       picker.$emit('pick', [start, end]);
//     }
//   }]
// }
const checkTime = ref('')

//   格式化日期
function formatDate(date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从 0 开始，需要加 1
  const day = String(date.getDate()).padStart(2, '0');
  return `${year}-${month}-${day}`;
}



const Check = () => {
  console.log(checkName.value);
  console.log(checkGender.value);
  console.log(checkIdCard.value);
  console.log(checkPhoneNumber.value);
  console.log(checkTime.value);
  console.log(formatDate(checkTime.value[0]));
  console.log(formatDate(checkTime.value[1]));
}

const handleClick = (tab, event) => {
  console.log(tab, event);
}
// 切换 Drawer 显示/隐藏的函数
const toggleDrawer = () => {
  isDrawerOpen.value = true;
};
</script>



<template>
  <div>
    <!-- 控制 Drawer 的按钮 -->
    <el-button type="primary" @click="toggleDrawer">
      搜索病例
    </el-button>


    <!-- Drawer 组件 -->
    <el-drawer v-model="isDrawerOpen" title="搜索栏" :direction="rtl" :size="size">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="精确查询" name="first">
          <p>身份证号:</p>
          <el-input placeholder="身份证号" v-model="checkIdCard" clearable></el-input>
          <p>电话号码:</p>
          <el-input placeholder="电话号码" v-model="checkPhoneNumber" clearable></el-input>
          <el-button type="primary" class="search-btn">搜索</el-button>
        </el-tab-pane>



        <el-tab-pane label="模糊查询" name="second">
          <p>姓名:</p>
          <el-input placeholder="姓名" v-model="checkName" clearable></el-input>
          <p>性别:</p>
          <el-radio v-model="checkGender" label='1'>男</el-radio>
          <el-radio v-model="checkGender" label='0'>女</el-radio>
          <p>诊断时间：</p>
          <div class="block">
            <el-date-picker v-model="checkTime" type="daterange" style="width: 94%;" unlink-panels range-separator="至"
              start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions">
            </el-date-picker>
          </div>
          <el-button type="primary" class="search-btn" @click="Check">搜索</el-button>
        </el-tab-pane>
      </el-tabs>



    </el-drawer>
    <el-pagination background layout="prev, pager, next" :total="1000">
    </el-pagination>


  </div>
</template>

<style>
.search-btn {
  margin-top: 2vw;
  margin-left: 83%;

}
</style>