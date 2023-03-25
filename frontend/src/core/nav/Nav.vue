<template>
    <nav class="navbar navbar-expand navbar-dark bg-dark">
        <a href="/" class="navbar-brand">
            <img id="profile-img" src="@/assets/logo.png" class="logo-img" />
            Metric Boet Portal
        </a>
        <div class="navbar-nav mr-auto">
            <li class="nav-item">
                <router-link to="/home" class="nav-link">
                    <font-awesome-icon icon="home" /> Home
                </router-link>
            </li>
            <li v-if="showAdminBoard" class="nav-item">
                <router-link to="/admin" class="nav-link">Admin Board</router-link>
            </li>
            <li v-if="showModeratorBoard" class="nav-item">
                <router-link to="/mod" class="nav-link">Moderator Board</router-link>
            </li>
            <li class="nav-item">
                <router-link v-if="currentUser" to="/user" class="nav-link">User Board</router-link>
            </li>
            <li class="nav-item dropdown" v-if="showAdminBoard">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true"
                    aria-expanded="false">
                    Admin Management
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <router-link to="/html-list" class="dropdown-item">
                        <font-awesome-icon icon="cogs" class="pr-2" />
                        Page Manager
                    </router-link>
                    <!-- Add additional dropdown items as needed -->
                </div>
            </li>

        </div>

        <div v-if="!currentUser" class="navbar-nav ml-auto">
            <li class="nav-item">
                <router-link to="/register" class="nav-link">
                    <font-awesome-icon icon="user-plus" /> Sign Up
                </router-link>
            </li>
            <li class="nav-item">
                <router-link to="/login" class="nav-link">
                    <font-awesome-icon icon="sign-in-alt" /> Login
                </router-link>
            </li>
        </div>

        <div v-if="currentUser" class="navbar-nav ml-auto">
            <li class="nav-item">
                <router-link to="/profile" class="nav-link">
                    <font-awesome-icon icon="user" />
                    {{ currentUser.username }}
                </router-link>
            </li>
            <li class="nav-item">
                <a class="nav-link" @click.prevent="logOut">
                    <font-awesome-icon icon="sign-out-alt" /> LogOut
                </a>
            </li>
        </div>
    </nav>
</template>
  
<script>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
/* import the fontawesome core */
import { library } from "@fortawesome/fontawesome-svg-core";

/* add some free styles */
import { faTwitter } from '@fortawesome/free-brands-svg-icons'
import { faUserSecret } from '@fortawesome/free-solid-svg-icons'

library.add(faUserSecret);
library.add(faTwitter);

/* import specific icons */
import { faHome } from "@fortawesome/free-solid-svg-icons/faHome";
import { faUser } from "@fortawesome/free-solid-svg-icons/faUser";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons/faUserPlus";
import { faSignInAlt } from "@fortawesome/free-solid-svg-icons/faSignInAlt";
import { faSignOutAlt } from "@fortawesome/free-solid-svg-icons/faSignOutAlt";

library.add(faHome);
library.add(faUser);
library.add(faUserPlus);
library.add(faSignInAlt);
library.add(faSignOutAlt);

export default {
    name: "Nav",
    components: {
        FontAwesomeIcon
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
  