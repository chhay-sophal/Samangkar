<template>
  <div class="page-container">
    <Sidebar :links="sidebarLinks" />
    <div class="main-content">
      <div class="search-container">
        <label for="search">Search:</label>
        <input type="text" id="search" v-model="searchQuery" placeholder="Search username" @input="searchUsers">
      </div>
      <h2>User Table</h2>
      <transition name="fade">
        <table v-if="users.length > 0" class="user-table">
          <thead>
            <tr>
              <th>Username</th>
              <th>Email</th>
              <th>User Type</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(user, index) in displayedUsers" :key="index">
              <td>{{ user.username }}</td>
              <td>{{ user.email }}</td>
              <td>{{ user.userType }}</td>
              <td>{{ user.active ? 'Active' : 'Not Active' }}</td>
              <td>
                <router-link to="/user/edit"><button class="edit-button" @click="editUser(index)">Edit</button></router-link>
                <button class="delete-button" @click="deleteUser(index)">Delete</button>
                <router-link to="/user/detail"><button class="detail-button" >Details</button></router-link>
              </td>
            </tr>
          </tbody>
        </table>
        <p v-else>No users found.</p>
      </transition>
      <div class="pagination">
       <Pagination/>
      </div>
      <router-link to="/add"><button class="add-user-button">Add User</button></router-link>
    </div>
  </div>
</template>

<script>
import Sidebar from "./../../components/sideBar.vue"; // Adjust the path as per your project structure
import Pagination from "./Pagination.vue";

export default {
  name: 'UserManagementPage',
  components: {
    Sidebar,
    Pagination
},
  data() {
    return {
      sidebarLinks: [
        { text: 'User Management', icon: 'mdi-account-multiple' },
        // Add more sidebar links as needed
      ],
      users: [
        { username: 'user1', email: 'user1@example.com', userType: 'User', active: true },
        { username: 'user2', email: 'user2@example.com', userType: 'Admin', active: false },
        // Add more users as needed
      ],
      currentPage: 1,
      usersPerPage: 10
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.users.length / this.usersPerPage);
    },
    displayedUsers() {
      const start = (this.currentPage - 1) * this.usersPerPage;
      const end = start + this.usersPerPage;
      return this.users.slice(start, end);
    }
  },
  methods: {  searchUsers() {
      // Perform search logic here
    },
    editUser(index) {
      // Logic to edit user
      console.log('Edit user:', this.users[index]);
    },
    deleteUser(index) {
      // Logic to delete user
      console.log('Delete user:', this.users[index]);
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    }
  }
}
</script>

<style scoped>
/* Existing styles */
.search-container {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
}

.search-container label {
  margin-right: 10px;
}

.search-container input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.page-container {
  display: flex;
}

.main-content {
  flex: 1;
  padding: 20px;
  margin-left: 250px;
}

.user-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

.user-table th,
.user-table td {
  padding: 12px;
  border: 1px solid #ccc;
}

.user-table th {
  background-color: #f0f0f0;
  font-weight: bold;
  text-align: left;
}

.user-table td {
  text-align: left;
}

.user-table tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
  opacity: 0;
}

.add-user-button {
  padding: 12px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s ease;
}

.add-user-button:hover {
  background-color: #0056b3;
}

.detail-button,
.edit-button,
.delete-button {
  padding: 8px 12px;
  margin-right: 6px;
  background-color: #28a745;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.3s ease;
}

.edit-button:hover,
.delete-button:hover {
  background-color: #218838;
}

.delete-button {
  background-color: #dc3545;
}
.detail-button{
  background-color: rgb(140, 14, 172);
}
.delete-button:hover {
  background-color: #c82333;
}

.pagination {
  margin-top: 20px;
}

.pagination-button {
  padding: 8px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s ease;
}

.pagination-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.pagination-button:hover {
  background-color: #0056b3;
}
</style>
