<template>
    <div class="package-table-page">
      <Sidebar :links="sidebarLinks" />
      <div class="main-content">
        <h2>Package Table</h2>
        <router-link to="/addpackage"><button @click="addNewPackage" class="add-package-button">Add Package</button></router-link>
        <div class="search-container">
          <label for="search">Search:</label>
          <input type="text" id="search" v-model="searchQuery" placeholder="Search by Package Name">
        </div>
        <div class="table-container">
          <table class="package-table">
            <thead>
              <tr>
                <th>Package Name</th>
                <th>Shop ID</th>
                <th>Description</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="pkg in filteredPackages" :key="pkg.id">
                <td>{{ pkg.packageName }}</td>
                <td>{{ pkg.shopID }}</td>
                <td>{{ pkg.description }}</td>
                <td>
                  <button @click="editPackage(pkg)" class="edit-button">Edit</button>
                  <button @click="deletePackage(pkg.id)" class="delete-button">Delete</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import Sidebar from "./../../components/AdminSidebar.vue"; // Adjust the path as per your project structure
  
  export default {
    components: {
      Sidebar
    },
    data() {
      return {
        sidebarLinks: [
          { text: 'Dashboard', icon: 'mdi-view-dashboard', route: '/dashboard' },
          { text: 'Users', icon: 'mdi-account', route: '/users' },
          { text: 'Shops', icon: 'mdi-store', route: '/shops' },
          // Add more sidebar links as needed
        ],
        packages: [
          { id: 1, packageName: 'Package A', shopID: 'Shop1', description: 'Description of Package A' },
          { id: 2, packageName: 'Package B', shopID: 'Shop2', description: 'Description of Package B' },
          // Add more packages as needed
        ],
        searchQuery: ''
      };
    },
    computed: {
      filteredPackages() {
        return this.packages.filter(pkg => pkg.packageName.toLowerCase().includes(this.searchQuery.toLowerCase()));
      }
    },
    methods: {
      addNewPackage() {
        // Logic to add new package
        console.log('Adding new package...');
      },
      editPackage(pkg) {
        // Logic to edit package
        console.log('Edit package:', pkg);
      },
      deletePackage(packageId) {
        // Logic to delete package
        console.log('Delete package with ID:', packageId);
      }
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
