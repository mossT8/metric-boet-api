<template>
  <div>
    <h2>Html Pages</h2>
    <table>
      <thead class="w-100">
        <tr>
          <th v-for="column in columns" :key="column">{{ column }}</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <page-item :html-page="htmlPage" v-for="htmlPage in htmlPages" :key="htmlPage.url" :columns="columns" />
      </tbody>
    </table>
  </div>
</template>

<style lang="scss">
th,
td {
  border: 1px solid black;
  padding: 8px;
}

thead th {
  width: 25%;
}
</style>

<script>
import PageItem from '@/components/html/pages/PageItem.vue';

export default {
  name: "page-list",
  components: {
    PageItem,
  },
  data() {
    return {
      columns: ['url', 'createdAt', 'updatedAt']
    }
  },
  props: {
    htmlPages: {
      type: Array,
      required: true
    }
  }
}
</script>
  