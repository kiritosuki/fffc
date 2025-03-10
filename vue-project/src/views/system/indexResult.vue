<script setup>
import { ref, onMounted, computed, watch } from 'vue'
import { useRoute } from 'vue-router';
import router from '../../router';
import { CheckPatientFir } from '../../api/patients';

// 接收数据
const route = useRoute()
// 使用 ref 来保存 id
const id = ref('')


// 图片初始化
const leftImg = ref('')
const rightImg = ref('')

// 检测病症初始化
const leftIllnessList = ref([])
const rightIllnessList = ref([])

const leftOtherIllness= ref('')
const rightOtherIllness= ref('')

const leftinput = ref(false)
const rightinput = ref(false)

// 输入栏内容初始化
const resInfo = ref('')

// 提交按钮加载控制初始化
const submitting = ref(false)

// 判断是否需要显示其他病症的输入栏
watch(() => leftIllnessList.value, (newValue, oldValue) => {
  if (newValue.includes('8')) {
    leftinput.value = true
  } else {
    leftinput.value = false
  }
},
{ immediate: true }
)
watch(() => rightIllnessList.value, (newValue, oldValue) => {
  if (newValue.includes('8')) {
    rightinput.value = true
  } else {
    rightinput.value = false
  }
},
{ immediate: true }
)














// const leftinput = computed(() => {
//   for (let i = 0; i < leftIllnessList.value.length; i++) {
//     if (leftIllnessList.value[i] === "8") {
//       return true
//     }
//   }
//   return false
// })
// const rightinput = computed(() => {
//     for (let i = 0; i < rightIllnessList.value.length; i++) {
//       if (rightIllnessList.value[i] === "8") {
//         return true
//       }
//     }
//     return false
// })


// 通过 watch 监听 route.query 的变化
onMounted(() => {
  id.value = route.query.id // 获取查询参数中的 id
  console.log("当前 ID:", id.value)

  // 请求图片和病症信息
  const response = CheckPatientFir(id.value)
  if (response.data.code === 1) {
    //  导入图片
    leftImg.value = response.data.data.leftImg
    rightImg.value = response.data.data.rightImg

    // 检测出的典型病症
    leftIllnessList.value = response.data.data.leftStatusIllList
    rightIllnessList.value = response.data.data.rightStatusIllList

    // 判断是否需要显示其他病症的输入栏
    // leftinput =ref(leftIllnessList.includes('8'))
    // rightinput =ref(rightIllnessList.includes('8'))

    resInfo.value = response.data.data.resInfo
  }
})




// 最后提交按钮点击事件
const handleFinalResult = () => {
    // 开始加载动画
    submitting.value = true


    // 请求提交病例改动

    // 跳转

}
</script>


<template>
    <div class="indexResult">
      <div class="checkbox">
        <p>左眼：</p>
        <img :src="leftImg" alt="左眼图片损毁">
        <p>潜在病症：</p>
        <input type="checkbox" name="leftIllnessList" value="1" id="1" v-model="leftIllnessList">
        <label for="1">1号病症</label><br>
        <input type="checkbox" name="leftIllnessList" value="2" id="2" v-model="leftIllnessList">
        <label for="2">2号病症</label><br>
        <input type="checkbox" name="leftIllnessList" value="3" id="3" v-model="leftIllnessList">
        <label for="3">3号病症</label><br>
        <input type="checkbox" name="leftIllnessList" value="4" id="4" v-model="leftIllnessList">
        <label for="4">4号病症</label><br>
        <input type="checkbox" name="leftIllnessList" value="5" id="5" v-model="leftIllnessList">
        <label for="5">5号病症</label><br>
        <input type="checkbox" name="leftIllnessList" value="6" id="6" v-model="leftIllnessList">
        <label for="6">6号病症</label><br>
        <input type="checkbox" name="leftIllnessList" value="7" id="7" v-model="leftIllnessList">
        <label for="7">7号病症</label><br>
        <input type="checkbox" name="leftIllnessList" value="8" id="8" v-model="leftIllnessList">
        <label for="8">其他</label><br>
        <div id="lefInput">
        <el-input type="textarea" :rows="3" placeholder="请输入其他病症" v-model="leftOtherIllness" v-if="leftinput">
        </el-input>
      </div>
</div>
    </div>

    <div class="indexResult">
      <div class="checkbox">
        <p>右眼：</p>
        <img :src="rightImg" alt="右眼图片损毁">
        <p>潜在病症：</p>
        <input type="checkbox" name="rightIllnessList" value="1" id="9" v-model="rightIllnessList">
        <label for="9">1号病症</label><br>
        <input type="checkbox" name="rightIllnessList" value="2" id="10" v-model="rightIllnessList">
        <label for="10">2号病症</label><br>
        <input type="checkbox" name="rightIllnessList" value="3" id="11" v-model="rightIllnessList">
        <label for="11">3号病症</label><br>
        <input type="checkbox" name="rightIllnessList" value="4" id="12" v-model="rightIllnessList">
        <label for="12">4号病症</label><br>
        <input type="checkbox" name="rightIllnessList" value="5" id="13" v-model="rightIllnessList">
        <label for="13">5号病症</label><br>
        <input type="checkbox" name="rightIllnessList" value="6" id="14" v-model="rightIllnessList">
        <label for="14">6号病症</label><br>
        <input type="checkbox" name="rightIllnessList" value="7" id="15" v-model="rightIllnessList">
        <label for="15">7号病症</label><br>
        <input type="checkbox" name="rightIllnessList" value="8" id="16" v-model="rightIllnessList">
        <label for="16">其他</label><br>
        <div id="rigInput">
        <el-input type="textarea" :rows="3" placeholder="请输入其他病症" v-model="rightOtherIllness" v-if="rightinput">
        </el-input></div>
      </div>
    </div>

    <div>
        <el-input type="textarea" :rows="5" placeholder="本内容由大语言模型生成，仅作参考" v-model="resInfo" style="width: 70vw;">
        </el-input>
    </div>

    <el-button type="primary" :loading="submitting" @click="handleFinalResult" id="submit"> 
        {{ submitting ? '提交中...' : '提交病例' }}
    </el-button>

</template>

<style scoped>
img {
    width: 150px;
    height: 150px;
}


.indexResult {
    position: relative;
    display: inline-block;
    width: 25vw;
    margin: 3vw;
    padding: 1vw 1vw 3vw 3vw;
    border: 1px solid #ccc;
    border-radius: 10px;
}
.checkbox {
    display: inline-block;
    width: 50%;
}
#lefInput, #rigInput {
  display: inline-block;
  width: 15vw;
  position: absolute;
  top: 70%;
  left: 40%;
}
#submit {
  margin-top: 1vw;
  margin-left: 64vw;
}
</style>