<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>{{ currentUser.username }}</strong> Profile
      </h3>
      <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
        {{ message }}
      </div>
    </header>
    <p>
      <strong>Token:</strong>
      {{ currentUser.accessToken.substring(0, 20) }} ... {{ currentUser.accessToken.substr(currentUser.accessToken.length
        -
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
    <div v-roles="['Admin', 'Moderator']" class="mb-2 d-flex justify-content-between">
      <strong>Devices ({{ totalDevices }}) </strong>
      <Button  v-roles="['Admin']" type="success" @click.stop="addDeviceForm">Add Device </Button>
    </div>
    <DeviceList v-roles="['Admin', 'Moderator']" :devices="allDevices" />
  </div>
</template>

<script>

import Button from "@/components/button/Button.vue"
import DeviceList from "@/components/device/DeviceList.vue";
import DeviceService from "@/services/device.service";


export default {
  name: 'Profile',
  components: {
    Button,
    DeviceList
  },
  data() {
    return {
      devices: [],
      load: false,
      message: "",
    }
  },
  async created() {
    this.loading = true;
    try {
      this.devices = await DeviceService.getAllDevicesForUser();
    } catch (error) {
      this.message = error.message;
    } finally {
      this.loading = false;
    }

  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    totalDevices() {
      return this.devices.length || 0;
    },
    allDevices() {
      return this.devices || [];
    },
  },
  methods: {
    addDeviceForm() {
      this.$router.push('/device-add');
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>