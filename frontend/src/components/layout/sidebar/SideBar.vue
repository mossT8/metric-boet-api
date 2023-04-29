<template>
  <div class="sidebar" :data="backgroundColor">
    <div class="sidebar-wrapper" id="style-3">
      <div class="w-100">
        <ul class="nav">
          <slot name="links">
            <div class="sidebar-container">

              <div class="sidebar-links-top">
                <h5 class="logo-section">
                  <img id="logo-img" src="@/assets/logo.png" class="logo-img" />
                  Metric Boet API
                </h5>
                <sidebar-link v-for="link of sidebarLinksTop" @hideSideBar="hideSidebar" :key="link.route"
                  :to="link.route" :name="link.label" :icon="link.icon" />
              </div>
              <div class="sidebar-links-bottum">
                <sidebar-link v-for="link of sidebarLinksBottum" @hideSideBar="hideSidebar" :key="link.route"
                  :to="link.route" :name="link.label" :icon="link.icon" />
              </div>
            </div>
          </slot>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
import SidebarLink from "@/components/layout/sidebar/SidebarLink.vue";

export default {
  name: 'side-bar',
  props: {
    title: {
      type: String,
      default: () => "Metric Boet API"
    },
    backgroundColor: {
      type: String,
      default: "vue"
    },
    activeColor: {
      type: String,
      default: "success",
      validator: value => {
        let acceptedValues = [
          "primary",
          "info",
          "success",
          "warning",
          "danger"
        ];
        return acceptedValues.indexOf(value) !== -1;
      }
    },
    sidebarLinksTop: {
      type: Array,
      default: () => []
    },
    sidebarLinksBottum: {
      type: Array,
      default: () => []
    },
    autoClose: {
      type: Boolean,
      default: true
    }
  },
  provide() {
    return {
      autoClose: this.autoClose,
      addLink: this.addLink,
      removeLink: this.removeLink
    };
  },
  components: {
    SidebarLink
  },
  data() {
    return {
      linkHeight: 65,
      activeLinkIndex: 0,
      windowWidth: 0,
      isWindows: false,
      hasAutoHeight: false,
      links: []
    };
  },
  computed: {
    /**
     * Styles to animate the arrow near the current active sidebar link
     * @returns {{transform: string}}
     */
    arrowMovePx() {
      return this.linkHeight * this.activeLinkIndex;
    },
    shortTitle() {
      return this.title.split(' ')
        .map(word => word.charAt(0))
        .join('').toUpperCase();
    }
  },
  methods: {
    findActiveLink() {
      this.links.forEach((link, index) => {
        if (link.isActive()) {
          this.activeLinkIndex = index;
        }
      });
    },
    hideSidebar() {
      this.$emit('hideSidebar');
    },
    addLink(link) {
      this.links.push(link);
    },
    reveLink(link) {
      const index = this.links.indexOf(link);
      if (index > -1) {
        this.links.splice(index, 1);
      }
    },
  },
  munted() {
    this.$watch("$route", this.findActiveLink, {
      immediate: true
    });
  }
};
</script>
