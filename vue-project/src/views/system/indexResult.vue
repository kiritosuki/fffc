<script setup>
import { ref, onMounted, computed, watch } from 'vue'
import { useRoute } from 'vue-router';
import router from '../../router';
import { CheckPatientFir } from '../../api/patients';
import { ElImage, ElLoading, ElMessage } from 'element-plus'
import api from '../../api/index'


defineProps({
  id: {
    type: [String, Number], // 根据需要，调整类型
    required: true
  }
});


// 接收数据
const route = useRoute()
// 保存 id
const id = route.query.id;

// console.log("当前dddddd ID:", idint)
const loading = ref(null); // 用于存储加载动画的实例

// 图片初始化
const leftImg = ref('')
const rightImg = ref('')

// 图片大图阅览
const leftImgList = ref([leftImg.value])
const rightImgList = ref([rightImg.value])

// 检测病症初始化
const leftIllnessList = ref([])
const rightIllnessList = ref([])

// 是否是正常的
const ifLeftNomal = computed(() => leftIllnessList.value.includes('1'))
const ifRightNomal = computed(() => rightIllnessList.value.includes('1'))

// 其他异常病症
const leftOtherIllness = ref('')
const rightOtherIllness = ref('')

// 是否有输入栏
const leftinput = computed(() => leftIllnessList.value.includes('8'));
const rightinput = computed(() => rightIllnessList.value.includes('8'));

// 诊断关键字
const leftDiag = ref('')
const rightDiag = ref('')

// 输入栏内容初始化
const resInfo = ref('')

// 提交按钮加载控制初始化
const submitting = ref(false)

/* watch(leftIllnessList, (newVal) => {
  if (newVal.includes('1')) {
    // 如果包含正常选项，则过滤掉其他选项
    leftIllnessList.value = ['1']
  }
}, { deep: true })

watch(rightIllnessList, (newVal) => {
  if (newVal.includes('1')) {
    // 如果包含正常选项，则过滤掉其他选项
    rightIllnessList.value = ['1']
  }
}, { deep: true }) */


onMounted(async () => {

  // console.log("当前 ID:", idint)

  loading.value = ElLoading.service({
    text: '加载中...',
    background: 'rgba(0, 0, 0, 0.7)', // 背景颜色
    spinner: 'el-icon-loading', // 自定义加载图标
    target: document.body, // 指定加载动画覆盖的区域
  });

  try {
    // 使用 await 等待数据返回
    const response = await CheckPatientFir(id)
    console.log("firresponse:", response)
    // 确保返回的数据结构正确
    if (response.code === 1) {
      leftImg.value = response.data.leftImg
      rightImg.value = response.data.rightImg

      leftIllnessList.value = response.data.leftStatusIllList
      rightIllnessList.value = response.data.rightStatusIllList
      idint.value = response.data.id
      console.log("当前 ID:", id)
      // if (leftIllnessList.value.includes('8')){
      //   leftinput.value = true
      // } else {
      //   leftinput.value = false
      // }
      // if (rightIllnessList.value.includes('8')){
      //   rightinput.value = true
      // } else {
      //   rightinput.value = false
      // }


      // 改为手动赋值
      // leftDiag.value = response.data.leftDiag
      // rightDiag.value = response.data.rightDiag

      resInfo.value = response.data.resInfo
    } else {
      console.error("后端返回的 code 不是 1:", response?.data)
    }
  } catch (error) {
    console.error("请求失败:", error)
  } finally {
    loading.value.close();
  }
})





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
  id = route.query.id // 获取查询参数中的 id
  console.log("当前 ID:", id)

  // 请求图片和病症信息
  const response = CheckPatientFir(id)
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

//  onMounted(async () => {
//   id = route.query.id // 获取查询参数中的 id
//   console.log("当前 ID:", id)

//   try {
//     // 使用 await 等待数据返回
//     const response = await CheckPatientFir(id)

