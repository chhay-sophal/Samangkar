<template>
    <div class="section">
      <h2>User Management</h2>
      <div class="stats">
        <div class="stat active">
          <h3>Active Accounts</h3>
          <p>{{ activeAccounts }}</p>
        </div>
        <div class="stat inactive">
          <h3>Inactive Accounts</h3>
          <p>{{ inactiveAccounts }}</p>
        </div>
        <div class="stat new">
          <h3>New User Registrations</h3>
          <p>{{ newUserRegistrations }}</p>
        </div>
      </div>
      <button @click="addNewAccount">Add New Account</button>
      <!-- User management component -->
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        activeAccounts: 0,
        inactiveAccounts: 0,
        newUserRegistrations: 0
      };
    },
    methods: {
      addNewAccount() {
        // Logic to add new user account
        // This could involve opening a modal or navigating to a new page for user creation
      }
    }
  }
  </script>
  
  <style scoped>
  .section {
    background-color: #f9f9f9;
    border: 1px solid #ccc;
    border-radius: 10px; /* Border radius increased for rounded corners */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Shadow added */
    padding: 20px;
  }
  
  .stats {
    display: flex;
    justify-content: space-around;
    margin-bottom: 20px;
  }
  
  .stat {
    text-align: center;
    padding: 15px;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .stat h3 {
    margin-bottom: 5px;
    font-size: 18px;
  }
  
  .active {
    background-color: #4caf50;
    color: #fff;
  }
  
  .inactive {
    background-color: #f44336;
    color: #fff;
  }
  
  .new {
    background-color: #2196f3;
    color: #fff;
  }
  
  button {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  </style>
  