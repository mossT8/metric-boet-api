<template>
    <div class=" container jumbotron">
        <div v-if="error()" class="alert" :class="successful() ? 'alert-success' : 'alert-danger'">
            {{ error() }}
        </div>
        <div v-if="message() && message() !== 'Success!'" class="alert-success">{{ message() }}</div>
        <div v-if="isLoading()">Loading...</div>
        <UserForm v-else @onSubmit="updateUser" @onCancel="onCancel" :firstName="firstName" :username="username"
            :lastName="lastName" :email="email" :phone="phone" :isSigningUp="false" />
    </div>
</template>
  
<script>
// Vue
import { ref, onMounted, defineComponent, getCurrentInstance } from 'vue'

// Service
import userService from "@/services/user-service";
import router from '@/router/router';

// Component
import UserForm from "@/components/user/UserForm.vue";
import { useAsyncWrapper } from "@/composables/util/async/useAsyncWrapper";

const UpdateUserPage = defineComponent({
    name: "update-user-page",
    components: {
        UserForm,
    },
    setup() {
        const username = ref("");
        const instance = getCurrentInstance()
        if (instance) {
            if (instance.proxy) {
                username.value = instance.proxy.$route.params['username'].toString();
            }
        }

        const firstName = ref("");
        const lastName = ref("");
        const email = ref("");
        const phone = ref("");

        const { isLoading, message, error, callAsync, successful } = useAsyncWrapper();

        const loadUser = () => {
            userService.getUserByUsername(username.value).then(
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
export default UpdateUserPage;
</script>
  