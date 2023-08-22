<template>
    <div class="container">
        <header class="jumbotron">
            <h2 class="d-flex justify-content-center">
                <font-awesome-icon class="pr-2" icon='fa-solid fa-cogs' />
                Site Management
            </h2>
            <Tabs :initialTabs="tabList">
                <template #tab-0>
                    <BackendDashboard />
                </template>
                <template #tab-1>
                    <p>Custom content for Tab 2</p>
                    <img src="tab2-image.jpg" alt="Tab 2 Image" />
                </template>
                <template #tab-2>
                    <p>Custom content for Tab 3</p>
                    <ul>
                        <li>Item 1</li>
                        <li>Item 2</li>
                        <li>Item 3</li>
                    </ul>
                </template>
            </Tabs>
        </header>
    </div>
</template>
  
<script>

// Vue
import { defineComponent } from 'vue'
// components
import BackendDashboard from '@/components/dashboard/BackendDashboard.vue';
import Tabs from '@/components/tabs/Tabs.vue';

const SiteManagementPage = defineComponent({
    components: {
        BackendDashboard,
        Tabs
    },
    data() {
        return {
            tabList: [
                { label: 'Backend', icon: 'fas fa-server', content: 'Default content of Tab 1' },
                { label: 'Frontend', icon: 'fa-desktop', content: 'Default content of Tab 2' },
                { label: 'Site Activity', icon: 'fas fa-chart-line', content: 'Default content of Tab 3' },
            ],
        };
    },
});

export default SiteManagementPage;
</script>
  
<style>
/* Style if needed */
</style>