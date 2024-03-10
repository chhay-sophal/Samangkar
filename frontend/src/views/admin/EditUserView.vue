<template>
    <div class="admin-dashboard">
      <Sidebar :links="sidebarLinks" />
      <div class="main-content">
        <router-link to="/user" class="back-icon">
          <i class="mdi mdi-arrow-left">Back</i> <!-- Assuming you're using Material Design Icons -->
        </router-link>
        <div class="admin-form">
          <h2 class="form-title">Edit User</h2> <!-- Changed title to "Edit User" -->
          <form @submit.prevent="handleUpdate">
            <div class="form-group">
              <label for="username">Username</label>
              <input type="text" id="username" v-model="userInfo.username" required>
            </div>
            <div class="form-group">
              <label for="email">Email</label>
              <input type="email" id="email" v-model="userInfo.email" required>
            </div>
            
            <!-- <div class="form-group">
              <label for="password">Password</label>
              <input type="password" id="password" v-model="admin.password" required>
            </div> -->
            <div class="form-group">
              <label for="profile-picture">Profile Picture</label>
              <input type="file" id="profile-picture" accept="image/*" @change="onFileChange">
            </div>
            <button type="submit" class="submit-button">Update</button> <!-- Changed button text to "Update" -->
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
        userInfo: {
          userId: null,
          username: '',
          email: '',
          profilePicture: null
        }
      };
    },
    methods: {
      handleUpdate() {
        if (confirm("Are you sure you want to update?")) {
          // Call update method
          this.updateUser();
        }
      },
      async updateUser() {
        try {
          await http.post(`api/users/update/${this.userInfo.userId}`, { 
            "username": this.userInfo.username, 
            "email": this.userInfo.email 
          });

          if (this.userInfo.profilePicture) {
            const formData = new FormData();
            formData.append('file', convertImageToBlob(this.userInfo.profilePicture), 'image.jpg')

            await http.post(`api/users/${this.userInfo.userId}/image/upload`, formData);
          }

          console.log("User updated:", this.userInfo);
          alert("User updated successfuly!");
          this.$router.push("/user");
        } catch (error) {
          console.error(error);
        }
      },
      onFileChange(event) {
        const file = event.target.files[0];

            if (file) {
                const reader = new FileReader();

                reader.onload = (e) => {
                  this.userInfo.profilePicture = e.target.result;
                };

                reader.readAsDataURL(file);
            }
      }
    },
    async mounted() {
      this.userInfo.userId = this.$route.params.userId;
      try {
        const response = await http.get(`api/users/get/${this.userInfo.userId}`);
        this.userInfo.username = response.data.username;
        this.userInfo.email = response.data.email;
        console.log(response.data);
      } catch (error) {
        console.error(error);
      }
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
