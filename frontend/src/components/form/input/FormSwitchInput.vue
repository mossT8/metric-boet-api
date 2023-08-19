<template>
    <div class="form-group" :class="inline ? 'd-flex d-inline' : ''">
        <label>{{ label }}</label>
        <div class="switch-button-control" @click="toggle">
            <div class="switch-button" :class="{ 'enabled': innerValue, 'inline-switch': inline }"
                :style="{ '--color': color }">
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
// vue
import { defineComponent } from 'vue'
// services
import { ErrorMessage } from 'vee-validate'

const FormSwitchInput = defineComponent({    
    name: "form-switch-input",
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
        positiveColorValue: {
            type: String,
            default: '#28a745'
        },
        negativeColorValue: {
            type: String,
            default: '#F53731'
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
        },
        inline: {
            type: Boolean,
            default: false
        }
    },
    computed: {
        positiveLabelDefault() {
            return { value: true, label: this.positiveValue || "Yes" };
        },
        negativeLabelDefault() {
            return { value: false, label: this.negativeValue || "No" };
        },
        positiveColorDefault() {
            return this.positiveColorValue || "#28a745";
        },
        negativeColorDefault() {
            return this.negativeColorValue || "#F53731";
        },
        options() {
            return [this.positiveLabelDefault, this.negativeLabelDefault];
        },
        currentLabel() {
            return this.innerValue ? this.positiveLabelDefault.label : this.negativeLabelDefault.label;
        },
        color() {
            return this.innerValue ? this.positiveColorDefault : this.negativeColorDefault;
        },
    },
    watch: {
        innerValue(newValue) {
            this.$emit('input', newValue);
            this.$emit('change');
        },
        value(newValue) {
            this.innerValue = newValue;
        }
    },
    created() {
        if (this.value) {
            this.innerValue = this.value;
        }
    },
    methods: {
        toggle() {
            this.innerValue = !this.innerValue;
        }
    },
});

export default FormSwitchInput;
</script>