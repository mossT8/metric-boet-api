<template>
    <div class="container">
        <header class="jumbotron">
            <h2 class="d-flex justify-content-center">
                <font-awesome-icon class="pr-2" icon='fa-solid fa-cogs' />
                Site Management
            </h2>
            <Tabs :initialTabs="tabList">
                <template #tab-0>
                    <DatabaseDashboard />
                </template>
                <template #tab-1>
                    <BackendDashboard />
                </template>
                <template #tab-2>
                    <FrontendDashboard />
                </template>
                <template #tab-3>
                    <SiteActivityDashboard />
                </template>
            </Tabs>
        </header>
    </div>
</template>
  
<script>

// Vue
import { defineComponent } from 'vue'
// components
import DatabaseDashboard from '@/components/dashboard/DatabaseDashboard.vue';
import BackendDashboard from '@/components/dashboard/BackendDashboard.vue';
import FrontendDashboard from '@/components/dashboard/FrontendDashboard.vue';
import SiteActivityDashboard from '@/components/dashboard/SiteActivityDashboard.vue';
import Tabs from '@/components/tabs/Tabs.vue';

const SiteManagementPage = defineComponent({
    components: {
        DatabaseDashboard,
        BackendDashboard,
        FrontendDashboard,
        SiteActivityDashboard,
        Tabs
    },
    data() {
        return {
            tabList: [
                { id: 'database', label: 'Database', icon: 'fas fa-database', content: 'Default content of Tab 1' },
                { id: 'backend', label: 'Backend', icon: 'fas fa-server', content: 'Default content of Tab 1' },
                { id: 'frontend', label: 'Frontend', icon: 'fa-desktop', content: 'Default content of Tab 2' },
                { id: 'site-activity', label: 'Site Activity', icon: 'fas fa-chart-line', content: 'Default content of Tab 3' },
            ],
        };
    },
});

export default SiteManagementPage;
</script>
  
<style>
/* Style if needed */
</style>