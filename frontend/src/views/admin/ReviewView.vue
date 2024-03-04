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
                <th>Rating</th>
                <th>Comment</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="review in reviews" :key="review.id">
                <td>{{ review.userID }}</td>
                <td>{{ review.shopID }}</td>
                <td>{{ review.rating }}</td>
                <td>{{ review.comment }}</td>
                <td>
                  <button @click="editReview(review)" class="edit-button">Edit</button>
                  <button @click="deleteReview(review.id)" class="delete-button">Delete</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import Sidebar from "./../../components/sideBar.vue"; // Adjust the path as per your project structure
  
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
          { id: 1, userID: 'User1', shopID: 'Shop1', rating: 4, comment: 'Great service!' },
          { id: 2, userID: 'User2', shopID: 'Shop2', rating: 5, comment: 'Excellent experience!' },
          // Add more reviews as needed
        ]
      };
    },
    methods: {
      editReview(review) {
        // Logic to edit review
        console.log('Edit review:', review);
      },
      deleteReview(reviewId) {
        // Logic to delete review
        console.log('Delete review with ID:', reviewId);
      }
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
  