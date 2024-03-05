<template>
  <div class="shop-table-page">
    <Sidebar />
    <h1 class="page-title">Shop Table</h1>
    <div class="search-container">
      <label for="search">Search:</label>
      <input type="text" id="search" v-model="searchQuery" list="shops-list" />
      <datalist id="shops-list">
        <option v-for="shop in shops" :value="shop.name">
          {{ shop.name }}
        </option>
      </datalist>
    </div>
    <div class="table-container">
      <table class="shop-table">
        <thead>
          <tr>
            <th>Shop ID</th>
            <th>Name</th>
            <th>Profile Image</th>
            <th>Owner ID</th>
            <th>Status</th>
            <th>Trending</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="shop in filteredShops" :key="shop.shopID">
            <td>{{ shop.shopID }}</td>
            <td>{{ shop.name }}</td>
            <td>
              <img
                :src="shop.profileImage"
                alt="Profile Image"
                class="profile-image"
              />
            </td>
            <td>{{ shop.ownerID }}</td>
            <td>{{ shop.active ? "Active" : "Inactive" }}</td>
            <td>{{ shop.trending ? "Yes" : "No" }}</td>
            <td>
              <button @click="editShop(shop)" class="action-button edit-button">
                Edit
              </button>
              <button
                @click="deleteShop(shop.shopID)"
                class="action-button delete-button"
              >
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import Sidebar from "./../../components/AdminSidebar.vue"; // Adjust the path as per your project structure

export default {
  name: "ShopTablePage",
  components: {
    Sidebar,
  },
  data() {
    return {
      shops: [
        {
          shopID: 1,
          name: "Shop A",
          profileImage: "https://via.placeholder.com/50", // Sample profile image URL
          ownerID: "owner1",
          active: true,
          trending: true,
        },
        {
          shopID: 2,
          name: "Shop B",
          profileImage: "https://via.placeholder.com/50", // Sample profile image URL
          ownerID: "owner2",
          active: false,
          trending: false,
        },
        {
          shopID: 3,
          name: "Shop C",
          profileImage: "https://via.placeholder.com/50", // Sample profile image URL
          ownerID: "owner3",
          active: true,
          trending: false,
        },
      ],
      searchQuery: "",
    };
  },
  computed: {
    filteredShops() {
      return this.shops.filter((shop) =>
        shop.name.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    },
  },
  methods: {
    editShop(shop) {
      // Handle edit action
      console.log("Edit shop:", shop);
    },
    deleteShop(shopID) {
      // Handle delete action
      console.log("Delete shop with ID:", shopID);
    },
  },
};
</script>

<style scoped>
.shop-table-page {
  padding: 20px;
  margin-left: 250px;
}

.page-title {
  font-size: 24px;
  margin-bottom: 20px;
}

.search-container {
  margin-bottom: 20px;
}

.search-container label {
  margin-right: 10px;
}

.search-container input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.table-container {
  overflow-x: auto;
}

.shop-table {
  width: 100%;
  border-collapse: collapse;
  background-color: #fff;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

.shop-table th,
.shop-table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.shop-table th {
  background-color: #f8f9fa;
}

.shop-table tbody tr:hover {
  background-color: #f2f2f2;
}

.profile-image {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.action-button {
  padding: 6px 12px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.edit-button {
  margin-right: 20px;
  background-color: #007bff;
  color: #fff;
}

.edit-button:hover {
  background-color: #0056b3;
}

.delete-button {
  background-color: #dc3545;
  color: #fff;
}

.delete-button:hover {
  background-color: #c82333;
}
</style>
