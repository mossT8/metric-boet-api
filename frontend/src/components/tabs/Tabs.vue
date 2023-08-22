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
  
<style scoped>
.tabs-container {
    border: 1px solid #ddd;
    border-radius: 4px;
    overflow: hidden;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    font-family: Arial, sans-serif;
}

.tab-buttons {
    display: flex;
    background-color: #f0f0f0;
}

.tab-buttons button {
    flex: 1;
    padding: 10px 15px;
    border: none;
    background-color: transparent;
    border-bottom: 2px solid transparent;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.2s, border-bottom 0.2s;
}

.tab-buttons button.active {
    background-color: #fff;
    border-bottom: 2px solid #007bff;
}

.tab-content {
    padding: 20px;
    border-top: 1px solid #ddd;
}

.tab-content div[style*="display: block"] {
    display: block;
}
</style>