<script setup>
import * as echarts from "echarts";
import { onMounted } from "vue";
onMounted(() => {
  var stringChart1 = echarts.init(document.getElementById("stringChart1"));
  // 原始数据
const seriesData = [
  { name: '0-20', data: [25, 32, 28, 35, 30, 27, 38, 33, 40, 45] },
  { name: '21-40', data: [160, 185, 170, 195, 180, 165, 200, 190, 210, 230] },
  { name: '41-60', data: [1130, 1222, 1212, 1275, 1289, 1245, 1368, 1421, 1443, 1557] },
  { name: '61-80', data: [1223, 1378, 1328, 1443, 1355, 1269, 1469, 1563, 1547, 1634] },
  { name: '80+', data: [73, 87, 72, 96, 76, 75, 99, 87, 104, 117] }
];

// 按平均值降序排序
const sortedSeries = [...seriesData].sort((a, b) => {
  const avgA = a.data.reduce((sum, val) => sum + val, 0) / a.data.length;
  const avgB = b.data.reduce((sum, val) => sum + val, 0) / b.data.length;
  return avgB - avgA; // 大的在前（底层）
});
  // 绘制图表
  var stringOption1 = {
  title: {
    text: '患者年龄分布'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    data: ['0-20', '21-40', '41-60', '61-80', '80+']
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  toolbox: {
    feature: {
      saveAsImage: {}
    }
  },
  xAxis: {
    type: 'category',
    boundaryGap: false,
    data: ['2015','2016','2017','2018', '2019', '2020', '2021', '2022', '2023', '2024']
  },
  yAxis: {
    type: 'value'
  },
  tooltip: {
      order: 'valueDesc',
      trigger: 'axis'
    },
  series: [
    {
      name: '0-20',
      type: 'line',
      stack: 'Total',
      data: [25, 32, 28, 35, 30, 27, 38, 33, 40, 45]
    },    
    {
      name: '80+',
      type: 'line',
      stack: 'Total',
      data: [73, 87, 72, 96, 76, 75, 99, 87, 104, 117]
    },
    {
      name: '21-40',
      type: 'line',
      stack: 'Total',
      data: [160, 185, 170, 195, 180, 165, 200, 190, 210, 230]
    },
    {
      name: '41-60',
      type: 'line',
      stack: 'Total',
      data: [1130, 1222, 1212, 1275, 1289, 1245, 1368, 1421, 1443, 1557]
    },
    {
      name: '61-80',
      type: 'line',
      stack: 'Total',
      data: [1223, 1378, 1328, 1443, 1355, 1269, 1469, 1563, 1547, 1634]
    }

  ],

  // 增强视觉效果
  emphasis: {
    focus: 'series',
    label: {
      show: true,
      fontSize: 14
    }
  }
};

  stringChart1.setOption(stringOption1);














  
  var binChart1 = echarts.init(document.getElementById("binChart1"));

  var binOption1 = {
    title: {
      text: "患者性别分布",
    },
    tooltip: {},
    series: [
      {
        name: "性别",
        type: "pie",
        data: [
          {
            value: 0.529,
            name: "男性",
            itemStyle: { color: 'rgb(162, 201, 244)' }
          },
          {
            value: 0.471,
            name: "女性",
            itemStyle: { color: 'rgb(255, 180, 130)' }
            
          },
        ],
      },
    ],
  };

  binChart1.setOption(binOption1);

  var barChart2 = echarts.init(document.getElementById("barChart2"));

  // 绘制图表
  var barOption2 = {
    title: {
      text: "疾病分布",
      left: 'center'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: { type: 'shadow' },
      // 自定义悬停内容
    formatter: function(params) {
      // params[0] 是当前悬停的柱子数据
      const data = params[0];
      // 定义自定义标签映射（示例：将 N/D/G 转为完整疾病名称）
      const labelMap = {
        'N': 'N:正常',
        'D': 'D:糖尿病',
        'G': 'G:青光眼',
        'C': 'C:白内障',
        'A': 'A:AMD',
        'H': 'H:高血压',
        'M': 'M:近视',
        'O': 'O:其他疾病/异常'
      };
      // 返回自定义内容
      return `${labelMap[data.name]}<br/>患病人数: ${data.value}`;
    }
    },
    xAxis: {
      data: ["N", "D", "G", "C", "A", "H", "M", "O"],
      axisLabel: { rotate: 45 }
    },
    yAxis: { type: 'value', name: '患病人数' },
    series: [{
      name: "患病人数",
      type: "bar",
      data: [
        { value: 1130, itemStyle: { color: 'rgb(173, 217, 230)' } }, // N - 深蓝
        { value: 510, itemStyle: { color: 'rgb(254, 204, 203)' } },  // D - 橙
        { value: 210, itemStyle: { color: 'rgb(216, 248, 217)' } },  // G - 绿
        { value: 210, itemStyle: { color: 'rgb(255, 255, 203)' } },  // C - 红
        { value: 180, itemStyle: { color: 'rgb(255, 216, 183)' } },  // A - 紫
        { value: 80, itemStyle: { color: 'rgb(231, 204, 255)' } },   // H - 棕
        { value: 190, itemStyle: { color: 'rgb(247, 216, 248)' } },  // M - 粉
        { value: 980, itemStyle: { color: 'rgb(203, 229, 254)' } }   // O - 灰
      ],
      itemStyle: {
        borderRadius: 4,
        borderWidth: 0
      },
      // 正常状态下的标签样式
    label: { 
      show: true, 
      position: 'top',
      color: '#333' // 默认文字颜色
    },
    // 悬停时的配置
    emphasis: {
      focus: 'self', // 只高亮当前柱子（设为 'series' 会高亮所有同系列柱子）
      itemStyle: {
        shadowBlur: 10,
        shadowColor: 'rgba(0, 0, 0, 0.5)'
      },
      label: {
        color: '#c23531', // 悬停时文字变红
        fontWeight: 'bold'
      }
    },
    // 非悬停柱子的样式（淡化效果）
    blur: {
      itemStyle: {
        opacity: 0.2 // 其他柱子透明度降低
      },
      label: {
        opacity: 0.5 // 其他标签透明度降低
      }
    }
  }]
  };

  // 应用配置
  barChart2.setOption(barOption2);

  // 响应式调整
  window.addEventListener('resize', function () {
    barChart2.resize();
  });



  var hitChart1 = echarts.init(document.getElementById("hitChart1"));

  // prettier-ignore
  const data = [
    [0, 0, 21.5], [0, 1, 6.3], [0, 2, 13.9], [0, 3, 38.0], [0, 4, 10.1], [0, 5, 0.0], [0, 6, 3.8], [0, 7, 21.5],
    [1, 0, 33.7], [1, 1, 18.1], [1, 2, 9.6], [1, 3, 9.3], [1, 4, 6.1], [1, 5, 2.4], [1, 6, 6.8], [1, 7, 36.5],
    [2, 0, 45.6], [2, 1, 19.8], [2, 2, 4.9], [2, 3, 3.8], [2, 4, 5.0], [2, 5, 3.3], [2, 6, 4.5], [2, 7, 31.6],
    [3, 0, 51.1], [3, 1, 13.6], [3, 2, 7.8], [3, 3, 4.3], [3, 4, 2.8], [3, 5, 1.4], [3, 6, 5.0], [3, 7, 29.1],
    [4, 0, 21.1], [4, 1, 0], [4, 2, 0], [4, 3, 0], [4, 4, 5.3], [4, 5, 0], [4, 6, 68.4], [4, 7, 10.5]]
    .map(function (item) {
      return [item[1], item[0], item[2] || '-'];
    });
  var hitOption1 = {
    title: { text: "各年龄组疾病分布" },
    tooltip: {
      position: "top",
    },
    grid: {
      height: "50%",
      top: "10%",
    },
    xAxis: {
      type: "category",
      data: ["N", "D", "G", "C", "A", "H", "M", "O"],
      splitArea: {
        show: true,
      },
    },
    yAxis: {
      type: "category",
      data: ["80+", "61-80", "41-60", "21-40", "0-20"],
      splitArea: {
        show: true,
      },
      interval: 0
    },
    visualMap: {
      min: 0,
      max: 60,
      calculable: true,
      orient: "horizontal",
      left: "center",
      bottom: "15%",
      inRange: {
        color: ["#F7A24F", "#C6133B"],
      },
    },
    series: [
      {
        name: "该年龄组的疾病分布",
        type: "heatmap",
        data: data,
        label: {
          show: true,
        },
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowColor: "rgba(0, 0, 0, 0.5)",
          },
        },
      },
    ],
  };

  hitChart1.setOption(hitOption1);

  var hitChart2 = echarts.init(document.getElementById("hitChart2"));

  // 疾病相关性数据
  // prettier-ignore
  // prettier-ignore
  const hitdata2 = [
    [0, 0, -0.57], [1, 0, 0.22], [2, 0, -0.06], [3, 0, -0.11], [4, 0, -0.12], [5, 0, -0.06], [6, 0, -0.04], [7, 0, 1.00],
    [0, 1, -0.20], [1, 1, -0.05], [2, 1, -0.03], [3, 1, -0.07], [4, 1, -0.04], [5, 1, -0.04], [6, 1, 1.00], [7, 1, -0.04],
    [0, 2, -0.13], [1, 2, 0.06], [2, 2, 0.02], [3, 2, -0.03], [4, 2, -0.01], [5, 2, 1.00], [6, 2, -0.04], [7, 2, -0.06],
    [0, 3, -0.20], [1, 3, -0.06], [2, 3, -0.01], [3, 3, -0.07], [4, 3, 1.00], [5, 3, -0.01], [6, 3, -0.04], [7, 3, -0.12],
    [0, 4, -0.23], [1, 4, -0.00], [2, 4, -0.06], [3, 4, 1.00], [4, 4, -0.07], [5, 4, -0.03], [6, 4, -0.07], [7, 4, -0.11],
    [0, 5, -0.23], [1, 5, -0.02], [2, 5, 1.00], [3, 5, -0.06], [4, 5, -0.01], [5, 5, 0.02], [6, 5, -0.03], [7, 5, -0.06],
    [0, 6, -0.38], [1, 6, 1.00], [2, 6, -0.02], [3, 6, -0.00], [4, 6, -0.06], [5, 6, 0.06], [6, 6, -0.05], [7, 6, 0.22],
    [0, 7, 1.00], [1, 7, -0.38], [2, 7, -0.23], [3, 7, -0.23], [4, 7, -0.20], [5, 7, -0.13], [6, 7, -0.20], [7, 7, -0.57]
  ].map(function (item) {
    return [item[0], item[1], item[2] || '-'];
  });

  var hitOption2 = {
    title: { text: "疾病相关性热图" },
    tooltip: {
      position: "top",
    },
    grid: {
      height: "50%",
      top: "10%",
    },
    xAxis: {
      type: "category",
      data: ["N", "D", "G", "C", "A", "H", "M", "O"],
      splitArea: {
        show: true,
      },
    },
    yAxis: {
      type: "category",
      data: ["N", "D", "G", "C", "A", "H", "M", "O"],
      splitArea: {
        show: true,
      },
    },
    visualMap: {
      min: -1,
      max: 1,
      calculable: true,
      orient: "horizontal",
      left: "center",
      bottom: "15%",

      inRange: {
        color: ["#F7A24F", "#C6133B"],
      },
    },
    series: [
      {
        name: "疾病相关性",
        type: "heatmap",
        data: hitdata2,
        label: {
          show: true,
        },
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowColor: "rgba(0, 0, 0, 0.5)",
          },
        },
      },
    ],
  };

  hitChart2.setOption(hitOption2);
});
</script>

<template>
  <div class="char" id="stringChart1"></div>
  <div class="char" id="barChart2"></div>

  <div class="char" id="hitChart1"></div>
  <div class="char" id="hitChart2"></div>
  <div class="char" id="binChart1"></div>
</template>

<style cscoped>
.char {
  width: 500px;
  height: 500px
}
</style>