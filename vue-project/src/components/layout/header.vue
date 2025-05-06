<script setup>
import { useRouter } from 'vue-router';
import { AddLocation, CaretLeft, DataAnalysis, Film, FolderAdd, HomeFilled, InfoFilled, UserFilled } from '@element-plus/icons-vue'
import { computed, onMounted, ref } from 'vue';
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

const ifOK = ref(0)

onMounted(() => {
  setTimeout(() => {
  ifOK.value = true
  console.log(ifOK.value)
  }, 100) // 添加微小延迟确保DOM已渲染
})



</script>








<template>
  <div class="app-layout" :style="contentCss" >
    <!-- 固定侧边栏 -->
    <aside class="sidebar" :style="asideCss" :class="{'show': ifOK , 'hide': !ifOK}">
      <div id="topContainer" v-if="ifBig">
        <b id="top">Auris Glow</b>
        <el-button id="butTop" @click="beSmall"><img class="icon" src="../../assets/image/箭头.png"></el-button>
      </div>
      <!-- <div id="topContainer">
      <div><span id="top">Auris Glow</span></div><button class="butTop">回</button>
      </div> -->
      <div v-if="ifBig" id="bigone">
        
        <el-button :class="home" @click="routerPush('/home')"><span>主页</span></el-button><br>
        <!-- <el-button :class="homeResult" @click="routerPush('/homeResult')" >主页二</el-button><br> -->
        <el-button :class="admin" @click="routerPush('/admin')"><span>管理</span></el-button><br>
        <el-button :class="aLotOfPictures" @click="routerPush('/aLotOfPictures')"><span>上传</span></el-button><br>
        <el-button :class="analyze" @click="routerPush('/analyze')"><span>分析</span></el-button><br>
        <el-button :class="introduce" @click="routerPush('/introduce')"><span>介绍</span></el-button><br>
        <!-- <el-button :class="personal" @click="routerPush('/personal')" >个人</el-button><br> -->
        <!-- <el-button :class="history" @click="routerPush('/patients/history')" >历史记录</el-button><br> -->


        <div class="per">
          <div class="personal">
            <img id="personal" src="../../assets/image/personal-center.png" class="img">
            <div id="name">

              <span id="name" class="name">
                zhangsan
              </span>
            </div>
            <div id="email" class="email">
              1234567890@qq.com
            </div>
          </div>

        </div>

      </div>

      <div>
        <el-button id="bigger" @click="beBig" v-if="ifSmall"><img id="logo"
            src="../../assets/image/图标无字版.png"></el-button>
      </div>
      <div id="smallone" v-if="ifSmall">
        <el-button :class="home" @click="routerPush('/home')"><el-icon :size="29">
            <HomeFilled />
          </el-icon></el-button><br>
        <el-button :class="admin" @click="routerPush('/admin')"><el-icon :size="29">
            <Film />
          </el-icon></el-button><br>
        <el-button :class="aLotOfPictures" @click="routerPush('/aLotOfPictures')"><el-icon :size="29">
            <FolderAdd />
          </el-icon></el-button><br>
        <el-button :class="analyze" @click="routerPush('/analyze')"><el-icon :size="29">
            <DataAnalysis />
          </el-icon></el-button><br>
        <el-button :class="introduce" @click="routerPush('/introduce')"><el-icon :size="29">
            <InfoFilled />
          </el-icon></el-button><br>
        <!-- <el-button :class="personal" @click="routerPush('/personal')" ><el-icon :size="29"><UserFilled /></el-icon></el-button><br> -->


        <div class="per">
          <div class="personal">
            <img id="personal" src="../../assets/image/personal-center.png" class="img">
          </div>
        </div>
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
  grid-template-columns: var(--contentwidth) 1fr;
  /* 正确引用 */
  height: 100vh;
  width: 100vw;
  margin: -8px;
  padding: 0;
  /* transition: grid-template-columns 0.3s; */
  transition: grid-template-columns 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.sidebar {
  grid-area: sidebar;
  background: #fefefe;
  position: absolute;
  width: var(--asidewidth);
  /* 正确引用 */
  padding: 1.5rem;
  height: 100vh;
  overflow-y: auto;
  /* transition: width 0.3s; */
  /* 标准缓入缓出曲线 */
  box-shadow: 9px 9px 18px #e4e4e4,
    -9px -9px 18px #e6e6e6;
  transition: all 0.5s cubic-bezier(0.4, 0, 0.2, 1);
}
.hide {
  opacity: 0;
  transform: translateX(-100px);
}
.show {
  opacity: 1;
  transform: translateX(0);
}

