<template>
  <div id="app">
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
          <router-link v-if="currentUser" to="/user" class="nav-link">User</router-link>
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

    <div class="container">
      <router-view />
    </div>
  </div>
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
  mounted() {
    document.title = "Metric Boet Portal";
  },
};
</script>

<style lang="scss" >

@import'@/sass/variables.scss';
@import'@/sass/icon.scss';
@import '@/sass/editor.scss';
@import '@/sass/menubar.scss';
@import '@/sass/menububble.scss';
@import '@/sass/main.scss';

.logo-img {
  width: 32px;
  height: 32px;
  margin: 0 auto 10px;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>
