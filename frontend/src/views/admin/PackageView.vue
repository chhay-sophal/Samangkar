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
                <th>Services</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="pkg in packages" :key="pkg.id">
                <td>{{ pkg.name }}</td>
                <td>{{ pkg.shop.name }}</td>
                <td>{{ pkg.description }}</td>
                <td>
                  <ul>
                    <li v-for="service in pkg.services" :key="service.id">
                      - {{ service.name }}
                    </li>
                  </ul>
                </td>
                <td>
                  <button @click="showDetail(pkg.id)" class="edit-button">Details</button>
                  <router-link :to="`/admin/packages/edit/${pkg.id}`">
                    <button class="action-button edit-button">
                      Edit
                    </button>
                  </router-link>
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

      <div 
            v-if="showDetailPanel"
            class="top-1/2 left-1/2 bg-green-100 2xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl sm:h-1/3 h-2/5 -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
        >
            <div class="flex justify-end items-center pr-5 dark:text-stone-600">
                <button 
                    @click="hideDetail()"
                >
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-8 h-8">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                    </svg>
                </button>
            </div>
            <div class="text-3xl text-center pb-1 dark:text-stone-600">
                Detail
            </div>
            <div action="" class="w-full px-10 pb-10 dark:text-stone-600 flex flex-col">
              <div class="flex gap-4">
                <label for="old-password" class="block font-medium text-slate-600">Package name:</label>
                <div class="">{{ selectedPackage.name }}</div>
              </div>
              <div class="flex gap-4">
                <label for="old-password" class="block font-medium text-slate-600">Shop name:</label>
                <div class="">{{ selectedPackage.shop.name }}</div>
              </div>
              <div class="flex gap-4">
                <label for="old-password" class="block font-medium text-slate-600">Description:</label>
                <div class="">{{ selectedPackage.description }}</div>
              </div>
              <div class="flex gap-4">
                <label for="old-password" class="block font-medium text-slate-600">Price:</label>
                <div class="">{{ selectedPackage.price }}</div>
              </div>
              <div class="flex gap-4">
                <label for="old-password" class="block font-medium text-slate-600">Services:</label>
                <ul>
                  <li v-for="service in selectedPackage.services" :key="service.id">
                    - {{ service.name }}
                  </li>
                </ul>
              </div>
              <div class="">
                <router-link :to="`/admin/packages/edit/${selectedPackage.id}`">
                  <button class="action-button edit-button">
                    Edit
                  </button>
                </router-link>
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
      showDetailPanel: false,
      selectedPackage: null,
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
    showDetail(pkgId){
      this.selectedPackage = this.packages.find(pkg => pkg.id == pkgId);
      this.showDetailPanel = true;
    },
    hideDetail(){
      this.selectedPackage = null;
      this.showDetailPanel = false;
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
    /* background-color: #f0f0f0; */
    font-weight: bold;
    text-align: left;
  }
  
  .package-table td {
    text-align: left;
  }
  
  /* .package-table tbody tr:nth-child(even) {
    background-color: #f9f9f9;
  } */
  
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
