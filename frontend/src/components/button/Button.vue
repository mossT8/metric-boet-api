<template>
    <button class="normal" :class="type" :disabled="disabled" @click="callback">
        <slot></slot>
    </button>
</template>
  
<script>
export default {
    props: {
        type: String,
        disabled: Boolean,
    },
    methods: {
        callback(event) {
            this.$emit('click', event);
        },
    },
};
</script>
  