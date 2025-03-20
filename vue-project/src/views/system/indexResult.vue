<script setup>
import { ref, onMounted, computed, watch } from 'vue'
import { useRoute } from 'vue-router';
import router from '../../router';
import { ElImage, ElLoading, ElMessage } from 'element-plus'
import api from '../../api/index'
import { nextTick } from 'vue';
// 删除不必要的 `await nextTick();`



// 接收数据
const route = useRoute()
// 保存 id
const id = route.query.id;

const idInt = parseInt(id, 10)
console.log("当前dddddd ID:", id)

const loading = ref(null); // 用于存储加载动画的实例

// 图片初始化
const leftImg = ref('')
const rightImg = ref('')

// 图片大图阅览
// const leftImgList = ref([leftImg.value])
// const rightImgList = ref([rightImg.value])

const leftImgList = computed(() => [leftImg.value]);
const rightImgList = computed(() => [rightImg.value]);


watch(leftImg, (newVal) => {
  leftImgList.value = [newVal];
});
watch(rightImg, (newVal) => {
  rightImgList.value = [newVal];
});

// 检测病症初始化
const leftIllnessListInter = ref([])
const rightIllnessListInter = ref([])

// 是否是正常的
// const ifLeftNomal = computed(() => leftIllnessListInter.value.includes('1'))
// const ifRightNomal = computed(() => rightIllnessListInter.value.includes('1'))

// 其他异常病症
const leftOtherIllness = ref('')
const rightOtherIllness = ref('')

// 是否有输入栏
const leftinput = ref(false);
const rightinput = ref(false);


// const leftinput = computed(() => leftIllnessListInter.value.includes('8'));
// const rightinput = computed(() => rightIllnessListInter.value.includes('8'));

// const leftNoNomal = () => {
//   if (leftIllnessListInter.value.includes('1')) {
//     leftIllnessListInter.value.splice(leftIllnessListInter.value.indexOf('1'), 1)
//   }
//   if (leftIllnessListInter.value.includes('8')) {
//     leftinput.value = true
//   } else {
//     leftinput.value = false
//   }
// }

// const rightNoNomal = () => {
//   if (rightIllnessListInter.value.includes('1')) {
//     rightIllnessListInter.value.splice(rightIllnessListInter.value.indexOf('1'), 1)
//   }
//   if (rightIllnessListInter.value.includes('8')) {
//     rightinput.value = true
//   } else {
//     rightinput.value = false
//   }
// }

const leftNoNomal = () => {
  if (leftIllnessListInter.value.includes(1)) {
    leftIllnessListInter.value.splice(leftIllnessListInter.value.indexOf(1), 1)
  }
  if (leftIllnessListInter.value.includes(1)) {
    leftinput.value = true
  } else {
    leftinput.value = false
  }
}

const rightNoNomal = () => {
  if (rightIllnessListInter.value.includes(1)) {
    rightIllnessListInter.value.splice(rightIllnessListInter.value.indexOf(1), 1)
  }
  if (rightIllnessListInter.value.includes(8)) {
    rightinput.value = true
  } else {
    rightinput.value = false
  }
}

const leftIfNomal = () => {
  if (leftIllnessListInter.value.includes(1)) {
    leftIllnessListInter.value = [1]
  }
  if (leftIllnessListInter.value.includes(8)) {
    leftinput.value = true
  } else {
    leftinput.value = false
  }
}

const rightIfNomal = () => {
  if (rightIllnessListInter.value.includes(1)) {
    rightIllnessListInter.value = [1]
  }
  if (rightIllnessListInter.value.includes(8)) {
    rightinput.value = true
  } else {
    rightinput.value = false
  }
}





// 诊断关键字
const leftDiag = ref('')
const rightDiag = ref('')

// 输入栏内容初始化
const resInfo = ref('')

// 提交按钮加载控制初始化
const submitting = ref(false)

