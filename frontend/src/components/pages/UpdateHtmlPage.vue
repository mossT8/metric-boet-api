<template>
    <div class="container">
        <page-form v-if="!loading" :url="htmlUrl" :html="content" /> 
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
            loading: false,
        };
    },
    computed: {
        htmlUrl() {
            return this.$route.params['url'] || "";
        },
    },
    mounted() {
        this.loading = true;
        htmlPagesService.getPageByUrl(this.htmlUrl).then(
            (response) => {
                this.content = response.html;
            },
            (error) => {
                this.content =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();
            }
        ).finally(() => {
            this.loading = false;
        });
    },
};
</script>
  