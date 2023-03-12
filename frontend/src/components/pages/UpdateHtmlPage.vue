<template>
    <div class="container">
        <page-form :url="url" :content="content" />
    </div>
</template>
  
<script>
import htmlPagesService from "@/services/html-pages-service";
import PageForm from "@/components/html/pages/PageForm.vue";

export default {
    name: "update-html-page",
    components: {
        PageForm
    },
    data() {
        return {
            content: "",
            url: "",
        };
    },
    computed: {
        htmlUrl() {
            return this.$route.params['url'] || "";
        },
    },
    mounted() {
        htmlPagesService.getPageByUrl(this.htmlUrl).then(
            (response) => {
                this.content = response.data;
            },
            (error) => {
                this.content =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();
            }
        );
    },
};
</script>
  