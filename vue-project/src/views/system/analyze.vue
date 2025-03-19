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

})



</script>


<template>
    分析
    <div id="barChart1" style="width: 600px;height:400px;"></div>
    <div id="barChart2" style="width: 600px;height:400px;"></div>
    <div id="barChart3" style="width: 600px;height:400px;"></div>
    <div id="binChart1" style="width: 600px;height:400px;"></div>
</template>