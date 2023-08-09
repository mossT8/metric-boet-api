<template>
  <simple-layout />
</template>

<script>
import SimpleLayout from "@/components/layout/Layout.vue";
import jwt_decode from "jwt-decode";
import { LOCAL_STOAGE_USER_KEY } from "@/services/auth.service";
import { useAuthModuleFeatures } from "./composables/store/useAuthModule";

export default {
  components: {
    SimpleLayout
  },
  methods: {

  },
  mounted() {
    document.title = "Metric Boet Portal";
  },
};
</script>

<style lang="scss" >
@import '@/assets/sass/_index.scss';
</style>
