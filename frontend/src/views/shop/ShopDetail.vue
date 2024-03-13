<template>
    <div>
      <!-- Shop Detail Section -->
      <section class="shop-detail">
        <div class="container">
          <h1 class="main-heading">Shop Detail</h1>
          <div class="shop-info">
            <div class="shop-image">
              <img :src="shop.image" :alt="shop.name">
            </div>
            <div class="shop-description">
              <h2 class="sub-heading">{{ shop.name }}</h2>
              <p>{{ shop.description }}</p>
              <div class="contact-info">
                <h3>Contact Information</h3>
                <p><strong>Phone:</strong> {{ shop.contact.phone }}</p>
                <p><strong>Email:</strong> <a :href="'mailto:' + shop.contact.email">{{ shop.contact.email }}</a></p>
                <p><strong>Address:</strong> {{ shop.contact.address }}</p>
              </div>
              <div class="services">
                <h3>Services</h3>
                <ul>
                  <li v-for="service in shop.services" :key="service">{{ service }}</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </section>
  
      <!-- Package Section -->
      <section class="packages">
        <div class="container">
          <div class="search-package">
            <input type="text" placeholder="Search Package..." class="search-input" v-model="searchText" @input="search">
          </div>
          <h2 class="sub-heading">Packages</h2>
          <div class="package-list">
            <div class="package-item" v-for="(pkg, index) in filteredPackages" :key="index">
              <img :src="pkg.image" :alt="pkg.name" class="package-image">
              <div class="package-details">
                <h3>{{ pkg.name }}</h3>
                <div class="package-price">{{ pkg.price }}</div>
                <p>{{ pkg.details }}</p>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        searchText: '',
        shop: {
          name: "Sample Shop",
          image: "https://via.placeholder.com/300",
          description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla vestibulum diam eget nunc consectetur condimentum. Sed sed lacus quis libero elementum egestas.",
          contact: {
            phone: "123-456-7890",
            email: "sample@example.com",
            address: "123 Main Street, City, Country"
          },
          services: ["Service 1", "Service 2", "Service 3"]
        },
        packages: [
          { name: "Package 1", price: "$10", details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://via.placeholder.com/150" },
          { name: "Package 2", price: "$20", details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://via.placeholder.com/150" },
          { name: "Package 3", price: "$30", details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://via.placeholder.com/150" }
        ]
      };
    },
    computed: {
      filteredPackages() {
        if (!this.searchText) return this.packages;
        const searchRegex = new RegExp(this.searchText.trim(), 'i');
        return this.packages.filter(pkg => searchRegex.test(pkg.name) || searchRegex.test(pkg.details));
      }
    }
  };
  </script>
  
  
  <style>
  .container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
  }
  
  .main-heading {
    text-align: center;
    margin-bottom: 30px;
    font-size: 2.5em;
  }
  
  .sub-heading {
    font-size: 2em;
    margin-bottom: 20px;
  }
  
  .shop-detail .shop-info {
    display: flex;
  }
  
  .shop-image img {
    width: 300px;
    height: auto;
    border-radius: 5px;
    padding-right: 20px;
  }
  
  .contact-info h3, .services h3 {
    margin-top: 0;
  }
  
  ul {
    list-style-type: none;
    padding: 0;
  }
  
  ul li {
    margin-bottom: 5px;
  }
  
  .packages {
    margin-top: 50px;
  }
  
  .search-package {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .search-input {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .search-button {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .package-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  
  .package-item {
    width: calc(33.333% - 20px);
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .package-image {
    width: 100%;
    border-radius: 5px 5px 0 0;
  }
  
  .package-details {
    padding: 20px;
  }
  </style>
  