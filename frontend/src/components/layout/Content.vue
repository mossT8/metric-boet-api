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
export default {
    components: {}
};
</script>
  