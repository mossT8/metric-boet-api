<template>
    <update-page />
</template>
  
<script>
// vue
import { defineComponent } from 'vue';

// components
import UpdatePage from "@/components/html/pages/UpdatePage.vue";

const HtmlUpdatePage = defineComponent({
    name: "html-update-page",
    components: { UpdatePage }
});

export default HtmlUpdatePage;
</script>
  