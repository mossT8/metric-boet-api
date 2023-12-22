<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <font-awesome-icon icon="fa-solid fa-user" />
        <strong>{{ user.username }}</strong> Profile
      </h3>
      <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
        {{ message }}
      </div>
    </header>
    <p>
      <strong>Id:</strong>
      {{ user.id }}
    </p>
    <p>
      <strong>Email:</strong>
      {{ user.email }}
    </p>
    <strong>Authorities:</strong>
    <ul>
      <li v-for="role in user.roles" :key="role">{{ role }}</li>
    </ul>
    <div v-roles="['Admin', 'Moderator']" class="mb-2 d-flex justify-content-between">
      <strong>Devices ({{ totalDevices }}) </strong>
      <Button v-roles="['Admin']" type="success" @click.stop="addDeviceForm">Add Device </Button>
    </div>
    <DeviceList v-roles="['Admin', 'Moderator']" :devices="allDevices" />
  </div>
</template>

<script>
// vue
import { defineComponent } from 'vue'

// services
import DeviceService from "@/services/device.service";
import { useAuthModuleFeatures } from '@/composables/store/useAuthModule';

//components
import Button from "@/components/button/Button.vue"
import DeviceList from "@/components/device/DeviceList.vue";
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

const UserProfilePage = defineComponent({
  name: 'user-profile-page',
  components: {
    Button,
    DeviceList,
    FontAwesomeIcon
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
  setup() {
    return { ...useAuthModuleFeatures() }
  },
  computed: {
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
    if (!this.user) {
      this.$router.push('/login');
    }
  }
});

export default UserProfilePage;
</script>