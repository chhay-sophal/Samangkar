<template>
    <div class="package-table-page">
        <Sidebar :links="sidebarLinks" />
        <div class="main-content">
            <h2>Services Table</h2>
            <div class="p-5 pt-0">
                <button @click="handleShowAddServicePanel()" class="edit-button">Add Service</button>
            </div>
            <!-- <div class="search-container">
                <label for="search">Search:</label>
                <input type="text" id="search" v-model="searchQuery" placeholder="Search" @input="searchServices">
            </div> -->
            <div class="table-container">
            <table class="package-table">
                <thead>
                <tr>
                    <th>Service Name</th>
                    <th>Shop Name</th>
                    <th>Description</th>
                    <th>Unit Price</th>
                    <th>Image</th>
                    <th>Trending</th>
                    <th>Status</th>
                    <!-- <th>Packages</th> -->
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="service in services" :key="service.id">
                    <td>{{ service.name }}</td>
                    <td>{{ service.shopName }}</td>
                    <td>{{ service.description }}</td>
                    <td>{{ service.unitPrice }}</td>
                    <!-- <td>{{ service.image }}</td> -->
                    <td>
                        <div class="flex items-center justify-center aspect-square overflow-hidden size-16">
                            <ImageViewer :imageData="service.image" />
                        </div>
                    </td>
                    <td>{{ service.trending ? "Yes" : "No" }}</td>
                    <td>{{ service.deletedAt ? "Deleted" : "Active" }}</td>
                    <!-- <td>
                    <ul>
                        <li v-for="service in service.services" :key="service.id">
                        - {{ service.name }}
                        </li>
                    </ul>
                    </td> -->
                    <td>
                        <div class="flex w-fit items-center justify-center">
                            <button @click="showDetail(service.id)" class="edit-button">Details</button>
                            <button @click="handleShowEditPanel(service)" class="edit-button">Edit</button>
                            <button v-if="!service.deletedAt" @click="handleDelete(service.id)" class="delete-button">Delete</button>
                        </div>
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

    <!-- Show service details panel -->
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
        <div class="w-full px-10 pb-10 dark:text-stone-600 flex flex-col">
            <div class="flex w-full">
                <div class="flex flex-col gap-5 w-1/3">
                    <label class="block font-medium text-slate-600">Service name:</label>
                    <label class="block font-medium text-slate-600">Shop:</label>
                    <label class="block font-medium text-slate-600">Description:</label>
                    <label class="block font-medium text-slate-600">Unit price:</label>
                    <label class="block font-medium text-slate-600">Trending:</label>
                </div>
                <div class="flex flex-col w-2/3 gap-5">
                    <p>{{ selectedService.name }}</p>
                    <p>{{ selectedService.shopName }}</p>
                    <p>{{ selectedService.description }}</p>
                    <p>{{ selectedService.unitPrice }}</p>
                    <p>{{ selectedService.trending ? "Yes" : "No" }}</p>
                </div>
            </div>
            <div class="w-full flex items-center justify-center">
                <button @click="handleShowEditPanel(selectedService)" class="action-button edit-button">
                    Edit
                </button>
            </div>
        </div>
    </div>
    
    <!-- Add service panel -->
    <div 
    v-if="showAddServicePanel"
    class="top-1/2 left-1/2 bg-green-100 2xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl sm:h-1/3 h-2/5 -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
    >
        <div class="flex justify-end items-center pr-5 dark:text-stone-600">
            <button 
                @click="handleHideAddServicePanel()"
            >
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-8 h-8">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                </svg>
            </button>
        </div>
        <div class="text-3xl text-center pb-1 dark:text-stone-600">
            Information
        </div>
        <div class="w-full px-10 pb-10 dark:text-stone-600 flex flex-col gap-5">
            <div class="flex w-full">
                <div class="flex flex-col gap-5 w-1/3">
                    <label class="block font-medium text-slate-600">Service name:</label>
                    <label class="block font-medium text-slate-600">Shop:</label>
                    <label class="block font-medium text-slate-600">Description:</label>
                    <label class="block font-medium text-slate-600">Unit price:</label>
                </div>
                <div class="flex flex-col w-2/3 gap-5">
                    <input type="text" v-model="serviceInput.name">
                    <input type="text" id="search" v-model="serviceInput.shopName" list="shops-list" />
                    <datalist id="shops-list">
                        <option v-for="shop in shops" :value="shop.name">
                            {{ shop.name }}
                        </option>
                    </datalist>
                    <!-- <textarea v-model="addServiceInput.description"></textarea> -->
                    <input type="text" v-model="serviceInput.description">
                    <input type="number" v-model="serviceInput.unitPrice">
                </div>
            </div>
            <div class="w-full flex items-center justify-center">
                <button @click="handleAdd()" class="action-button edit-button">
                    Add
                </button>
            </div>
        </div>
    </div>
        
    <!-- Edit service panel -->
    <div 
    v-if="showEditPanel"
    class="top-1/2 left-1/2 bg-green-100 2xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl sm:h-1/3 h-2/5 -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
    >
        <div class="flex justify-end items-center pr-5 dark:text-stone-600">
            <button 
                @click="handleHideEditPanel()"
            >
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-8 h-8">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                </svg>
            </button>
        </div>
        <div class="text-3xl text-center pb-1 dark:text-stone-600">
            Update Service
        </div>
        <div class="w-full px-10 pb-10 dark:text-stone-600 flex flex-col gap-5">
            <div class="flex w-full">
                <div class="flex flex-col gap-5 w-1/3">
                    <label class="block font-medium text-slate-600">Service name:</label>
                    <label class="block font-medium text-slate-600">Shop:</label>
                    <label class="block font-medium text-slate-600">Description:</label>
                    <label class="block font-medium text-slate-600">Unit price:</label>
                    <label class="block font-medium text-slate-600">Trending:</label>
                </div>
                <div class="flex flex-col w-2/3 gap-5">
                    <input type="text" v-model="serviceInput.name">
                    <input type="text" id="search" v-model="serviceInput.shopName" list="shops-list" />
                    <datalist id="shops-list">
                        <option v-for="shop in shops" :value="shop.name">
                            {{ shop.name }}
                        </option>
                    </datalist>
                    <!-- <textarea v-model="addServiceInput.description"></textarea> -->
                    <input type="text" v-model="serviceInput.description">
                    <input type="number" v-model="serviceInput.unitPrice">
                    <div class="flex gap-5">
                        <label>
                            <input type="radio" id="trending" v-model="serviceInput.isTrending" value="true" />
                            Yes
                        </label>
                        <label>
                            <input type="radio" id="trending" v-model="serviceInput.isTrending" value="false" />
                            No
                        </label>
                    </div>
                </div>
            </div>
            <div class="w-full flex items-center justify-center">
                <button @click="handleUpdate()" class="action-button edit-button">
                    Update
                </button>
            </div>
        </div>
    </div>

  </template>
  
