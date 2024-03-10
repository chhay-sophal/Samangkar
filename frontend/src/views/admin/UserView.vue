<template>
  <div class="page-container">
    <Sidebar :links="sidebarLinks" />
    <div class="main-content">
      <div class="flex gap-5">
        <div class="search-container">
          <label for="search">Search:</label>
          <input type="text" id="search" v-model="searchQuery" placeholder="Search" @input="searchUsers">
        </div>
      </div>
      <h2>User Table</h2>
      <transition name="fade">
        <table v-if="users" class="user-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Username</th>
              <th>Email</th>
              <th>Role</th>
              <th>Status</th>
              <th>Profile</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(user, index) in users" :key="index">
              <td>{{ user.id }}</td>
              <td>{{ user.username }}</td>
              <td>{{ user.email }}</td>
              <td>{{ user.role }}</td>
              <td>{{ !user.deletedAt ? 'Active' : 'Deleted' }}</td>
              <td class="flex items-center justify-center">
                <div class="aspect-square overflow-hidden size-16">
                  <ImageViewer :imageData="user.profile" />
                </div>
              </td>
              <td>
                <router-link :to="`/user/edit/${user.id}`"><button class="edit-button">Edit</button></router-link>
                <button v-if="!user.deletedAt" class="delete-button" @click="deleteUser(user.id)">Delete</button>
                <router-link :to="`/user/detail/${user.id}`"><button class="detail-button" >Details</button></router-link>
              </td>
            </tr>
          </tbody>
        </table>
        <p v-else>No users found.</p>
      </transition>
      <div class="pagination">
       <Pagination :currentPage="currentPage" :totalPages="totalPages" :first="first" :last="last" @page-change="handlePageChange"/>
      </div>
      <router-link to="/add"><button class="add-user-button">Add User</button></router-link>
    </div>
  </div>
</template>

<script>
import Sidebar from "./../../components/AdminSidebar.vue"; // Adjust the path as per your project structure
import Pagination from "./Pagination.vue";
import ImageViewer from "@/components/ImageViewer.vue";
import http from "@/services/httpService";

export default {
  name: 'UserManagementPage',
  components: {
    Sidebar,
    Pagination,
    ImageViewer,
},
  data() {
    return {
      sidebarLinks: [
        { text: 'User Management', icon: 'mdi-account-multiple' },
        // Add more sidebar links as needed
      ],
      users: [],
      totalPages: 0,
      totalElements: 0,
      last: false,
      first: true,
      currentPage: 1,
      size: 5,
      searchQuery: "",
    };
  },
  methods: {  
    async deleteUser(userId) {
      try {
        if (confirm(`Are you sure to delete user with id ${userId}?`)) {
          await http.post(`api/users/delete/${userId}`);
          alert(`User with id ${userId} deleted successfully!`);
          this.fetchUserList();
        }
      } catch (error) {
        console.error(error);
      }
    },
    async searchUsers() {
      try {
        if (this.searchQuery) {
          const response = await http.get(`api/users/search?page=${this.currentPage - 1}&size=${this.size}&query=${this.searchQuery}`);
          this.users = response.data.content;
          this.totalPages = response.data.totalPages;
          this.totalElements = response.data.totalElements;
          this.first = response.data.first;
          this.last = response.data.last;
          if (!response.data) {
            this.fetchUserList();
          }
          console.log(this.users);
        } else {
          this.fetchUserList();
        }
      } catch (error) {
        console.error(error)
      }
    },
    async fetchUserList() {
      try {
        const response = await http.get(`api/users/get-all/pagable?page=${this.currentPage - 1}&size=${this.size}`);
        this.users = response.data.content;
        this.totalPages = response.data.totalPages;
        this.totalElements = response.data.totalElements;
        this.first = response.data.first;
        this.last = response.data.last;
        console.log(this.users);
      } catch (error) {
        console.error(error)
      }
    },
    async handlePageChange(newPage) {
      this.currentPage = newPage;
      await this.searchUsers();
    },
  },
  mounted() {
    this.fetchUserList();
    this.filterdUsers = this.users;
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
  /* background-color: #f0f0f0; */
  font-weight: bold;
  text-align: left;
}

.user-table td {
  text-align: left;
}

.user-table tbody tr:nth-child(even) {
  background-color: #f9f9f9;
  color: darkslategray;
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
