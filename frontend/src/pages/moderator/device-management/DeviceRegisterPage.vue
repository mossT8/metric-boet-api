<template>
    <DeviceForm :is-registering="true" @onSubmit="handleRegister" />
</template>
  
<script >
import { deviceShema } from "@/types/device/device"
import DeviceForm from "@/components/device/DeviceForm.vue";
import DeviceService from "@/services/device.service";

export default {
    name: "device-register-page",
    components: {
        DeviceForm
    },
    data() {
        const schema = deviceShema;

        return {
            successful: false,
            loading: false,
            currentStep: 1,
            message: "",
            schema,
        };
    },
    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        },
    },
    mounted() {
        if (this.loggedIn) {
            this.$router.push("/device-add");
        }
    },
    methods: {
        handleRegister(device) {
            DeviceService.addDevice(device)
        },
    },
};
</script>