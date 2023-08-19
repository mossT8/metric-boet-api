<template>
    <create-page />
</template>
  
<script>
// Vue
import { defineComponent } from 'vue';

// components
import CreatePage from "@/components/html/pages/CreatePage.vue";
const CreateHtmlPage = defineComponent({
    name: "create-html-page",
    components: { CreatePage }
});

export default CreateHtmlPage;
</script>
  