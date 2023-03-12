<template>
    <div class="card card-container">
        <Form @submit.prevent="submitForm">
            <form-text-input name="url" label="Url" />
            <Editor :initialContent="htmlInnverValue" />

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
import { Editor } from "@tiptap/vue-3";
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
            message: ''
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
    , mounted() {
        this.urlInnverValue = this.url || '';
        this.htmlInnverValue = this.html || '';
    }
}
</script>
