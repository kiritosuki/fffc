<template>
  <div>
    <!-- 控制 Drawer 的按钮 -->
    <el-button type="primary" @click="toggleDrawer">
      搜索
    </el-button>

    <!-- Drawer 组件 -->
    <el-drawer v-model="isDrawerOpen" title="精准搜索" :direction="rtl" :size="size">
      <p>姓名:</p>
      <el-input placeholder="姓名" v-model="checkName" clearable></el-input>
      <p>性别:</p>
      <el-input placeholder="性别" v-model="checkGender" clearable></el-input>
      <p>姓名:</p>
      <el-input placeholder="姓名" v-model="checkName" clearable></el-input>
      <p>姓名:</p>
      <el-input placeholder="姓名" v-model="checkName" clearable></el-input>
      <p>姓名:</p>
      <el-input placeholder="姓名" v-model="checkName" clearable></el-input>
      <p>姓名:</p>
      <el-input placeholder="姓名" v-model="checkName" clearable></el-input>

      <el-button type="primary">搜索</el-button>

    </el-drawer>
    <el-pagination background layout="prev, pager, next" :total="1000">
    </el-pagination>


  </div>
</template>

<script setup>
import { ref } from 'vue';

// 控制 Drawer 显示/隐藏的状态
const isDrawerOpen = ref(false);

const checkName = ref('');
const checkGender = ref('');

const size = ref('30%'); // Drawer 的宽度或高度

// 切换 Drawer 显示/隐藏的函数
const toggleDrawer = () => {
  isDrawerOpen.value = true;
};
</script>

<style scoped>
/* 你可以在这里添加一些样式 */
</style>