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

// 是否是正常的
const ifLeftNomal = ref(false)
const ifRightNomal = ref(false)

// 其他异常病症
const leftOtherIllness= ref('')
const rightOtherIllness= ref('')

// 是否有输入栏
const leftinput = ref(false)
const rightinput = ref(false)

// 诊断关键字
const leftDiag = ref('')
const rightDiag = ref('')

// 输入栏内容初始化
const resInfo = ref('')

// 提交按钮加载控制初始化
const submitting = ref(false)















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
/*onMounted(() => {
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

    // 判断是否需要显示其他异常病症的输入栏
    // leftinput =ref(leftIllnessList.includes('8'))
    // rightinput =ref(rightIllnessList.includes('8'))

    resInfo.value = response.data.data.resInfo
  }
})
*/
onMounted(async () => {
  id.value = route.query.id // 获取查询参数中的 id
  console.log("当前 ID:", id.value)

  try {
    // 使用 await 等待数据返回
    const response = await CheckPatientFir(id.value)

    // 确保返回的数据结构正确
    if (response.code === 1) {
      leftImg.value = response.data.leftImg
      rightImg.value = response.data.rightImg

      leftIllnessList.value = response.data.leftStatusIllList
      rightIllnessList.value = response.data.rightStatusIllList

      if (leftIllnessList.value.includes('8')){
        leftinput.value = true
      } else {
        leftinput.value = false
      }
      if (rightIllnessList.value.includes('8')){
        rightinput.value = true
      } else {
        rightinput.value = false
      }
      


      leftDiag.value = response.data.leftDiag
      rightDiag.value = response.data.rightDiag

      resInfo.value = response.data.resInfo
    } else {
      console.error("后端返回的 code 不是 1:", response?.data)
    }
  } catch (error) {
    console.error("请求失败:", error)
  }
})


// 判断是否需要显示其他异常病症的输入栏
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

watch(() => leftIllnessList.value, (newValue,oldValue) => {
  if (newValue.includes('1')) {
        // 如果选中了“正常”，则清空其他选项
        // leftIllnessList.value = ['1'];
        ifLeftNomal.value = true
      } else {
        ifLeftNomal.value = false
      }
    },
    { immediate: true }
);

watch(() => rightIllnessList.value, (newValue,oldValue) => {
      if (newValue.includes('1')) {
        // 如果选中了“正常”，则清空其他选项
        // rightIllnessList.value = ['1'];
        ifRightNomal.value = true
      } else {
        ifRightNomal.value = false
      }
    },
    { immediate: true }
);

// const handleLeftIllnessList = () => {
//   if (leftIllnessList.value.includes('1')) {
//     // 如果选中了“正常”，则清空其他选项
//     // leftIllnessList.value = ['1'];
//     ifLeftNomal.value = true
//   } else {
//     ifLeftNomal.value = false
//   }
// }
// const handleRightIllnessList = (e) => {
//   if (rightIllnessList.value.includes('1')) {
//     // 如果选中了“正常”，则清空其他选项
//     // rightIllnessList.value = ['1'];
//     ifRightNomal.value = true
//   } else {
//     ifRightNomal.value = false
//   }
// }









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
        <input type="checkbox" name="leftIllnessList" value='1' id="1" v-model="leftIllnessList" @click="handleLeftIllnessList">
        <label for="1">正常</label><br>
        <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal" value='2' id="2" v-model="leftIllnessList">
        <label for="2">糖尿病</label><br>
        <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal" value='3' id="3" v-model="leftIllnessList">
        <label for="3">青光眼</label><br>
        <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal" value='4' id="4" v-model="leftIllnessList">
        <label for="4">白内障</label><br>
        <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal" value='5' id="5" v-model="leftIllnessList">
        <label for="5">AMD</label><br>
        <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal" value='6' id="6" v-model="leftIllnessList">
        <label for="6">高血压</label><br>
        <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal" value='7' id="7" v-model="leftIllnessList">
        <label for="7">近视</label><br>
        <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal" value='8' id="8" v-model="leftIllnessList">
        <label for="8">其他异常</label><br>
        <p>{{ leftDiag }}</p>
        <div id="lefInput">
        <el-input type="textarea" :rows="3" placeholder="请输入其他异常病症" v-model="leftOtherIllness" v-show="leftinput">
        </el-input>
      </div>
</div>
    </div>

    <div class="indexResult">
      <div class="checkbox">
        <p>右眼：</p>
        <img :src="rightImg" alt="右眼图片损毁">
        <p>潜在病症：</p>
        <input type="checkbox" name="rightIllnessList" value='1' id="9" v-model="rightIllnessList" @click="handleRightIllnessList">
        <label for="9">正常</label><br>
        <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal" value='2' id="10" v-model="rightIllnessList">
        <label for="10">糖尿病</label><br>
        <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal" value='3' id="11" v-model="rightIllnessList">
        <label for="11">青光眼</label><br>
        <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal" value='4' id="12" v-model="rightIllnessList">
        <label for="12">白内障</label><br>
        <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal" value='5' id="13" v-model="rightIllnessList">
        <label for="13">AMD</label><br>
        <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal" value='6' id="14" v-model="rightIllnessList">
        <label for="14">高血压</label><br>
        <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal" value='7' id="15" v-model="rightIllnessList">
        <label for="15">近视</label><br>
        <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal" value='8' id="16" v-model="rightIllnessList">
        <label for="16">其他异常</label><br>
        <p>{{ rightDiag }}</p>
        <div id="rigInput">
        <el-input type="textarea" :rows="3" placeholder="请输入其他异常病症" v-model="rightOtherIllness" v-show="rightinput">
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