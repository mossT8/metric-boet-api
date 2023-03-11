<template>
    <button class="normal" :class="type" :disabled="disabled" @click="callback">
        <slot></slot>
    </button>
</template>

<style lang="scss">
@import "@/assets/styles/button.scss";
</style>
  
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
  