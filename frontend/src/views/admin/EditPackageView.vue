<template>
    <div class="admin-dashboard">
      <Sidebar :links="sidebarLinks" />
      <div class="main-content">
        <router-link to="/admin/packages" class="back-icon">
          <i class="mdi mdi-arrow-left">Back</i> 
        </router-link>
        <div class="admin-form">
          <h2 class="form-title">Edit Package</h2> 
          <form @submit.prevent="handleUpdate">
            <div class="form-group">
              <label for="name">Package Name</label>
              <input class="dark:text-stone-600" type="text" id="name" v-model="packageInfo.name" required>
            </div>
            <div class="form-group">
              <label for="description">Description</label>
              <textarea class="w-full rounded-lg p-3 dark:text-stone-600" id="description" v-model="packageInfo.description" required></textarea>
            </div>
            <div class="form-group">
              <div class="flex gap-2" v-for="service in services" :key="service.id">
                <input type="checkbox" v-model="packageInfo.serviceIds" :value="service.id">
                <label>{{ service.name }}</label>
              </div>
            </div>
            <button type="submit" class="submit-button" @click="handleUpdate">Update</button>
          </form>
        </div>
      </div>
    </div>
  </template>
  
  
<script>
import Sidebar from "./../../components/AdminSidebar.vue"; // Adjust the path as per your project structure
import http from "@/services/httpService";
import convertImageToBlob from "@/services/convertImageToBlob";

export default {
  name: 'AdminDashboard',
  components: {
    Sidebar
  },
  data() {
    return {
      sidebarLinks: [
        { text: 'Admin Registration', icon: 'mdi-account-plus' },
        { text: 'Other Section', icon: 'mdi-folder' },
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
    };
  },
  methods: {
    handleUpdate() {
      if (confirm("Are you sure you want to update?")) {
        // Call update method
        this.updatePackage();
      }
    },
    updatePackage() {
      try {
        http.put(`api/packages/update/${this.packageInfo.id}`, { 
          "packageName": this.packageInfo.name,
          "description": this.packageInfo.description,
          "shopId": this.packageInfo.shopId,
          "serviceIds": this.packageInfo.serviceIds,
        });
    
        console.log("Package updated:", this.packageInfo);
        alert("Package updated successfuly!");
        this.$router.push("/admin/packages");
      } catch (error) {
        console.error(error);
      }
    },
    async fetchPackage() {
      try {
        this.packageInfo.id = this.$route.params.packageId;
        const response = await http.get(`api/packages/get/${this.packageInfo.id}`);
        this.packageInfo.name = response.data.name;
        this.packageInfo.description = response.data.description;
        this.packageInfo.shopId = response.data.shop.id;
        this.packageInfo.serviceIds = response.data.services.map(service => service.id);
        console.log(response.data);
      } catch (error) {
        console.error(error);
      }
    },
    async fetchServices() {
      try {
        const response = await http.get(`api/services/get/${this.packageInfo.shopId}`);
        this.services = response.data;
        console.log(this.services);
      } catch (error) {
        console.error(error);
      }
    }
  },
  async mounted() {
    await this.fetchPackage();
    this.fetchServices();
  },
}
</script>
  
<style scoped>
  .back-icon {
  color: #007bff; /* Blue color for the icon, adjust as needed */
  text-decoration: none;
  margin-right: 20px;
  font-size: 24px;
}
  .admin-dashboard {
    display: flex;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.431); /* horizontal-offset vertical-offset blur spread color */
  
  }
  
  .main-content {
    flex: 1;
    padding: 20px;
    margin-left: 250px;
  }
  
  .admin-form {
    background-color: #f5f5f5;
    border-radius: 12px;
    padding: 30px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    max-width: 400px;
    margin: auto;
  }
  
  .form-title {
    margin-bottom: 20px;
    font-size: 24px;
    color: #333;
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
  input[type="email"],
  input[type="password"],
  input[type="file"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-sizing: border-box;
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
  
  #usertype{
    background-color: #c6e4e6;
    border-radius: 12px;
    padding: 10px;
    transition: background-color 0.3s ease;
  
  }
  #usertype:hover{
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.431); /* horizontal-offset vertical-offset blur spread color */
  
  }
</style>
