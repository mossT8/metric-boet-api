<template>
    <div>
        <router-view v-slot="{ Component }">
            <Transition name="slide-fade">
                <component :is="Component" />
            </Transition>
        </router-view>
    </div>
</template>

<script>
// vue
import { defineComponent } from 'vue'

const Content = defineComponent({
    components: {}
});

export default Content;
</script>
  