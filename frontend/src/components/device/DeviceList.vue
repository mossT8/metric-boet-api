<template>
    <table>
        <thead class="w-100">
            <tr>
                <th v-for="column in columns" :key="column">{{ column }}</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <device-item v-for="device in devices" :key="device.id" :device="device" :columns="columns"
                :onUpdate="updateDevice" :onView="viewDevice" />
        </tbody>
    </table>
</template>

<style lang="scss" scoped>
th,
td {
    border: 1px solid black;
    padding: 8px;
}

thead th {
    width: 25%;
}
</style>
  
<script>
import DeviceItem from '@/components/device/DeviceItem.vue'

export default {
    name: "DeviceList",
    components: {
        DeviceItem
    },
    props: {
        devices: {
            type: Array,
            required: true
        },
    },
    data() {
        return {
            columns: ['name', 'location', 'status']
        }
    },
    methods: {
        updateDevice(device) {
            // Implement update logic here
            console.log(device);
        },
        viewDevice(device) {
            // Implement view logic here
            console.log(device);

        }
    }
}
</script>
  