<template>
  <div>
    <nav>
      <div class="sidebaricon" @click="toggleSidebar">
        <i class="fas fa-bars"></i>
      </div>

      <div class="sidebar" :class="{ 'active': isSidebarActive }" id="sidebar">
        <div class="sidebar-header">
          <div class="back-icon" @click="hideSidebar">
            <i class="fas fa-arrow-left"></i>
          </div>

          <!-- Add admin picture and name -->
          <div class="admin-info">
            <img src="https://th.bing.com/th/id/OIP.CBFZpMOFqyCjyHOJxouwVAHaE8?rs=1&pid=ImgDetMain" alt="Admin Picture" class="admin-picture">
            <p class="admin-name"> Nidet </p>
          </div>
        </div>
        <ul class="sidebar-list">
          <router-link to="/add"><div class="menu">Add Users</div></router-link>
          <router-link to="/overview"><div class="menu"><i class="fas fa-chart-pie"></i> Overview</div></router-link>
          <router-link to="/user"><div class="menu"><i class="fas fa-users"></i> Users</div></router-link>
          <router-link to="/shop"><div class="menu"><i class="fas fa-store"></i> Shop</div></router-link>
          <router-link to="/package"><div class="menu"><i class="fas fa-shopping-cart"></i> Package</div></router-link>
          <!-- <div class="menu"><router-link to="/contact"><i class="fas fa-box"></i> contact</router-link></div> -->
          <router-link to="/review"><div class="menu"><i class="fas fa-comments"></i> Review</div></router-link>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isSidebarActive: false
    };
  },
  methods: {
    toggleSidebar() {
      this.isSidebarActive = !this.isSidebarActive;
    },
    hideSidebar() {
      this.isSidebarActive = false;
    }
  }
};
</script>

<style scoped>
/* Your CSS styles go here */
.menu{
  padding-left:20px;
  padding-top: 20px;
}
.menu:hover{
  background-color: rgba(220, 207, 240, 0.417);
  transition: ease;
  color: #242b38;
}
.sidebaricon {
  display: none;
}

.sidebar {
  position: fixed;
  left: 0;
  top: 0;
  height: 100%;
  width: 240px; /* Default width for larger screens */
  background-color: #2f3542; /* Dark background color */
  color: #fff;
  transition: width 0.3s ease;
}

.back-icon {
  display: none;
}

/* Responsive styles */


.sidebar.active {
  left: 0; /* Move the sidebar into view when active */
}

.sidebar-header {
  padding: 20px;
  text-align: center;
  background-color: #242b38;
}

.sidebar-header h3 {
  margin: 0;
  font-size: 1.2rem;
}

.sidebar-list {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.sidebar-list li {
  padding: 10px 20px;
  border-bottom: 1px solid #39465e; /* Darker border color */
}

.sidebar-list li:last-child {
  border-bottom: none;
}

.sidebar-list li a {
  color: #fff;
  text-decoration: none;
  display: flex;
  align-items: center;
  transition: background-color 0.3s ease; /* Smooth hover effect */
}

.sidebar-list li a:hover {
  background-color: #39465e; /* Darker background color on hover */
}

.sidebar-list li a i {
  margin-right: 10px;
}

.sidebar-icon {
  position: fixed;
  top: 20px;
  right: 20px;
  cursor: pointer;
  z-index: 1000; /* Ensure it's above the sidebar */
}

.admin-info {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.admin-picture {
  width: 50px; /* Adjust as needed */
  height: 50px; /* Adjust as needed */
  border-radius: 50%; /* Ensures the picture is round */
}

.admin-name {
  margin-top: 10px;
}
/* Font Awesome icons */
.fa-bars {
  font-size: 24px;
}
</style>