//     // 确保返回的数据结构正确
//     if (response.code === 1) {
//       leftImg.value = response.data.leftImg
//       rightImg.value = response.data.rightImg

//       leftIllnessList.value = response.data.leftStatusIllList
//       rightIllnessList.value = response.data.rightStatusIllList

//       leftDiag.value = response.data.leftDiag
//       rightDiag.value = response.data.rightDiag

//       resInfo.value = response.data.resInfo

//       // 确保 UI 及时更新
//       // updateIllnessState()
//     } else {
//       console.error("后端返回的 code 不是 1:", response?.data)
//     }
//   } catch (error) {
//     console.error("请求失败:", error)
//   }
// })



// 判断是否需要显示其他异常病症的输入栏
// watch(() => leftIllnessList.value, (newValue, oldValue) => {
//   if (newValue.includes('8')) {
//     leftinput.value = true
//   } else {
//     leftinput.value = false
//   }
// },
// { immediate: true , deep:true}
// )
// watch(() => rightIllnessList.value, (newValue, oldValue) => {
//   if (newValue.includes('8')) {
//     rightinput.value = true
//   } else {
//     rightinput.value = false
//   }
// },
// { immediate: true , deep:true}
// )

// watch(() => leftIllnessList.value, (newValue,oldValue) => {
//   if (newValue.includes('1')) {
//         // 如果选中了“正常”，则清空其他选项
//         // leftIllnessList.value = ['1'];
//         ifLeftNomal.value = true
//       } else {
//         ifLeftNomal.value = false
//       }
//     },
//     { immediate: true , deep:true}
// );

// watch(() => rightIllnessList.value, (newValue,oldValue) => {
//       if (newValue.includes('1')) {
//         // 如果选中了“正常”，则清空其他选项
//         // rightIllnessList.value = ['1'];
//         ifRightNomal.value = true
//       } else {
//         ifRightNomal.value = false
//       }
//     },
//     { immediate: true , deep:true}
// );

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
  try{// 开始加载动画
  submitting.value = true
  console.log(id)

  // 请求提交病例改动
  const resultdata = {
    leftStatusIllList:leftIllnessList,
    id: parseInt(id),
  rightStatusIllList: rightIllnessList,
  leftDiag: leftDiag.value,
  rightDiag: rightDiag.value,
  leftIllInfo: leftOtherIllness.value,
  rightIllInfo: rightOtherIllness.value,
  resInfo: resInfo.value,
}
  const response = api.UploadAddPatient(resultdata)

  console.log(response)


    ElMessage.success('提交成功')
    router.push({
      path: `/homeResult`,
    })
  // 跳转
  } catch (error) {
    ElMessage.error('服务器繁忙，提交失败')
    console.error("请求失败:", error)
  }
  finally {
    submitting.value = false
  }
}
</script>


