<template>
  <div class="">
    <!-- <Sidebar :links="sidebarLinks" /> -->
    <div class="">
      <div class="w-full flex items-center justify-center">
        <ImageViewer :imageData="shop.imageUrl" class=" w-1/2 h-fit rounded-xl overflow-hidden"/>
      </div>
      <div class="text-4xl font-bold flex items-center justify-center p-5">Shop Management</div>

      <!-- Basic Info -->
      <div class="section">
        <div class="flex justify-between">
          <h2>Basic Info</h2>
        </div>
        <!-- <router-link to="/admin/shops" class="view-all-link">View All</router-link> -->
        <div class="stats">
          <div class="stat flex flex-col gap-3 dark:bg-gray-700">
            <div class="border p-2 rounded-lg">
              <h3>Shop Id</h3>
              <p>{{ shop.id }}</p>
            </div>
            <div class="border p-2 rounded-lg">
              <h3>Shop Name</h3>
              <p>{{ shop.name }}</p>
            </div>
            <div class="border p-2 rounded-lg">
              <h3>Shop Descriptions</h3>
              <p>{{ shop.description }}</p>
            </div>
            <div class="border p-2 rounded-lg">
              <h3>Trending</h3>
              <p>{{ shop.isTrending ? 'Yes' : 'No' }}</p>
            </div>
            <div class="border p-2 rounded-lg">
              <h3>Created Date</h3>
              <p>{{ formatDate(shop.createdAt) }}</p>
            </div>
          </div>
        </div>
      </div>

      <!-- Services -->
      <div class="section">
        <div class="flex justify-between">
          <h2>Services ({{ services.length }})</h2>
          <button @click="displayAddServicePanel()" class="text-xl">
            Add
          </button>
        </div>
        <div class="stats w-full h-96">
          <div class="h-5/6 overflow-x-auto flex items-center relative pb-3">
              <!-- Shop Cards Container -->
              <div class="px-4 h-full">
                  <div class="flex space-x-4 h-full text-2xl">
                      <!-- Loop through your shop cards -->
                      <button 
                      @click="displayServiceDetailsPanel(service)"
                      v-for="service in services" 
                      :key="service.id" 
                      class="flex-none w-64 bg-green-400 p-3 flex flex-col justify-center items-center rounded-lg"
                      >
                          {{ service.name }}
                          <ImageViewer :imageData="service.image" class=" w-1/2 h-fit rounded-xl overflow-hidden"/>
                      </button>
                  </div>
              </div>
          </div>
        </div>
      </div>

      <!-- Packages -->
      <div class="section">
        <div class="flex justify-between">
          <h2>Packages ({{ packages.length }})</h2>
          <button @click="displayAddPackagePanel()" class="text-xl">
              Add
          </button>
        </div>
        <div class="stats w-full h-96">
          <div class="h-5/6 overflow-x-auto flex items-center relative pb-3">
              <!-- Shop Cards Container -->
              <div class="px-4 h-full">
                  <div class="flex space-x-4 h-full text-2xl">
                      <!-- Loop through your shop cards -->
                      <button @click="displayPackageDetailsPanel(pkg)" v-for="pkg in packages" :key="pkg.id" class="flex-none w-64 bg-green-400 p-3 flex flex-col justify-center items-center rounded-lg">
                          {{ pkg.name }}
                          <ImageViewer :imageData="pkg.image" class=" w-1/2 h-fit rounded-xl overflow-hidden"/>
                      </button>
                  </div>
              </div>
          </div>
        </div>
      </div>

      <!-- Reviews -->
      <div class="section">
        <div class="flex justify-between">
          <h2>Reviews ({{ reviews.length }})</h2>
        </div>
        <div class="stats w-full h-96">
          <div class="h-5/6 overflow-x-auto flex items-center relative pb-3">
              <!-- Shop Cards Container -->
              <div class="px-4 h-full">
                  <div class="flex space-x-4 h-full text-2xl">
                      <!-- Loop through your shop cards -->
                      <div v-for="review in reviews" :key="review.id" class="flex-none gap-2 w-64 bg-green-400 p-5 flex flex-col justify-center items-center rounded-lg">
                          <div class="flex gap-2">
                              <div v-for="star in review.stars">
                                  <svg xmlns="http://www.w3.org/2000/svg" fill="red" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="M11.48 3.499a.562.562 0 0 1 1.04 0l2.125 5.111a.563.563 0 0 0 .475.345l5.518.442c.499.04.701.663.321.988l-4.204 3.602a.563.563 0 0 0-.182.557l1.285 5.385a.562.562 0 0 1-.84.61l-4.725-2.885a.562.562 0 0 0-.586 0L6.982 20.54a.562.562 0 0 1-.84-.61l1.285-5.386a.562.562 0 0 0-.182-.557l-4.204-3.602a.562.562 0 0 1 .321-.988l5.518-.442a.563.563 0 0 0 .475-.345L11.48 3.5Z" />
                                  </svg>
                              </div>
                          </div>
                        
                          <div class="">
                            {{ review.title }}
                          </div>
                          <div class="text-lg">
                            {{ review.description }}
                          </div>
                          <div class="text-lg">
                            Created by:
                            {{ review.user.username }}
                          </div>
                          <div class="text-sm">
                            {{ formatDate(review.createdAt) }}
                          </div>
                          
                          <!-- <ImageViewer :imageData="review.image" class=" w-1/2 h-fit rounded-xl overflow-hidden"/> -->
                      </div>
                  </div>
              </div>
          </div>
        </div>
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
              @click="hideAddServicePanel()"
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
                  <label class="block font-medium text-slate-600">Description:</label>
                  <label class="block font-medium text-slate-600">Unit price:</label>
              </div>
              <div class="flex flex-col w-2/3 gap-5">
                  <input type="text" v-model="serviceInput.name">
                  <input type="text" v-model="serviceInput.description">
                  <input type="number" v-model="serviceInput.unitPrice">
              </div>
          </div>
          <div class="w-full flex items-center justify-center">
              <button @click="addService()" class="action-button edit-button">
                  Add
              </button>
          </div>
      </div>
  </div>

  <!-- Add package panel -->
  <div 
  v-if="showAddPackagePanel"
  class="top-1/2 left-1/2 min-h-fit bg-green-100 2xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
  >
      <div class="flex justify-end items-center pr-5 dark:text-stone-600">
          <button 
              @click="hideAddPackagePanel()"
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
                  <label class="block font-medium text-slate-600">Package name:</label>
                  <label class="block font-medium text-slate-600">Description:</label>
                  <label class="block font-medium text-slate-600">Services:</label>
              </div>
              <div class="flex flex-col w-2/3 gap-5">
                  <input type="text" v-model="packageInput.name">
                  <input type="text" v-model="packageInput.description">
                  <div class="grid grid-cols-3">
                    <div class="flex gap-2" v-for="service in services" :key="service.id">
                      <input type="checkbox" v-model="packageInput.serviceIds" :value="service.id">
                      <label>{{ service.name }}</label>
                    </div>
                  </div>
              </div>
          </div>
          <div class="w-full flex items-center justify-center">
              <button @click="AddPackage()" class="action-button edit-button">
                  Add
              </button>
          </div>
      </div>
  </div>

  <!-- Service details panel -->
  <div 
  v-if="showServiceDetailsPanel"
  class="top-1/2 left-1/2 bg-green-100 2xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl sm:h-1/3 h-2/5 -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
  >
      <div class="flex justify-end items-center pr-5 dark:text-stone-600">
          <button 
              @click="hideServiceDetailsPanel()"
          >
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-8 h-8">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
              </svg>
          </button>
      </div>
      <div class="text-3xl text-center pb-1 dark:text-stone-600">
          Service Details
      </div>
      <div class="w-full px-10 pb-10 dark:text-stone-600 flex flex-col gap-5">
          <div class="flex w-full">
              <div class="flex flex-col gap-5 w-1/3">
                  <label class="block font-medium text-slate-600">Service name:</label>
                  <label class="block font-medium text-slate-600">Description:</label>
                  <label class="block font-medium text-slate-600">Unit price:</label>
                  <label class="block font-medium text-slate-600">Trending:</label>
              </div>
              <div class="flex flex-col w-2/3 gap-5">
                  <input type="text" v-model="selectedService.name">
                  <input type="text" v-model="selectedService.description">
                  <input type="number" v-model="selectedService.unitPrice">
                  <div class="flex gap-5">
                      <label>
                          <input type="radio" id="trending" v-model="selectedService.isTrending" value="true" />
                          Yes
                      </label>
                      <label>
                          <input type="radio" id="trending" v-model="selectedService.isTrending" value="false" />
                          No
                      </label>
                  </div>
              </div>
          </div>
          <div class="w-full flex items-center justify-center">
              <button @click="updateService()" class="action-button edit-button border px-5 py-3 rounded-lg bg-stone-300">
                  Update
              </button>
          </div>
      </div>
  </div>

  <!-- Package details panel -->
  <div 
  v-if="showPackageDetailsPanel"
  class="top-1/2 left-1/2 bg-green-100 2xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl sm:h-1/3 h-2/5 -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
  >
      <div class="flex justify-end items-center pr-5 dark:text-stone-600">
          <button 
              @click="hidePackageDetailsPanel()"
          >
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-8 h-8">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
              </svg>
          </button>
      </div>
      <div class="text-3xl text-center pb-1 dark:text-stone-600">
          Package Details
      </div>
      <div class="w-full px-10 pb-10 dark:text-stone-600 flex flex-col gap-5">
          <div class="flex w-full">
              <div class="flex flex-col gap-5 w-1/3">
                  <label class="block font-medium text-slate-600">Package name:</label>
                  <label class="block font-medium text-slate-600">Description:</label>
                  <label class="block font-medium text-slate-600">Services:</label>
              </div>
              <div class="flex flex-col w-2/3 gap-5">
                  <input type="text" v-model="selectedPackage.name">
                  <input type="text" v-model="selectedPackage.description">
                  <div class="grid grid-cols-3">
                    <div class="flex gap-2" v-for="service in services" :key="service.id">
                      <input type="checkbox" v-model="selectedPackage.serviceIds" :value="service.id">
                      <label>{{ service.name }}</label>
                    </div>
                  </div>
              </div>
          </div>
          <div class="w-full flex items-center justify-center">
              <button @click="updatePackage()" class="action-button edit-button border px-5 py-3 rounded-lg bg-stone-300">
                  Update
              </button>
          </div>
      </div>
  </div>

