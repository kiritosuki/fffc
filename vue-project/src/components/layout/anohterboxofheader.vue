<script setup>
import { useRouter } from 'vue-router';
import { AddLocation, CaretLeft, DataAnalysis, Film, FolderAdd, HomeFilled, InfoFilled, UserFilled } from '@element-plus/icons-vue'
import { computed, ref  } from 'vue';
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

const asidewidth = ref(210)
const contentwidth = ref(270)

const asideCss = computed(() => ({
  '--asidewidth': `${asidewidth.value}px`  // 添加 -- 前缀并添加单位
}))
const contentCss = computed(() => ({
  '--contentwidth': `${contentwidth.value}px`  // 添加 -- 前缀并添加单位
}))
const ifBig = ref(1)
const ifSmall = ref(0)

const beSmall = () => {
  asidewidth.value = 60
  contentwidth.value = 110
  ifBig.value = 0
  ifSmall.value = 1
}
const beBig = () => {
  asidewidth.value = 210
  contentwidth.value = 270
  ifBig.value = 1
  ifSmall.value = 0
}
</script>








<template>
  <div class="app-layout" :style="contentCss">
    <!-- 固定侧边栏 -->
    <aside class="sidebar" :style="asideCss">
      <div id="topContainer" v-if="ifBig">
      <b id="top">Auris Glow</b>
      <el-button id="butTop" @click="beSmall"><el-icon :size="32"><CaretLeft /></el-icon></el-button>
      </div>
      <!-- <div id="topContainer">
      <div><span id="top">Auris Glow</span></div><button class="butTop">回</button>
      </div> -->
      <div v-if="ifBig">
      <el-button :class="home" @click="routerPush('/home')" >主页</el-button><br>
      <!-- <el-button :class="homeResult" @click="routerPush('/homeResult')" >主页二</el-button><br> -->
      <el-button :class="admin" @click="routerPush('/admin')" >管理</el-button><br>
      <el-button :class="analyze" @click="routerPush('/analyze')" >分析</el-button><br>
      <el-button :class="introduce" @click="routerPush('/introduce')" >介绍</el-button><br>
      <!-- <el-button :class="personal" @click="routerPush('/personal')" >个人</el-button><br> -->
      <!-- <el-button :class="history" @click="routerPush('/patients/history')" >历史记录</el-button><br> -->
      <el-button :class="aLotOfPictures" @click="routerPush('/aLotOfPictures')" >大量图片上传</el-button>
    </div>
    
    <div>
      <el-button id="bigger" @click="beBig" v-if="ifSmall"><img id="logo" src="../../assets/image/图标.png"></el-button>
    </div>
    <div id="smallone" v-if="ifSmall">
      <el-button :class="home" @click="routerPush('/home')" ><el-icon :size="29"><HomeFilled /></el-icon></el-button><br>
      <el-button :class="admin" @click="routerPush('/admin')" ><el-icon :size="29"><Film /></el-icon></el-button><br>
      <el-button :class="analyze" @click="routerPush('/analyze')" ><el-icon :size="29"><DataAnalysis /></el-icon></el-button><br>
      <el-button :class="introduce" @click="routerPush('/introduce')" ><el-icon :size="29"><InfoFilled /></el-icon></el-button><br>
      <!-- <el-button :class="personal" @click="routerPush('/personal')" ><el-icon :size="29"><UserFilled /></el-icon></el-button><br> -->
      <el-button :class="aLotOfPictures" @click="routerPush('/aLotOfPictures')" ><el-icon :size="29"><FolderAdd /></el-icon></el-button>
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
    grid-template-columns: var(--contentwidth) 1fr;  /* 正确引用 */
  height: 100vh;
  width: 100vw;
  margin: -8px;
  padding: 0;
  /* transition: grid-template-columns 0.3s; */
  transition: grid-template-columns 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.sidebar {
  grid-area: sidebar;
  background: rgb(249, 251, 255);
  position: absolute;
  width: var(--asidewidth);  /* 正确引用 */
  padding: 1.5rem;
  height: 100vh;
  overflow-y: auto;
  /* transition: width 0.3s; */
  transition: width 0.3s cubic-bezier(0.4, 0, 0.2, 1); /* 标准缓入缓出曲线 */
}

.content {
  grid-area: content;
  padding: 1rem;
  height: 100vh;
  overflow: auto;
}
#topContainer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  position: relative; /* 为绝对定位按钮提供参照 */
  width: 100%;
  margin-top: 10px;
  margin-bottom: 30px;
  height: auto;
  padding-right: 40px; /* 为按钮预留空间 */
}

#top {
  display: inline-block;
  font-size: 30px;
  font-family: 'Futura', 'Century Gothic', 'Avenir', sans-serif;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: calc(100% - 50px);
}

#butTop {
  position: absolute;
  right: 30px;
  top: 50%;
  transform: translateY(-50%);
  width: 32px;
  height: 32px;
  margin: 0;
  padding: 0;
  background-color: rgb(249, 251, 255);
  border: none;
  flex-shrink: 0; /* 禁止按钮缩小 */
}
/* 
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
  width: 5px;
  margin-left: 15px;
  padding-left: 15px;
  padding-bottom: 20px;
  background-color: rgb(249, 251, 255);
  border: none;
} */

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
  font-family: 'Consolas', 'PingFang SC','Helvetica Neue', Arial, sans-serif;
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

#logo{
  width: 90px;
  height: 50px;
}
#bigger{
  margin-top: 30px;
  margin-left: -30px;
  margin-bottom: 50px;
  border: none;
  background-color: rgb(249, 251, 255);
}
#smallone > button{
  margin-bottom: 20px;
}

</style>