<template>
  <div class="indexResult">
    <div class="checkbox">
      <p>左眼：</p>
      <el-image
      :src="leftImg"
      fit="cover"
      :preview-src-list="leftImgList"
      class="Elimage"
      >
    <div slot="error" class="image-slot"><i class="el-icon-picture-outline"></i></div>
    </el-image>
      <p>潜在病症：</p>
      <input type="checkbox" name="leftIllnessList" value='1' id='1' v-model="leftIllnessList">
      <label for='1'>正常</label><br>
      <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal || leftIllnessList.includes('1')" value='2'
        id="2" v-model="leftIllnessList">
      <label for="2">糖尿病</label><br>
      <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal || leftIllnessList.includes('1')" value='3'
        id="3" v-model="leftIllnessList">
      <label for="3">青光眼</label><br>
      <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal || leftIllnessList.includes('1')" value='4'
        id="4" v-model="leftIllnessList">
      <label for="4">白内障</label><br>
      <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal || leftIllnessList.includes('1')" value='5'
        id="5" v-model="leftIllnessList">
      <label for="5">AMD</label><br>
      <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal || leftIllnessList.includes('1')" value='6'
        id="6" v-model="leftIllnessList">
      <label for="6">高血压</label><br>
      <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal || leftIllnessList.includes('1')" value='7'
        id="7" v-model="leftIllnessList">
      <label for="7">近视</label><br>
      <input type="checkbox" name="leftIllnessList" :disabled="ifLeftNomal || leftIllnessList.includes('1')" value='8'
        id="8" v-model="leftIllnessList">
      <label for="8">其他异常</label><br>
      <div id="lefInput">
        <el-input type="textarea" :rows="3" placeholder="请输入左眼的症状" v-model="leftDiag" class="input"></el-input>
        <el-input type="textarea" :rows="3" placeholder="请输入其他异常病症" v-model="leftOtherIllness" v-show="leftinput">
        </el-input>
      </div>
    </div>
  </div>

  <div class="indexResult">
    <div class="checkbox">
      <p>右眼：</p>
      <el-image
      class="Elimage"
      :src="rightImg"
      fit="cover"
      :preview-src-list="rightImgList"
      >
      <div slot="error" class="image-slot"></div>
    </el-image>
      <p>潜在病症：</p>
      <input type="checkbox" name="rightIllnessList" value='1' id="9" v-model="rightIllnessList"
        @click="handleRightIllnessList">
      <label for="9">正常</label><br>
      <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal || rightIllnessList.includes('1')" value='2'
        id="10" v-model="rightIllnessList">
      <label for="10">糖尿病</label><br>
      <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal || rightIllnessList.includes('1')" value='3'
        id="11" v-model="rightIllnessList">
      <label for="11">青光眼</label><br>
      <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal || rightIllnessList.includes('1')" value='4'
        id="12" v-model="rightIllnessList">
      <label for="12">白内障</label><br>
      <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal || rightIllnessList.includes('1')" value='5'
        id="13" v-model="rightIllnessList">
      <label for="13">AMD</label><br>
      <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal || rightIllnessList.includes('1')" value='6'
        id="14" v-model="rightIllnessList">
      <label for="14">高血压</label><br>
      <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal || rightIllnessList.includes('1')" value='7'
        id="15" v-model="rightIllnessList">
      <label for="15">近视</label><br>
      <input type="checkbox" name="rightIllnessList" :disabled="ifRightNomal || rightIllnessList.includes('1')" value='8'
        id="16" v-model="rightIllnessList">
      <label for="16">其他异常</label><br>

      <div id="rigInput">
        <el-input type="textarea" :rows="3" placeholder="请输入右眼的症状" v-model="rightDiag" class="input"></el-input>
        <el-input type="textarea" :rows="3" placeholder="请输入其他异常病症" v-model="rightOtherIllness" v-show="rightinput">
        </el-input>
      </div>
    </div>
  </div>

  <div>
    <el-input type="textarea" :rows="5" placeholder="本内容由大语言模型生成，仅作参考" v-model="resInfo" class="result">
    </el-input>
  </div>

  <el-button type="primary" :loading="submitting" @click="handleFinalResult" id="submit">
    {{ submitting ? '提交中...' : '提交病例' }}
  </el-button>

</template>

<style scoped>
.indexResult {
  position: relative;
  display: inline-block;
  width: 30vw;
  margin: 3vw;
  padding: 1vw 1vw 3vw 3vw;
  border: 1px solid #ccc;
  border-radius: 10px;
}

.Elimage {
  width: 12vw;
  height: 12vw;
}




.checkbox {
  display: inline-block;
  width: 50%;
}

#lefInput,
#rigInput {
  display: inline-block;
  width: 15vw;
  position: absolute;
  top: 30%;
  left: 50%;
}
.input {
  margin-bottom: 12vw;
}
.result {
  display: inline-block;
  margin-left: 3vw;
  width: 64vw;
}
#submit {
  margin-top: 1vw;
  margin-left: 59vw;
}
</style>
