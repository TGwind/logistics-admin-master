<template>
  <div style="width: 100%;height: 800px">
    <v-chart class="chart" :option="option" autoresize />
  </div>

</template>

<script setup>
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { PieChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
} from 'echarts/components';
import VChart, { THEME_KEY } from 'vue-echarts';
import { ref, provide } from 'vue';
import ECharts from "vue-echarts";

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
]);

provide(THEME_KEY, 'white');

const option = ref({
  title: {
    text: '校园快递分布',
    left: 'center',
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b} : {c} ({d}%)',
  },
  legend: {
    orient: 'vertical',
    left: 'left',
    data: ['顺丰', '圆通', '韵达', '中通', '京东'],
  },
  series: [
    {
      name: '校内快递占比',
      type: 'pie',
      radius: '55%',
      center: ['50%', '50%'],
      data: [
        { value: 335, name: '顺丰' },
        { value: 310, name: '圆通' },
        { value: 234, name: '韵达' },
        { value: 135, name: '中通' },
        { value: 1548, name: '京东' },
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 1)',
        },
      },
    },
  ],
});
</script>

<style scoped>
.chart {
  margin: 0 0;
}
</style>
