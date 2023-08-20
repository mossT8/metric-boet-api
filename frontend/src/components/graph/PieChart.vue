<template>
    <div>
        <div id="chart">
            <apexchart type="donut" :options="chartOptions" :series="series"></apexchart>
        </div>
    </div>
</template>
  
<script>
// vue
import { defineComponent } from 'vue';
// components
import VueApexCharts from 'vue3-apexcharts';

const PieChart = defineComponent({
    name: 'pie-graph',
    components: {
        apexchart: VueApexCharts,
    },
    data() {
        return {
            series: [44, 55, 41, 17, 15],
            chartOptions: {
                chart: {
                    type: 'donut',
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

export default PieChart;
</script>
  
<style scoped>
#chart {
    max-width: 380px;
    margin: 35px auto;
    padding: 0;
}
</style>
  