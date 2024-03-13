<template>
    <div class="service">
      <section class="slideshow-container">
        <div class="slides">
          <div class="slide" v-for="(service, index) in defaultServices" :key="index">
            <!-- <ImageViewer :imageData="service.image" /> -->
            <img :src="service.image" :alt="service.name">
          </div>
        </div>
        <!-- <button class="prev" @click="prevSlide">&#10094;</button>
        <button class="next" @click="nextSlide">&#10095;</button> -->
      </section>
      <section class="popular-services">
        <h2 style="text-align: center;">Popular Services</h2>
        <div class="service-list">
          <div class="service-item" v-for="(service, index) in services" :key="index">
            <div class="flex h-80 object-cover items-center">
              <ImageViewer :imageData="service.image" />
              <!-- <img :src="service.image" :alt="service.name" class="w-full"> -->
            </div>
            <div class="service-details">
              <div class="items">{{ service.name }}</div>
              <p>{{ service.description }}</p>
            </div>
          </div>
        </div>
      </section>
      <div class="title"> All Services</div>
      <div class="search-filter">
        <input type="text" v-model="searchText" placeholder="Search...">
      </div>
      <div class="service-list">
        <div class="service-item" v-for="(service, index) in services" :key="index">
          <ImageViewer :imageData="service.image" />
          <!-- <img :src="service.image" :alt="service.name"> -->
          <div class="service-details">
            <div class="items">{{ service.name }}</div>
            <p>{{ service.description }}</p>
          </div>
        </div>
      </div>
    </div>
  </template>
  
<script>
import http from "@/services/httpService";
import ImageViewer from "@/components/ImageViewer.vue";

export default {
  name: 'Service',
  components: {
    ImageViewer,
  },
  data() {
    return {
      defaultServices: [
        { name: "Service A", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://i.pinimg.com/736x/6f/89/8e/6f898ed73565070b6278b200a15eae95.jpg" },
        { name: "Service B", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://i.pinimg.com/564x/1b/fc/b1/1bfcb19ba87b83e6d3eaf74c5ad2798f.jpg" },
        { name: "Service C", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://i.pinimg.com/564x/b7/e7/63/b7e763771c8345539fa9b95c2da2a8a3.jpg" },
        // Add more services as needed
      ],
      popularServices: [
        { name: "Popular Service 1", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://i.pinimg.com/564x/90/1f/ae/901faeb84e0c7bedc998a67a98a208fd.jpg" },
        { name: "Popular Service 2", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://i.pinimg.com/564x/47/ad/ab/47adab748a6fca7d2948651af2e95ad6.jpg" },
        { name: "Popular Service 3", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://i.pinimg.com/564x/ff/3f/52/ff3f5254a0d9997e7b30e685b77e65ea.jpg" },
        // Add more popular services as needed
      ],
      services: [],
      searchText: '',
      selectedCategory: '',
      filteredServices: [],
    }
  },
  computed: {
    filteredServices() {
      const searchText = this.searchText.toLowerCase();
      const categoryFilter = this.selectedCategory.toLowerCase();
      return this.defaultServices.filter(service => {
        const nameMatches = service.name.toLowerCase().includes(searchText);
        const categoryMatches = categoryFilter === '' || service.category.toLowerCase() === categoryFilter;
        return nameMatches && categoryMatches;
      });
    }
  },
  methods: {
    searchAndFilter() {
      const searchText = this.searchText.toLowerCase();
      const categoryFilter = this.selectedCategory.toLowerCase();
      this.filteredServices = this.defaultServices.filter(service => {
        const nameMatches = service.name.toLowerCase().includes(searchText);
        const categoryMatches = categoryFilter === '' || service.category.toLowerCase() === categoryFilter;
        return nameMatches && categoryMatches;
      });
    },
    prevSlide() {
      // Implement logic for previous slide
    },
    nextSlide() {
      // Implement logic for next slide
    },
    async fetchServices() {
      try {
        const response = await http.get(`api/services/get-all/pageable?page=0&size=10`);
        this.services = response.data.content;
        // this.popularShops = response.data.content.filter(s => s.isTrending == true);
        console.log(this.shops)
      } catch (error) {
        console.error("Error:", error);
      }
    },
  },
  mounted() {
    this.fetchServices();
  },
}
</script>
  
<style scoped>
.service {
  max-width: 1200px;
  margin: auto;
  padding: 20px;
}

.slideshow-container {
  position: relative;
  margin-bottom: 20px;
  overflow: hidden;
}

.slides {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 400px;
  overflow-x: auto;
  scroll-snap-type: x mandatory;
}

.slide {
  flex: 0 0 auto;
}

.slide img {
  max-width: 100%;
  height: auto;
}

.popular-services {
  margin-bottom: 20px;
}

.service-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.service-item {
  margin: 10px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: 200px;
}

.service-item img {
  max-width: 100%;
  height: auto;
}

.service-details {
  padding: 10px;
}

.title {
  font-size: 24px;
  margin-bottom: 10px;
  text-align: center;
}

.search-filter {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  justify-content: center;
}

.search-filter input[type="text"],
.search-filter select {
  margin-right: 10px;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.search-filter button {
  padding: 5px 10px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
}

.footer {
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #f8f9fa;
  padding: 10px 20px;
  text-align: center;
}
</style>
