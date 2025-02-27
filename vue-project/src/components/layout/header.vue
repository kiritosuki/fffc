<template>
    <div class="app-layout">
      <!-- 固定页眉 -->
      <header class="header">Header</header>
  
      <!-- 固定侧边栏 -->
      <aside class="sidebar">Sidebar</aside>
  
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