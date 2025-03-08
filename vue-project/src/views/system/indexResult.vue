<script setup>
import { ref, onMounted, computed } from 'vue'
import router from '../../router';

// 接收数据
const phone = computed(() => router.query.phone)
const idcard = computed(() => router.query.idcard)
const leftImg = computed(() => router.query.leftImg)
const rightImg = computed(() => router.query.rightImg)

// 手动快速添加病症
const leftIllness = ref([])
const rightIllness = ref([])

// 输入栏内容
const textarea = ref('')

// 提交按钮加载控制
const submitting = ref(false)

// 最后提交按钮点击事件
const handleFinalResult = () => {
    // 开始加载动画
    submitting.value = true


}
</script>


<template>
    <div class="indexResult">
        <p>左眼：</p>
        <img :src="leftImg" alt="左眼图片损毁">
        <p>潜在病症：</p>
        <input type="checkbox" name="leftIllness" value="1" id="1" v-model="leftIllness">
        <label for="1">1号病症</label>
        <input type="checkbox" name="leftIllness" value="2" id="2" v-model="leftIllness">
        <label for="2">2号病症</label>
        <input type="checkbox" name="leftIllness" value="3" id="3" v-model="leftIllness">
        <label for="3">3号病症</label>
        <input type="checkbox" name="leftIllness" value="4" id="4" v-model="leftIllness">
        <label for="4">4号病症</label>
        <input type="checkbox" name="leftIllness" value="5" id="5" v-model="leftIllness">
        <label for="5">5号病症</label>
        <input type="checkbox" name="leftIllness" value="6" id="6" v-model="leftIllness">
        <label for="6">6号病症</label>
        <input type="checkbox" name="leftIllness" value="7" id="7" v-model="leftIllness">
        <label for="7">7号病症</label>
    </div>

    <div class="indexResult">
        <p>右眼：</p>
        <img :src="rightImg" alt="右眼图片损毁">
        <p>潜在病症：</p>
        <input type="checkbox" name="rightIllness" value="1" id="1" v-model="rightIllness">
        <label for="1">1号病症</label>
        <input type="checkbox" name="rightIllness" value="2" id="2" v-model="rightIllness">
        <label for="2">2号病症</label>
        <input type="checkbox" name="rightIllness" value="3" id="3" v-model="rightIllness" />
        <label for="3">3号病症</label>
        <input type="checkbox" name="rightIllness" value="4" id="4" v-model="rightIllness">
        <label for="4">4号病症</label>
        <input type="checkbox" name="rightIllness" value="5" id="5" v-model="rightIllness">
        <label for="5">5号病症</label>
        <input type="checkbox" name="rightIllness" value="6" id="6" v-model="rightIllness">
        <label for="6">6号病症</label>
        <input type="checkbox" name="rightIllness" value="7" id="7" v-model="rightIllness">
        <label for="7">7号病症</label>
    </div><br>

    <div>
        <el-input type="textarea" :rows="5" placeholder="本内容由大语言模型生成，仅作参考" v-model="textarea">
        </el-input>
    </div>

    <el-button type="primary" :loading="submitting" @click="handleFinalResult"> 
        {{ submitting ? '提交中...' : '提交病例' }}
    </el-button>

</template>

<style scoped>
img {
    width: 150px;
    height: 150px;
}

.indexResult {
    display: inline-block;
    width: 35vw;
    margin: 3vw;
    padding: 3vw;
    border: 1px solid #ccc;
    border-radius: 10px;
}
</style>