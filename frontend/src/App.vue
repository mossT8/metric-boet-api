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
