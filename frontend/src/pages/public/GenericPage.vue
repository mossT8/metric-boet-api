<template>
    <div class="container">
        <header class="jumbotron">
            <!-- Use this.content to display your HTML content -->
            <div v-if="success" v-html="content"></div>
            <NotFoundPage v-else />
        </header>
    </div>
</template>
  
<script>
// vue
import { defineComponent, ref } from 'vue';
// service
import { useAsyncWrapper } from "@/composables/util/async/useAsyncWrapper";
import htmlPagesService from '@/services/html-pages-service';
import NotFoundPage from '@/pages/NotFoundPage.vue';


const GenericPage = defineComponent({
    name: 'generic-page',
    components: {
        NotFoundPage
    },
    setup() {
        const content = ref('');

        const success = ref(true);

        return {
            ...useAsyncWrapper(),
            content,
            success
        };
    },
    watch: {
        '$route.params.pageName'(value) {
            console.log(value);
            this.fetchContent(value || 'home');
        }
    },
    mounted() {
        // Access route parameter 'pageName'
        const pageName = this.$route.params.pageName;
        this.fetchContent(pageName || 'home');
    },
    methods: {
        async fetchContent(pageName) {
            console.log(pageName);
            await this.callAsync(async () => { this.loadContent(pageName) }, '', 'Failed to load Page!');
        },
        async loadContent(pageName) {
            let page = null;
            let success = false;

            try {
                const response = await htmlPagesService.viewPageByUrl(pageName);
                page = response.html;
                success = true;
            } catch (error) {
                console.error("Error loading content:", error);
            }

            this.success = success;
            this.content = page;
        }

    },
});

export default GenericPage;
</script>
  