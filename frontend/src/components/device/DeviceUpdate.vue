<template>
    <DeviceForm :is-registering="false" @onSubmit="handleUpdate" />
</template>
  
<script >
import DeviceForm from "@/components/device/DeviceForm.vue";
import DeviceService from "@/services/device.service";

export default {
    name: "DeviceUpdate",
    components: {
        DeviceForm
    },
    data() {

        return {
            successful: false,
            loading: false,
            device: null,
            currentStep: 1,
            message: "",
        };
    },
    async created() {
        await this.loadDevice(this.deviceId);
    },
    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        },
        deviceId() {
            return this.$route.params.uid || 0;
        }
    },
    mounted() {
        if (this.loggedIn) {
            this.$router.push("/device/" + this.deviceId + "/update");
        }
    },
    methods: {
        handleRegister(device) {
            DeviceService.addDevice(device)
        },
        async loadDevice(deviceId) {
            if (deviceId > 0) {
                this.loading = true;
                try {
                    this.device = await DeviceService.getDeviceByUid(deviceId);
                } catch (error) {
                    this.message = error.message;
                } finally {
                    this.loading = false;
                }
            }
        }
    },
    watch: {
        deviceId(newValue) {
            if (newValue > 0) {
                this.loadDevice(newValue);
            }
        },
    }
};
</script>