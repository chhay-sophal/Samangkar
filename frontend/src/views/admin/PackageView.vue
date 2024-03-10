<template>
    <div class="package-table-page">
      <Sidebar :links="sidebarLinks" />
      <div class="main-content">
        <h2>Package Table</h2>
        <router-link to="/admin/packages/add"><button class="add-package-button">Add Package</button></router-link>
        <div class="search-container">
          <label for="search">Search:</label>
          <input type="text" id="search" v-model="searchQuery" placeholder="Search" @input="searchPackages">
        </div>
        <div class="table-container">
          <table class="package-table">
            <thead>
              <tr>
                <th>Package Name</th>
                <th>Shop Name</th>
                <th>Description</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="pkg in packages" :key="pkg.id">
                <td>{{ pkg.name }}</td>
                <td>{{ pkg.shop.name }}</td>
                <td>{{ pkg.description }}</td>
                <td>
                  <router-link :to="`/admin/packages/edit/${pkg.id}`">
                    <button class="action-button edit-button">
                      Edit
                    </button>
                  </router-link>
                  
                  <!-- <button @click="editPackage(pkg)" class="edit-button">Edit</button> -->
                  <button @click="handleDelete(pkg.id)" class="delete-button">Delete</button>
                </td>
              </tr>
            </tbody>
          </table>
          <div>
            <Pagination :currentPage="currentPage" :totalPages="totalPages" :first="first" :last="last" @page-change="handlePageChange"/>
          </div>
        </div>
      </div>
    </div>
  </template>
  
<script>
import Sidebar from "./../../components/AdminSidebar.vue"; // Adjust the path as per your project structure
import Pagination from "./Pagination.vue";
import http from "@/services/httpService";
export default {
  components: {
    Sidebar,
    Pagination,
  },
  data() {
    return {
      sidebarLinks: [
        { text: 'Dashboard', icon: 'mdi-view-dashboard', route: '/dashboard' },
        { text: 'Users', icon: 'mdi-account', route: '/users' },
        { text: 'Shops', icon: 'mdi-store', route: '/shops' },
        // Add more sidebar links as needed
      ],
      packages: [],
      searchQuery: "",
      currentPage: 1,
      size: 5,
      totalPages: 0,
      totalElements: 0,
      first: true,
      last: false,
    };
  },
  computed: {
    filteredPackages() {
      return this.packages.filter(pkg => pkg.packageName.toLowerCase().includes(this.searchQuery.toLowerCase()));
    }
  },
  methods: {
    handleDelete(packageId) {
      if (confirm("Are you sure you want to delete?")) {
        // Call update method
        this.deletePackage(packageId);
      }
    },
    async deletePackage(packageId) {
      try {
        await http.post(`api/packages/delete/${packageId}`);
        alert("Package deleted successfuly!");
        this.fetchPackages();
      } catch (error) {
        console.error("Error:", error);
      }
    },
    async searchPackages() {
      try {
        if (this.searchQuery) {
          const response = await http.get(`api/packages/search/${this.searchQuery}?page=${this.currentPage - 1}&size=${this.size}`);
          this.packages = response.data.content;
          this.totalPages = response.data.totalPages;
          this.first = response.data.first;
          this.last = response.data.last;
          console.log(this.packages)
        } else {
          this.fetchPackages();
        }
      } catch (error) {
        console.error("Error:", error);
      }
    },
    async fetchPackages() {
      try {
        const response = await http.get(`api/packages/get-all/pagable?page=${this.currentPage - 1}&size=${this.size}`);
        this.packages = response.data.content;
        this.totalPages = response.data.totalPages;
        this.first = response.data.first;
        this.last = response.data.last;
        console.log(this.packages)
      } catch (error) {
        console.error("Error:", error);
      }
    },
    async handlePageChange(newPage) {
      this.currentPage = newPage;
      await this.searchPackages();
    },
  },
  mounted() {
    this.fetchPackages();
  }
};
  </script>
  
  <style scoped>
  .package-table-page {
    display: flex;
    margin-left:250px
  }
  
  .main-content {
    flex: 1;
    padding: 20px;
  }
  
  h2 {
    font-size: 24px;
    margin-bottom: 20px;
  }
  
  .add-package-button {
    padding: 12px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-size: 16px;
    margin-bottom: 20px;
  }
  
  .add-package-button:hover {
    background-color: #0056b3;
  }
  
  .search-container {
    margin-bottom: 20px;
  }
  
  .search-container label {
    margin-right: 10px;
  }
  
  .search-container input {
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  .table-container {
    overflow-x: auto;
  }
  
  .package-table {
    width: 100%;
    border-collapse: collapse;
  }
  
  .package-table th,
  .package-table td {
    padding: 12px;
    border: 1px solid #ccc;
  }
  
  .package-table th {
    background-color: #f0f0f0;
    font-weight: bold;
    text-align: left;
  }
  
  .package-table td {
    text-align: left;
  }
  
  .package-table tbody tr:nth-child(even) {
    background-color: #f9f9f9;
  }
  
  .edit-button,
  .delete-button {
    padding: 8px 12px;
    margin-right: 6px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 14px;
    transition: background-color 0.3s ease;
  }
  
  .delete-button:hover {
    background-color: #dc3545;
  }
  
  .edit-button:hover {
    background-color: #0056b3;
  }
  </style>
