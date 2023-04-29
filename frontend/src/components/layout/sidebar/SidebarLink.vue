<template>
  <component :is="tag" @click.stop="hideSidebar" class="nav-item" v-bind="$attrs" tag="li">
    <a class="nav-link">
      <div class="w-100 d-flex justify-content-between">
        <font-awesome-icon v-if="icon" :icon="icon" />
        <p>{{ name }}</p>
      </div>
    </a>
  </component>
</template>

<style lang="scss" scoped>
p,
svg {
  margin: auto 0px;
}
</style>

<script>
export default {
  name: "sidebar-link",
  inheritAttrs: false,
  inject: {
    autoClose: {
      default: true
    },
    addLink: {
      default: () => { }
    },
    removeLink: {
      default: () => { }
    }
  },
  props: {
    name: String,
    icon: {
      type: String,
      default: "fa-solid fa-bars"
    },
    tag: {
      type: String,
      default: "router-link"
    }
  },
  methods: {
    hideSidebar() {
      if (this.autoClose) {
        this.$emit('hideSideBar');
      }
    },
    isActive() {
      return this.$el.classList.contains("active");
    }
  },
  mounted() {
    if (this.addLink) {
      this.addLink(this);
    }
  },
  beforeUnmount() {
    if (this.$el && this.$el.parentNode) {
      this.$el.parentNode.removeChild(this.$el)
    }
    if (this.removeLink) {
      this.removeLink(this);
    }
  }
};
</script>
