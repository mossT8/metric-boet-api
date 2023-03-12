<template>
    <div>
        <page-list :html-pages="htmlPages" />
    </div>
</template>
  
<script>
import PageList from '@/components/html/pages/PageList.vue';
import htmlPagesService from '@/services/html-pages-service';

export default {
    name: 'admin-page-editor-list',
    components: {
        PageList,
    },
    data() {
        return {
            pages: [],
            load: false,
            message: "",
        }
    },
    mounted() {
        if (this.loggedIn) {
            this.$router.push("/html-pages");
        }
    },
    async created() {
        this.loading = true;
        try {
            this.pages = await htmlPagesService.getAllPages();
        } catch (error) {
            this.message = error.message;
        } finally {
            this.loading = false;
        }

    },
    computed: {
        htmlPages() {
            return this.pages;
        }
    }
};
</script>
  