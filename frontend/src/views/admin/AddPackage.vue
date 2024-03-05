<template>
  <div class="add-package-page">
    <Sidebar :links="sidebarLinks" />
    <div class="main-content">
      <h2>Add Package</h2>
      <form @submit.prevent="addPackage">
        <div class="form-group">
          <label for="package-name">Package Name</label>
          <input type="text" id="package-name" v-model="packageName" required>
        </div>
        <div class="form-group">
          <label for="description">Description</label>
          <textarea id="description" v-model="description" required></textarea>
        </div>
        <div class="form-group">
          <label for="shop-id">Shop ID</label>
          <input type="text" id="shop-id" v-model="shopID" required>
        </div>
        <button type="submit" class="submit-button">Add Package</button>
      </form>
    </div>
  </div>
</template>

<script>
// i
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
      packageName: '',
      description: '',
      shopID: ''
    };
  },
  methods: {
    addPackage() {
      // Here you would typically send an API request to add the package to the database
      // For simplicity, I'm just logging the package data
      console.log('Package Name:', this.packageName);
      console.log('Description:', this.description);
      console.log('Shop ID:', this.shopID);

      // Clear form fields after submission
      this.packageName = '';
      this.description = '';
      this.shopID = '';
    }
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
