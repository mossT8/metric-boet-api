<template>
  <div class="container">
    <header class="jumbotron">
      <Editor :initialContent="content" />
    </header>
  </div>
</template>

<script>
import UserService from "@/services/user.service";
import Editor from "@/main";
export default {
  name: "Admin",
  components: {
    Editor,
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
};
</script>
