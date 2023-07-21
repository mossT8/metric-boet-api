<template>
  <div class="sidebar" :data="backgroundColor">
    <div class="sidebar-wrapper" id="style-3">
      <div class="w-100">
        <ul class="nav">
          <slot name="links">
            <div class="sidebar-container">

              <div class="sidebar-links-top">
                <!-- home option -->
                <h5 class="logo-section" @click.stop="goHome">
                  <img id="logo-img" src="@/assets/logo.png" class="logo-img" />
                  Metric Boet API
                </h5>

                <!-- Main Options -->
                <sidebar-link v-for="link of sidebarLinksTop" v-roles="link.roles" @hideSideBar="hideSidebar"
                  :key="link.route" :to="link.route" :name="link.label" :icon="link.icon" />
              </div>

              <div class="sidebar-links-bottum">
                <!-- Profile -->
                <sidebar-link v-if="loggedIn" @hideSideBar="hideSidebar" key="user-profile" to="/user-profile"
                  name="Profile" icon="fa-solid fa-user-circle" />

                <!-- Settings -->
                <sidebar-link v-if="loggedIn" @hideSideBar="hideSidebar" key="settings" to="/settings" name="Settings"
                  icon="fa-solid fa-cog" />

                <!-- Logout -->
                <sidebar-link v-if="loggedIn" @hideSideBar="hideSidebar" key="user-logout" to="/user-logout"
                  name="Logout" icon="fa-solid fa-sign-out-alt" />

                <!-- Login -->
                <sidebar-link v-else @hideSideBar="hideSidebar" key="user-login" to="/user-login" name="Login"
                  icon="fa-solid fa-sign-in-alt" />
              </div>
            </div>
          </slot>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>

// vue
import { defineComponent } from 'vue';
// services
import { useAuthModuleFeatures } from "@/composables/store/useAuthModule";
import { Roles } from '@/types/roles';
// components
import SidebarLink from "@/components/layout/sidebar/SidebarLink.vue";
import ConfirmationModal from "@/components/modals/ConfirmationModal.vue";


const Sidebar = defineComponent({
  name: 'sidebar',
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
    SidebarLink,
    ConfirmationModal,
  },
  data() {
    return {
      linkHeight: 65,
      activeLinkIndex: 0,
      windowWidth: 0,
      isWindows: false,
      hasAutoHeight: false,
      links: [],
      showModalFlag: false,
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
    isOptionPublic(option) {
      console.log(option);
      if (option && option.roles) {
        console.log(option.roles);
        return option.roles.includes(Roles.ROLE_PUBLIC);
      }
      return false;
    },
    goHome() {
      this.$router.push('home');
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
    showLogoutConfirmationModal() {
      this.showModalFlag = true;
    },

    hideModal() {
      this.showModalFlag = false;
    },

    logoutClicked() {
      this.logout();
      this.hideModal();
    },
  },
  setup() {
    return {
      ...useAuthModuleFeatures(),
    }
  },
  munted() {
    this.$watch("$route", this.findActiveLink, {
      immediate: true
    });
  }
});

export default Sidebar;
</script>
