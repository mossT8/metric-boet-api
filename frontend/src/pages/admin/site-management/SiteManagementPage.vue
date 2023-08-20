<template>
    <div class="container">
        <header class="jumbotron">
            <BackendDashboard />
        </header>
    </div>
</template>
  
<script>

// Vue
import { defineComponent } from 'vue'
// components
import BackendDashboard from '@/components/dashboard/BackendDashboard.vue';

const SiteManagementPage = defineComponent({
    components: {
        BackendDashboard
    },
});

export default SiteManagementPage;
</script>
  
<style>
/* Style if needed */
</style>