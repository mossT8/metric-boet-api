<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img id="profile-img" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" class="profile-img-card" />
      <Form @submit="handleRegister" :validation-schema="schema">
        <div v-if="!successful">
          <div v-if="currentStep === 1">
            <h2>Step 1: Personal Info</h2>
            <div class="form-group">
              <label for="firstName">First Name</label>
              <Field name="firstName" type="text" class="form-control" />
              <ErrorMessage name="firstName" class="error-feedback" />
            </div>
            <div class="form-group">
              <label for="lastName">Last Name</label>
              <Field name="lastName" type="text" class="form-control" />
              <ErrorMessage name="lastName" class="error-feedback" />
            </div>
            <div class="form-group">
              <label for="phone">Phone</label>
              <Field name="phone" type="text" class="form-control" />
              <ErrorMessage name="phone" class="error-feedback" />
            </div>
            <div class="btn btn-primary float-right"  @click="currentStep++">Next</div>
          </div>

          <div v-if="currentStep === 2">
            <h2>Step 2: Account Info</h2>
            <div class="form-group">
              <label for="username">Username</label>
              <Field name="username" type="text" class="form-control" />
              <ErrorMessage name="username" class="error-feedback" />
            </div>
            <div class="form-group">
              <label for="email">Email</label>
              <Field name="email" type="email" class="form-control" />
              <ErrorMessage name="email" class="error-feedback" />
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <Field name="password" type="password" class="form-control" />
              <ErrorMessage name="password" class="error-feedback" />
            </div>
            <div class="btn btn-outline-primary"  @click="currentStep--">Previous</div>
            <button class="btn btn-primary float-right" :disabled="loading">
              <span v-show="loading" @click.stop="console.log(schema)" class="spinner-border spinner-border-sm"></span>
              Sign Up
            </button>
          </div>
        </div>
      </Form>

      <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import {userSchema} from "@/types/user"

export default {
  name: "Register",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = userSchema;

    return {
      successful: false,
      loading: false,
      currentStep: 1,
      message: "",
      schema,
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
  },
  methods: {
    handleRegister(user) {
      console.log(user)
      this.message = "";
      this.successful = false;
      this.loading = true;

      this.$store.dispatch("auth/register", user).then(
        (data) => {
          this.message = data.message;
          this.successful = true;
          this.loading = false;
        },
        (error) => {
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
          this.successful = false;
          this.loading = false;
        }
      );
    },
  },
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}

.error-feedback {
  color: red;
}
</style>
