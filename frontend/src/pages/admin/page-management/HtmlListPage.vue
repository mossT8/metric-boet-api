<template>
    <PageList v-if="!loading" :htmlPages="htmlPages" />
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
import htmlPagesService from '@/services/html-pages-service';
import { useAuthModuleFeatures } from '@/composables/store/useAuthModule';

// components
import PageList from '@/components/html/pages/PageList.vue';

const HtmlListPage = defineComponent({
    name: "html-list-page",
    components: {
        PageList
    },
    setup() {
        const router = useRouter();
        const { loggedIn } = useAuthModuleFeatures();
        const htmlPages = ref([]);
        const loading = ref(false);
        const message = ref('');

        onMounted(async () => {
            loading.value = true;
            try {
                htmlPages.value = await htmlPagesService.getAllPages();
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
            htmlPages,
            loading,
            message
        };
    }
});

export default HtmlListPage;
</script>
  