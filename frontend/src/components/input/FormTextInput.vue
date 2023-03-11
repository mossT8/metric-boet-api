<template>
    <div class="form-group">
        <label :for="name">{{ label }}</label>
        <Field :name="name" :type="type" :disabled="disabled" class="form-control" />
        <ErrorMessage :name="name" class="error-feedback" />
    </div>
</template>
  
<script>
import { defineComponent } from 'vue'
import { Field, ErrorMessage } from 'vee-validate'

export default defineComponent({
    name: 'FormTextInput',
    props: {
        name: {
            type: String,
            required: true
        },
        label: {
            type: String,
            required: true
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
    components: {
        Field,
        ErrorMessage
    }
})
</script>
  