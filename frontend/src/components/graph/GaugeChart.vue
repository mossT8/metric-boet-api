<template>
    <div>
        <div id="chart">
            <VueApexCharts type="donut" :options="chartOptions" :series="series"></VueApexCharts>
        </div>
    </div>
</template>
  
<script>
// vue
import { defineComponent } from 'vue';
// components
import VueApexCharts from 'vue3-apexcharts';

const GaugeChart = defineComponent({
    name: 'gauge-chart',
    components: {
        VueApexCharts,
    },
    data() {
        return {
            series: [80, 20],
            chartOptions: {
                chart: {
                    type: 'donut',
                },
                plotOptions: {
                    pie: {
                        startAngle: -90,
                        endAngle: 90,
                        offsetY: 10,
                    },
                },
                grid: {
                    padding: {
                        bottom: -80,
                    },
                },
                responsive: [
                    {
                        breakpoint: 480,
                        options: {
                            chart: {
                                width: 200,
                            },
                            legend: {
                                position: 'bottom',
                            },
                        },
                    },
                ],
            },
        };
    },
});

export default GaugeChart;
</script>
  
<style scoped>
#chart {
    max-width: 380px;
    margin: 35px auto;
    padding: 0;
}
</style>
  