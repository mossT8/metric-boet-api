<template>
    <div class="card card-container-xl">
        <Form class="portal-form" @submit.prevent="submitForm" :validation-schema="schema" :initialValues="initialValues">
            <form-text-input v-model="urlInnverValue" name="url" label="Url" :disabled="true" />
            <div class="d-flex justify-content-between">
                <form-switch-input v-model="visibleForUsersInnverValue" :color="successColor" :isEnabled="true"
                    name="visibleForUsers" label="Visible for Users" />
                <form-switch-input v-model="visibleForModeratorsInnverValue" :color="successColor" :isEnabled="true"
                    name="visibleForModerators" label="Visible for Moderators" />
                <form-switch-input v-model="visibleOnNavInnverValue" :color="successColor" :isEnabled="true"
                    name="visibleOnNav" label="Visible on Navbar" />
            </div>
            <Editor :initialContent="htmlInnverValue" heading="Page Editor" :showButtons="false" />

            <button class="btn btn-primary float-right" :disabled="loading">
                <span v-show="loading" class="spinner-border spinner-border-sm"></span>
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
            urlInput: this.url,
            htmlInput: this.html,
            visibleForUsersInput: this.visibleForUsers,
            visibleForModeratorsInput: this.visibleForModerators,
            visibleOnNavInput: this.visibleOnNav,
            loading: false,
            message: '',
            schema: htmpPageSchema,
        }
    },
    computed: {
        urlInnverValue: {
            get() {
                return this.urlInput
            },
            set(newValue) {
                this.urlInput = newValue
            }
        },
        successColor() {
            return "#F53731";
        },
        htmlInnverValue: {
            get() {
                return this.htmlInput
            },
            set(newValue) {
                this.htmlInput = newValue
            }
        },
        visibleForUsersInnverValue: {
            get() {
                return this.visibleForUsersInput
            },
            set(newValue) {
                this.visibleForUsersInput = newValue
            }
        },
        visibleForModeratorsInnverValue: {
            get() {
                return this.visibleForModeratorsInput
            },
            set(newValue) {
                this.visibleForModeratorsInput = newValue
            }
        },
        visibleOnNavInnverValue: {
            get() {
                return this.visibleOnNavInput
            },
            set(newValue) {
                this.visibleOnNavInput = newValue
            }
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
            return this.html.length > 0 || this.url.length > 0 ? 'Update' : 'Create';
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
            this.$emit('onSubmit', pageData);
        }
    }
    ,
    mounted() {
        this.urlInnverValue = this.url || '';
        this.htmlInnverValue = this.html || '';
    },
    watch: {
        html(newValue) {
            this.htmlInnverValue = newValue;
        },
        url(newValue) {
            this.urlInnverValue = newValue;
        }
    }
}
</script>
