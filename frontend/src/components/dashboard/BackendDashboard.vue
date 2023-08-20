<template>
    <div>
        <h3>time series</h3>
        <TimeSeriesChart />
        <h3>guage</h3>
        <GaugeChart />
        <h3>pie</h3>
        <PieChart />
        <h3>bar</h3>
        <BarGraph />
        <h3>group</h3>
        <GroupedBarGraph />
        <h3>radar</h3>
        <RadarGraph />
    </div>
</template>
  
<script>

// Vue
import { defineComponent } from 'vue'
// components
import TimeSeriesChart from '@/components/graph/TimeSeriesChart.vue';
import GaugeChart from '@/components/graph/GaugeChart.vue';
import PieChart from '@/components/graph/PieChart.vue';
import RadarGraph from '@/components/graph/RadarGraph.vue';
import BarGraph from '@/components/graph/BarGraph.vue';
import GroupedBarGraph from '@/components/graph/GroupedBarGraph.vue';

const BackendDashboard = defineComponent({
    name: 'backend-dashboard',
    components: {
        TimeSeriesChart,
        GaugeChart,
        PieChart,
        BarGraph,
        GroupedBarGraph,
        RadarGraph
    },
});

export default BackendDashboard;
</script>
  
<style>
/* Style if needed */
</style>