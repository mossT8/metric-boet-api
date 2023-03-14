<template>
    <div class="card card-container-xl">
        <Form @submit.prevent="submitForm" :validation-schema="schema" :initialValues="initialValues">
            <form-text-input v-model="urlInnverValue" name="url" label="Url" :disabled="true" />
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
import { htmpPageSchema } from "@/types/pages/html-pages";
import Editor from "@/core/editor/Editor.vue";
export default {
    components: {
        Form,
        FormTextInput,
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
        }
    },
    data() {
        return {
            urlInput: this.url,
            htmlInput: this.html,
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
        htmlInnverValue: {
            get() {
                return this.htmlInput
            },
            set(newValue) {
                this.htmlInput = newValue
            }
        },
        initialValues() {
            return {
                url: this.urlInnverValue,
                html: this.htmlInnverValue
            }
        },
        btnTxt() {
            return this.html.length > 0 || this.url.length > 0 ? 'Update' : 'Create';
        }
    },
    methods: {
        submitForm() {
            const pageData = {
                url: this.url,
                html: this.html
            }
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
