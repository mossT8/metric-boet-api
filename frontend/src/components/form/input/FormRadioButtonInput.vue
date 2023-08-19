<template>
    <div class="form-group">
        <label>{{ label }}</label>
        <div v-for="(option, index) in options" :key="index">
            <input type="radio" :name="name" :value="option.value" v-model="selectedOption" />
            <label>{{ option.label }}</label>
        </div>
        <ErrorMessage :name="name" class="error-feedback" />
    </div>
</template>
  
<script>
// vue
import { defineComponent } from 'vue'
// services
import { ErrorMessage } from 'vee-validate'

const FormRadioButtonInput = defineComponent({
    name: 'form-radio-button-input',
    props: {
        name: {
            type: String,
            required: true
        },
        label: {
            type: String,
            required: true
        },
        options: {
            type: Array,
            required: true
        },
        selectedValue: {
            type: String,
            required: false
        }
    },
    components: {
        ErrorMessage
    },
    data() {
        return {
            selectedOption: this.selectedValue || ''
        }
    },
    watch: {
        selectedOption: function (newVal) {
            this.$emit('update:selectedValue', newVal);
        }
    }
});

export default FormRadioButtonInput;
</script>
  