.content {
  grid-area: content;
  padding: 1rem;
  height: 100vh;
  overflow: auto;
  text-align: center;
}

.content>* {
  display: block;
  margin: 0 auto;
  /* 水平居中 */
  text-align: left;
  /* 防止内部文本也居中 */
  max-width: 100%;
  /* 防止溢出 */
}

#topContainer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  position: relative;
  /* 为绝对定位按钮提供参照 */
  width: 100%;
  margin-top: 10px;
  margin-bottom: 50px;
  height: auto;
  padding-right: 40px;
  /* 为按钮预留空间 */
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
  background-color: #fefefe;
  border: none;
  flex-shrink: 0;
  /* 禁止按钮缩小 */
  color: #646ac2;
}

.icon {
  width: 30px;
  height: 30px;

}


.per {
  position: absolute;
  bottom: 30px;
  left: 50%;
}

.personal {
  position: relative;
  align-items: center;
  text-align: center;
  display: flex;
  flex-direction: row;
  /*设置主轴方向是水平方向*/
  align-items: center;
  bottom: 50px;
}

.img {
  width: 45px;
  height: 45px;
  border-radius: 50%;
  margin-left: -30px;
  position: absolute;
}

.name {
  font-size: 16px;
  font-family: 'PingFang SC', 'Helvetica Neue', Arial, sans-serif;
  color: black;
  margin-top: 35px;
  margin-left: -40px;
  position: absolute;
}

.email {
  font-size: 14px;
  font-family: 'PingFang SC', 'Helvetica Neue', Arial, sans-serif;
  color: gray;
  margin-top: 135px;
  margin-left: -70px
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
  margin-bottom: 30px;
  width: 95%;
  background-color: none;
  height: 35px;
  background-color: #fefefe;
  border: none;
  font-size: 20px;
  border-radius: 16px;
  font-family: 'PingFang SC', 'Helvetica Neue', Arial, sans-serif;
  transition: all 0.3s;
}

#bigone .el-button:hover {
  background-color: #626AC2 !important;
  color: white;

}



.butRouter span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.butRouter span::after {
  content: '>';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -25px;
  transition: 0.5s;
}

.butRouter:hover span {
  padding-right: 15px;
}

.butRouter:hover span::after {
  opacity: 1;
  right: -5px;
}

.butRouter:active span::after{
  opacity: 0;
  top: 0;
  right: -25px;
  transition: 0.5s;
}
.butRouter:active span{
  padding-right: 0px;
  transition: 0.5s;
}


.butRouterDeeper {
  margin-bottom: 30px;
  width: 95%;
  background-color: none;
  height: 35px;
  background-color: #626AC2;
  border: none;
  font-size: 20px;
  color: white;
  border-radius: 16px !important;
  font-family: 'PingFang SC', 'Helvetica Neue', Arial, sans-serif;
  transition: all 0.3s;
}

#logo {
  width: 90px;
  height: 50px;
}

#bigger {
  margin-top: 30px;
  margin-left: -30px;
  margin-bottom: 50px;
  border: none;
  background-color: #fefefe;

}

#smallone>button {
  margin-bottom: 30px;
  margin-left: 10px;
  transition: all 0.3s;

}

#smallone .el-button:hover {
  background-color: #626AC2 !important;
  color: white;
  width: 40px;
  height: 40px;
}

#smallone .butRouter {
  width: 40px;
  height: 40px;
  border-radius: 10px !important;
}

#smallone .butRouterDeeper {
  border-radius: 10px !important;
  width: 40px;
  height: 40px;
  background-color: #626AC2 !important;
  margin-left: 15px;
}

#smallone .el-icon {
  color: inherit;
}



</style>