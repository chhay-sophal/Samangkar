<template>
    <div class="package-detail">
      <div class="container">
        <h2 class="sub-heading">Package Detail</h2>
        <div class="row">
          <div class="col-md-6">
            <div class="package-image">
              <img :src="selectedPackage.image" :alt="selectedPackage.name">
            </div>
          </div>
          <div class="col-md-6">
            <div class="package-info">
              <h3>{{ selectedPackage.name }}</h3>
              <div class="package-price">{{ selectedPackage.price }}</div>
              <p>{{ selectedPackage.details }}</p>
              <router-link class="contact-button" to="/shop/detail">Contact Us</router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        selectedPackage: null
      };
    },
    created() {
      const packageId = this.$route.params.id;
      this.selectedPackage = this.fetchPackageDetails(packageId);
    },
    methods: {
      fetchPackageDetails(packageId) {
        // Fetch package details based on the ID
        // Replace this with your actual data fetching logic
        return {
          id: packageId,
          name: "Sample Package",
          price: "$50",
          details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
          image: "https://via.placeholder.com/300"
        };
      }
    }
  };
  </script>
  
  <style scoped>
  .package-detail {
    padding: 50px 0;
  }
  
  .container {
    max-width: 800px;
    margin: 0 auto;
  }
  
  .sub-heading {
    text-align: center;
    font-size: 2em;
    margin-bottom: 30px;
  }
  
  .package-image img {
    width: 100%;
    height: auto;
    border-radius: 5px;
  }
  
  .package-info {
    padding: 0 20px;
  }
  
  .package-info h3 {
    font-size: 1.8em;
    margin-bottom: 10px;
  }
  
  .package-price {
    font-size: 1.5em;
    margin-bottom: 20px;
  }
  
  .package-info p {
    font-size: 1.2em;
    line-height: 1.6;
    margin-bottom: 30px;
  }
  
  .contact-button {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    font-size: 1.2em;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .contact-button:hover {
    background-color: #0056b3;
  }
  </style>
  