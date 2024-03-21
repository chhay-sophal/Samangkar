<template>
  <div class="">
    <!-- <Sidebar :links="sidebarLinks" /> -->
    <div class="">
      <div class="w-full flex items-center justify-center relative">
        <ImageViewer :imageData="shop.imageUrl" class=" w-1/2 h-fit rounded-xl overflow-hidden"/>
        <button class="absolute right-1 top-1" @click="openFileInput()">
          <input type="file" ref="fileInput" style="display: none" @change="handleFileChange">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-8 h-8">
            <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
          </svg>
        </button>
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
            <div class="border p-2 rounded-lg relative">
              <h3>Shop Name</h3>
              <p>{{ shop.name }}</p>
              <button class="absolute right-1 top-1" @click="displayPanel('changeShopNamePanel', shop.name)">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                  <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
                </svg>
              </button>
            </div>
            <div class="border p-2 rounded-lg relative">
              <h3>Shop Description</h3>
              <p>{{ shop.description }}</p>
              <button class="absolute right-1 top-1" @click="displayPanel('changeShopDescriptionPanel', shop.description)">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                  <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
                </svg>
              </button>
            </div>
            <div class="border p-2 rounded-lg relative">
              <h3>Trending</h3>
              <p>{{ shop.isTrending ? 'Yes' : 'No' }}</p>
              <button class="absolute right-1 top-1" @click="displayPanel('changeShopTrendingPanel', shop.isTrending)">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                  <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
                </svg>
              </button>
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
          <button @click="displayPanel('addServicePanel')" class="text-xl">
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
                      @click="displayPanel('serviceDetailsPanel', service)"
                      v-for="service in services" 
                      :key="service.id" 
                      class="flex-none w-64 dark:bg-gray-700 border-2 p-3 flex flex-col justify-center items-center rounded-lg"
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
          <button @click="displayPanel('addPackagePanel')" class="text-xl">
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
                        @click="displayPanel('packageDetailsPanel', pkg)" 
                        v-for="pkg in packages" :key="pkg.id" 
                        class="flex-none w-64 dark:bg-gray-700 border-2 p-3 flex flex-col justify-center items-center rounded-lg"
                      >
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
                      <div 
                        v-for="review in reviews" :key="review.id" 
                        class="flex-none gap-2 w-64 dark:bg-gray-700 border-2 p-5 flex flex-col justify-center items-center rounded-lg"
                      >
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

  <!-- Change Shop Details panel -->
  <div 
  v-if="showChangeShopDetailsPanel"
  class="top-1/2 left-1/2 bg-slate-400 2xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl min-h-fit -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
  >
      <div class="flex justify-end items-center dark:text-stone-600">
          <button 
              @click="hidePanel()"
          >
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-8 h-8">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
              </svg>
          </button>
      </div>
      <div class="text-3xl text-center pb-1 dark:text-stone-600">
          {{ changeShopDetails.label }}
      </div>
      <div class="w-full px-10 pb-10 dark:text-stone-600 flex flex-col gap-5">
        <div v-if="changeShopDetails.label == 'Trending'" class="flex w-full">
          <div class="flex flex-col w-full gap-5 items-center">
            <div class="flex gap-5">
              <label>
                  <input type="radio" id="trending" v-model="changeShopDetails.input" value='true' />
                  Yes
              </label>
              <label>
                  <input type="radio" id="trending" v-model="changeShopDetails.input" value='false' />
                  No
              </label>
            </div>
          </div>
        </div>  
        <div v-else-if="changeShopDetails.label == 'Image'" class="w-full flex flex-col items-center">
          <!-- <ImageViewer :imageData="changeShopDetails.input" class=" w-72"/> -->
          <img class="w-72" :src="changeShopDetails.input" alt="Profile Picture">
          <button class="" @click="openFileInput()">
            <input type="file" ref="fileInput" style="display: none" @change="handleFileChange">
            <div>Choose Image</div>
          </button>
        </div>
        <div v-else class="flex w-full">
          <div class="flex flex-col gap-5 w-1/3">
              <label class="block font-medium text-slate-600">New Shop {{ changeShopDetails.label }}:</label>
          </div>
          <div class="flex flex-col w-2/3 gap-5">
            <input type="text" v-model="changeShopDetails.input">
          </div>
        </div>
        <div class="w-full flex items-center justify-center">
            <button @click="updateShop()" class="action-button edit-button border px-5 py-3 rounded-lg bg-stone-300">
                Update
            </button>
        </div>
      </div>
  </div>

  <!-- Add service panel -->
  <div 
  v-if="showAddServicePanel"
  class="top-1/2 left-1/2 bg-slate-400 2xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl sm:h-1/3 h-2/5 -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
  >
      <div class="flex justify-end items-center pr-5 dark:text-stone-600">
          <button 
              @click="hidePanel()"
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
  class="top-1/2 left-1/2 min-h-fit bg-slate-400 2xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
  >
      <div class="flex justify-end items-center pr-5 dark:text-stone-600">
          <button 
              @click="hidePanel()"
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
              <button @click="addPackage()" class="action-button edit-button">
                  Add
              </button>
          </div>
      </div>
  </div>

  <!-- Service details panel -->
  <div 
  v-if="showServiceDetailsPanel"
  class="top-1/2 left-1/2 bg-slate-400 2xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl min-h-fit -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
  >
      <div class="flex justify-end items-center dark:text-stone-600">
          <button 
              @click="hidePanel()"
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
                  <label class="block font-medium text-slate-600">Image:</label>
              </div>
              <div class="flex flex-col w-2/3 gap-5">
                  <input type="text" v-model="selectedService.name">
                  <input type="text" v-model="selectedService.description">
                  <input type="number" v-model="selectedService.unitPrice">
                  <div class="flex gap-5">
                      <label>
                          <input type="radio" id="trending" v-model="selectedService.trending" value="true" />
                          Yes
                      </label>
                      <label>
                          <input type="radio" id="trending" v-model="selectedService.trending" value="false" />
                          No
                      </label>
                  </div>
                  <input type="file" id="image" accept="image/*" @change="onFileChange">
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
  class="top-1/2 left-1/2 bg-slate-400 2xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl sm:h-1/3 h-2/5 -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
  >
      <div class="flex justify-end items-center pr-5 dark:text-stone-600">
          <button 
              @click="hidePanel()"
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
                  <label class="block font-medium text-slate-600">Image:</label>
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
                  <input type="file" id="image" accept="image/*" @change="onFileChange">
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
import convertImageToBlob from "@/services/convertImageToBlob";

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
      showChangeShopDetailsPanel: false,
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
      selectedService: {
      },
      selectedPackage: {
        serviceIds: [],
      },
      changeShopDetails: {
        label: '',
        input: null
      },
      newImage: null,
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
    displayPanel(panelName, data) {
      if (panelName == 'addServicePanel') {
        this.showAddServicePanel = true;
      } else if (panelName == 'addPackagePanel') {
        this.showAddPackagePanel = true;
      } else if (panelName == 'serviceDetailsPanel') {
        this.showServiceDetailsPanel = true;
        this.selectedService = { ...this.selectedService, ...data };
        console.log(this.selectedService)
        console.log(this.selectedService.name)
      } else if (panelName == 'packageDetailsPanel') {
        this.showPackageDetailsPanel = true;
        this.selectedPackage.serviceIds = data.services.map(service => service.id);
        this.selectedPackage = { ...this.selectedPackage, ...data };
      } else if (panelName == 'changeShopNamePanel') {
        this.showChangeShopDetailsPanel = true;
        this.changeShopDetails.label = 'Name';
        this.changeShopDetails.input = data;
      } else if (panelName == 'changeShopDescriptionPanel') {
        this.showChangeShopDetailsPanel = true;
        this.changeShopDetails.label = 'Description';
        this.changeShopDetails.input = data;
      } else if (panelName == 'changeShopTrendingPanel') {
        this.showChangeShopDetailsPanel = true;
        this.changeShopDetails.label = 'Trending';
        this.changeShopDetails.input = data;
      } else if (panelName == 'changeShopImagePanel') {
        this.showChangeShopDetailsPanel = true;
        this.changeShopDetails.label = 'Image';
      }
    },
    hidePanel() {
      this.showAddServicePanel = false;
      this.serviceInput.name = null;
      this.serviceInput.description = null;
      this.serviceInput.unitPrice = null;

      this.showAddPackagePanel = false;
      this.packageInput.name = null;
      this.packageInput.description = null;
      this.packageInput.serviceIds = [];

      this.showServiceDetailsPanel = false;
      this.selectedService = {};

      this.showPackageDetailsPanel = false;
      this.selectedPackage = {};

      this.showChangeShopDetailsPanel = false;
      this.changeShopDetails.label = '';
      this.changeShopDetails.input = null;
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
            this.hidePanel();
        } catch (error) {
            console.log(error);
        }
      }
    },
    async addPackage() {
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
          this.hidePanel();
        } catch (error) {
            console.log(error);
        }
      }
    },
    async updateService() {
      if (confirm("Are you sure to update this service?")) {
        try {
            await http.post(`api/services/update`, {
                'id': this.selectedService.id,
                'name': this.selectedService.name,
                'description': this.selectedService.description,
                'unitPrice': this.selectedService.unitPrice,
                'shopId': this.selectedService.shopId,
                'isTrending': this.selectedService.isTrending,
            });

            if (this.newImage) {
              const formData = new FormData();
              formData.append('file', convertImageToBlob(this.newImage), 'image.jpg')

              await http.post(`api/services/${this.selectedService.id}/image/upload`, formData);
              this.newImage = null;
            }

            alert("Service updated successfully!");
            this.fetchServices(this.selectedService.shopId);
            this.hidePanel();
        } catch (error) {
            console.log(error);
        }
      }
    },
    onFileChange(event) {
      const file = event.target.files[0];

          if (file) {
              const reader = new FileReader();

              reader.onload = (e) => {
                this.newImage = e.target.result;
              };

              reader.readAsDataURL(file);
          }
    },
    async updatePackage() {
      if (confirm("Are you sure to update this package?")) {
        try {
          await http.put(`api/packages/update/${this.selectedPackage.id}`, { 
            "packageName": this.selectedPackage.name,
            "description": this.selectedPackage.description,
            "shopId": this.selectedPackage.shopId,
            "serviceIds": this.selectedPackage.serviceIds,
          });

          if (this.newImage) {
            const formData = new FormData();
            formData.append('file', convertImageToBlob(this.newImage), 'image.jpg')

            await http.post(`api/packages/${this.selectedPackage.id}/image/upload`, formData);
            this.newImage = null;
          }

          alert("Package updated successfuly!");
          this.fetchPackages(this.shop.id);
          this.hidePanel();
        } catch (error) {
          console.error(error);
        }
      }
    },
    async updateShop() {
      try {
        if (this.changeShopDetails.label == 'Name') {
          await http.put(`api/shops/update/${this.shop.id}`, {
            "shopName": this.changeShopDetails.input, 
          });
        } else if(this.changeShopDetails.label == 'Description') {
          await http.put(`api/shops/update/${this.shop.id}`, {
            "description": this.changeShopDetails.input,
          });
        } else if (this.changeShopDetails.label == 'Trending') {
          await http.put(`api/shops/update/${this.shop.id}`, {
            "isTrending": this.changeShopDetails.input,
          });
        } else if (this.changeShopDetails.label == 'Image') {
          const formData = new FormData();
          formData.append('file', convertImageToBlob(this.changeShopDetails.input), 'image.jpg')

          await http.post(`api/shops/${this.shop.id}/image/upload`, formData);
        }

        alert("Shop updated successfuly!");
        this.fetchShopDetails(this.shop.id);
        this.hidePanel();
      } catch (error) {
        console.error(error);
      }
    },
    openFileInput() {
        // this.changeShopDetails.input = null
        this.$refs.fileInput.value = null
        this.$refs.fileInput.click()
    },
    handleFileChange(event) {
        const file = event.target.files[0];

        if (file) {
            const reader = new FileReader();

            reader.onload = (e) => {
                this.changeShopDetails.input = e.target.result;
                this.displayPanel('changeShopImagePanel')
            };

            reader.readAsDataURL(file);
        }
    },
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
  