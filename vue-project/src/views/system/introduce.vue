<template>
        <div class="container">
          <div class="header" :class="{ hidden: currentPage !== 1 }">
            <div class="a">
        <div class="b"></div>
        <div class="c"></div>
        <div class="d"></div>
        <div class="e"></div>
        <div class="f"><img class="icon" src="../../assets/image/图标无字版.png" alt="">
          <h1>AURIS GLOW</h1>
          <p>Project Overview</p>
        </div>
        
    </div>
          </div>

  

            <div class="text" :class="{ visible: currentPage === 2 }">
            <div id="main">
                <div id="click-section">
                <div id="drawerboxes">
                    <div class="drawerbox" :class="{ active: chosenSlideNumber === 1 }">
                      <button class="drawer-btn" 
         :class="{ active: chosenSlideNumber === 1 }"
         @click="slideTo(1)">Project Overview<span class="drawer-head">1</span></button>
                    </div>
                    <div class="drawerbox" :class="{ active: chosenSlideNumber === 2 }">
                      <button class="drawer-btn" 
         :class="{ active: chosenSlideNumber === 2 }"
         @click="slideTo(2)">Technical Details<span class="drawer-head">2</span></button>
                    </div>
                    <div class="drawerbox" :class="{ active: chosenSlideNumber === 3 }">
                      <button class="drawer-btn" 
         :class="{ active: chosenSlideNumber === 3 }"
         @click="slideTo(3)">Key Features<span class="drawer-head">3</span></button>
                    </div>
                    <div class="drawerbox" :class="{ active: chosenSlideNumber === 4 }">
                      <button class="drawer-btn" 
         :class="{ active: chosenSlideNumber === 4 }"
         @click="slideTo(4)">Applications<span class="drawer-head">4</span></button>
                    </div>
                    <div class="drawerbox" :class="{ active: chosenSlideNumber === 5 }">
                      <button class="drawer-btn" 
         :class="{ active: chosenSlideNumber === 5 }"
         @click="slideTo(5)">Demo<span class="drawer-head">5</span></button>
                    </div>
                    
                </div>
                </div>
                <div id="slide-section">
                <div id="slide-bar">
                    <div id="bar"></div>
                </div>
                <div id="card-section">
                    <div id="card1" class="card" :style="{ transform: `translateY(${offset}%)` }">
                    <div class="card-small-title">Auris Glow</div>
                    <div class="card-title">Project Overview</div>
                    <div class="card-content">

                      <div class="shell">
                            <div class="box">
                                <div class="images">
                                    <img src="../../assets/image/机器办公.png">
                                </div>
                                <div class="content">
                                    <h2>Problem Statement</h2>
                                    <h3>诊断复杂性高：眼底病变常伴随多种疾病并发，需依赖医生经验识别细微病理特征，易受主观判断影响。</h3>
                                      <h3>资源分布不均：优质眼科医疗资源集中于大城市，基层医疗机构缺乏专业设备与专家支持，导致偏远地区筛查覆盖率低。</h3>
                                      <h3>效率与成本问题：传统人工诊断耗时较长，且依赖专家经验，难以满足大规模筛查需求。
                                    </h3>
                                </div>
                            </div>
                            <div class="box">
                                <div class="images">
                                    <img src="../../assets/image/智能方案.png">
                                </div>
                                <div class="content">
                                    <h2>Our Solution</h2>
                                    <!-- <h3>本项目提出基于微调的RET-CLIP多模态模型，针对眼底图像的多标签分类任务进行优化。其核心创新点包括：</h3> -->
                                    <h3>多模态融合：结合视觉（眼底图像）与文本（临床描述）信息，通过双编码器架构（Vision Transformer + RoBERTa）建立跨模态关联，提升复杂病变的识别能力。</h3>
                                    <h3>元数据增强：创新性地整合患者年龄、性别等元数据，通过多层感知机（MLP）编码后与图像特征融合，增强模型对年龄相关疾病（如病理性近视）的敏感性。</h3>
                                  <h3>高效训练策略：采用贝叶斯优化与自适应学习率调度，平衡模型复杂度与计算资源，确保在有限算力下实现高精度</h3>
                                  </div>
                            </div>
                            <div class="box">
                                <div class="images">
                                    <img src="../../assets/image/机器人.png">
                                </div>
                                <div class="content">
                                    <h2>Value Proposition</h2>
                                    <h3>提升诊断效率与准确性：模型在多标签分类任务中表现优异（如糖尿病视网膜病变准确率达95%），减少医生工作负担，降低漏诊率。</h3>
                                    <h3>助力医疗普惠：支持基层医疗机构通过便携设备与云端模型实现远程筛查，推动优质医疗资源下沉，助力健康中国战略。</h3>
                                  <h3>科研与教育价值：通过大规模数据分析发现疾病关联性，为临床研究提供新视角；同时为医学生与基层医生提供标准化培训工具。</h3>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                   
                    </div>
                    <div id="card2" class="card" :style="{ transform: `translateY(${offset}%)` }">
                    <div class="card-small-title">Auris Glow</div>
                    <div class="card-title">Technical Details</div>
                    <div class="card-content">
                      <div class="shell">
                            <div class="box">
                                <div class="images">
                                    <img src="../../assets/image/安全.png">
                                </div>
                                <div class="content">
                                    <h2>Model Introduction</h2>
                                    <h3>RET-CLIP采用了双编码器架构，包含一个图像编码器和一个文本编码器。图像编码器是Vision Transformer (ViT),而文本编码器则是基于Transformer架构的BERT。这两个编码器分别将图像和文本映射到共享的高维特征空间，使得语义相关的图文对在该空间中距离较近，而不相关的图文对距离较远。</h3>
                                </div>
                            </div>
                            <div class="box">
                                <div class="images">
                                    <img src="../../assets/image/智能办公.png">
                                </div>
                                <div class="content">
                                    <h2>Data</h2>
                                    <h3>数据来源：基于国内多家三甲医院合作的匿名化眼底图像数据集（共5,826张），涵盖糖尿病视网膜病变、青光眼、黄斑变性等10类疾病，数据分布兼顾年龄、性别多样性（中位年龄58岁，男性占比52%）。</h3>
                                    <h3>图像增强：通过CLAHE均衡化与BM3D去噪，提升图像对比度并减少噪声。</h3>
                                    <h3>数据清洗：剔除低质量图像（如镜头污点、视盘不可见），保留高质量样本以提高模型鲁棒性。</h3>
                                 
                                  </div>
                            </div>
                            <div class="box">
                                <div class="images">
                                    <img src="../../assets/image/工作.png">
                                </div>
                                <div class="content">
                                    <h2>Performance</h2>
                                    <h3>相比YOLOv5，本模型在各类疾病上表现更均衡（如青光眼95%、正常样本82%），且支持多模态推理。</h3>
                                    
                                </div>
                                </div>
                            </div>
                    </div>
                    
                    </div>
                    <div id="card3" class="card" :style="{ transform: `translateY(${offset}%)` }">
                    <div class="card-small-title">Auris Glow</div>
                    <div class="card-title">Key Features</div>
                    <div class="card-content">
                      
                      <div class="shell">
                            <div class="box">
                                <div class="images">
                                    <img src="../../assets/image/智能7.png">
                                </div>
                                <div class="content">
                                    <h2>Model Introduction</h2>
                                    <h3>同步检测糖尿病视网膜病变、青光眼、黄斑变性、病理性近视等10类眼底疾病，支持并发病例分析。</h3>
                                </div>
                            </div>
                            <div class="box">
                                <div class="images">
                                    <img src="../../assets/image/智能体.png">
                                </div>
                                <div class="content">
                                    <h2>高效响应</h2>
                                    <h3>单张图像分析耗时<2秒，支持每小时处理千级样本，满足基层筛查需求。</h3>
                                    
                                 
                                  </div>
                            </div>
                            <div class="box">
                                <div class="images">
                                    <img src="../../assets/image/AI协作.png">
                                </div>
                                <div class="content">
                                    <h2>动态扩展性</h2>
                                    <h3>基于零样本学习能力，可快速适应新疾病类别（如未标注的罕见病），无需重新标注数据。</h3>
                                    
                                </div>
                                </div>
                            </div>
                          
                          </div>
                    
                    </div>
                    <div id="card4" class="card" :style="{ transform: `translateY(${offset}%)` }">
                    <div class="card-small-title">Auris Glow</div>
                    <div class="card-title">Applications</div>
                    <div class="card-content">
                      <div class="shell">
                            <div class="box">
                                <div class="images">
                                    <img src="../../assets/image/人机交互.png">
                                </div>
                                <div class="content">
                                    <h2>临床辅助诊断</h2>
                                    <h3>帮助医生更快、更准地进行诊断，减少漏诊误诊。</h3>
                                </div>
                            </div>
                            <div class="box">
                                <div class="images">
                                    <img src="../../assets/image/bot.png">
                                </div>
                                <div class="content">
                                    <h2>远程医疗/基层筛查</h2>
                                    <h3>用于眼科学生或初级医生的培训。</h3>
                                    
                                 
                                  </div>
                            </div>
                            <div class="box">
                                <div class="images">
                                    <img src="../../assets/image/智能12.png">
                                </div>
                                <div class="content">
                                    <h2>教育培训</h2>
                                    <h3>基于零样本学习能力，可快速适应新疾病类别（如未标注的罕见病），无需重新标注数据。</h3>
                                    
                                </div>
                                </div>
                            </div>
                    </div>
                    
                    </div>

                    <div id="card5" class="card" :style="{ transform: `translateY(${offset}%)` }">
                    <div class="card-small-title">Auris Glow</div>
                    <div class="card-title">Video</div>
                    <div class="card-content"></div>
                    <div class="card-img">
                        <img class="fengmian" src="../../assets/image/FENGMIAN.png" alt="">
                    </div>
                    </div>

                    
                </div>
                </div>
            </div>
            </div>

          </div>
  </template>
  
  <script setup>
 import { ref, onMounted, onBeforeUnmount } from 'vue';

