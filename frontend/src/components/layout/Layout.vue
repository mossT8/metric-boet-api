<template>
    <div class="wrapper">
        <div class="sidebar" :class="{ 'hide-sidebar': !sidebarOpen }">
            <side-bar :sidebarLinksTop="mainNavOptions" :sidebarLinksBottum="subNavOptions" @toggleSidebar="toggleSidebar"
                @hideSidebar="hideSidebar" />
        </div>
        <div class="content">
            <TopNavbar @toggleSidebar="toggleSidebar" :sidebarVisible="sidebarOpen" @hideSidebar="hideSidebar" />
            <SimpleContent :class="{ 'blur': sidebarOpen }" @click.stop="hideSidebar" />
            <SimpleFooter :class="{ 'blur': sidebarOpen }" />
        </div>
    </div>
</template>
<script>

import SideBar from "@/components/layout/sidebar/SideBar.vue";
import TopNavbar from "@/components/layout/TopNavbar.vue";
import SimpleContent from "@/components/layout/Content.vue";
import SimpleFooter from "@/components/layout/Footer.vue";
import { mainNavOptions, subNavOptions } from "@/types/nav/index"
export default {
    components: {
        TopNavbar,
        SimpleFooter,
        SimpleContent,
        SideBar
    },
    data() {
        return {
            sidebarOpen: false,
            mainNavOptions,
            subNavOptions
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
  