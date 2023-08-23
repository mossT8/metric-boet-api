<template>
    <div class="tabs-container">
        <div class="tab-buttons">
            <button v-for="(tab, index) in tabs" :key="index" @click="activeTab = index"
                :class="{ active: activeTab === index }">
                <font-awesome-icon v-if="tab.icon" class="pr-2" :icon="tab.icon" />
                {{ tab.label }}
            </button>
        </div>
        <div class="tab-content">
            <div v-for="(tab, index) in tabs" :key="index" v-show="activeTab === index">
                <slot :name="'tab-' + index">{{ tab.content }}</slot>
            </div>
        </div>
    </div>
</template>
  
<script>
// vue
import { defineComponent } from 'vue'

const Tabs = defineComponent({
    props: {
        initialTabs: {
            type: Array,
            required: true,
        },
    },
    data() {
        return {
            activeTab: 0,
            tabs: this.initialTabs,
        };
    },
});

export default Tabs;
</script>
