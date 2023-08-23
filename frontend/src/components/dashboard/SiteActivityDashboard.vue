<template>
    <div>
        <h3>Site Activity Dashboard</h3>
        <div class="metrics">
            <div class="metric">
                <TimeSeriesChart title="Active Sessions" titleSpot="center" :data="data" />
            </div>
            <div class="metric">
                <TimeSeriesChart title="New Users" titleSpot="center" :data="data" />
            </div>
        </div>
        <h1> Sales </h1>
        To Do Section
        <h1> Complaints </h1>
        To Do Section
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

const SiteActivityDashboard = defineComponent({
    name: 'site-activity-dashboard',
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

        const TICKINTERVAL = 86400000;
        let lastDate = 0;
        
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

        getDayWiseTimeSeries(new Date().getTime(), 10, {
            min: 10,
            max: 90,
        });

        return {
            data,
            logEntries
        }
    },
});

export default SiteActivityDashboard;
</script>
  
<style lang="scss" scoped></style>