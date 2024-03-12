<template>
  <div class="admin-dashboard">
    <Sidebar :links="sidebarLinks" />
    <div class="main-content">
      <div class="admin-form">
        <h2 class="form-title">User Registration</h2>
        <form @submit.prevent="registerAdmin">
          <div class="form-group">
            <label for="usertype">User Type</label>
            <select id="usertype" v-model="admin.userType" required>
              <option value="User">User</option>
              <option value="ShopOwner">Shop Owner</option>
              <option value="Admin">Admin</option>
            </select>
          </div>
          <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" v-model="admin.username" required>
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" v-model="admin.email" required>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" v-model="admin.password" required>
          </div>
          <div class="form-group">
            <label for="profile-picture">Profile Picture</label>
            <input type="file" id="profile-picture" accept="image/*" @change="onFileChange">
          </div>
          <button type="submit" class="submit-button">Register</button>
        </form>
      </div>
    </div>
  </div>
</template>


<script>
import Sidebar from "./../../components/sideBar.vue"; // Adjust the path as per your project structure

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
      }
    };
  },
  methods: {
    onFileChange(event) {
      this.admin.profilePicture = event.target.files[0];
    },
    registerAdmin() {
      // Logic to register new admin
      console.log(this.admin);
      // Reset form fields after submission
      this.admin = {
        username: '',
        email: '',
        password: '',
        profilePicture: null
      };
    }
  }
}
</script>

<style scoped>
.admin-dashboard {
  display: flex;
  /* box-shadow: 0 0 10px rgba(0, 0, 0, 0.431);  */
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
