<template>
    <button class="normal" :class="type" :disabled="disabled" @click="callback">
        <slot></slot>
    </button>
</template>
  
<script>
// vue
import { defineComponent } from 'vue'

const Button = defineComponent({
    name: "button",
    props: {
        type: String,
        disabled: Boolean,
    },
    methods: {
        callback(event) {
            this.$emit('click', event);
        },
    },
});

export default Button;
</script>
  