const currentPage = ref(1); // 当前页面 1-header 2-text
let isScrolling = false;    // 防止滚动冲突

const handleWheel = (e) => {
  if (isScrolling) return;
  isScrolling = true;

  // 判断滚动方向
  const delta = Math.sign(e.deltaY);
  
  if (delta > 0 && currentPage.value === 1) {
    // 向下滚动切换到text
    currentPage.value = 2;
  } else if (delta < 0 && currentPage.value === 2) {
    // 向上滚动切换回header
    currentPage.value = 1;
  }

  setTimeout(() => {
    isScrolling = false;
  }, 800); // 滚动节流时间
};

onMounted(() => {
  window.addEventListener('wheel', handleWheel, { passive: false });
});

onBeforeUnmount(() => {
  window.removeEventListener('wheel', handleWheel);
});
  
  let chosenSlideNumber = ref(1); // 当前选择的幻灯片编号
  let offset = 0; // 幻灯片偏移量
  let barOffset = 0; // 导航条偏移量
  
  const slideTo = (slideNumber) => {
    if (chosenSlideNumber.value === slideNumber) return; // 防止重复点击
  
    const previousSlideNumber = chosenSlideNumber.value;
    chosenSlideNumber.value = slideNumber;
  
    // 更新幻灯片的偏移量
    offset += (slideNumber - previousSlideNumber) * -100;
    barOffset += (slideNumber - previousSlideNumber) * 100;
  
    // 切换幻灯片位置
    const slides = document.querySelectorAll('.card');
    slides.forEach(slide => {
      slide.style.transform = `translateY(${offset}%)`;
    });
  
    // 更新导航条位置
    const bar = document.querySelector('#bar');
    if (bar) {
      bar.style.transform = `translateY(${barOffset}%)`;
    }
  };

  onMounted(() => {
  const background = document.querySelector(".background");

  document.addEventListener('scroll', () => {
    const scrollY = window.scrollY;

    if (scrollY !== 0) {
      background.style.backgroundPosition = `calc(50% + ${scrollY}px) calc(50% + ${scrollY}px)`;
    } else {
      background.style.backgroundPosition = '';
    }
  });
});



  // const background = document.querySelector(".background")

  //       document.addEventListener('scroll', () => {
  //           const scrollY = window.scrollY

  //           if (scrollY !== 0) {
  //               background.style.backgroundPosition = `calc(50% + ${scrollY}px) calc(50% + ${scrollY}px)`
  //           }else{
  //               background.style.backgroundPosition = ''
  //           }
  //       })
  </script>
  

  

