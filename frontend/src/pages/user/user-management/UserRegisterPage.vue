<template>
  <user-form />
</template>

<script>

// vue
import { defineComponent } from 'vue'

// components
import UserForm from "@/components/user/UserForm.vue";

const UserRegsterPage = defineComponent({
  name: "user-register-page",
  components: {
    UserForm,
  },
  data() {

    return {
      successful: false,
      loading: false,
      currentStep: 1,
      message: "",
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  mounted() {
    if (!this.loggedIn) {
      this.$router.push("/home");
    }
  },
  methods: {
    handleRegister(user) {

      this.message = "";
      this.successful = false;
      this.loading = true;

      this.$store.dispatch("auth/register", user).then(
        (data) => {
          this.message = data.message;
          this.successful = true;
          this.loading = false;
        },
        (error) => {
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
          this.successful = false;
          this.loading = false;
        }
      );
    },
  },
});

export default UserRegsterPage;
</script>