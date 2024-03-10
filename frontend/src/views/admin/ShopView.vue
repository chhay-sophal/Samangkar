<template>
  <div class="shop-table-page">
    <Sidebar />
    <h1 class="page-title">Shop Table</h1>
    <div class="flex gap-5">
      <div class="search-container">
        <label for="search">Search:</label>
        <input type="text" id="search" v-model="searchQuery" placeholder="Search" @input="searchShops">
      </div>
      <!-- <div class="search-container">
        <label for="search">Search:</label>
        <input type="text" id="search" v-model="searchQuery" list="shops-list" />
        <datalist id="shops-list">
          <option v-for="shop in shops.content" :value="shop.name">
            {{ shop.name }}
          </option>
        </datalist>
      </div> -->
    </div>
    <div class="table-container">
      <table class="shop-table">
        <thead>
          <tr>
            <th>Shop ID</th>
            <th>Name</th>
            <th>Profile Image</th>
            <th>Owner</th>
            <th>Status</th>
            <th>Trending</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="shop in shops" :key="shop.id">
            <td>{{ shop.id }}</td>
            <td>{{ shop.name }}</td>
            <td>
              <div class="aspect-square overflow-hidden flex items-center size-16">
                <ImageViewer :imageData="shop.imageUrl" />
              </div>
            </td>
            <td>{{ shop.owner.username }}</td>
            <td>{{ !shop.deletedAt ? "Active" : "Inactive" }}</td>
            <td>{{ shop.isTrending ? "Yes" : "No" }}</td>
            <td>
              <router-link :to="`/shop/edit/${shop.id}`">
                <button class="action-button edit-button">
                  Edit
                </button>
              </router-link>
              <button
                v-if="shop.deletedAt"
                @click="activateShop(shop.id)"
                class="action-button delete-button w-24"
              >
                Activate
              </button>
              <button
                v-else
                @click="deactivateShop(shop.id)"
                class="action-button delete-button w-24"
              >
                Deactivate
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <div>
        <Pagination :currentPage="currentPage" :totalPages="totalPages" :first="first" :last="last" @page-change="handlePageChange"/>
      </div>
    </div>
  </div>
</template>

<script>
import Sidebar from "./../../components/AdminSidebar.vue"; // Adjust the path as per your project structure
import Pagination from "./Pagination.vue";
import http from "@/services/httpService";
import ImageViewer from "@/components/ImageViewer.vue";

export default {
  name: "ShopTablePage",
  components: {
    Sidebar,
    ImageViewer,
    Pagination,
  },
  data() {
    return {
      shops: [],
      searchQuery: "",
      currentPage: 1,
      size: 10,
      totalPages: 0,
      totalElements: 0,
      first: true,
      last: false,
    };
  },
  methods: {
    async activateShop(shopId) {
      try {
        if (confirm(`Are you sure to activate shop with id ${shopId}?`)) {
          await http.post(`api/shops/activate/${shopId}`);
          alert(`User with id ${shopId} activated successfully!`);
          this.fetchShops();
        }
      } catch (error) {
        console.error(error);
      }
    },
    async deactivateShop(shopId) {
      try {
        if (confirm(`Are you sure to deactivate shop with id ${shopId}?`)) {
          await http.post(`api/shops/deactivate/${shopId}`);
          alert(`User with id ${shopId} deactivated successfully!`);
          this.fetchShops();
        }
      } catch (error) {
        console.error(error);
      }
    },
    async searchShops() {
      try {
        if (this.searchQuery) {
          const response = await http.get(`api/shops/search/${this.searchQuery}?page=${this.currentPage - 1}&size=${this.size}`);
          this.shops = response.data.content;
          this.totalPages = response.data.totalPages;
          this.first = response.data.first;
          this.last = response.data.last;
          console.log(this.shops)
        } else {
          this.fetchShops();
        }
      } catch (error) {
        console.error("Error:", error);
      }
    },
    async fetchShops() {
      try {
        const response = await http.get(`api/shops/get-all/pagable?page=${this.currentPage - 1}&size=${this.size}`);
        this.shops = response.data.content;
        this.totalPages = response.data.totalPages;
        this.first = response.data.first;
        this.last = response.data.last;
        console.log(this.shops)
      } catch (error) {
        console.error("Error:", error);
      }
    },
    async handlePageChange(newPage) {
      this.currentPage = newPage;
      await this.searchShops();
    },
  },
  mounted() {
    this.fetchShops();
  }
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
  color: darkslategray
}

.table-container {
  overflow-x: auto;
}

.shop-table {
  width: 100%;
  border-collapse: collapse;
  /* background-color: #fff; */
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
  color: darkslategray;
}

.shop-table tbody tr:hover {
  color: darkslategray;
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
