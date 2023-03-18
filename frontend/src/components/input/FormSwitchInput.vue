<template>
    <div class="form-group">
        <label>{{ label }}</label>
        <div class="switch-button-control" @click="toggle">
            <div class="switch-button" :class="{ enabled: innerValue }" :style="{ '--color': color }">
                <div class="button"></div>
                <div class="label">{{ currentLabel }}</div>
            </div>
            <div class="switch-button-label">
                <slot></slot>
            </div>
        </div>
        <ErrorMessage :name="name" class="error-feedback" />
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import { ErrorMessage } from 'vee-validate'
<style src="@vueform/toggle/themes/default.css"></style>

export default defineComponent({
    components: {
        ErrorMessage,
    },
    data() {
        return {
            innerValue: false
        }
    },
    props: {
        label: {
            type: String,
            default: ''
        },
        name: {
            type: String,
            default: ''
        },
        color: {
            type: String,
            default: ''
        },
        positiveValue: {
            type: String,
            default: 'On'
        },
        negativeValue: {
            type: String,
            default: 'Off'
        },
        value: {
            type: Boolean,
            default: false
        }
    },
    emits: ['update:modelValue'],
    computed: {
        positiveDefault() {
            return { value: true, label: this.positiveValue || "Yes" };
        },
        negativeDefault() {
            return { value: false, label: this.negativeValue || "No" };
        },
        options() {
            return [this.positiveDefault, this.negativeDefault];
        },
        currentLabel() {
            return this.innerValue ? this.positiveDefault.label : this.negativeDefault.label;
        }
    },
    watch: {
        value(newValue) {
            this.innerValue = newValue;
        },
        innerValue(newValue) {
            this.$emit('update:modelValue', newValue);
        }
    },
    methods: {
        toggle() {
            this.innerValue = !this.innerValue;
        }
    },
});

</script>