<style scoped>
* {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
      
    }

    body {
      display: flex;
      justify-content: center;
      align-items: center;
      overflow-x: hidden;
      
      height: 100vh;
      background: linear-gradient(to right bottom,
          rgba(255, 255, 255,.6),
          rgba(255, 255, 255,.3),
          rgba(255, 255, 255,.2)),;
      background-size: cover;
    }
    
    /* .header{
      
            margin: 0;
            padding: 0;
            height: 200vh;
            overflow-x: hidden;
         
    } */

  


    #main {
      
      height: 100vh;
      width: 100vw;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    #click-section {
      width: 20%;
      height: 100%;
      padding: 20px 0;
      position: relative;
    }

    #drawerboxes {
      margin-left: 10%;
      width: 100%;
      height: 100%;
      position: relative;
      overflow: hidden;
      display: flex;
      flex-direction: column;
      justify-content: space-evenly;
    }

    #drawerboxes::before {
      content: "";
      width: 8px;
      height: 94%;
      position: absolute;
      left: -10px;
      top: 3%;
      z-index: 120;
    }

    .drawerbox {
      height: calc(100% / 5.5);
      width: 70%;
      position: relative;
      z-index: 100;
      transform: translateX(-70%);
      transition: transform .5s ease-in-out;
    }

    .drawerbox.active {
      transform: translateX(0);
    }

    .drawer-btn {
      user-select: none;
      width: 100%;
      height: 100%;
      font: 800 30px '';
      background-color: rgb(175, 190, 255);
      border: none;
      transition: background-color .5s ease-in-out;
      color: #ffffff00;
      
    }

    /* .drawer-btn.active {
      
      background-size: cover;
      color: rgb(70, 100, 180);
      z-index: 1010;
    } */

    :deep(.drawer-btn.active) {
      user-select: none;
  background-color: #626AC2 !important;
  color: #ffffff00 !important;
  }

    .drawer-btn:hover {
      cursor: pointer;
    }

    .drawer-head {
      position: absolute;
      color: rgb(255, 255, 255);
      font-size: 200px;
      font-weight: 700;
      right: -38px;
      top: calc(50% - 135px);
      text-shadow: 2px -1px 8px rgba(250, 80, 193, 0.323);
    }

    #slide-section {
      user-select: none;
      position: relative;
      height: 100%;
      width: 100%;
      display: flex;
      justify-content: center;
      padding: 0 40px;
      background: linear-gradient(to right bottom,
          rgba(255, 255, 255, .6),
          rgba(255, 255, 255, .3),
          rgba(255, 255, 255, .2));
      backdrop-filter: blur(11px);
    }

    #slide-bar {
      user-select: none;
      position: absolute;
      top: 10%;
      left: 40px;
      height: 80%;
      width: 1px;
      background-color: rgb(223, 223, 223);
    }

    #bar {
      user-select: none;
      position: absolute;
      height: calc(100% / 4);
      width: 5px;
      top: 0;
      left: -1.2px;
      background-color: rgb(175, 190, 255);
      transition: transform .5s ease-in-out;
    }

    #card-section {
      height: 100%;
      width: 95%;
      overflow: hidden;
    }

    .card {
      user-select: none;
      display: flex;
      flex-direction: column;
      height: 100%;
      width: 100%;
      padding: 10% 0;
      color: white;
      font-size: 30px;
      transition: transform .5s ease-in-out;
    }

    .card-small-title {
      user-select: none;
      font-size: 30px;
      font-weight: 600;
      padding-bottom: min(5%, 10px);
      color: rgb(70, 100, 180);
    }

    .card-title {
      user-select: none;
      font-size: 80px;
      font-weight: 700;
      padding-bottom: min(20%, 40px);
      color: rgb(70, 100, 180);
    }

    .card-content {
      user-select: none;
      font-size: 24px;
      font-weight: 400;
      color: rgb(94, 123, 255);
      margin-bottom: 60px;
      display: flex;
            justify-content: center;
            align-items: center;
    }

    .card-img {
      width: 100%;
      height: 1000px;
      
      margin-left:-30px;
    }

    

    .container {
  height: 300vh;
  overflow: hidden;
}

