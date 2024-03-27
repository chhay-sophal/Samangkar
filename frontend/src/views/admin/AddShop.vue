<template>
    <div class="admin-dashboard">
      <Sidebar :links="sidebarLinks" />
      <div class="main-content">
        <router-link to="/admin/shops" class="back-icon">
          <i class="mdi mdi-arrow-left">Back</i> 
        </router-link>
        <div class="admin-form">
          <h2 class="form-title">Add Shop</h2> 
          <form @submit.prevent="registerShop">
            <div class="form-group">
              <label for="shop-id">Owner</label>
              <input type="text" id="search" v-model="shopInfo.ownerId" list="shops-list" />
              <datalist id="shops-list">
                <option v-for="user in users.filter(u => u.role == 'SHOP_OWNER')" :value="user.id">
                  {{ user.username }}
                </option>
              </datalist>
            </div>
            <div class="form-group">
              <label for="name">Shop Name</label>
              <input class="dark:text-stone-600" type="text" id="name" v-model="shopInfo.name" required>
            </div>
            <div class="form-group">
              <label for="description">Description</label>
              <textarea class="w-full rounded-lg p-3 dark:text-stone-600" id="description" v-model="shopInfo.description" required></textarea>
            </div>
            <!-- <div class="form-group">
              <label for="trending">Trending</label>
              <label>
                <input type="radio" id="trending" v-model="shopInfo.isTrending" value="true" />
                  Yes
              </label>
              <label>
                <input type="radio" id="trending" v-model="shopInfo.isTrending" value="false" />
                  No
              </label>
            </div> -->
            <!-- <div class="form-group">
              <label for="image">Shop Image</label>
              <input type="file" id="image" accept="image/*" @change="onFileChange">
            </div> -->
            <button type="submit" class="submit-button">Add</button>
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
        admin: {
          username: '',
          email: '',
          password: '',
          profilePicture: null
        },
        shopInfo: {
          shopId: null,
          ownerId: null,
          name: '',
          description: '',
          isTrending: null,
          image: null,
        },
        shops: [],
        users: []
      };
    },
    methods: {
      async fetchShops() {
        try {
          const response = await http.get(`api/shops/get-all`);
          this.shops = response.data;
          console.log(this.shops)
        } catch (error) {
          console.error("Error:", error);
        }
      },
      onFileChange(event) {
        this.admin.profilePicture = event.target.files[0];
      },
      // registerShop() {
      //   // Logic to register new admin
      //   // console.log(this.admin);
      //   // Reset form fields after submission
      //   this.admin = {
      //     username: '',
      //     email: '',
      //     password: '',
      //     profilePicture: null
      //   };
      // },
      async registerShop() {
        try {
          await http.post(`api/shops/insert`, { 
            "shopName": this.shopInfo.name, 
            "shopImageUrl": null,
            "shopOwnerId": this.shopInfo.ownerId,
            "description": this.shopInfo.description,
            "isTrending": this.shopInfo.isTrending,
          });

          // if (this.shopInfo.image) {
          //   const formData = new FormData();
          //   formData.append('file', convertImageToBlob(this.shopInfo.image), 'image.jpg')

          //   await http.post(`api/shops/${this.shopInfo.shopId}/image/upload`, formData);
          // }

          console.log("Shop added:", this.shopInfo);
          alert("Shop added successfuly!");
          this.$router.push("/admin/shops");
        } catch (error) {
          console.error(error);
        }
      },
      async fetchUserList() {
      try {
        const response = await http.get(`api/users/get-all`);
        this.users = response.data;
        console.log(this.users)
      } catch (error) {
        console.error(error)
      }
    },
    },
    mounted() {
      this.fetchUserList();
    }
  }
  </script>
  
  <style scoped>
  .admin-dashboard {
    display: flex;
  }
  
  .main-content {
    margin-left: 250px;

    flex: 1;
    padding: 20px;
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
  </style>
