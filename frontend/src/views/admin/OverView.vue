<template>
  <div class="overview-page">
    <Sidebar :links="sidebarLinks" />
    <div class="main-content">
      <h1>Overview</h1>
      <div class="section">
        <h2>User Management</h2>
        <router-link to="/admin/users" class="view-all-link">View All</router-link>
        <div class="stats">
          <div class="stat dark:bg-gray-700">
            <h3>Total Users</h3>
            <p>{{ usersData.length }}</p>
          </div>
          <div class="stat dark:bg-gray-700">
            <h3>Active Users</h3>
            <p>{{ usersData.filter(user => !user.deletedAt).length }}</p>
          </div>
          <div class="stat dark:bg-gray-700">
            <h3>Inactive Users</h3>
            <p>{{ usersData.filter(user => user.deletedAt).length }}</p>
          </div>
        </div>
      </div>
      <div class="section">
        <h2>Shop Management</h2>
        <router-link to="/admin/shops" class="view-all-link">View All</router-link>
        <div class="stats">
          <div class="stat dark:bg-gray-700">
            <h3>Total Shops</h3>
            <p>{{ shopsData.length }}</p>
          </div>
          <div class="stat dark:bg-gray-700">
            <h3>Active Shops</h3>
            <p>{{ shopsData.filter(shop => !shop.deletedAt).length }}</p>
          </div>
          <div class="stat dark:bg-gray-700">
            <h3>Inactive Shops</h3>
            <p>{{ shopsData.filter(shop => shop.deletedAt).length }}</p>
          </div>
        </div>
      </div>
      <div class="section">
        <h2>Requests Management</h2>
        <router-link to="/admin/request" class="view-all-link">View All</router-link>
        <div class="stats">
          <div class="stat dark:bg-gray-700">
            <h3>Total Requests</h3>
            <p>{{ requests.length }}</p>
          </div>
          <div class="stat dark:bg-gray-700">
            <h3>Pending Requests</h3>
            <p>{{ requests.filter(r => !r.deletedAt).length }}</p>
          </div>
          <div class="stat dark:bg-gray-700">
            <h3>Completed Requests</h3>
            <p>{{ requests.filter(r => r.deletedAt).length }}</p>
          </div>
        </div>
      </div>
      <div class="section">
        <h2>Packages Management</h2>
        <router-link to="/admin/packages" class="view-all-link">View All</router-link>
        <div class="stats">
          <div class="stat dark:bg-gray-700">
            <h3>Total Packages</h3>
            <p>{{ packagesData.filter(p => !p.deletedAt).length }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Sidebar from "./../../components/AdminSidebar.vue"; // Adjust the path as per your project structure
import http from "@/services/httpService";
import { useUserStore } from '@/store/userStore'

export default {
  components: {
    Sidebar
  },
  data() {
    return {
      sidebarLinks: [
        { text: 'Dashboard', icon: 'mdi-view-dashboard', route: '/dashboard' },
        { text: 'Users', icon: 'mdi-account', route: '/users' },
        { text: 'Shops', icon: 'mdi-store', route: '/shops' },
        { text: 'Reviews', icon: 'mdi-message', route: '/reviews' },
      ],
      // Sample data for stats
      // totalUsers: 100,
      // activeUsers: 80,
      // inactiveUsers: 20,
      // totalShops: 50,
      // activeShops: 40,
      // inactiveShops: 10,
      // totalReviews: 200,
      // positiveReviews: 150,
      // negativeReviews: 50,

      usersData: [],
      shopsData: [],
      reviewsData: [],
      packagesData: [],
      requests: [],
    };
  },
  methods: {
    async fetchUserList() {
      try {
        const response = await http.get(`api/users/get-all`);
        this.usersData = response.data;
        console.log(this.usersData);
      } catch (error) {
        console.error(error)
      }
    },
    async fetchShopList() {
      try {
        const response = await http.get(`api/shops/get-all`);
        this.shopsData = response.data;
        console.log(this.shopsData);
      } catch (error) {
        console.error(error)
      }
    },
    async fetchPackageList() {
      try {
        const response = await http.get(`api/packages/get-all`);
        this.packagesData = response.data;
        console.log(this.packagesData);
      } catch (error) {
        console.error(error)
      }
    },
    async fetchRequests() {
      try {
        this.requests = (await http.get(`api/requests/get-all`)).data;
        this.requests = response.data;
        console.log(this.requests)
      } catch (error) {
        console.error("Error:", error);
      }
    }
  },
  mounted() {
    const userStore = useUserStore()
    if (userStore.user.role == "USER") {
        this.$router.push({ name: 'profilePageRoute' })
    } else if (userStore.user.role == "SHOP_OWNER") {
        this.$router.push({ name: 'ShopOwnerProfile' })
    } 
    
    if (!userStore.user) {
        this.$router.push({ name: 'loginPageRoute' })
    }

    this.fetchUserList();
    this.fetchShopList();
    this.fetchPackageList();
    this.fetchRequests();
  }
};
</script>

<style scoped>
.overview-page {
  margin-left: 250px;
  display: flex;
}

.main-content {
  flex: 1;
  padding: 20px;
}

h1 {
  font-size: 36px;
  margin-bottom: 20px;
}

.section {
  margin-bottom: 40px;
}

h2 {
  font-size: 24px;
  margin-bottom: 10px;
}

.stats {
  display: flex;
  justify-content: space-between;
  gap: 10px;
}

.stat {
  flex: 1;
  text-align: center;
  padding: 20px;
  border-radius: 12px;
  /* background-color: #f5f5f5; */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.stat h3 {
  margin-bottom: 10px;
  font-size: 18px;
}

.stat p {
  font-size: 24px;
  font-weight: bold;
}

.view-all-link {
  font-size: 16px;
  color: #007bff;
  text-decoration: none;
  transition: color 0.3s ease;
}

.view-all-link:hover {
  color: #0056b3;
}
</style>
