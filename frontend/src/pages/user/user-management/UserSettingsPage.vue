<template>
    <UpdateCurrentUser />
</template>
  
<script>
// Vue
import { defineComponent } from "vue";
// Service
import authService from "@/services/auth.service";
// Components
import UpdateCurrentUser from "@/components/user/UpdateCurrentUser.vue";

const UserSettingsPage = defineComponent({
    name: "user-settings-page",
    components: {
        UpdateCurrentUser,
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
        // if (!this.loggedIn) {
        //     this.$router.push("/home");
        // }
    },
    computed: {
        username() {
            return authService.getUsername();
        }
    },
});

export default UserSettingsPage;
</script>