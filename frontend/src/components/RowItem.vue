<template>
    <tr class="basic-item" v-if="item">
        <td v-for="column in columns" :key="column">
            <div>{{ item[column] }}</div>
        </td>
        <td v-if="actionItems" class="action-items">
            <Button type="primary-outline" class="mr-2" @click="onUpdate(item)">Update</Button>
            <Button type="primary" @click="onView(item)">View</Button>
        </td>
    </tr>
</template>
  
<style lang="scss">
.action-items {
    display: flex;
    justify-content: center;
    margin: 10px auto;
    padding-left: 4px;
    padding-right: 4px;
}

.basic-item {
    display: table-row;
    margin: 10px;
    /* Adjust the desired gap between rows using margin */
    td {
        padding: 4px;
    }
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
import Button from '@/components/button/Button.vue';
import SwitchValueDisplay from '@/components/form/display/SwitchValueDisplay.vue';

const RowItem = defineComponent({
    name: 'row-item',
    components: {
        Button,
        SwitchValueDisplay
    },
    emit: {
        onUpdate(item) {
            return !item;
        },
        onView(item) {
            return !item;
        },
    },
    props: {
        item: {
            type: Object,
            required: true
        },
        actionItems: {
            type: Boolean,
            required: true,
        },
        columns: {
            type: Array,
            required: true
        }
    },
    methods: {
        onUpdate(item) {
            this.$emit('onUpdate', item);
        },
        onView(item) {
            this.$emit('onUpdate', item);
        },
    }
});

export default RowItem;
</script>
    