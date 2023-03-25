<template>
  <div>
    <h2>Html Pages</h2>
    <table>
      <thead class="w-100">
        <tr>
          <th v-for="column in columns" :key="column.value">{{ column.heading }}</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <page-item :html-page="htmlPage" v-for="htmlPage in htmlPages" :key="htmlPage.url" :columns="columnFields" />
      </tbody>
    </table>
  </div>
</template>

<style lang="scss" scoped>
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
    PageItem
  },
  data() {
    return {
      columns: 
      [{ value: 'url', heading: 'Url' }, 
      { value: 'visibleForUsers', heading: 'Users' }, 
      { value: 'visibleForModerators', heading: 'Moderators' },
      { value: 'visibleOnNav', heading: 'Nav' },
      { value: 'createdAt', heading: 'Created At' },
      { value: 'updatedAt', heading: 'Updated At' },
      ]
    }
  },
  methods: {
    goToCreatePage() {
      this.$router.push('html-create');
    },
  },
  computed: {
    columnFields() {
      return this.columns.map(column => column.value);
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
  