onMounted(async () => {
  console.log("当前 ID1:", id)
  // console.log("当前 ID:", idInt)

  loading.value = ElLoading.service({
    text: '加载中...',
    background: 'rgba(0, 0, 0, 0.7)', // 背景颜色
    spinner: 'el-icon-loading', // 自定义加载图标
    target: document.body, // 指定加载动画覆盖的区域
  });
  console.log("当前 ID2:", id)
  try {
    // 使用 await 等待数据返回
    console.log("当前 ID3:", id)
    console.log(typeof idInt)
    console.log("当前 IDint:", idInt )
    const response = await api.CheckPatientFir(idInt);
    console.log("Response:", response);
    console.log("当前 ID4:", id)
    console.log("firresponse:", response)
    // 确保返回的数据结构正确
    console.log(response.data?.code)
    if (response.data?.code === 1) {
      leftImg.value = response.data.data?.leftImg
      rightImg.value = response.data.data?.rightImg

      // leftIllnessListInter.value = response.data.leftStatusIllList
      // rightIllnessListInter.value = response.data.rightStatusIllList
      // 改为string类型
      console.log("leftIllnessListInter:", leftIllnessListInter.value)
      console.log("rightIllnessListInter:", rightIllnessListInter.value)
      console.log("当前 ID:", id)
      leftIllnessListInter.value = response.data.data?.leftStatusIllList
      rightIllnessListInter.value = response.data.data?.rightStatusIllList
/*       leftIllnessListInter.value = (response.data.data?.leftStatusIllList || []).map(item => String(item));
      rightIllnessListInter.value = (response.data.data?.rightStatusIllList || []).map(item => String(item)); */
      console.log("leftIllnessListInter:", leftIllnessListInter.value)
      console.log("rightIllnessListInter:", rightIllnessListInter.value)
      console.log("当前 ID:", id)
      await Vue.nextTick();
      // if (leftIllnessListInter.value.includes('8')){
      //   leftinput.value = true
      // } else {
      //   leftinput.value = false
      // }
      // if (rightIllnessListInter.value.includes('8')){
      //   rightinput.value = true
      // } else {
      //   rightinput.value = false
      // }


      // 改为手动赋值
      // leftDiag.value = response.data.leftDiag
      // rightDiag.value = response.data.rightDiag

      resInfo.value = response.data.data?.resInfo
    } else {
      console.error(response.msg)
    }
  } catch (error) {
    ElMessage.error("请求失败",error)
  } finally {
    loading.value.close();
  }
})
// 最后提交按钮点击事件
const handleFinalResult = async () => {
  submitting.value = true;
/*   leftIllnessListInter.value = (leftIllnessListInter || [])
  .map(item => parseInt(item)); // 或者使用 Number(item) 或 +item
  rightIllnessListInter.value = (rightIllnessListInter || [])
  .map(item => parseInt(item)); // 或者使用 Number(item) 或 +item */
  const resultData = {
    id: idInt,
    leftStatusIllList: leftIllnessListInter.value,
    rightStatusIllList: rightIllnessListInter.value,
    leftDiag: leftDiag.value,
    rightDiag: rightDiag.value,
    leftIllInfo: leftOtherIllness.value,
    rightIllInfo: rightOtherIllness.value,
    resInfo: resInfo.value,
  };


  try {
    const res = await api.UploadAddPatient(resultData);
    console.log(res);
    if (res.data?.code === 1) {
      ElMessage.success('提交成功');
      router.push({ path: `/home` });
    } else {
      ElMessage.error(res.msg);
    }
  } catch (error) {
    ElMessage.error('服务器繁忙，提交失败');
  } finally {
    submitting.value = false;
  }
};

// const handleFinalResult = async () => {
//   submitting.value = true
//     // 请求提交病例改动
//     console.log("endid:",id)
//     console.log(idInt)
//     console.log(typeof idInt)
//     const resultdata = {
//       id: id,
//       leftStatusIllList: leftIllnessListInter.value,
//       rightStatusIllList: rightIllnessListInter.value,
//       leftDiag: leftDiag.value,
//       rightDiag: rightDiag.value,
//       leftIllInfo: leftOtherIllness.value,
//       rightIllInfo: rightOtherIllness.value,
//       resInfo: resInfo.value,
//     }
  
//   try {
//     // 开始加载动画
//     const res = await api.UploadAddPatient(resultdata)
//     console.log(res)
//     if (res.code === 1){
//     ElMessage.success('提交成功')
//     console.log(id)
//     // 跳转
//     router.push({
//       path: `/homeResult`,
//     })
//     } else {
//       ElMessage.error(res.msg)
//     }
//   } catch (error) {
//     ElMessage.error('服务器繁忙，提交失败')
//     console.error("请求失败:", error)
//   }
//   finally {
//     submitting.value = false
//   }
// }



/* watch(leftIllnessListInter, (newVal) => {
  if (newVal.includes('1')) {
    // 如果包含正常选项，则过滤掉其他选项
    leftIllnessListInter.value = ['1']
  }
}, { deep: true })

watch(rightIllnessListInter, (newVal) => {
  if (newVal.includes('1')) {
    // 如果包含正常选项，则过滤掉其他选项
    rightIllnessListInter.value = ['1']
  }
}, { deep: true }) */

