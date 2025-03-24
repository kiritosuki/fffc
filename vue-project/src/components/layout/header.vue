<template>
    <div class="app-layout">
      <!-- 固定页眉 -->
      <header class="header">Header</header>
  
      <!-- 固定侧边栏 -->
      <aside class="sidebar">
        <router-link to="/home">主页</router-link><br>
        <router-link to="/homeResult">主页二</router-link><br>
        <router-link to="/admin">管理</router-link><br>
        <router-link to="/analyze">分析</router-link><br>
        <router-link to="/introduce">介绍</router-link><br>
        <router-link to="/personal">个人</router-link><br>
        <router-link to="/patients/history">历史记录</router-link>
        <router-link to="/aLotOfPictures">大量图片上传</router-link>
      </aside>
  
      <!-- 动态内容区域 -->
      <main class="content">
        <router-view :key="$route.fullPath" />
      </main>
    </div>
  </template>
  
  <style scoped>
  .app-layout {
    display: grid;
    grid-template-areas:
      "header header"
      "sidebar content";
    grid-template-rows: auto 1fr;
    grid-template-columns: 200px 1fr;
    height: 100vh;
    width: 100vw;
  }
  
  .header {
    grid-area: header;
    background: #333;
    color: white;
    padding: 1rem;
  }
  
  .sidebar {
    grid-area: sidebar;
    background: #f5f5f5;
    padding: 1rem;
  }
  
  .content {
    grid-area: content;
    padding: 1rem;
    overflow: auto;
  }
  </style>