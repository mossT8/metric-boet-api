<template>
    <div class=" container jumbotron">
        <h2>Users</h2>
        <div class="center-table">
            <table>
                <thead>
                    <tr>
                        <th v-for="column in columns" :key="column.value" class="inline-text">{{ column.heading }}</th>
                        <th class="inline-text">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <UserItem v-for="user in users" @onUpdate="onUpdateUser" @onView="onViewUser" :user="user"
                        :key="user.username" :columns="columnFields" />
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
// vue
import { defineComponent } from 'vue';
// components
import UserItem from '@/components/user/UserItem.vue';

const UserList = defineComponent({
    name: "user-list",
    components: {
        UserItem
    },
    data() {
        return {
            columns:
                [{ value: 'username', heading: 'Username' },
                { value: 'firstName', heading: 'First Name' },
                { value: 'lastName', heading: 'Last Name' },
                { value: 'email', heading: 'Email' },
                { value: 'phone', heading: 'Phone' },
                { value: 'createdAt', heading: 'Created At' },
                { value: 'updatedAt', heading: 'Updated At' },
                ]
        }
    },
    methods: {
        goToCreatePage() {
            this.$router.push('user-create');
        },
        onUpdateUser(user) {
            this.$router.push("/user-edit/" + user.username);
        },
        onViewUser(user) {
            this.$router.push("/user-view/" + user.username);
        }
    },
    computed: {
        columnFields() {
            return this.columns.map(column => column.value);
        }
    },
    props: {
        users: {
            type: Array,
            required: true
        }
    }
});

export default UserList;

</script>
  