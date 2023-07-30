<template>
    <RowItem v-if="user" :item="user" :columns="columns" :actionItems="true" @onView="onView" @onUpdate="onUpdate" />
</template>

<style lang="scss">
.action-items {
    display: flex;
    justify-content: center;
    margin: 10px auto;
    padding-left: 4px;
    padding-right: 4px;
}

.page-item {
    display: table-row;
    margin-bottom: 10px;
    /* Adjust the desired gap between rows using margin */
}

.switch-item {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>

<script>
// vue
import { defineComponent } from 'vue';
// components
import RowItem from "@/components/RowItem.vue";
import Button from "@/components/button/Button.vue"
import SwitchValueDisplay from "@/components/form/display/SwitchValueDisplay.vue";

const UserItem = defineComponent({
    name: "user-item",
    components: {
        Button,
        SwitchValueDisplay,
        RowItem,
    },
    props: {
        user: {
            type: Object,
            required: true
        },
        columns: {
            type: Array,
            required: true
        }
    },
    methods: {
        onUpdate(user) {
            this.$emit('onUpdate', user);
        },
        onView(user) {
            this.$emit('onView', user);
        },
    }
});

export default UserItem;
</script>
