<template>
    <div class=" container jumbotron">
        <div v-if="error()" class="alert" :class="successful() ? 'alert-success' : 'alert-danger'">
            {{ error() }}
        </div>
        <div v-if="message() && message() !== 'Success!'" class="alert-success">{{ message() }}</div>
        <div v-if="isLoading()">Loading...</div>
        <UserForm v-else @onSubmit="updateUser" @onCancel="onCancel" :firstName="firstName" :username="username" :lastName="lastName"
            :email="email" :phone="phone" :isSigningUp="false" />
    </div>
</template>
  
<script>
// Vue
import { ref, onMounted, defineComponent } from 'vue'
// Service
import userService from "@/services/user-service";
import authService from '@/services/auth.service';
import router from '@/router/router';
// Component
import UserForm from "@/components/user/UserForm.vue";
import { useAsyncWrapper } from "@/composables/util/async/useAsyncWrapper";


const UpdateCurrentUser = defineComponent({
    name: "update-user",
    components: {
        UserForm
    },
    setup() {

        const firstName = ref("");
        const lastName = ref("");
        const email = ref("");
        const phone = ref("");
        const username = ref("");

        const { isLoading, message, error, callAsync, successful } = useAsyncWrapper();

        const loadUser = () => {
            const loggedInUser = authService.username();
            userService.getUserByUsername(loggedInUser).then(
                (response) => {
                    firstName.value = response.firstName;
                    lastName.value = response.lastName;
                    username.value = response.username;
                    email.value = response.email;
                    phone.value = response.phone;
                });
        };

        function updateUser(user) {
            callAsync(async () => {
                userService.update(user);
            }, '', 'Failed to update User!');
        };

        function onCancel() {
            router.push('home');
        };

        onMounted(async () => {
            await callAsync(async () => { loadUser() }, '', 'Failed to load User!');
        });

        return {
            username,
            firstName,
            lastName,
            email,
            phone,
            isLoading,
            successful,
            message,
            error,
            callAsync,
            onCancel,
            updateUser
        };
    }
});
export default UpdateCurrentUser;
</script>
  