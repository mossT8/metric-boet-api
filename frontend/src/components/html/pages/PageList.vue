<template>
    <div class=" container jumbotron">
    <h2>Html Pages</h2>
    <div class="center-table">
      <table>
      <thead>
        <tr>
          <th v-for="column in columns" :key="column.value" class="inline-text">{{ column.heading }}</th>
          <th class="inline-text">Actions</th>
        </tr>
      </thead>
      <tbody>
        <page-item :html-page="htmlPage" v-for="htmlPage in htmlPages" :key="htmlPage.url" :columns="columnFields" />
      </tbody>
    </table>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.center-table {
  display: flex;
  justify-content: center;
}
th,
td {
  border: 1px solid black;
  padding: 8px;
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
  