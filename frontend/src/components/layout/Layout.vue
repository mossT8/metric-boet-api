<template>
    <div class="wrapper">
        <div class="sidebar" :class="{ 'hide-sidebar': !sidebarOpen }">
            <side-bar :sidebarLinks="mainNavOptions" @toggleSidebar="toggleSidebar" @hideSidebar="hideSidebar" />
        </div>
        <div class="content">
            <TopNavbar @toggleSidebar="toggleSidebar" @hideSidebar="hideSidebar" />
            <SimpleContent @click.stop="hideSidebar" />
            <SimpleFooter />
        </div>
    </div>
</template>
<script>

import SideBar from "@/components/sidebar/SideBar.vue";
import TopNavbar from "@/components/nav/TopNavbar.vue";
import SimpleContent from "@/components/layout/Content.vue";
import SimpleFooter from "@/components/layout/Footer.vue";
import { mainNavOptions } from "@/types/nav/index"
export default {
    components: {
        TopNavbar,
        SimpleFooter,
        SimpleContent,
        SideBar
    },
    data() {
        return {
            sidebarOpen: true,
            mainNavOptions: mainNavOptions,
        };
    },
    methods: {
        toggleSidebar() {
            this.sidebarOpen = !this.sidebarOpen;
        },
        hideSidebar() {
            this.sidebarOpen = false;
        }
    }
}
</script>
  