<template>
  <div class="container">
    <header class="jumbotron">
      <Editor :initialContent="content" />
    </header>
  </div>
</template>

<script>
// vue
import { defineComponent } from 'vue'
// services
import UserService from "@/services/user.service";
// components
import Editor from "@/components/editor/Editor.vue";

const AdminBoardPage = defineComponent({
  name: "admin-board-page",
  components: {
    Editor
  },
  data() {
    return {
      content: "",
    };
  },
  mounted() {
    UserService.getAdminEditableUserContent().then(
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
});

export default AdminBoardPage;
</script>
