<template>
    <div class="container">
        <header class="jumbotron">
            <!-- Use this.content to display your HTML content -->
            <div v-html="content"></div>
        </header>
    </div>
</template>
  
<script>
// vue
import { defineComponent, ref } from 'vue';
// service
import { useAsyncWrapper } from "@/composables/util/async/useAsyncWrapper";
import htmlPagesService from '@/services/html-pages.service';


const GenericPage = defineComponent({
    name: 'generic-page',
    setup() {
        const content = ref('');
        return {
            ...useAsyncWrapper(),
            content,
        };
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
            const page = await htmlPagesService.viewPageByUrl(pageName);
            console.log(page);
            this.content = page.html;
        }
    },
});

export default GenericPage;
</script>
  