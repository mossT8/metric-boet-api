<template>
  <div id="app">
    <Nav />

    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
import Nav from '@/core/nav/Nav.vue';

export default {
  components: {
    Nav
  },
  mounted() {
    document.title = "Metric Boet Portal";
  },
};
</script>

<style lang="scss" >
@import '@/assets/sass/main.scss';
</style>
