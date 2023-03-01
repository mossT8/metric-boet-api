<template>
    <button class="normal" :class="type" :disabled="disabled" @click="callback">
        <slot></slot>
    </button>
</template>

<style lang="scss" >
button {
    padding: 10px 20px;
    border: 1px solid #ddd;
    color: #333;
    background-color: #fff;
    border-radius: 4px;
    font-size: 14px;
    cursor: pointer;

    &[disabled] {
        cursor: not-allowed;
    }

    &.danger {
        background-color: #ff4949;
        color: #fff;
    }

    &.success {
        background-color: #13ce66;
        color: #fff;
    }

    &.info {
        background-color: #50bfff;
        color: #fff;
    }
}
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
  