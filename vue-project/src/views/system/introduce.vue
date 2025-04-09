<template>
        
            <div>
            <div id="main">
                <div id="click-section">
                <div id="drawerboxes">
                    <div class="drawerbox" :class="{ active: chosenSlideNumber === 1 }">
                    <button class="drawer-btn" @click="slideTo(1)">The Wind Rises<span class="drawer-head">1</span></button>
                    </div>
                    <div class="drawerbox" :class="{ active: chosenSlideNumber === 2 }">
                    <button class="drawer-btn" @click="slideTo(2)">Children of the Wind<span class="drawer-head">2</span></button>
                    </div>
                    <div class="drawerbox" :class="{ active: chosenSlideNumber === 3 }">
                    <button class="drawer-btn" @click="slideTo(3)">Castle in the Sky<span class="drawer-head">3</span></button>
                    </div>
                    <div class="drawerbox" :class="{ active: chosenSlideNumber === 4 }">
                    <button class="drawer-btn" @click="slideTo(4)">Spirited Away<span class="drawer-head">4</span></button>
                    </div>
                </div>
                </div>
                <div id="slide-section">
                <div id="slide-bar">
                    <div id="bar"></div>
                </div>
                <div id="card-section">
                    <div id="card1" class="card" :style="{ transform: `translateY(${offset}%)` }">
                    <div class="card-small-title">Miyazaki Hayao</div>
                    <div class="card-title">The Wind Rises</div>
                    <div class="card-content">Quote: "Even in the dark night, the stars will not disappear." Reflection: In the journey of life, we may encounter storms and difficulties, but as long as we have faith and hope in our hearts, we can find light even in the darkness.</div>
                    <div class="card-img">
                        <img src="./img/17.gif" alt="">
                    </div>
                    </div>
                    <div id="card2" class="card" :style="{ transform: `translateY(${offset}%)` }">
                    <div class="card-small-title">Miyazaki Hayao</div>
                    <div class="card-title">Children of the Wind</div>
                    <div class="card-content">Quote: "The wind is calling, to fly freely." Reflection: The wind symbolizes freedom and inspiration. We should bravely follow the voice of our hearts, break through all limitations, and pursue our dreams. </div>
                    <div class="card-img">
                        <img src="./img/08.gif" alt="">
                    </div>
                    </div>
                    <div id="card3" class="card" :style="{ transform: `translateY(${offset}%)` }">
                    <div class="card-small-title">Miyazaki Hayao</div>
                    <div class="card-title">Castle in the Sky</div>
                    <div class="card-content">Quote: "At the end of the sky, there is another world." Reflection: Sometimes we may feel lost and confused, but as long as we maintain faith and hope, we will surely find our own piece of sky and achieve our dreams. </div>
                    <div class="card-img">
                        <img src="./img/03.gif" alt="">
                    </div>
                    </div>
                    <div id="card4" class="card" :style="{ transform: `translateY(${offset}%)` }">
                    <div class="card-small-title">Miyazaki Hayao</div>
                    <div class="card-title">Spirited Away</div>
                    <div class="card-content">Quote: "Don't stop, don't be afraid, just keep going, and you will find the answer." Reflection: In the journey of life, we will encounter various difficulties and challenges, but as long as we persevere, fear no hardship, and believe in our own strength, we will find the answers and achieve our dreams.</div>
                    <div class="card-img">
                        <img src="./img/04.gif" alt="">
                    </div>
                    </div>
                </div>
                </div>
            </div>
            </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  
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
      
      background-size: cover;
    }

    #main {
      
      height: 100vh;
      width: 100vw;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    #click-section {
      width: 35%;
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
      width: 100%;
      height: 100%;
      font: 800 30px '';
      background-color: rgb(175, 190, 255);
      border: none;
      transition: background-color .5s ease-in-out;
      color: #ffffff00;
    }

    .drawer-btn.active {
      
      background-size: cover;
      color: rgb(70, 100, 180);
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
      position: relative;
      height: 100%;
      width: 65%;
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
      position: absolute;
      top: 10%;
      left: 40px;
      height: 80%;
      width: 1px;
      background-color: rgb(223, 223, 223);
    }

    #bar {
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
      width: 80%;
      overflow: hidden;
    }

    .card {
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
      font-size: 30px;
      font-weight: 600;
      padding-bottom: min(5%, 10px);
      color: rgb(70, 100, 180);
    }

    .card-title {
      font-size: 80px;
      font-weight: 700;
      padding-bottom: min(20%, 40px);
      color: rgb(70, 100, 180);
    }

    .card-content {
      font-size: 24px;
      font-weight: 400;
      color: rgb(94, 123, 255);
      margin-bottom: 60px;
    }

    .card-img {
      width: 100%;
      height: 400px;
      overflow: hidden;
    }

    .card-img img {
      width: 100%;
    }
</style>