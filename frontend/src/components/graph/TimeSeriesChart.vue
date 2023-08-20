<template>
    <div>
        <label for="xAxisRange">Select X-Axis Range:</label>
        <select id="xAxisRange" v-model="selectedRange">
            <option v-for="range in xAxisRanges" :key="range.value" :value="range.value">{{ range.label }}</option>
        </select>
        <apexchart type="line" height="350" ref="chart" :options="chartOptions" :series="series"></apexchart>
    </div>
</template>
  
<script>
import VueApexCharts from 'vue3-apexcharts';
import { defineComponent } from 'vue'
let seconds = 1;
let minutes = 1;
let hours = 1;
let days = 1;
let milliseconds = 1;


let XAXISRANGE = 60 * seconds * 60 * minutes * 24 * hours * 7 * days * 1000 * milliseconds;

const TICKINTERVAL = 86400000;

const TimeSeriesChart = defineComponent({
    name: 'time-series-chart',
    components: {
        apexchart: VueApexCharts,
    },
    data() {
        const chartOptions = {
            chart: {
                id: 'realtime',
                height: 350,
                type: 'line',
                animations: {
                    enabled: true,
                    easing: 'linear',
                    dynamicAnimation: {
                        speed: 1000,
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
                text: 'Dynamic Updating Chart',
                align: 'left',
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
        };

        return {
            series: [{
                data: [],
            }],
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
    mounted() {
        let lastDate = 0;
        let data = [];

        function getDayWiseTimeSeries(baseval, count, yrange) {
            let i = 0;
            while (i < count) {
                let x = baseval;
                let y = Math.floor(Math.random() * (yrange.max - yrange.min + 1)) + yrange.min;
                data.push({
                    x,
                    y,
                });
                lastDate = baseval;
                baseval += TICKINTERVAL;
                i++;
            }
        }

        getDayWiseTimeSeries(new Date('11 Feb 2017 GMT').getTime(), 10, {
            min: 10,
            max: 90,
        });

        function getNewSeries(baseval, yrange) {
            let newDate = baseval + TICKINTERVAL;
            lastDate = newDate;

            for (let i = 0; i < data.length - 10; i++) {
                // IMPORTANT
                // we reset the x and y of the data which is out of drawing area
                // to prevent memory leaks
                data[i].x = newDate - XAXISRANGE - TICKINTERVAL;
                data[i].y = 0;
            }

            data.push({
                x: newDate,
                y: Math.floor(Math.random() * (yrange.max - yrange.min + 1)) + yrange.min,
            });
        }

        function resetData() {
            // Alternatively, you can also reset the data at certain intervals to prevent creating a huge series
            data = data.slice(data.length - 10, data.length);
        }

        const me = this;
        window.setInterval(function () {
            getNewSeries(lastDate, {
                min: 10,
                max: 90,
            });

            me.$refs.chart.updateSeries([
                {
                    data: data,
                },
            ]);
        }, 1000);

        // every 60 seconds, we reset the data to prevent memory leaks
        window.setInterval(function () {
            resetData();

            me.$refs.chart.updateSeries([
                {
                    data: data,
                },
            ], false, true);
        }, 60000);
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
  