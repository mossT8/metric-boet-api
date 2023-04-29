<template>
  <tr>
    <td v-for="column in columns" :key="column">
      <div v-if="column == 'visibleForUsers' || column == 'visibleForModerators' || column == 'visibleOnNav'">
        <SwitchValueDisplay :value="htmlPage[column]" />
      </div>
      <div v-else>{{ htmlPage[column] }}</div>
    </td>
    <td class="d-flex d-inline">
      <Button type="primary-outline" class="mr-2" @click="onUpdate(htmlPage)">Update</Button>
      <Button type="primary" @click="onView(htmlPage)">View</Button>
    </td>
  </tr>
</template>
  
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
  