// const leftinput = computed(() => {
//   for (let i = 0; i < leftIllnessListInter.value.length; i++) {
//     if (leftIllnessListInter.value[i] === "8") {
//       return true
//     }
//   }
//   return false
// })
// const rightinput = computed(() => {
//     for (let i = 0; i < rightIllnessListInter.value.length; i++) {
//       if (rightIllnessListInter.value[i] === "8") {
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
    leftIllnessListInter.value = response.data.data.leftStatusIllList
    rightIllnessListInter.value = response.data.data.rightStatusIllList

    // 判断是否需要显示其他异常病症的输入栏
    // leftinput =ref(leftIllnessListInter.includes('8'))
    // rightinput =ref(rightIllnessListInter.includes('8'))

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

//       leftIllnessListInter.value = response.data.leftStatusIllList
//       rightIllnessListInter.value = response.data.rightStatusIllList

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
// watch(() => leftIllnessListInter.value, (newValue, oldValue) => {
//   if (newValue.includes('8')) {
//     leftinput.value = true
//   } else {
//     leftinput.value = false
//   }
// },
// { immediate: true , deep:true}
// )
// watch(() => rightIllnessListInter.value, (newValue, oldValue) => {
//   if (newValue.includes('8')) {
//     rightinput.value = true
//   } else {
//     rightinput.value = false
//   }
// },
// { immediate: true , deep:true}
// )

// watch(() => leftIllnessListInter.value, (newValue,oldValue) => {
//   if (newValue.includes('1')) {
//         // 如果选中了“正常”，则清空其他选项
//         // leftIllnessListInter.value = ['1'];
//         ifLeftNomal.value = true
//       } else {
//         ifLeftNomal.value = false
//       }
//     },
//     { immediate: true , deep:true}
// );

// watch(() => rightIllnessListInter.value, (newValue,oldValue) => {
//       if (newValue.includes('1')) {
//         // 如果选中了“正常”，则清空其他选项
//         // rightIllnessListInter.value = ['1'];
//         ifRightNomal.value = true
//       } else {
//         ifRightNomal.value = false
//       }
//     },
//     { immediate: true , deep:true}
// );

// const handleLeftIllnessList = () => {
//   if (leftIllnessListInter.value.includes('1')) {
//     // 如果选中了“正常”，则清空其他选项
//     // leftIllnessListInter.value = ['1'];
//     ifLeftNomal.value = true
//   } else {
//     ifLeftNomal.value = false
//   }
// }
// const handleRightIllnessList = (e) => {
//   if (rightIllnessListInter.value.includes('1')) {
//     // 如果选中了“正常”，则清空其他选项
//     // rightIllnessListInter.value = ['1'];
//     ifRightNomal.value = true
//   } else {
//     ifRightNomal.value = false
//   }
// }

</script>


<template>
  <div class="indexResult">
    <div class="checkbox">
      <p>左眼：</p>
      <el-image :src="leftImg" fit="cover" :preview-src-list="leftImgList" class="Elimage">
        <div class="image-slot"><i class="el-icon-picture-outline"></i></div>
      </el-image>
      <el-checkbox-group v-model="leftIllnessListInter" :min="1">
    <el-checkbox :value="1" @change="leftIfNomal">正常</el-checkbox><br>
    <el-checkbox :value="2" @change="leftNoNomal">糖尿病</el-checkbox><br>
    <el-checkbox :value="3" @change="leftNoNomal">青光眼</el-checkbox><br>
    <el-checkbox :value="4" @change="leftNoNomal">白内障</el-checkbox>
    <el-checkbox :value="5" @change="leftNoNomal">AMD</el-checkbox>
    <el-checkbox :value="6" @change="leftNoNomal">高血压</el-checkbox>
    <el-checkbox :value="7" @change="leftNoNomal">近视</el-checkbox>
    <el-checkbox :value="8" @change="leftNoNomal">其他异常</el-checkbox>
  </el-checkbox-group>
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
      <el-image class="Elimage" :src="rightImg" fit="cover" :preview-src-list="rightImgList">
        <div class="image-slot"></div>
      </el-image>
      <el-checkbox-group v-model="rightIllnessListInter" :min="1">
    <el-checkbox :value="1" @change="rightIfNomal">正常</el-checkbox><br>
    <el-checkbox :value="2" @change="rightNoNomal">糖尿病</el-checkbox><br>
    <el-checkbox :value="3" @change="rightNoNomal">青光眼</el-checkbox><br>
    <el-checkbox :value="4" @change="rightNoNomal">白内障</el-checkbox>
    <el-checkbox :value="5" @change="rightNoNomal">AMD</el-checkbox>
    <el-checkbox :value="6" @change="rightNoNomal">高血压</el-checkbox>
    <el-checkbox :value="7" @change="rightNoNomal">近视</el-checkbox>
    <el-checkbox :value="8" @change="rightNoNomal">其他异常</el-checkbox>
  </el-checkbox-group>
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




