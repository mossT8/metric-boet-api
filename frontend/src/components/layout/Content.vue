<template>
    <div class="content">
        <router-view v-slot="{ Component }">
            <Transition name="slide-fade">
                <component :is="Component" />
            </Transition>
        </router-view>
    </div>
</template>

<style lang="scss" scoped>
.slide-fade-enter-active {
    transition: all 0.3s ease-out;
}

.slide-fade-leave-active {
    transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
    transform: translateX(20px);
    opacity: 0;
}
</style>

<script>
export default {
    components: {}
};
</script>
  