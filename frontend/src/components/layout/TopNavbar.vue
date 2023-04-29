<template>
    <div class="top-navbar">
        <div class="left-hand-side">
            <div class="navbar-brand" @click.stop="toggleSidebar">
                <font-awesome-icon icon="fa-solid fa-bars" />
            </div>
            <a class="navbar-brand" href="/home">Home</a>
        </div>

        <div class="right-hand-side" :class="{ 'blur': sidebarVisible }">
            <div class="search-bar input-group" @click="searchModalVisible = true">
                <input type="text" class="form-control" placeholder="Search...">
                <div class="input-group-addon"><i class="custom-icons icon-zoom-split"></i></div>
                <button class="btn btn-link" id="search-button" data-toggle="modal" data-target="#searchModal">
                    <font-awesome-icon icon="fa-solid fa-search" />
                </button>
            </div>
            <modal v-model="searchModalVisible" class="modal-search" id="searchModal" :centered="false" :show-close="true">
                <input v-model="searchQuery" type="text" class="form-control" id="inlineFormInputGroup"
                    placeholder="SEARCH">
            </modal>
        </div>
    </div>
</template>

<script setup>
import Modal from '@/components/Modal.vue';
import BaseDropdown from '@/components/BaseDropdown.vue';
import { defineEmit, defineProps, ref } from 'vue';

const props = defineProps({
    sidebarVisible: {
        type: Boolean,
        required: true,
        default: false,
    },
});

let searchModalVisible = ref(false);
let searchQuery = ref('');

const emit = defineEmit(['toggleSidebar', 'hideSidebar']);

const toggleSidebar = () => {
    emit('toggleSidebar');
};

const components = {
    Modal,
    BaseDropdown
};
</script>