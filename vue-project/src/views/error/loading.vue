<!-- src/components/common/LoadingPage.vue -->
<!-- <template>
    <div class="loading-container" :style="{ background }">
      <div class="loading-content">
        <el-icon class="loading-icon" :style="{ color, fontSize: size }">
          <LoadingIcon />
        </el-icon>
        <div class="loading-text" :style="{ color }">{{ text }}</div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { Loading as LoadingIcon} from '@element-plus/icons-vue'
  
  const props = defineProps({
    text: {
      type: String,
      default: '数据加载中...'
    },
    color: {
      type: String,
      default: '#409EFF'
    },
    size: {
      type: String,
      default: '40px'
    },
    background: {
      type: String,
      default: 'rgba(255, 255, 255, 0.9)'
    }
  })


  </script>
  
  <style scoped>
  .loading-container {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 9999;
  }
  
  .loading-content {
    text-align: center;
  }
  
  .loading-icon {
    animation: rotating 2s linear infinite;
  }
  
  .loading-text {
    margin-top: 15px;
    font-size: 14px;
  }
  
  @keyframes rotating {
    from { transform: rotate(0deg); }
    to { transform: rotate(360deg); }
  }
  </style> -->