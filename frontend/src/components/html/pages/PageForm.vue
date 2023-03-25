<template>
    <div class="card card-container-xl">
        <Form class="portal-form" @submit="submitForm" >
            <form-text-input v-model="urlInnverValue" name="url" label="Url" :disabled="true" />
            <div class="d-flex justify-content-between">
                <form-switch-input v-model="visibleForUsersInnverValue" :value="visibleForUsersInnverValue" :inline="true"
                    :color="successColor" :isEnabled="true" name="visibleForUsers" label="Visible for Users" />
                <form-switch-input v-model="visibleForModeratorsInnverValue" :value="visibleForModeratorsInnverValue"
                    :inline="true" :color="successColor" :isEnabled="true" name="visibleForModerators"
                    label="Visible for Moderators" />
                <form-switch-input v-model="visibleOnNavInnverValue" :value="visibleOnNavInnverValue" :inline="true"
                    :color="successColor" :isEnabled="true" name="visibleOnNav" label="Visible on Navbar" />
            </div>
            <Editor v-model="htmlInnverValue" :value="htmlInnverValue" heading="Page Editor" :showButtons="false" />

            <button class="btn btn-primary float-right" :disabled="loading">
                <span v-show="loading" class="spinner-border spinner-border-sm" ></span>
                {{ btnTxt }}
            </button>
        </Form>
        <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
            {{ message }}
        </div>
    </div>
</template>

<script>
import { Form } from "vee-validate";
import FormTextInput from "@/components/input/FormTextInput.vue";
import FormSwitchInput from "@/components/input/FormSwitchInput.vue";
import { htmpPageSchema } from "@/types/pages/html-pages";
import Editor from "@/core/editor/Editor.vue";
export default {
    components: {
        Form,
        FormTextInput,
        FormSwitchInput,
        Editor
    },
    props: {
        url: {
            type: String,
            default: ''
        },
        html: {
            type: String,
            default: ''
        },
        visibleForUsers: {
            type: Boolean,
            default: true
        },
        visibleForModerators: {
            type: Boolean,
            default: true
        },
        visibleOnNav: {
            type: Boolean,
            default: true
        },
    },
    data() {
        return {
            urlInnverValue: '',
            htmlInnverValue: '',
            visibleForUsersInnverValue: false,
            visibleForModeratorsInnverValue: false,
            visibleOnNavInnverValue: false,
            loading: false,
            message: '',
            schema: htmpPageSchema,
        }
    },
    computed: {
        successColor() {
            return "#F53731";
        },
        initialValues() {
            return {
                url: this.urlInnverValue,
                html: this.htmlInnverValue,
                visibleForUsers: this.visibleForUsersInnverValue,
                visibleForModerators: this.visibleForModeratorsInnverValue,
                visibleOnNav: this.visibleOnNavInnverValue,
            }
        },
        btnTxt() {
            return 'Update';
        }
    },
    methods: {
        submitForm() {
            const pageData = {
                url: this.urlInnverValue,
                html: this.htmlInnverValue,
                visibleForUsers: this.visibleForUsersInnverValue,
                visibleForModerators: this.visibleForModeratorsInnverValue,
                visibleOnNav: this.visibleOnNavInnverValue,
            };
            console.log(pageData);
            this.$emit('onSubmit', pageData);
        }
    }
    ,
    mounted() {
        this.urlInnverValue = this.url || '';
        this.htmlInnverValue = this.html || '';
        this.visibleForUsersInnverValue = this.visibleForUsers || false;
        this.visibleForModeratorsInnverValue = this.visibleForModerators || false;
        this.visibleOnNavInnverValue = this.visibleOnNav || false;
    },
    watch: {
        html(newValue) {
            this.htmlInnverValue = newValue;
        },
        url(newValue) {
            this.urlInnverValue = newValue;
        },
        visibleForUsers(newValue) {
            this.visibleForUsersInnverValue = newValue;
        },
        visibleForModerators(newValue) {
            this.visibleForModeratorsInnverValue = newValue;
        },
        visibleOnNav(newValue) {
            this.visibleOnNavInnverValue = newValue;
        },

    }
}
</script>
