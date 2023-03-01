<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>{{ currentUser.username }}</strong> Profile
      </h3>
    </header>
    <p>
      <strong>Token:</strong>
      {{ currentUser.accessToken.substring(0, 20) }} ... {{ currentUser.accessToken.substr(currentUser.accessToken.length -
        20) }}
    </p>
    <p>
      <strong>Id:</strong>
      {{ currentUser.id }}
    </p>
    <p>
      <strong>Email:</strong>
      {{ currentUser.email }}
    </p>
    <strong>Authorities:</strong>
    <ul>
      <li v-for="role in currentUser.roles" :key="role">{{ role }}</li>
    </ul>
    <div>
      <strong>Devices ({{ totalDevices }}) </strong>
      <Button class="float-right" type="success">Add Device </Button>
    </div>
  </div>
</template>

<script>

import Button from "@/components/button/Button.vue"

export default {
  name: 'Profile',
  components: {
    Button,
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    totalDevices() {
      return 0;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>