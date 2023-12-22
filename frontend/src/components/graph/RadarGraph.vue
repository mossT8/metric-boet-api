<template>
    <div>
        <div id="chart">
            <VueApexCharts type="radar" height="350" :options="chartOptions" :series="series"></VueApexCharts>
        </div>
    </div>
</template>
  
<script>
// vue
import { defineComponent } from 'vue';
// components
import VueApexCharts from 'vue3-apexcharts';

const RadarGraph = defineComponent({
    name: 'radar-graph',
    components: {
        VueApexCharts,
    },
    data() {
        return {
            series: [
                {
                    name: 'Series 1',
                    data: [80, 50, 30, 40, 100, 20],
                }
            ],
            chartOptions: {
                chart: {
                    height: 350,
                    type: 'radar',
                },
                title: {
                    text: 'Basic Radar Chart',
                },
                xaxis: {
                    categories: ['January', 'February', 'March', 'April', 'May', 'June'],
                },
            },
        };
    },
});

export default RadarGraph;
</script>
  
<style scoped>
#chart {
    max-width: 450px;
    margin: 35px auto;
}
</style>
  