</template>
  
<script>
import Sidebar from "./../../components/AdminSidebar.vue"; // Adjust the path as per your project structure
import http from "@/services/httpService";
import { useUserStore } from "@/store/userStore";
import ImageViewer from '@/components/ImageViewer.vue'
import AddPackage from "../admin/AddPackage.vue";

export default {
  components: {
    Sidebar,
    ImageViewer,
  },
  data() {
    return {
      sidebarLinks: [
        { text: 'Dashboard', icon: 'mdi-view-dashboard', route: '/dashboard' },
        { text: 'Users', icon: 'mdi-account', route: '/users' },
        { text: 'Shops', icon: 'mdi-store', route: '/shops' },
        { text: 'Reviews', icon: 'mdi-message', route: '/reviews' },
      ],
      shop: [],
      services: [],
      reviews: [],
      packages: [],
      showAddServicePanel: false,
      showAddPackagePanel: false,
      showServiceDetailsPanel: false,
      showPackageDetailsPanel: false,
      showUpdateShopPanel: false,
      serviceInput: {
        name: '',
        description: '',
        unitPrice: null,
      },
      packageInput: {
        name: '',
        description: '',
        serviceIds: [],
      },
      selectedService: {},
      selectedPackage: {
        serviceIds: [],
      },
    };
  },
  methods: {
    async fetchShopDetails(shopId) {
      try {
        const response = await http.get(`api/shops/get/${shopId}`);
        this.shop = response.data;
        console.log(this.shop);
      } catch (error) {
        console.error(error)
      }
    },
    async fetchServices(shopId) {
      try {
        const response = await http.get(`api/services/get-all/${shopId}`);
        this.services = response.data;
        console.log(this.services)
      } catch (error) {
        console.error("Error:", error);
      }
    },
    async fetchPackages(shopId) {
      try {
        const response = await http.get(`api/packages/get-all/${shopId}`);
        this.packages = response.data;
        console.log(this.packages);
      } catch (error) {
        console.error(error);
      }
    },
    async fetchReviews(shopId) {
      try {
        const response = await http.get(`api/users/reviews/all/${shopId}`);
        this.reviews = response.data;
        console.log(this.reviews);
      } catch (error) {
        console.error(error);
      }
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return new Date(dateString).toLocaleDateString(undefined, options);
    },
    displayAddServicePanel() {
      this.showAddServicePanel = true;
    },
    hideAddServicePanel() {
      this.showAddServicePanel = false;
      this.serviceInput.name = null;
      this.serviceInput.description = null;
      this.serviceInput.unitPrice = null;
    },
    async addService() {
      if (confirm("Are you sure to add this service?")) {
        try {
            await http.post(`api/services/add`, {
                'name': this.serviceInput.name,
                'description': this.serviceInput.description,
                'unitPrice': this.serviceInput.unitPrice,
                'shopId': this.shop.id,
            });
            alert("Service added successfully!");
            this.fetchServices(this.shop.id);
            this.hideAddServicePanel();
        } catch (error) {
            console.log(error);
        }
      }
    },
    displayAddPackagePanel() {
      this.showAddPackagePanel = true;
    },
    hideAddPackagePanel() {
      this.showAddPackagePanel = false;
      this.packageInput.name = null;
      this.packageInput.description = null;
      this.packageInput.serviceIds = [];
    },
    async AddPackage() {
      if (confirm("Are you sure to add this package?")) {
        try {
          await http.post(`api/packages/add`, {
            "packageName": this.packageInput.name,
            "description": this.packageInput.description,
            "shopId": this.shop.id,
            "serviceIds": this.packageInput.serviceIds,
          });
      
          alert("Package added successfuly!");
          this.fetchPackages(this.shop.id);
          this.hideAddPackagePanel();
        } catch (error) {
            console.log(error);
        }
      }
    },
    displayServiceDetailsPanel(service) {
      this.showServiceDetailsPanel = true;
      this.selectedService = service;
      console.log(this.selectedService)
      console.log(this.selectedService.name)
    },
    hideServiceDetailsPanel() {
      this.showServiceDetailsPanel = false;
      this.selectedService = {};
    },
    async updateService() {
      if (confirm("Are you sure to update this service?")) {
        try {
            await http.post(`api/services/update`, {
                'id': this.selectedService.id,
                'name': this.selectedService.name,
                'description': this.selectedService.description,
                'unitPrice': this.selectedService.unitPrice,
                'shopId': this.selectedService.shop.id,
                'isTrending': this.selectedService.isTrending,
            });
            alert("Service updated successfully!");
            this.fetchServices();
            this.hideServiceDetailsPanel();
        } catch (error) {
            console.log(error);
        }
      }
    },
    displayPackageDetailsPanel(pkg) {
      this.showPackageDetailsPanel = true;
      this.selectedPackage.serviceIds = pkg.services.map(service => service.id);
      this.selectedPackage = { ...this.selectedPackage, ...pkg };
    },
    hidePackageDetailsPanel() {
      this.showPackageDetailsPanel = false;
      this.selectedPackage = {};
    },
    async updatePackage() {
      if (confirm("Are you sure to update this package?")) {
        try {
          http.put(`api/packages/update/${this.selectedPackage.id}`, { 
            "packageName": this.selectedPackage.name,
            "description": this.selectedPackage.description,
            "shopId": this.selectedPackage.shop.id,
            "serviceIds": this.selectedPackage.serviceIds,
          });
      
          alert("Package updated successfuly!");
          this.fetchPackages(this.selectedPackage.shop.id);
          this.hidePackageDetailsPanel();
        } catch (error) {
          console.error(error);
        }
      }
    }
  },
  mounted() {
      const userStore = useUserStore();
      if (!userStore.user.username) {
          this.$router.push({ name: 'loginPageRoute' });
      }
      const shopId = this.$route.params.shopId;
      this.fetchShopDetails(shopId);
      this.fetchServices(shopId);
      this.fetchPackages(shopId);
      this.fetchReviews(shopId);
  },
};
</script>
  
<style scoped>
.overview-page {
  margin-left: 250px;
  display: flex;
}

.main-content {
  flex: 1;
  padding: 20px;
}

h1 {
  font-size: 36px;
  margin-bottom: 20px;
}

.section {
  margin-bottom: 40px;
}

h2 {
  font-size: 24px;
  margin-bottom: 10px;
}

.stats {
  display: flex;
  justify-content: space-between;
  gap: 10px;
}

.stat {
  flex: 1;
  text-align: center;
  padding: 20px;
  border-radius: 12px;
  /* background-color: #f5f5f5; */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.stat h3 {
  margin-bottom: 10px;
  font-size: 18px;
}

.stat p {
  font-size: 24px;
  font-weight: bold;
}

.view-all-link {
  font-size: 16px;
  color: #007bff;
  text-decoration: none;
  transition: color 0.3s ease;
}

.view-all-link:hover {
  color: #0056b3;
}
</style>
  