.header {
  position: fixed;
  display: flex;
            justify-content: center;
            background-image: url("../../assets/image/介绍页背景.jpg");
            height: 100vh;

            width: 90vw;
  transition: transform 0.8s cubic-bezier(0.4, 0, 0.2, 1);
  overflow: hidden;
}

.header.hidden {
  transform: translateY(-110%);
}

.text {
  user-select: none;
  position: fixed;
  top: 110vh;
  
  width: 100%;
  height: 100vh;
  transition: transform 0.8s cubic-bezier(0.4, 0, 0.2, 1);
}

.text.visible {
  transform: translateY(-110%);
  
}

    /* .header{
            display: flex;
            justify-content: center;
            background-image: url("../../assets/image/介绍页背景.jpg");
            height: 100vh;
            
        } */
        .a{
          user-select: none;
            position: relative;
            top: 100px;
            width: 70vw;
            height: 600px;
            background-image: url("163727-15190294471b41.jpg");
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: hidden;
        }
        .b,
        .c,
        .d,
        .e{
          user-select: none;
            position: absolute;
            width: 1000px;
            height: 600px;
            /* 设置阴影 */
            filter: drop-shadow(4px 4px 12px rgba(0,0,0,.5));
            background-size: cover;
            opacity: .7;
            transition: .5s;
        }
        .b::after,
        .c::after,
        .d::after,
        .e::after{
          user-select: none;
            content: "";
            position: absolute;
            width: 1000px;
            height: 600px;
            background-image: url("../../assets/image/介绍页背景.jpg");
        }
        .b{
            left: -400px;
            transform: rotateZ(100deg);
            overflow: hidden;
        }
        .b::after{
            transform: rotateZ(-100deg);
        }

        .c{
            left: -400px;
            transform: rotateZ(-100deg);
            overflow: hidden;
        }
        .c::after{
            transform: rotateZ(100deg);
        }

        .d{
            right: -400px;
            transform: rotateZ(105deg);
            overflow: hidden;
        }
        .d::after{
            transform: rotateZ(-105deg);
        }

        .e{
            right: -400px;
            transform: rotateZ(-100deg);
            overflow: hidden;
        }
        .e::after{
            transform: rotateZ(100deg);
        }
        .f{
            opacity: 0;
            font: 900 50px '';
            /* 设置字体间距 */
            letter-spacing: 10px;
            color: rgb(60,60,70);
            transition: .5s;
        }
        /* 设置动画 */
        .a:hover .b{
            left: -550px;
        }
        .a:hover .c{
            left: -600px;
        }
        .a:hover .d{
            right: -550px;
        }
        .a:hover .e{
            right: -610px;
        }
        .a:hover .f{
            opacity: 1;
        }

        .icon{
          user-select: none;
          height: 25vh;
          justify-items: center;
          
        }

        h1{
          user-select: none;
          margin-top:-50px;
          font-size: 50px;
          color: rgb(60,60,70);
          text-align: center;
          z-index: 1000;
        }

        p{
          user-select: none;
          font-size: 30px;
          text-align: center;
          letter-spacing: 3px;
        }
   
