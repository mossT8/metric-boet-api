<template>
    <div>
        <h3>Server Metrics</h3>
        <div class="metrics">
            <div class="metric">
                <TimeSeriesChart title="CPU Usage (%)" titleSpot="center" :data="data" />
            </div>
            <div class="metric">
                <TimeSeriesChart title="Memory Usage (%)" titleSpot="center" :data="data" />
            </div>
            <!-- Add more TimeSeriesChart components as needed -->
        </div>
        <ServerLogs :logs="logEntries" />
    </div>
</template>
  
<script>

// Vue
import { defineComponent, ref } from 'vue'
// components
import TimeSeriesChart from '@/components/graph/TimeSeriesChart.vue';
import GaugeChart from '@/components/graph/GaugeChart.vue';
import PieChart from '@/components/graph/PieChart.vue';
import RadarGraph from '@/components/graph/RadarGraph.vue';
import BarGraph from '@/components/graph/BarGraph.vue';
import GroupedBarGraph from '@/components/graph/GroupedBarGraph.vue';
import ServerLogs from '@/components/logs/ServerLogs.vue';

const BackendDashboard = defineComponent({
    name: 'backend-dashboard',
    components: {
        TimeSeriesChart,
        GaugeChart,
        PieChart,
        BarGraph,
        GroupedBarGraph,
        ServerLogs,
        RadarGraph
    },
    setup() {
        let lastDate = 0;
        const data = ref([]);

        const logEntries = [
            "2023-08-23 15:04:09.417  INFO 6088 --- [           main] c.m.boet.api.BackendServerApplication    : Starting BackendServerApplication using Java 11.0.17 on LAPTOP-MG6K85T7 with PID 6088 (D:\\Workspace\\metric-boet-api\\backend\\target\\classes started by TannarMossSMSPortal in D:\\Workspace\\metric-boet-api\\backend)",
            "2023-08-23 15:04:09.424  INFO 6088 --- [           main] c.m.boet.api.BackendServerApplication    : No active profile set, falling back to 1 default profile: \"default\"",
            "2023-08-23 15:04:16.977  INFO 6088 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.",
            "2023-08-23 15:04:17.524  INFO 6088 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 519 ms. Found 4 JPA repository interfaces.",
            "2023-08-23 15:04:21.845  INFO 6088 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)",
            "2023-08-23 15:04:21.905  INFO 6088 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]",
            "2023-08-23 15:04:21.905  INFO 6088 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.65]",
            "2023-08-23 15:04:22.893  INFO 6088 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext",
            // ... (other log entries)
            "2023-08-23 15:05:46.003  INFO 6088 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'",
            "2023-08-23 15:05:46.005  INFO 6088 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms",
            "2023-08-23 15:05:46.076 ERROR 6088 --- [nio-8080-exec-1] c.m.b.a.security.jwt.AuthEntryPointJwt   : Unauthorized error: Full authentication is required to access this resource",
            "2023-08-23 15:07:54.418 ERROR 6088 --- [nio-8080-exec-3] c.m.b.a.security.jwt.AuthEntryPointJwt   : Unauthorized error: Full authentication is required to access this resource"
        ];


        let seconds = 1;
        let minutes = 1;
        let hours = 1;
        let days = 1;
        let milliseconds = 1;

        let XAXISRANGE = 60 * seconds * 60 * minutes * 24 * hours * 7 * days * 1000 * milliseconds;

        const TICKINTERVAL = 86400000;

        function getDayWiseTimeSeries(baseval, count, yrange) {
            let i = 0;
            while (i < count) {
                let x = baseval;
                let y = Math.floor(Math.random() * (yrange.max - yrange.min + 1)) + yrange.min;
                data.value.push({
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

            for (let i = 0; i < data.value.length - 10; i++) {
                // IMPORTANT
                // we reset the x and y of the data which is out of drawing area
                // to prevent memory leaks
                data.value[i].x = newDate - XAXISRANGE - TICKINTERVAL;
                data.value[i].y = 0;
            }

            const newPoint = {
                x: newDate,
                y: Math.floor(Math.random() * (yrange.max - yrange.min + 1)) + yrange.min,
            }

            data.value.push(newPoint);
        }

        function resetData() {
            // Alternatively, you can also reset the data at certain intervals to prevent creating a huge series
            data.value = data.value.slice(data.length - 10, data.length);
        }


        window.setInterval(function () {
            getNewSeries(lastDate, {
                min: 10,
                max: 90,
            });
        }, 1000);

        // every 60 seconds, we reset the data to prevent memory leaks
        window.setInterval(function () {
            resetData();
        }, 60000);

        return {
            data,
            logEntries
        }
    },
});

export default BackendDashboard;
</script>
  
<style lang="scss" scoped>
.metrics {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between; /* Adjust as needed */
    gap: 20px; /* Adjust the gap between graphs */
}

.metric {
    width: 48%;
    flex-grow: 1; /* Allow graphs to grow and evenly distribute space */

    @media (max-width: 768px) {
        width: 100%;
    }
}

</style>