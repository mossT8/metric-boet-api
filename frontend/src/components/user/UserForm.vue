<template>
    <div class="col-md-12">
        <div class="card card-container">
            <img id="profile-img" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" class="profile-img-card" />
            <Form class="portal-form" @submit="handleRegister" :validation-schema="schema" :initial-values="intialValues">
                <div v-if="!successful">
                    <form-text-input name="firstName" label="First Name" />
                    <form-text-input name="lastName" label="Last Name" />
                    <form-text-input name="phone" label="Phone" />
                    <form-text-input name="username" label="Username" />
                    <form-text-input name="email" label="Email" />
                    <form-text-input name="password" label="Password" />
                    <div>
                        <button class="btn btn-primary float-right" :disabled="loading">
                            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                            {{ isSigningUp ? "Sign Up" : "Update" }}
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
import { defineComponent } from "vue";
import { userSchema } from "@/types/form/form";
import { Form } from "vee-validate";
import FormTextInput from "@/components/input/FormTextInput.vue";
export default defineComponent({
    name: "user-form",
    components: {
        Form,
        FormTextInput
    },
    props: {
        firstName: {
            type: String,
            required: false,
            default: "",
        },
        lastName: {
            type: String,
            required: false,
            default: "",
        },
        phone: {
            type: String,
            required: false,
            default: "",
        },
        username: {
            type: String,
            required: false,
            default: "",
        },
        email: {
            type: String,
            required: false,
            default: "",
        },
        password: {
            type: String,
            required: false,
            default: "",
        },
        isSigningUp: {
            type: Boolean,
            required: false,
            default: false,
        }
    },
    data() {
        const schema = userSchema;

        return {
            successful: false,
            loading: false,
            message: "",
            schema,
        };
    },
    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        },
        intialValues() {
            return {
                firstName: this.firstName,
                lastName: this.lastName,
                phone: this.phone,
                username: this.username,
                email: this.email,
                password: this.password
            }
        },
        btnTxt() {
            return this.isSigningUp ? "Sign Up" : "Update";
        },
        typeOptions() {
            return [{ value: "standard", label: "Standard" }, { value: "live_save", label: "Live Saver" }];
        },
        statusOptions() {
            return [{ value: "0", label: "Disabled" }, { value: "1", label: "Active" }];
        }
    },
    mounted() {
        if (this.loggedIn) {
            this.$router.push("/profile");
        }
    },
    methods: {
        handleRegister(device) {
            this.$emit("onSubmit", device);
        },
    },
});
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

  