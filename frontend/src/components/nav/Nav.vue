<template>
    <nav class="navbar navbar-expand-lg navbar-absolute" :class="{ 'bg-white': showMenu, 'navbar-transparent': !showMenu }">
        <div class="container-fluid">
            <div class="navbar-wrapper">
                <div class="navbar-toggle d-inline">
                    <button type="button" class="navbar-toggler" @click="toggleSidebar">
                        <span class="navbar-toggler-bar bar1"></span>
                        <span class="navbar-toggler-bar bar2"></span>
                        <span class="navbar-toggler-bar bar3"></span>
                    </button>
                </div>
                <a class="navbar-brand" href="#pablo">{{ routeName }}</a>
            </div>
            <button class="navbar-toggler" type="button" @click="toggleMenu" data-toggle="collapse"
                data-target="#navigation" aria-controls="navigation-index" aria-label="Toggle navigation">
                <span class="navbar-toggler-bar navbar-kebab"></span>
                <span class="navbar-toggler-bar navbar-kebab"></span>
                <span class="navbar-toggler-bar navbar-kebab"></span>
            </button>

            <collapse-transition>
                <div class="collapse navbar-collapse show" v-show="showMenu">
                    <ul class="navbar-nav mr-auto" >
                        <div class="search-bar input-group" @click="searchModalVisible = true">
                            <!-- <input type="text" class="form-control" placeholder="Search...">
              <div class="input-group-addon"><i class="custom-icons icon-zoom-split"></i></div> -->
                            <button class="btn btn-link" id="search-button" data-toggle="modal" data-target="#searchModal">
                                <i class="custom-icons icon-zoom-split"></i>
                            </button>
                            <!-- You can choose types of search input -->
                        </div>
                        <modal v-model="searchModalVisible" class="modal-search" id="searchModal" :centered="false"
                            :show-close="true">
                            <input v-model="searchQuery" type="text" class="form-control" id="inlineFormInputGroup"
                                placeholder="SEARCH">
                        </modal>
                        <base-dropdown tag="li" :menu-on-right="false" title-tag="a" class="nav-item"
                            menu-classes="dropdown-navbar">
                            <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="true">
                                <div class="photo">
                                    <img src="img/anime3.png">
                                </div>
                                <b class="caret d-none d-lg-block d-xl-block"></b>
                                <p class="d-lg-none">
                                    Log out
                                </p>
                            </a>
                            <li class="nav-link">
                                <a href="#" class="nav-item dropdown-item">Profile</a>
                            </li>
                            <li class="nav-link">
                                <a href="#" class="nav-item dropdown-item">Settings</a>
                            </li>
                            <div class="dropdown-divider"></div>
                            <li class="nav-link">
                                <a href="#" class="nav-item dropdown-item">Log out</a>
                            </li>
                        </base-dropdown>
                    </ul>
                </div>
            </collapse-transition>
        </div>
    </nav>
</template>
  
<script>
import Modal from '@/components/Modal.vue';
import BaseDropdown from '@/components/BaseDropdown.vue';

export default {
    name: "Nav",
    components: {
        Modal,
        BaseDropdown
    },
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
        showAdminBoard() {
            if (this.currentUser && this.currentUser['roles']) {
                return this.currentUser['roles'].includes('ROLE_ADMIN');
            }

            return false;
        },
        showModeratorBoard() {
            if (this.currentUser && this.currentUser['roles']) {
                return this.currentUser['roles'].includes('ROLE_MODERATOR');
            }

            return false;
        }
    },
    methods: {
        logOut() {
            this.$store.dispatch('auth/logout');
            this.$router.push('/login');
        }
    },
};
</script>
  