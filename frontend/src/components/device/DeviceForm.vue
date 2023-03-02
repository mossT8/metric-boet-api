<template>
    <div class="col-md-12">
        <div class="card card-container">
            <img id="profile-img" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" class="profile-img-card" />
            <Form @submit="handleRegister" :validation-schema="schema" :initial-values="intialValues">
                <div v-if="!successful">
                    <FormTextInput name="name" label="Name" />
                    <FormSelectListInput name="type" label="Typr" :options="typeOptions" />
                    <FormTextInput name="location" label="Location" />
                    <FormRadioButtonInput name="status" label="Status" :options="statusOptions" />
                    <FormTextInput name="token" label="Token" />
                    <button class="btn btn-primary float-right" :disabled="loading">
                        <span v-show="loading" @click.stop="console.log(schema)"
                            class="spinner-border spinner-border-sm"></span>
                        {{ btnTxt }}
                    </button>
                </div>
            </Form>

            <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
                {{ message }}
            </div>
        </div>
    </div>
</template>
  
<script>
import { Form } from "vee-validate";
import { deviceShema } from "@/types/device"
import FormTextInput from "@/components/input/FormTextInput.vue";
import FormSelectListInput from "@/components/input/FormSelectListInput.vue";
import FormRadioButtonInput from "@/components/input/FormRadioButtonInput.vue";


export default {
    name: "device-form",
    components: {
        Form,
        FormTextInput,
        FormSelectListInput,
        FormRadioButtonInput
    },
    props: {
        name: {
            type: String,
            required: false,
            default: "",
        },
        type: {
            type: String,
            required: false,
            default: "",
        },
        location: {
            type: String,
            required: false,
            default: "",
        },
        status: {
            type: Boolean,
            required: false,
            default: false,
        },
        token: {
            type: String,
            required: false,
            default: "",
        },
        isRegistering: {
            type: Boolean,
            required: false,
            default: false,
        }
    },
    data() {
        const schema = deviceShema;

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
        intialValues() {
            return {
                name: this.name,
                type: this.type,
                location: this.location,
                status: this.status,
                token: this.token
            }
        },
        btnTxt() {
            return this.isRegistering ? "Registering" : "Update";
        },
        typeOptions() {
            return [{ value: "standard", label: "Standard" }, { value: "live_save", label: "Live Saver" }];
        },
        statusOptions() {
            return [{ value: "standard", label: "Standard" }, { value: "live_save", label: "Live Saver" }];
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
  