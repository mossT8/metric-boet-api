<template>
    <div class="server-logs">
        <h3>{{ title }}</h3>
        <div class="log-entry" v-for="(log, index) in logs" :key="index" :class="logClass(log)">
            {{ log }}
        </div>
    </div>
</template>
  
<script>
// Vue
import { defineComponent, ref } from 'vue'
const ServerLogs = defineComponent({
    name: "server-logs",
    props: {
        logs: {
            type: Array,
            default: []
        },
        title: {
            type: String,
            default: "Server Logs"
        }
    },
    setup(props) {

        const logs = ref(props.logs)
        return {
            logs,
        };
    },
    methods: {
        logClass(log) {
            if (log.includes("ERROR")) {
                return "log-error";
            } else if (log.includes("WARNING")) {
                return "log-warning";
            } else if (log.includes("INFO")) {
                return "log-info";
            }
            // Default class for other cases
            return "log-default";
        },
    },
});

export default ServerLogs;
</script>
  
<style lang="scss" scoped>
.server-logs {
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.log-entry {
    margin-bottom: 10px;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-family: monospace;
    white-space: pre-wrap;
}

.log-error {
    background-color: #f8d7da;
    border-color: #f5c6cb;
}

.log-warning {
    background-color: #fff3cd;
    border-color: #ffeeba;
}

.log-info {
    background-color: #d1ecf1;
    border-color: #bee5eb;
}

.log-default {
    background-color: #f9f9f9;
    border-color: #ddd;
}
</style>
  