<script>
import Sidebar from "./../../components/AdminSidebar.vue"; // Adjust the path as per your project structure
import Pagination from "./Pagination.vue";
import http from "@/services/httpService";
import ImageViewer from "@/components/ImageViewer.vue";

export default {
  components: {
    Sidebar,
    Pagination,
    ImageViewer,
  },
  data() {
    return {
      sidebarLinks: [
        { text: 'Dashboard', icon: 'mdi-view-dashboard', route: '/dashboard' },
        { text: 'Users', icon: 'mdi-account', route: '/users' },
        { text: 'Shops', icon: 'mdi-store', route: '/shops' },
        // Add more sidebar links as needed
      ],
      services: [],
      serviceInput: {
        id: null,
        name: '',
        description: '',
        unitPrice: null,
        shopId: null,
        shopName: '',
        isTrending: null,
      },
    //   packages: [],
      shops: [],
      searchQuery: "",
      currentPage: 1,
      size: 5,
      totalPages: 0,
      totalElements: 0,
      first: true,
      last: false,
      showDetailPanel: false,
      showEditPanel: false,
      selectedService: null,
      showAddServicePanel: false,
    };
  },
  methods: {
    handleShowAddServicePanel() {
        this.showAddServicePanel = true;
        this.fetchShops();
    },
    handleAdd() {
        if (confirm("Are you sure you want to add?")) {
            this.addService();
        }
    },
    async addService() {
        try {
            this.serviceInput.shopId = this.shops.find(s => s.name == this.serviceInput.shopName).id;
            await http.post(`api/services/add`, {
                'name': this.serviceInput.name,
                'description': this.serviceInput.description,
                'unitPrice': this.serviceInput.unitPrice,
                'shopId': this.serviceInput.shopId,
            });
            alert("Service added successfully!");
            this.fetchServices();
            this.handleHideAddServicePanel();
        } catch (error) {
            console.log(error);
        }
    },
    handleShowEditPanel(service) {
        this.showEditPanel = true;
        this.showDetailPanel = false;
        this.serviceInput.id = service.id;
        this.serviceInput.name = service.name;
        this.serviceInput.description = service.description;
        this.serviceInput.unitPrice = service.unitPrice;
        this.serviceInput.shopName = service.shopName;
        this.serviceInput.isTrending = service.trending;
        this.fetchShops();
    },
    handleHideEditPanel() {
        this.showEditPanel = false;
        this.serviceInput.name = '';
        this.serviceInput.description = '';
        this.serviceInput.unitPrice = null;
        this.serviceInput.shopName = null;
        this.serviceInput.shopId = null;
    },
    handleUpdate() {
        if (confirm("Are you sure you want to update?")) {
            this.updateService();
        }
    },
    async updateService() {
        try {
            this.serviceInput.shopId = this.shops.find(s => s.name == this.serviceInput.shopName).id;
            await http.post(`api/services/update`, {
                'id': this.serviceInput.id,
                'name': this.serviceInput.name,
                'description': this.serviceInput.description,
                'unitPrice': this.serviceInput.unitPrice,
                'shopId': this.serviceInput.shopId,
                'isTrending': this.serviceInput.isTrending,
            });
            alert("Service updated successfully!");
            this.fetchServices();
            this.handleHideEditPanel();
        } catch (error) {
            console.log(error);
        }
    },
    handleDelete(serviceId) {
      if (confirm("Are you sure you want to delete?")) {
        // Call update method
        this.deleteService(serviceId);
      }
    },
    showDetail(serviceId){
      this.selectedService = this.services.find(s => s.id == serviceId);
      this.showDetailPanel = true;
    },
    hideDetail(){
      this.selectedService = null;
      this.showDetailPanel = false;
    },
    handleHideAddServicePanel() {
        this.showAddServicePanel = false;
        this.serviceInput.name = '';
        this.serviceInput.description = '';
        this.serviceInput.unitPrice = null;
        this.serviceInput.shopName = null;
        this.serviceInput.shopId = null;
    },
    async deleteService(serviceId) {
      try {
        await http.post(`api/services/delete/${serviceId}`);
        alert("Service deleted successfuly!");
        this.fetchServices();
      } catch (error) {
        console.error("Error:", error);
      }
    },
    // async searchServices() {
    //   try {
    //     if (this.searchQuery) {
    //       const response = await http.get(`api/services/search/${this.searchQuery}?page=${this.currentPage - 1}&size=${this.size}`);
    //       this.services = response.data.content;
    //       this.totalPages = response.data.totalPages;
    //       this.first = response.data.first;
    //       this.last = response.data.last;
    //       console.log(this.services)
    //     } else {
    //       this.fetchPackages();
    //     }
    //   } catch (error) {
    //     console.error("Error:", error);
    //   }
    // },
    async fetchServices() {
      try {
        const response = await http.get(`api/services/get-all/pageable?page=${this.currentPage - 1}&size=${this.size}`);
        this.services = response.data.content;
        this.totalPages = response.data.totalPages;
        this.first = response.data.first;
        this.last = response.data.last;
        console.log(this.services)
      } catch (error) {
        console.error("Error:", error);
      }
    },
    async fetchShops() {
      try {
        const response = await http.get(`api/shops/get-all`);
        this.shops = response.data;
        console.log(this.shops)
      } catch (error) {
        console.error("Error:", error);
      }
    },
    async handlePageChange(newPage) {
      this.currentPage = newPage;
      await this.fetchServices();
    },
  },
  mounted() {
    this.fetchServices();
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
