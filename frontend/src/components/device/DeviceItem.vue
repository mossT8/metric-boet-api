<template>
    <tr>
        <td v-for="column in columns" :key="column">{{ device[column] }}</td>
        <td class="d-flex d-inline">
            <Button type="primary-outline" class="mr-2" @click="onUpdate(device)">Update</Button>
            <Button type="primary" @click="onView(device)">View</Button>
        </td>
    </tr>
</template>
  
<script>
// vue
import { defineComponent } from 'vue'
// components
import Button from "@/components/button/Button.vue"

const DeviceItem = defineComponent({
    name: "DeviceItem",
    components: {
        Button,
    },
    props: {
        device: {
            type: Object,
            required: true
        },
        columns: {
            type: Array,
            required: true
        }
    },

    methods: {
        onUpdate() {
            this.$emit("onUpdate", this.device);
        },
        onView() {
            this.$emit("onView", this.device);

        }
    },
});

export default DeviceItem;
</script>
  