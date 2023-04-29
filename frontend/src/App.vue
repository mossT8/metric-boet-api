<template>
  <simple-layout />
</template>

<script>
import SimpleLayout from "@/components/layout/Layout.vue";

export default {
  components: {
    SimpleLayout
  },
  mounted() {
    document.title = "Metric Boet Portal";
  },
};
</script>

<style lang="scss" >
@import '@/assets/sass/_index.scss';
</style>
