<template>
  <tr class="page-item">
    <td v-for="column in columns" :key="column">
      <div v-if="column == 'visibleForUsers' || column == 'visibleForModerators' || column == 'visibleOnNav'"
        class="switch-item">
        <SwitchValueDisplay :value="htmlPage[column]" />
      </div>
      <div v-else>{{ htmlPage[column] }}</div>
    </td>
    <td class="action-items">
      <Button type="primary-outline" class="mr-2" @click="onUpdate(htmlPage)">Update</Button>
      <Button type="primary" @click="onView(htmlPage)">View</Button>
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
import Button from "@/components/button/Button.vue"
import SwitchValueDisplay from "@/components/form/display/SwitchValueDisplay.vue";

export default {
  name: "page-item",
  components: {
    Button,
    SwitchValueDisplay
  },
  props: {
    htmlPage: {
      type: Object,
      required: true
    },
    columns: {
      type: Array,
      required: true
    }
  },
  methods: {
    onUpdate(htmlPage) {
      this.$router.push({ name: 'html-edit', params: { url: htmlPage.url } })
    },
    onView() {
        
    },
  }
}
</script>
  