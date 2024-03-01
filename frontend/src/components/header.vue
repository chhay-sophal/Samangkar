<template>
    <header class="navbar">
      <div class="container">
        <img
          class="logo"
          src="https://i.fbcd.co/products/original/872706a08d201dc5e37c525ac9e2281a621b64a002d302cac603a9ba1b238fe7.jpg"
          alt="Logo"
        />
        <nav class="nav-links">
          <router-link to="/">Home</router-link>
          <router-link to="/about">About</router-link>
          <router-link to="/services">Services</router-link>
          <router-link to="/contact">Contact</router-link>
        </nav>
      </div>
    </header>
  </template>
  
  <script>
  export default {
    name: "Navbar",
  };
  </script>
  
  <style scoped>

.navbar {
  background-color: #ffffff;
  color: #181515;
  padding: 10px 0;
}

.container {
  display: flex;
  align-items: center;
  justify-content: space-between; /* Adjusted */
  max-width: 1200px;
  margin: 0 auto;
}

.logo {
  width: 100px;
  height: auto;
}

.nav-links {
  flex: 1; /* Added */
  display: flex;
  justify-content: center; /* Adjusted */
}

.nav-links a {
  text-decoration: none;
  color: #181515;
  margin: 0 20px; /* Adjust spacing between links */
  transition: color 0.3s;
}

.nav-links a:hover {
  color: #dd57c6;
}

  </style>
  