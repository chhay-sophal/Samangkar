<template>
    <div class="section">
      <h2 class="section-title">Shop Management</h2>
      <div class="stats">
        <div class="stat active">
          <h3>Active Shops</h3>
          <p>{{ activeShops }}</p>
        </div>
        <div class="stat inactive">
          <h3>Inactive Shops</h3>
          <p>{{ inactiveShops }}</p>
        </div>
        <div class="stat new">
          <h3>New Shop Registrations</h3>
          <p>{{ newShopRegistrations }}</p>
        </div>
      </div>
      <button @click="addNewShop" class="add-button">Add New Shop</button>
      <!-- Shop management component -->
    </div>
  </template>
  
  <script>
  export default {
   
    data() {
      return {
        activeShops: 0,
        inactiveShops: 0,
        newShopRegistrations: 0,
      };
    },
    methods: {
      addNewShop() {
        // Logic to add new shop
        // This could involve opening a modal or navigating to a new page for shop creation
      },
    },
  };
  </script>
  
  <style scoped>
  /* Existing styles */
  .section {
    background-color: #f5f5f5;
    border-radius: 12px;
    padding: 30px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  .section-title {
    color: #333;
    font-size: 24px;
    margin-bottom: 20px;
  }
  
  .stats {
    display: flex;
    justify-content: space-around;
    margin-bottom: 30px;
  }
  
  .stat {
    text-align: center;
    padding: 20px;
    border-radius: 12px;
    background-color: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s ease;
  }
  
  .stat h3 {
    margin-bottom: 10px;
    font-size: 18px;
    color: #333;
  }
  
  .stat p {
    font-size: 24px;
    color: #ffffff;
    font-weight: bold;
  }
  
  .active {
    background-color: #43a047;
    color: #fff;
  }
  
  .inactive {
    background-color: #e53935;
    color: #fff;
  }
  
  .new {
    background-color: #1e88e5;
    color: #fff;
  }
  
  .add-button {
    padding: 15px 40px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 12px;
    cursor: pointer;
    font-size: 18px;
    transition: background-color 0.3s ease;
  }
  
  .add-button:hover {
    background-color: #0056b3;
  }
  </style>
  