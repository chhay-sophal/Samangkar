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

          <h3><i class="fas fa-chart-line"></i> Dashboard </h3>
        </div>
        <ul class="sidebar-list">
          <div class="menu"><router-link to="/admin"><i class="fas fa-chart-pie"></i> Overview</router-link></div>
          <!-- <div class="menu"><router-link to="/add">Add</router-link></div> -->
          <!-- <div class="menu"><router-link to="/user"><i class="fas fa-users"></i> Administrators</router-link></div>
          <div class="menu"><router-link to="/user"><i class="fas fa-users"></i> Shop Owners</router-link></div> -->
          <div class="menu"><router-link to="/user"><i class="fas fa-users"></i> Users</router-link></div>
          <div class="menu"><router-link to="/shop"><i class="fas fa-store"></i> Shop</router-link></div>
          <div class="menu"><router-link to="/package"><i class="fas fa-shopping-cart"></i> Package</router-link></div>
          <!-- <div class="menu"><router-link to="/contact"><i class="fas fa-box"></i> contact</router-link></div> -->
          <div class="menu"><router-link to="/review"><i class="fas fa-comments"></i> Review</router-link></div>
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
  padding-top: 10px;
}
.menu:hover{
  background-color: rgb(76, 70, 86);
  transition: ease;
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

/* Font Awesome icons */
.fa-bars {
  font-size: 24px;
}
</style>
