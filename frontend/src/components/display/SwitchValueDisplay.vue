<template>
    <div class="switch-value-display-container" :style="{ '--color': color }">
        <div class="value-dot"></div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
    props: {
        positiveColorValue: {
            type: String,
            default: '#28a745'
        },
        negativeColorValue: {
            type: String,
            default: '#F53731'
        },
        value: {
            type: Boolean,
            default: false
        },
    },
    computed: {
        positiveColorDefault() {
            return this.positiveColorValue || "#28a745";
        },
        negativeColorDefault() {
            return this.negativeColorValue || "#F53731";
        },
        options() {
            return [this.positiveLabelDefault, this.negativeLabelDefault];
        },
        color() {
            return this.value ? this.positiveColorDefault : this.negativeColorDefault;
        },
    },
});

</script>