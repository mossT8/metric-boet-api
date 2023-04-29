<template>
    <div class="form-group">
        <label :for="name">{{ label }}</label>
        <Field type="hidden" :name="name" v-model="selectedOption"/>
        <select  :name="name" class="form-control" v-model="selectedOption">
            <option value="" disabled selected>Please Select</option>
            <option v-for="(option, index) in options" :key="index" :value="option.value">{{ option.label }}</option>
        </select>
        <ErrorMessage :name="name" class="error-feedback" />
    </div>
</template>
  
<script>
import { defineComponent } from 'vue'
import { ErrorMessage , Field} from 'vee-validate'

export default defineComponent({
    name: 'form-select-list-input',
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
        ErrorMessage, Field
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
})
</script>
  