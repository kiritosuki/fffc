<script setup>
import { ref, onMounted } from 'vue'
import { CheckHistory } from '../../api/patients'
import { ElLoading, ElMessage } from 'element-plus'
import router from '../../router';
import { useRoute } from 'vue-router';


const pageVisible = ref(false)


// 接收数据
const route = useRoute()
const id = route.query.id;
console.log(id);
// 打开的所有折叠页面
const historyListNumber = ref([])

// 直接接收的数据
const historyListRecieve = ref([
  { id: 1, name: '1', leftStatusStrList: ["糖尿病", "高血压"], rightStatusStrList: ["心脏病", "其他异常"] },
  { id: 2, name: '2', leftStatusStrList: ["糖尿病"], rightStatusStrList: ["心脏病", "高血压", "其他异常"] },
])

// 处理后的数据
const historyListFixed = ref([])

// 回掉函数
const selectedListChange = (() => {

})

// 加载对象
const loading = ref(null)

// 页面初始化
onMounted(async () => {
  // 开始加载动画
  loading.value = ElLoading.service({
    text: "加载中...",
    background: "rgba(0, 0, 0, 0.7)", // 背景颜色
    spinner: "el-icon-loading", // 自定义加载图标
    target: document.body, // 指定加载动画覆盖的区域
  });
  try {
    console.log("你好")
    await CheckHistory(id).then((res) => {
      console.log("你好1")
      if (res.data?.code == 1) {
        historyListRecieve.value = res.data.data;
        historyListFixed.value = historyListRecieve.value
        console.log(historyListRecieve.value)
        historyListFixed.value.forEach(item => {
          item.leftStatusStrList = item.leftStatusStrList.filter(ill => ill !== "其他异常");
          item.rightStatusStrList = item.rightStatusStrList.filter(ill => ill !== "其他异常");
        });
      } else {
        ElMessage.error(res.data?.msg);
      }
    })
  } catch (error) {
    ElMessage.error("加载失败,请稍后再试");
  } finally {
    loading.value.close();
  }
  // 页面加载完成后触发动画
  setTimeout(() => {
    pageVisible.value = true
  }, 100) // 添加微小延迟确保DOM已渲染
})



const back = async () => {
  loading.value = ElLoading.service({
    text: "加载中...",
    background: "rgba(0, 0, 0, 0.7)", // 背景颜色
    spinner: "el-icon-loading", // 自定义加载图标
    target: document.body, // 指定加载动画覆盖的区域
  });
  console.log("跳转参数:", {
    path: "/patients/info",
    query: { id: id },
  });
  // 跳转组件
  router
    .push({
      path: "/patients/info",
      query: { id: id },
    })
    .then(() => {
      console.log("跳转成功");
    })
    .catch((err) => {
      console.error("跳转失败:", err);
    });
  loading.value.close()
};



</script>


<template>
  <div class="page-container" :class="{ 'page-visible': pageVisible }">
    <div class="history-container">
    <el-collapse v-model="historyListNumber" @change="selectedListChange">
      <!-- <el-collapse-item title="编号             查询时间" :name="A"></el-collapse-item> -->
      <el-collapse-item :title="`第${index + 1}次    诊断时间：${item.diagTime}`" :name="index"
        v-for="(item, index) in historyListFixed" :key="item.id">
        <div>左眼诊断结果：<span v-for="thing in item.leftStatusStrList">{{ thing + " " }} </span><span>{{ item.leftIllInfo
            }}</span></div>
        <div>右眼诊断结果：<span v-for="thing in item.rightStatusStrList">{{ thing + " " }} </span><span>{{ item.rightIllInfo
            }}</span></div>
        <div>诊断结果：{{ item.resInfo }}</div>
      </el-collapse-item>
    </el-collapse>
  </div>
     <div class="option">
    <el-button id="back" @click="back">返回</el-button>
  </div>
  </div>
 
</template>

<style scoped>
.page-container {
  opacity: 0;
  transform: translateY(30px);
  transition: all 0.6s cubic-bezier(0.25, 0.1, 0.25, 1);
}

.page-visible {
  opacity: 1;
  transform: translateY(0);
}
.page-container {
  text-align: center;
  position: relative;
  margin-top: 20px;
  width: 80%;
  height: 85vh;
  display: inline-block;
  padding: 20px;
  background: #fefefe;
  border-radius: 30px;
  box-shadow: 9px 9px 18px #c3c3c3,
    -9px -9px 18px #e6e6e6;
}

.history-container {
  margin: 0px auto;
  width: 95%;
  height: 92%;
  text-align: left;
  overflow-y: auto;
}

.option {
  position: absolute;
  bottom: 14px;
  left: 0;
  right: 0;
  display: flex;
  justify-content: center; /* 水平居中 */
}

</style>