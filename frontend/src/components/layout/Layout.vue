<template>
    <div class="wrapper">
        <div class="sidebar" :class="{ 'hide-sidebar': !sidebarOpen }">
            <SideBar :sidebarLinksTop="mainNavOptions" :sidebarLinksBottum="subNavOptions" @toggleSidebar="toggleSidebar"
                @hideSidebar="hideSidebar" />
        </div>
        <div class="main-screen-wrapper">
            <header>
                <TopNavbar @toggleSidebar="toggleSidebar" :sidebarVisible="sidebarOpen" @hideSidebar="hideSidebar" />
            </header>
            <main class="content">
                <Content :class="{ 'blur': sidebarOpen }" @click.stop="hideSidebar" />
            </main>
            <footer>
                <CustomFooter :class="{ 'blur': sidebarOpen }" />
            </footer>
        </div>
    </div>
</template>
<script>
// vue

import SideBar from "@/components/layout/sidebar/SideBar.vue";
import TopNavbar from "@/components/layout/TopNavbar.vue";
import Content from "@/components/layout/Content.vue";
import CustomFooter from "@/components/layout/Footer.vue";
import { mainNavOptions, subNavOptions } from "@/types/nav/index"
export default {
    components: {
        TopNavbar,
        CustomFooter,
        Content,
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
  