<template>
    <div>
        <apexchart type="line" height="350" ref="chart" :options="chartOptions" :series="series"></apexchart>
    </div>
</template>
  
<script>
let seconds = 1;
let minutes = 1;
let hours = 1;
let days = 1;
let milliseconds = 1;


let XAXISRANGE = 60 * seconds * 60 * minutes * 24 * hours * 7 * days * 1000 * milliseconds;

const TICKINTERVAL = 86400000;

// vue
import { defineComponent, reactive, ref } from 'vue'
//components
import VueApexCharts from 'vue3-apexcharts';

const TimeSeriesChart = defineComponent({
    name: 'time-series-chart',
    components: {
        apexchart: VueApexCharts,
    },
    props: {
        id: {
            type: String,
            require: true
        },
        speed: {
            type: Number,
            default: 1000,
            require: false
        },
        title: {
            type: String,
            default: 'Dynamic Updating Chart',
            require: false
        },
        titleSpot: {
            type: String,
            default: 'left',
            require: false
        },
        data: {
            type: Array,
            require: true,
            default: []
        }
    },
    setup(props) {
        const chartOptions = reactive({
            chart: {
                id: 'realtime' + props.id,
                type: 'line',
                animations: {
                    enabled: true,
                    easing: 'linear',
                    dynamicAnimation: {
                        speed: props.speed,
                    },
                },
                toolbar: {
                    show: false,
                },
                zoom: {
                    enabled: false,
                },
            },
            dataLabels: {
                enabled: false,
            },
            stroke: {
                curve: 'smooth',
            },
            title: {
                text: props.title,
                align: props.titleSpot,
            },
            markers: {
                size: 0,
            },
            xaxis: {
                type: 'datetime',
                range: XAXISRANGE,
            },
            yaxis: {
                max: 100,
            },
            legend: {
                show: false,
            },
        });

        const series = ref([{
            data: props.data,
        }]);

        return {
            series,
            selectedRange: 1, // Initial selected range value (in days)
            xAxisRanges: [
                { value: 1, label: '1 Day' },
                { value: 2, label: '2 Days' },
                { value: 3, label: '3 Days' },
                { value: 4, label: '4 Days' },
                { value: 5, label: '5 Days' },
                { value: 6, label: '6 Days' },
                { value: 7, label: '7 Days' },
            ],
            chartOptions,
        };
    },
    computed: {
        getXAxisRange() {
            return this.selectedRange * 86400000; // Convert selected range to milliseconds (1 day = 86400000 ms)
        },
    },
});

export default TimeSeriesChart;
</script>
  
<style scoped>
#chart {
    max-width: 650px;
    margin: 35px auto;
}
</style>
  