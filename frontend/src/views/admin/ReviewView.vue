<template>
    <div class="review-table-page">
      <Sidebar :links="sidebarLinks" />
      <div class="main-content">
        <h2>Review Table</h2>
        <div class="table-container">
          <table class="review-table">
            <thead>
              <tr>
                <th>User ID</th>
                <th>Shop ID</th>
                <th>Title</th>
                <th>Stars</th>
                <th>Description</th>
                <!-- <th>Deleted</th> -->
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="review in reviews" :key="review.id">
                <td>{{ review.user_id }}</td>
                <td>{{ review.shop_id }}</td>
                <td>{{ review.title }}</td>
                <td>{{ review.stars }}</td>
                <td>{{ review.description }}</td>
                <!-- <td>{{ review.deleteAt ? "Yes" : "No" }}</td> -->
                <td>
                  <!-- <button @click="editReview(review)" class="edit-button">Edit</button> -->
                  <button v-if="!review.deleteAt" @click="handleDelete(review)" class="delete-button">Delete</button>
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
      reviews: [
        { id: 1, user_id: 'User1', shop_id: 'Shop1', rating: 4, comment: 'Great service!' },
        { id: 2, user_id: 'User2', shop_id: 'Shop2', rating: 5, comment: 'Excellent experience!' },
        // Add more reviews as needed
      ]
    };
  },
  methods: {
    handleDelete(review) {
      if (confirm(`Are you sure to delete review with title "${review.title}"?`)) {
        this.deleteReview(review.id);
      }
    },
    editReview(review) {
      // Logic to edit review
      console.log('Edit review:', review);
    },
    async deleteReview(reviewId) {
      try {
        const response = await http.put(`api/users/reviews/delete/${reviewId}`);
        this.reviews = response.data;
        this.fetchReviews();
      } catch (error) {
        console.error("Error:", error);
      }
    },
    async fetchReviews() {
      try {
        const response = await http.get(`api/users/reviews/all`);
        this.reviews = response.data;
        console.log(this.reviews)
      } catch (error) {
        console.error("Error:", error);
      }
    }
  },
  mounted() {
    this.fetchReviews();
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