<template>
  <tr>
    <td v-for="column in columns" :key="column">{{ htmlPage[column] }}</td>
    <td class="d-flex d-inline">
      <Button type="primary-outline" class="mr-2" @click="onUpdate(htmlPage)">Update</Button>
      <Button type="primary" @click="onView(htmlPage)">View</Button>
    </td>
  </tr>
</template>
  
<script>
import Button from "@/components/button/Button.vue"

export default {
  name: "page-item",
  components: {
    Button,
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
  