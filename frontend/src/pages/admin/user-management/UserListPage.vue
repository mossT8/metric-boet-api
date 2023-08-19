<template>
    <UserList v-if="!loading" :users="users" />
    <div v-else>
        <p v-if="message">{{ message }}</p>
        <p v-else>Loading...</p>
    </div>
</template>
  
<script>
// vue
import { defineComponent, ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

// services
import userService from '@/services/user.service';
import { useAuthModuleFeatures } from '@/composables/store/useAuthModule';

// components
import UserList from '@/components/user/UserList.vue';

const UserListPage = defineComponent({
    name: "user-list-page",
    components: {
        UserList
    },
    setup() {
        const router = useRouter();
        const { loggedIn } = useAuthModuleFeatures();
        const users = ref([]);
        const loading = ref(false);
        const message = ref('');

        onMounted(async () => {
            loading.value = true;
            try {
                users.value = await userService.getAllUsers();
            } catch (error) {
                message.value = error.message;
            } finally {
                loading.value = false;
            }
        });

        if (!loggedIn.value) {
            router.push('/home');
        }

        return {
            users,
            loading,
            message
        };
    }
});

export default UserListPage;
</script>
  