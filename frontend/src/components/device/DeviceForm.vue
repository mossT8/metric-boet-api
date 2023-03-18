<template>
    <div class="col-md-12">
        <div class="card card-container">
            <img id="profile-img" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" class="profile-img-card" />
            <Form class="portal-form" @submit="handleRegister" :validation-schema="schema" :initial-values="intialValues">
                <div v-if="!successful">
                    <form-text-input name="name" label="Name" />
                    <form-select-list-input name="type" label="Type" :options="typeOptions" />
                    <form-text-input name="location" label="Location" />
                    <form-radio-button-input name="status" label="Status" :options="statusOptions" />
                    <form-text-input name="token" label="Token" />
                    <button class="btn btn-primary float-right" :disabled="loading">
                        <span v-show="loading" 
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
import { deviceShema } from "@/types/device/device"
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
};
</script>