/* 卡片悬浮 */

        .shell {
            position: relative;
            min-width: 1000px;
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            /* 设置3d变化 */
            transform-style: preserve-3d;
            /* 添加透视效果 */
            perspective: 900px;
            margin-top: 50px;
            margin-left: -50px;
        }

        .shell .box {
            position: relative;
            width: 250px;
            height: 350px;
            transition: 0.3s;
            overflow: hidden;
            margin: 30px;
            transform: rotateY(0deg);
            transition-delay: .1s;
            border-radius: 5px;
            border: #fff 5px solid;
        }

        /* 当鼠标所有卡片的父元素点shell上时，
        所有卡片像Y轴翻转20度 */
        .shell:hover .box {
            transform: rotateY(20deg);
        }

        /* 当鼠标悬浮在当前卡片上时，当前卡片的翻转角度为0，
        并放大一点二五倍，加个阴影 */
        .shell .box:hover {
            transform: rotateY(0deg) scale(1.25);
            box-shadow: 0 25px 40px rgba(0, 0, 0, 0.7);
            z-index: 1;
        }

        /* 当鼠标悬浮在卡片上时，
        当前卡片后面的所有卡片全部都反向翻转负20度 */
        .shell .box:hover~.box {
            transform: rotateY(-20deg);
        }

        .shell .box .images img {
          margin-top:50px;
            width: 100%;
        }

        .shell .box .content {
            position: absolute;
            top: 0;
            width: 95%;
            height: 100%;
            z-index: 999;
            padding: 15px;
            overflow: auto;
            overflow-y: hidden;
        }

        .shell .box .content h2 {
            color: black;
            transition: 0.3s;
            font-size: 20px;
            transform: translateY(-100px);
        }

        .shell .box:hover .content h2 {
            transform: translateY(-5px);
        }

        .shell .box .content h3 {
            color: rgb(0, 0, 0);
            transition: 0.3s;
            font-size: 14px;
            transform: translateY(600px);
            background-color: rgba(255, 255, 255, 0.7);
            overflow: auto;
            font-family: kaiti;
        }

        .shell .box:hover .content h3 {
            transform: translateY(30px);
        }

        @font-face {
          font-family: kaiti;
          src: url(../../assets/font/汉仪全唐诗简.ttf);
        }

        .fengmian{
          
          height: 50vh;
        }

        .card-img img{
          margin-top:-60px;
          margin-left:80px;
          width: 80%;
          height: 55vh;
        }

</style>