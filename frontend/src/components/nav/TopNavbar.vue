<template>
    <div class="top-navbar">
        <div class="left-hand-side">
            <div class="navbar-brand" @click.stop="toggleSidebar">
                <font-awesome-icon icon="fa-solid fa-bars" />
            </div>
            <a class="navbar-brand" href="/">Home</a>
        </div>

        <div class="right-hand-side">
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

            <base-dropdown tag="li" :menu-on-right="true" title-tag="a" class="nav-item" menu-classes="dropdown-navbar">
                <li class="d-flex justify-content-center">
                    <div class="photo">
                        <img src="@/assets/img/anime3.png">
                    </div>
                </li>
                <li class="nav-link">
                    <a class="nav-item dropdown-item">Profile</a>
                </li>
                <li class="nav-link">
                    <a href="#" class="nav-item dropdown-item">Settings</a>
                </li>
                <div class="dropdown-divider"></div>
                <li class="nav-link">
                    <div href="#" class="nav-item dropdown-item">Log out</div>
                </li>
            </base-dropdown>
        </div>
    </div>
</template>

<script setup lang="ts">
import Modal from '@/components/Modal.vue';
import BaseDropdown from '@/components/BaseDropdown.vue';
import { defineEmit, ref } from 'vue';

let activeNotifications = ref(false);
let showMenu = ref(false);
let searchModalVisible = ref(false);
let searchQuery = ref('');

const emit = defineEmit(['toggleSidebar', 'hideSidebar']);

const toggleNotificationDropDown = () => {
    activeNotifications.value = !activeNotifications.value;
};

const closeDropDown = () => {
    activeNotifications.value = false;
};

const toggleSidebar = () => {
    emit('toggleSidebar');
};

const hideSidebar = () => {
    emit('hideSidebar');
};

const toggleMenu = () => {
    showMenu.value = !showMenu.value;
};

const components = {
    Modal,
    BaseDropdown
};
</script>