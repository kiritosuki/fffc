<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();


const routerPush = (path) => {
  router.push(path);
}

</script>








<template>
  <div class="app-layout">
    <!-- 固定侧边栏 -->
    <aside class="sidebar">
      <div id="topContainer">
      <div ><span id="top">NADVS</span><button class="butTop">回</button></div>
      </div>
      <button class="butRouter" @click="routerPush('/home')">主页</button><br>
      <button class="butRouter" @click="routerPush('/homeResult')">主页二</button><br>
      <button class="butRouter" @click="routerPush('/admin')">管理</button><br>
      <button class="butRouter" @click="routerPush('/analyze')">分析</button><br>
      <button class="butRouter" @click="routerPush('/introduce')">介绍</button><br>
      <button class="butRouter" @click="routerPush('/personal')">个人</button><br>
      <button class="butRouter" @click="routerPush('/patients/history')">历史记录</button><br>
      <button class="butRouter" @click="routerPush('/aLotOfPictures')">大量图片上传</button>
    </aside>

    <!-- 动态内容区域 -->
    <main class="content">
    <router-view :key="router.fullPath" />
  </main>
  </div>
</template>

<style scoped>
.app-layout {
  display: grid;
  grid-template-areas:
    "sidebar content";
  grid-template-columns: 220px 1fr;
  height: 100vh;
  width: 100vw;
  margin: -8px;
  padding: 0;
}

.sidebar {
  grid-area: sidebar;
  background: #f5f5f5;
  position: absolute;
  width: 200px;
  padding: 1rem;
  height: 100vh;
  overflow-y: auto;
}

.content {
  grid-area: content;
  padding: 1rem;
  height: 100vh;
  overflow: auto;
}

#topContainer {
  display: inline-block;
  width: 100%;
  margin: 0px, 0px, 10px, 0px;
}

#topContainer > div {
  display: grid;
  grid-template-columns: auto;
  grid-template-rows: 1fr 1fr;
  width: 100%;
  height: auto;

}

#top {
  display: inline-block;
  font-size: 32px;
  font-family: 'Courier New', Courier, monospace;
}

#topbut {

}

/* #topContainer > div {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: auto;

}

#top {
  display: inline-block;
  font-size: 32px;
  font-family: 'Courier New', Courier, monospace;
}

#topbut {
flex: ;

} */


.butRouter {
  margin-bottom: 0.5rem;
  width: 100%;
}
</style>