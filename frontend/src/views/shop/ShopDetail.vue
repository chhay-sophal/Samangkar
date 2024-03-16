<template>
  <div>
    <!-- Shop Detail Section -->
    <section class="shop-detail">
      <div class="container">
        <h1 class="main-heading">Shop Details</h1>
        <div class="shop-info">
          <div class="shop-image">
            <div class="">
              <ImageViewer :imageData="shop.imageUrl" />
            </div>
          </div>
          <div class="shop-description">
            <h2 class="sub-heading">{{ shop.name }}</h2>
            <p>{{ shop.description }}</p>
            <div class="contact-info">
              <h3 class="text-xl pt-3 pb-1">Contact Information</h3>
              <p v-for="contact in contacts">
                <strong>{{ contact.contactType }}:</strong> 
                <a :href="contact.url" @click.prevent="openLink(contact.url)">{{ contact.url }}</a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Services Section -->
    <section class="packages">
      <div class="container">
        <!-- <div class="search-package">
          <input type="text" placeholder="Search Service..." class="search-input" v-model="searchText" @input="search">
        </div> -->
        <h2 class="sub-heading">Services</h2>
        <div class="grid grid-cols-3 gap-2">
          <div class="package-item" v-for="(service, index) in services" :key="index">
            <div class=""><ImageViewer :imageData="service.image" /></div>
            <!-- <img :src="service.image" :alt="service.name" class="package-image"> -->
            <div class="package-details">
              <h3>{{ service.name }}</h3>
              <div class="package-price">{{ service.unitPrice }}$</div>
              <p>{{ service.description }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Package Section -->
    <section class="packages">
      <div class="container">
        <!-- <div class="search-package">
          <input type="text" placeholder="Search Package..." class="search-input" v-model="searchText" @input="search">
        </div> -->
        <h2 class="sub-heading">Packages</h2>
        <div class="grid grid-cols-3 gap-2">
          <div class="package-item" v-for="(pkg, index) in packages" :key="index">
            <div class="">
              <ImageViewer :imageData="pkg.image" />
            </div>
            <div class="package-details">
              <h3>{{ pkg.name }}</h3>
              <div class="package-price">{{ pkg.price }}$</div>
              <p>{{ pkg.description }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import http from '@/services/httpService';
import ImageViewer from '@/components/ImageViewer.vue';

export default {
  components: {
    ImageViewer,
  },
  data() {
    return {
      searchText: '',
      shop: {
        name: "Sample Shop",
        image: "https://via.placeholder.com/300",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla vestibulum diam eget nunc consectetur condimentum. Sed sed lacus quis libero elementum egestas.",
      },
      contacts: {
        phone: "123-456-7890",
        email: "sample@example.com",
        address: "123 Main Street, City, Country"
      },
      services: ["Service 1", "Service 2", "Service 3"],
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
  },
  methods: {
    openLink(link) {
      if (link.startsWith("http://") || link.startsWith("https://")) {
        // If it's an absolute URL, open it directly
        window.open(link, "_blank");
      } else {
        // If it's a relative URL, open it without adding the current URL as a prefix
        window.open(link, "_blank");
      }
    },
    async fetchShopsDetails(shopId) {
      try {
        const response = await http.get(`api/shops/get/${shopId}`);
        this.shop = response.data;
        console.log(this.shop);
      } catch (error) {
        console.log(error);
      }
    },
    async fetchContacts(shopId) {
      try {
        const response = await http.get(`api/contacts/get-all/${shopId}`);
        this.contacts = response.data;
        console.log(this.contacts);
      } catch (error) {
        console.log(error);
      }
    },
    async fetchServices(shopId) {
      try {
        const response = await http.get(`api/services/get-all/${shopId}`);
        this.services = response.data;
        console.log(this.services);
      } catch (error) {
        console.log(error);
      }
    },
    async fetchPackages(shopId) {
      try {
        const response = await http.get(`api/packages/get-all/${shopId}`);
        this.packages = response.data;
        console.log(this.packages);
      } catch (error) {
        console.log(error);
      }
    }
  },
  mounted() {
    const shopId = this.$route.params.shopId;
    this.fetchShopsDetails(shopId);
    this.fetchContacts(shopId);
    this.fetchServices(shopId);
    this.fetchPackages(shopId);
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
  /* width: calc(33.333% - 20px); */
  width: auto;
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
