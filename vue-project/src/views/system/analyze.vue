<script setup>
import * as echarts from 'echarts';
import { onMounted } from 'vue';
onMounted(() => {
var barChart1 = echarts.init(document.getElementById('barChart1'));
// 绘制图表
var barOption1 = {
        title: {
          text: '患者年龄分布'
        },
        tooltip: {},
        legend: {
          data: ['年龄']
        },
        xAxis: {
          data: ['0-20', '21-40', '41-60', '61-80', '80+']
        },
        yAxis: {},
        series: [
          {
            name: '年龄',
            type: 'bar',
            data: [30, 180, 1260, 1360, 80]
          }
        ]
      };

barChart1.setOption(barOption1)



var binChart1 = echarts.init(document.getElementById('binChart1'));

var binOption1 = {
    title: {
      text: '患者性别分布'
    },
    tooltip: {},
  series: [
    {
    name: '性别',
    type: 'pie',
    data: [
        {
          value: 0.529,
          name: '男性'
        },
        {
          value: 0.471,
          name: '女性'
        }
      ]
    }
  ]
};

binChart1.setOption(binOption1)


var barChart2 = echarts.init(document.getElementById('barChart2'));
// 绘制图表
var barOption2 = {
        title: {
          text: '疾病分布'
        },
        tooltip: {},

        xAxis: {
          data: ['N', 'D', 'G', 'C', 'A', 'H', 'M', 'O']
        },
        yAxis: {},
        series: [
          {
            name: '患病人数',
            type: 'bar',
            data: [1130, 510, 210, 210, 180, 80, 190, 980]
          }
        ]
      };

barChart2.setOption(barOption2)



var barChart3 = echarts.init(document.getElementById('barChart3'));
// 绘制图表
var barOption3 = {
        title: {
          text: '左右眼诊断一致性'
        },
        tooltip: {},

        xAxis: {
          data: ['N', 'D']
        },
        yAxis: {
            name: '百分比 (%)'
        },
        series: [
          {
            name: '年龄',
            type: 'bar',
            data: [0.35, 0.65]
          }
        ]
      };

barChart3.setOption(barOption3)


var hitChart1 = echarts.init(document.getElementById('hitChart1'));



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
  title: { text: '各年龄组疾病分布' },
  tooltip: {
    position: 'top'
  },
  grid: {
    height: '50%',
    top: '10%'
  },
  xAxis: {
    type: 'category',
    data: ['N', 'D', 'G', 'C', 'A', 'H', 'M', 'O'],
    splitArea: {
      show: true
    }
  },
  yAxis: {
    type: 'category',
    data: ['80+','61-80' , '41-60', '21-40','0-20' ],
    splitArea: {
      show: true
    }
  },
  visualMap: {
    min: 0,
    max: 60,
    calculable: true,
    orient: 'horizontal',
    left: 'center',
    bottom: '15%',
    inRange: {
      color: ['#e0f7fa', '#00bcd4'] // 从浅天蓝色到深天蓝色
    }
  },
  series: [
    {
      name: '该年龄组的疾病分布',
      type: 'heatmap',
      data: data,
      label: {
        show: true
      },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
};

hitChart1.setOption(hitOption1);






var hitChart2 = echarts.init(document.getElementById('hitChart2'));

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
  title: { text: '疾病相关性热图' },
  tooltip: {
    position: 'top'
  },
  grid: {
    height: '50%',
    top: '10%'
  },
  xAxis: {
    type: 'category',
    data: ['N', 'D', 'G', 'C', 'A', 'H', 'M', 'O'],
    splitArea: {
      show: true
    }
  },
  yAxis: {
    type: 'category',
    data: ['N', 'D', 'G', 'C', 'A', 'H', 'M', 'O'],
    splitArea: {
      show: true
    }
  },
  visualMap: {
    min: -1,
    max: 1,
    calculable: true,
    orient: 'horizontal',
    left: 'center',
    bottom: '15%',
    
    inRange: {
      color: ['#e0f7fa', '#00bcd4'] // 从浅天蓝色到深天蓝色
    }
  },
  series: [
    {
      name: '疾病相关性',
      type: 'heatmap',
      data: hitdata2,
      label: {
        show: true
      },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
};

hitChart2.setOption(hitOption2);

})



</script>


<template>
    分析
    <div id="barChart1" style="width: 600px;height:400px;"></div>
    <div id="barChart2" style="width: 600px;height:400px;"></div>
    <div id="barChart3" style="width: 600px;height:400px;"></div>
    <div id="binChart1" style="width: 600px;height:400px;"></div>
    <div id="hitChart1" style="width: 600px;height:400px;"></div>
    <div id="hitChart2" style="width: 600px;height:400px;"></div>
</template>