<script setup>
import { useRouter } from 'vue-router';
import { CaretLeft } from '@element-plus/icons-vue'
import { computed  } from 'vue';
const router = useRouter();

const routerPush = (path) => {
  router.push(path);
}

const home = computed(() => {
  return router.currentRoute.value.path === '/home' ? 'butRouterDeeper' : 'butRouter'
})
const homeResult = computed(() => {
  return router.currentRoute.value.path === '/admin' ? 'butRouterDeeper' : 'butRouter'
})
const analyze = computed(() => {
  return router.currentRoute.value.path === '/analyze' ? 'butRouterDeeper' : 'butRouter'
})
const admin = computed(() => {
  return router.currentRoute.value.path === '/admin' ? 'butRouterDeeper' : 'butRouter'
})
const introduce = computed(() => {
  return router.currentRoute.value.path === '/introduce' ? "butRouterDeeper" : "butRouter"
})
const personal = computed(() => {
  return router.currentRoute.value.path === '/personal' ? 'butRouterDeeper' : 'butRouter'
})
const history = computed(() => {
  return router.currentRoute.value.path === '/patients/history' ? 'butRouterDeeper' : 'butRouter'
})
const aLotOfPictures = computed(() => {
  return router.currentRoute.value.path === '/aLotOfPictures' ? 'butRouterDeeper' : 'butRouter'
})
</script>








<template>
  <div class="app-layout">
    <!-- 固定侧边栏 -->
    <aside class="sidebar">
      <div id="topContainer">
      <b id="top">Auris Glow</b>
      <el-button id="butTop"><el-icon :size="32"><CaretLeft /></el-icon></el-button>
      </div>
      <!-- <div id="topContainer">
      <div><span id="top">Auris Glow</span></div><button class="butTop">回</button>
      </div> -->
      <div>
      <el-button :class="home" @click="routerPush('/home')">主页</el-button><br>
      <el-button :class="homeResult" @click="routerPush('/homeResult')">主页二</el-button><br>
      <el-button :class="admin" @click="routerPush('/admin')">管理</el-button><br>
      <el-button :class="analyze" @click="routerPush('/analyze')">分析</el-button><br>
      <el-button :class="introduce" @click="routerPush('/introduce')">介绍</el-button><br>
      <el-button :class="personal" @click="routerPush('/personal')">个人</el-button><br>
      <el-button :class="history" @click="routerPush('/patients/history')">历史记录</el-button><br>
      <el-button :class="aLotOfPictures" @click="routerPush('/aLotOfPictures')">大量图片上传</el-button>
    </div>
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
  grid-template-columns: 350px 1fr;
  height: 100vh;
  width: 100vw;
  margin: -8px;
  padding: 0;
}

.sidebar {
  grid-area: sidebar;
  background: rgb(249, 251, 255);
  position: absolute;
  width: 210px;

  padding: 1.5rem;
  height: 100vh;
  overflow-y: auto;
}

.content {
  grid-area: content;
  padding: 1rem;
  height: 100vh;
  overflow: auto;
}

#topContainer{
  display: inline-block;
  width: 100%;
  margin-top: 10px;
  margin-bottom: 30px;
  height: auto;
}


#top {
  display: inline-block;
  font-size: 30px;
  font-family: 'Futura', 'Century Gothic', 'Avenir', sans-serif;
}

#butTop {
  width: 10px;
  margin-left: 15px;
  padding-left: 15px;
  padding-bottom: 20px;
  background-color: rgb(249, 251, 255);
  border: none;
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
  margin-bottom: 15px;
  width: 95%;
  background-color: none;
  height: 35px;
  background-color:  rgb(249, 251, 255);
  border: none;
  font-size: 18px;
  font-family: 'PingFang SC','Helvetica Neue', Arial, sans-serif;
}
.butRouterDeeper {
  margin-bottom: 15px;
  width: 95%;
  background-color: none;
  height: 35px;
  background-color:  rgb(238, 245, 254);
  border: none;
  font-size: 18px;
  font-family: 'PingFang SC','Helvetica Neue', Arial, sans-serif;
}

</style>