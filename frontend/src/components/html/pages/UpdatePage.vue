<template>
    <div class="container">
        <div v-if="error()" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">{{ error() }}</div>
        <div v-if="message() && message() !== 'Success!'" class="alert-success">{{ message() }}</div>
        <div v-if="isLoading()">Loading...</div>
        <page-form v-else @onSubmit="updatePage" :url="htmlUrl" :html="content" :visibleForModerators="visibleForModerators"
            :visibleForUsers="visibleForUsers" :visibleOnNav="visibleOnNav" />

    </div>
</template>
  
<script>
import { ref, onMounted, computed, getCurrentInstance } from 'vue'
import htmlPagesService from "@/services/html-pages-service";
import PageForm from "@/components/html/pages/PageForm.vue";
import { useAsyncWrapper } from "@/composables/util/async/useAsyncWrapper";

export default {
    name: "update-page",
    components: {
        PageForm
    },
    setup() {

        const content = ref("");
        const visibleForModerators = ref(false);
        const visibleOnNav = ref(false);
        const visibleForUsers = ref(false);

        const instance = getCurrentInstance()
        const htmlUrl = computed(() => {
            return instance?.proxy.$route.params['url'] || '';
        })

        const loadPage = () => {
            htmlPagesService.getPageByUrl(htmlUrl.value).then(
                (response) => {
                    content.value = response.html;
                    visibleForModerators.value = response.visibleForModerators;
                    visibleOnNav.value = response.visibleOnNav;
                    visibleForUsers.value = response.visibleForUsers;
                });
        }
        const updatePage = (page) => {
            callAsync(() => {
                htmlPagesService.update(page);
                console.log('jjj');
            }, '', 'Failed to update Html Page!');
        };
        const { isLoading, message, error, callAsync } = useAsyncWrapper();

        onMounted(() => {
            callAsync(() => { loadPage() }, '', 'Failed to load Html Page!');
        });

        return {
            htmlUrl,
            content,
            visibleForModerators,
            visibleOnNav,
            visibleForUsers,
            isLoading,
            message,
            error,
            callAsync,
            updatePage
        };
    }
};
</script>
  