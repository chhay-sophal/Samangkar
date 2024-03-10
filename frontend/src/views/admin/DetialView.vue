<template>
    <div class="admin-dashboard">
      <Sidebar :links="sidebarLinks" />
      <div class="main-content">
        <div class="admin-form">
          <h2 class="form-title">User Profile</h2>
          <div class="profile-info">
            <div class="profile-picture">
              <ImageViewer :imageData="userInfo.profile" />
              <!-- <img :src="user.profile" alt="Profile Picture"> -->
            </div>
            <div class="profile-details">
              <div class="detail">
                <span class="label">Role:</span>
                <span>{{ userInfo.role }}</span>
              </div>
              <div class="detail">
                <span class="label">Username:</span>
                <span>{{ userInfo.username }}</span>
              </div>
              <div class="detail">
                <span class="label">Email:</span>
                <span>{{ userInfo.email }}</span>
              </div>
            </div>
          </div>
          <router-link :to="`/admin/users/edit/${userInfo.id}`"><button class="edit-button">Edit</button></router-link>

        </div>
      </div>
    </div>
  </template>
  
<script>
import Sidebar from "./../../components/AdminSidebar.vue"; // Adjust the path as per your project structure
import ImageViewer from "@/components/ImageViewer.vue";
import http from "@/services/httpService";

export default {
  name: 'AdminDashboard',
  components: {
    Sidebar,
    ImageViewer,
  },
  data() {
    return {
      sidebarLinks: [
        { text: 'User Profile', icon: 'mdi-account' },
        { text: 'Other Section', icon: 'mdi-folder' },
        // Add more sidebar links as needed
      ],
      userInfo: []
    };
  },
  async mounted() {
    this.userInfo.userId = this.$route.params.userId;
    try {
      const response = await http.get(`api/users/get/${this.userInfo.userId}`);
      this.userInfo = response.data;
      // this.userInfo.username = response.data.username;
      // this.userInfo.email = response.data.email;
      console.log(response.data);
      console.log(this.userInfo);
    } catch (error) {
      
    }
  },
}
</script>
<style scoped>
.edit-button {

    margin-top: 20px;
  padding: 10px 20px;
  border: none;
  border-radius: 8px;
  background-color: #28a745; /* Green color, you can adjust as needed */
  color: #fff; /* White color for text */
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.edit-button:hover {
  background-color: #218838; /* Darker shade of green on hover */
}

.admin-dashboard {
  display: flex;
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
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.42);
  max-width: 600px;
  margin: auto;
}

.form-title {
  margin-bottom: 20px;
  font-size: 24px;
  color: #333;
}

.profile-info {
  display: flex;
  align-items: center;
}

.profile-picture {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  overflow: hidden;
  margin-right: 20px;
}

.profile-picture img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.profile-details p {
  margin-bottom: 10px;
}

.label {
    font-weight: bold;
  font-size: 18px;
  padding: 8px 12px;
  border-radius: 8px;
  color: #bd0b0b; /* White color for text */
  display: inline-block;
}

.input-field {
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
