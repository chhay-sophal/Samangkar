<template>
  <div class="admin-dashboard">
    <Sidebar :links="sidebarLinks" />
    <div class="main-content">
      <div class="admin-form">
        <h2 class="form-title">User Registration</h2>
        <form @submit.prevent="registerUser">
          <div class="form-group">
            <label for="usertype">User Type</label>
            <select id="usertype" v-model="userInfo.role" required>
              <!-- <option value="USER">User</option> -->
              <option value="SHOP_OWNER">Shop Owner</option>
              <option value="ADMIN">Admin</option>
            </select>
          </div>
          <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" placeholder="Username" v-model="userInfo.username" required>
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" placeholder="Email" v-model="userInfo.email" required>
          </div>
          <div class="form-group">
            <label for="profile-picture">Profile Picture</label>
            <input type="file" id="profile-picture" accept="image/*" @change="onFileChange">
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" placeholder="Password" v-model="userInfo.password" required>
          </div>
          <div class="form-group">
            <label for="confirm-password">Confirm Password</label>
            <input type="password" id="confirm-password" placeholder="Comfirm password" v-model="userInfo.confirmPassword" required>
          </div>
          <button type="submit" class="submit-button">Register</button>
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
        role: null,
        username: '',
        email: '',
        password: '',
        confirmPassword: '',
        profilePicture: null
      }
    };
  },
  methods: {
    onFileChange(event) {
      const file = event.target.files[0];

          if (file) {
              const reader = new FileReader();

              reader.onload = (e) => {
                this.userInfo.profilePicture = e.target.result;
              };

              reader.readAsDataURL(file);
          }
    },
    async registerUser() {
      try {
        if (this.userInfo.password === this.userInfo.confirmPassword) {
          const user = await http.post(`api/users/register`, {
            "role": this.userInfo.role,
            "username": this.userInfo.username,
            "email": this.userInfo.email,
            "password": this.userInfo.password
          });

          if (this.userInfo.profilePicture) {
            const formData = new FormData();
            formData.append('file', convertImageToBlob(this.userInfo.profilePicture), 'image.jpg')

            await http.post(`api/users/${user.data.id}/image/upload`, formData);
          }

          alert("User registered successfuly!");
          this.$router.push("/admin/users");
        } else {
          alert("Passwords do not match!");
        }
      } catch (error) {
        console.error("Error:", error);
      }
    }
  }
}
</script>

<style scoped>
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
