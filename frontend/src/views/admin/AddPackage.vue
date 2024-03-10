<template>
  <div class="add-package-page">
    <Sidebar :links="sidebarLinks" />
    <div class="main-content">
      <h2>Add Package</h2>
      <form @submit.prevent="addPackage">
        <div class="form-group">
          <label for="package-name">Package Name</label>
          <input type="text" id="package-name" v-model="packageInfo.name" required>
        </div>
        <div class="form-group">
          <label for="description">Description</label>
          <textarea id="description" v-model="packageInfo.description" required></textarea>
        </div>
        <!-- <div class="form-group">
          <label for="shop-id">Shop ID</label>
          <input type="text" id="shop-id" v-model="packageInfo.shopId" required>
        </div> -->
        <div class="form-group">
          <label for="shop-id">Shop</label>
          <input type="text" id="search" v-model="selectedShopName" list="shops-list" @change="fetchServices" />
          <datalist id="shops-list">
            <option v-for="shop in shops" :value="shop.name">
              {{ shop.name }}
            </option>
          </datalist>
        </div>
        <div class="form-group">
          <label for="shop-id">Services</label>
          <!-- <input type="checkbox" id="search" v-model="selectedServiceName" list="shops-list" /> -->
          <div class="flex gap-10">
            <div class="flex gap-2" v-for="service in services" :key="service.id">
              <input type="checkbox" v-model="packageInfo.serviceIds" :value="service.id">
              <label>{{ service.name }}</label>
            </div>
          </div>
        </div>
        <button type="submit" class="submit-button">Add Package</button>
      </form>
    </div>
  </div>
</template>

<script>
import http from "@/services/httpService";
import Sidebar from '../../components/AdminSidebar.vue'

export default {
  name:'Package',
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
      packageInfo: {
        id: null,
        name: '',
        description: '',
        shopId: null,
        serviceIds: []
      },
      services: [],
      shops: [],
      selectedShopName: "",
    };
  },
  methods: {
    async addPackage() {
      try {
        await http.post(`api/packages/add`, {
          "packageName": this.packageInfo.name,
          "description": this.packageInfo.description,
          "shopId": this.packageInfo.shopId,
          "serviceIds": this.packageInfo.serviceIds,
        });
    
        alert("Package added successfuly!");
        this.$router.push("/admin/packages");
      } catch (error) {
        
      }
    },
    async fetchServices() {
      try {
        this.packageInfo.shopId = this.shops.find(shop => shop.name === this.selectedShopName).id;
        const response = await http.get(`api/services/get/${this.packageInfo.shopId}`);
        this.services = response.data;
        console.log(this.services);
      } catch (error) {
        console.error(error);
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
  },
  mounted() {
    this.fetchShops();
  }
};
</script>

<style scoped>
.add-package-page {
  margin-left: 250px;
  display: flex;
}

.main-content {
  flex: 1;
  padding: 20px;
}

h2 {
  font-size: 24px;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 5px;
  color: #333;
}

input[type="text"],
textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-sizing: border-box;
}

textarea {
  resize: vertical;
}

.submit-button {
  padding: 15px 40px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 12px;
  cursor: pointer;
  font-size: 18px;
  transition: background-color 0.3s ease;
}

.submit-button:hover {
  background-color: #0056b3;
}
</style>
