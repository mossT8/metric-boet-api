<template>
    <div class="form-group">
        <label :for="name">{{ label }}</label>
        <Field v-model="innerValue" :name="name" :type="type" :disabled="disabled" class="form-control" />
        <ErrorMessage :name="name" class="error-feedback" />
    </div>
</template>
  
<script>
import { defineComponent } from 'vue'
import { Field, ErrorMessage } from 'vee-validate'

export default defineComponent({
    name: 'form-text-imput',
    components: {
        Field,
        ErrorMessage
    },
    props: {
        name: {
            type: String,
            required: true
        },
        label: {
            type: String,
            required: true
        },
        value: {
            type: String,
            required: false,
            default: '',
        },
        type: {
            type: String,
            required: false,
            default: 'text'
        },
        disabled: {
            type: Boolean,
            required: false,
            default: false,
        }
    },
    data() {

        return {
            innerValue: '',
        }
    },
    watch: {
        innerValue(newValue) {
            this.$emit('input', newValue);
            this.$emit('change');
        },
        value(newValue) {
            this.innerValue = newValue;
        }
    }
})
</script>
  