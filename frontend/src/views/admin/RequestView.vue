<template>
    <div class="review-table-page">
      <Sidebar :links="sidebarLinks" />
      <div class="main-content">
        <h2>Shop Owner Requests</h2>
        <div class="table-container">
          <table class="w-full border">
            <thead>
              <tr>
                <th class="border">User ID</th>
                <th class="border">Shop ID</th>
                <th class="border">Purpose</th>
                <th class="border">Description</th>
                <th class="border">Created At</th>
                <th class="border">Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="request in requests" :key="request.id" class="h-10">
                <td class="border">{{ request.id }}</td>
                <td class="border">{{ request.shopId }}</td>
                <td class="border">{{ request.purpose }}</td>
                <td class="border">{{ request.description }}</td>
                <td class="border">{{ formatDate(request.createdAt) }}</td>
                <td class="border">
                  <button v-if="!request.deleteAt" @click="handleDelete(request.id)" class="size-full bg-blue-600">Complete</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </template>
  
<script>
import Sidebar from "./../../components/AdminSidebar.vue"; // Adjust the path as per your project structure
import http from "@/services/httpService";

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
        // Add more sidebar links as needed
      ],
      requests: []
    };
  },
  methods: {
    formatDate(dateString) {
      // Convert the date string to a Date object
      const date = new Date(dateString);

      // Check if the date is valid
      if (isNaN(date.getTime())) {
        // Handle invalid date (optional)
        return 'Invalid Date';
      }

      // Format options including the day of the week
      const options = { 
        weekday: 'long', // full name of the day of the week
        year: 'numeric', 
        month: 'long', 
        day: 'numeric', 
        hour: 'numeric', 
        minute: 'numeric', 
        second: 'numeric' 
      };

      // Format the date as desired
      const formattedDate = date.toLocaleString('en-US', options);

      return formattedDate;
    },
    async handleDelete(requestId) {
      if (confirm(`Are you sure to delete this request?`)) {
        try {
          const response = await http.post(`api/requests/delete/${requestId}`);
          console.log(response.data);
          this.fetchRequests();
        } catch (error) {
          console.error("Error:", error);
        }
      }
    },
    editReview(review) {
      // Logic to edit review
      console.log('Edit review:', review);
    },
    async fetchRequests() {
      try {
        const response = await http.get(`api/requests/get-all`);
        this.requests = response.data;
        console.log(this.requests)
      } catch (error) {
        console.error("Error:", error);
      }
    }
  },
  mounted() {
    this.fetchRequests();
  }
};
</script>
  
  <style scoped>
  .review-table-page {
    display: flex;
    margin-left: 250px;;
  }
  
  .main-content {
    flex: 1;
    padding: 20px;
  }
  
  h2 {
    font-size: 24px;
    margin-bottom: 20px;
  }
  
  .table-container {
    overflow-x: auto;
  }
  
  .review-table {
    width: 100%;
    border-collapse: collapse;
  }
  
  .review-table th,
  .review-table td {
    padding: 12px;
    border: 1px solid #ccc;
  }
  
  .review-table th {
    background-color: #f0f0f0;
    font-weight: bold;
    text-align: left;
  }
  
  .review-table td {
    text-align: left;
  }
  
  .review-table tbody tr:nth-child(even) {
    background-color: #f9f9f9;
  }
  
  .edit-button,
  .delete-button {
    padding: 8px 12px;
    margin-right: 6px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 14px;
    transition: background-color 0.3s ease;
  }
  
  .delete-button:hover {
    background-color: #dc3545;
  }
  
  .edit-button:hover {
    background-color: #0056b3;
  }
  </style>