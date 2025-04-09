<template>
        <div class="container">
          <div class="header">
            <div class="a">
        <div class="b"></div>
        <div class="c"></div>
        <div class="d"></div>
        <div class="e"></div>
        <p class="f">AURIS GLOW</p>
    </div>
          </div>

  

            <div class="text">
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
                    <div class="drawerbox" :class="{ active: chosenSlideNumber === 5 }">
                    <button class="drawer-btn" @click="slideTo(5)">Spirited Away<span class="drawer-head">5</span></button>
                    </div>
                    <div class="drawerbox" :class="{ active: chosenSlideNumber === 6 }">
                    <button class="drawer-btn" @click="slideTo(6)">Spirited Away<span class="drawer-head">6</span></button>
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

                    <div id="card5" class="card" :style="{ transform: `translateY(${offset}%)` }">
                    <div class="card-small-title">Miyazaki Hayao</div>
                    <div class="card-title">Spirited Away</div>
                    <div class="card-content">Quote: "Don't stop, don't be afraid, just keep going, and you will find the answer." Reflection: In the journey of life, we will encounter various difficulties and challenges, but as long as we persevere, fear no hardship, and believe in our own strength, we will find the answers and achieve our dreams.</div>
                    <div class="card-img">
                        <img src="./img/04.gif" alt="">
                    </div>
                    </div>

                    <div id="card6" class="card" :style="{ transform: `translateY(${offset}%)` }">
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
    
    .header{
      
            margin: 0;
            padding: 0;
            height: 200vh;
            overflow-x: hidden;
         
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

    .header{
            display: flex;
            justify-content: center;
            background-image: url("../../assets/image/介绍页背景.jpg");
            height: 100vh;
            width: 80vw;
        }
        .a{
            position: relative;
            top: 100px;
            width: 1000px;
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
   
</style>