<template>
  <div class="container">
    <div class="jumbotron">
      <h2>Logout</h2>
      <h4> Are you sure you want to logout <b>{{ userName }}</b> ?</h4>
      <div class="d-flex justify-content-end">
        <button class="btn btn-secondary mr-2" @click="backToPreviousRoute">Cancel</button>
        <button class="btn btn-primary" @click="confirmLogout">Confirm</button>
      </div>
    </div>
  </div>
</template>

<script>
// vue
import { defineComponent } from 'vue';
// services
import { useAuthModuleFeatures } from "@/composables/store/useAuthModule";

const UserLogoutPage = defineComponent({
  name: 'user-logout-page',
  computed: {
    userName() {
      return this.user ? this.user.username : 'Unknown';
    }
  },
  methods: {
    backToPreviousRoute() {
      this.$router.go(-1);
    },
    confirmLogout() {
      this.logout();
      this.$router.push('/home');
    }
  },
  setup() {

    return {
      ...useAuthModuleFeatures(),
    };
  }
});

export default UserLogoutPage;
</script>
