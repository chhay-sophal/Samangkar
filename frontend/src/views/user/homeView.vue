<template>
  <div>
    <section class="slideshow-container">
      <div class="slides">
        <div class="slide flex items-center justify-center" v-for="(shop, index) in shops" :key="index">
          <ImageViewer :imageData="shop.imageUrl"/>
          <!-- <img :src="shop.imgUrl" :alt="shop.name"> -->
        </div>
      </div>
      <button class="prev" @click="prevSlide">&#10094;</button>
      <button class="next" @click="nextSlide">&#10095;</button>
    </section>
    <section class="popular-shops">
      <div class="flex">
        <h2 class="text-2xl p-5 dark:text-stone-300 grow">Popular Shops</h2>
        <h2 class="text-2xl p-5 dark:text-stone-300">See all</h2>
      </div>
      <div class="shop-list" id="popularShopList">
        <div class="shop-item" v-for="(shop, index) in popularShops" :key="index">
          <ImageViewer :imageData="shop.imageUrl" class="flex aspect-square object-fill"/>
          <!-- <img :src="shop.image" :alt="shop.name"> -->
          <div class="shop-details">
            <div class="items">{{ shop.name }}</div>
            <p>{{ shop.description }}</p>
          </div>
        </div>
      </div>
    </section>
    <div class="title">Explore</div>
    <div class="search-filter">
      <input type="text" v-model="searchText" placeholder="Search...">
      <select v-model="selectedCategory">
        <option value="">All Categories</option>
        <option value="Premium">Premium</option>
        <option value="MiddleClass">MiddleClass</option>
        <option value="Simple">Simple</option>
      </select>
      <button @click="searchShops()">Apply</button>
    </div>
    <section class="shop-list flex flex-col" id="shopList">
      <div class="flex">
        <h2 class="text-2xl p-5 dark:text-stone-300 grow">Popular Shops</h2>
        <h2 class="text-2xl p-5 dark:text-stone-300">See all</h2>
      </div>
      <div class="shop-list">
        <div class="shop-item" v-for="(shop, index) in shops" :key="index">
          <div class="">
            <ImageViewer :imageData="shop.imageUrl" class="flex aspect-square object-fill"/>
          </div>
          <!-- <img :src="shop.image" :alt="shop.name"> -->
          <div class="shop-details">
            <div class="items">{{ shop.name }}</div>
            <p>{{ shop.description }}</p>
          </div>
        </div>
      </div>
    </section>
    <section class="packages">
      <!-- <h2>Packages</h2> -->
      <div class="flex">
        <h2 class="text-2xl p-5 dark:text-stone-300 grow">Packages</h2>
        <h2 class="text-2xl p-5 dark:text-stone-300">See all</h2>
      </div>
      <div class="package-list" id="packageList">
        <div class="package-item" v-for="(pkg, index) in packages" :key="index">
          <div class="">
            <ImageViewer :imageData="pkg.image" />
          </div>
          <div class="package-details">
            <div class="items">{{ pkg.name }}</div>
            <div>$ {{ pkg.price }}</div>
            <p>{{ pkg.description }}</p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import http from "@/services/httpService";
import ImageViewer from "@/components/ImageViewer.vue";
import { useUserStore } from "@/store/userStore";

export default {
  components: {
    ImageViewer,
  },
  data() {
    return {
      shops: [
        { name: "Shop A", category: "Premium", image: "https://z-p3-scontent.fpnh5-3.fna.fbcdn.net/v/t39.30808-6/428153665_791689982989980_6073961733013835102_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeG1MUA7vJDcBm9IiF8urEuE-jdovJ1I5bD6N2i8nUjlsEa03xSyAJ_zG_UOuQwxG0rU8LDBlKQKYew-9SwDlYjR&_nc_ohc=gQoGVfGg4LwAX8QsL7w&_nc_zt=23&_nc_ht=z-p3-scontent.fpnh5-3.fna&oh=00_AfANqkn6ZwC9NPF_Svd6jeNkKQjZ_h_a0PtSVGeS1M6hnQ&oe=65F4AE32" },
        { name: "Shop B", category: "MiddleClass", image: "https://z-p3-scontent.fpnh5-3.fna.fbcdn.net/v/t39.30808-6/425244851_18329928646128706_2411480114611815302_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeFm4aPmZM3ZADh5CT4L-6RSfCpdaYHG8AR8Kl1pgcbwBJkGa7-Nh0xX_kXwVsFK8XGWD-a-Syy1T_moYm0uQcu7&_nc_ohc=s9yb-t7yIysAX8pLDN9&_nc_zt=23&_nc_ht=z-p3-scontent.fpnh5-3.fna&oh=00_AfDAr2Kaq4qUxludAGmwYWhLzGQQ85JEnA9a_hOJR95meA&oe=65F56C9A" },
        { name: "Shop C", category: "Simple", image: "https://z-p3-scontent.fpnh5-3.fna.fbcdn.net/v/t39.30808-6/423134135_778479424311036_5424355302339905050_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeFQrPOx6ImZcwh731UFlfZgF1ytLNFDZ2wXXK0s0UNnbIDsx53bp2cw6cQiyfnIIENu4cS7sujE86HB3Z0MC5yS&_nc_ohc=2By98vsoTO0AX_FPaTn&_nc_zt=23&_nc_ht=z-p3-scontent.fpnh5-3.fna&oh=00_AfD_UMfscNLxEsqqW0nqOcClf9sYkSGcHHVLHFawTWMcjA&oe=65F59FC0" }
      ],
      popularShops: [
        { name: "Popular Shop 1", category: "Clothing", image: "https://via.placeholder.com/150" },
        { name: "Popular Shop 2", category: "Electronics", image: "https://via.placeholder.com/150" },
        { name: "Popular Shop 3", category: "Books", image: "https://via.placeholder.com/150" }
      ],
      packages: [
        { name: "Package 1", price: "$10", details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://i.pinimg.com/564x/f3/4d/2b/f34d2bc33c132f07d8e18265d24a78ec.jpg" },
        { name: "Package 2", price: "$20", details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://i.pinimg.com/564x/f3/4d/2b/f34d2bc33c132f07d8e18265d24a78ec.jpg" },
        { name: "Package 3", price: "$30", details: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", image: "https://i.pinimg.com/564x/f3/4d/2b/f34d2bc33c132f07d8e18265d24a78ec.jpg" }
      ],
      searchText: '',
      selectedCategory: '',
      filteredShops: []
    };
  },
  methods: {
    prevSlide() {
      // Logic for previous slide
      const slides = document.querySelectorAll('.slide');
      const lastSlide = slides[slides.length - 1];
      const firstSlide = slides[0];
      lastSlide.parentNode.insertBefore(lastSlide, firstSlide);
    },
    nextSlide() {
      // Logic for next slide
      const slides = document.querySelectorAll('.slide');
      const firstSlide = slides[0];
      firstSlide.parentNode.appendChild(firstSlide);
    },
    searchAndFilter() {
      // Logic for searching and filtering shops
      const searchText = this.searchText.toLowerCase();
      const categoryFilter = this.selectedCategory.toLowerCase();
      
      this.filteredShops = this.shops.filter(shop => 
        (shop.name.toLowerCase().includes(searchText) || shop.category.toLowerCase().includes(searchText)) &&
        (categoryFilter === "" || shop.category.toLowerCase() === categoryFilter)
      );
    },
    async searchShops() {
      try {
        if (this.searchText) {
          const response = await http.get(`api/shops/search/${this.searchText}?page=0&size=10`);
          this.shops = response.data.content;
          console.log(this.shops)
        } else {
          this.fetchShops();
        }
      } catch (error) {
        console.error("Error:", error);
      }
    },
    async fetchShops() {
      try {
        const response = await http.get(`api/shops/get-all/pagable?page=0&size=10`);
        this.shops = response.data.content;
        this.popularShops = response.data.content.filter(s => s.isTrending == true);
        console.log(this.shops)
      } catch (error) {
        console.error("Error:", error);
      }
    },
    async fetchPackages() {
      try {
        // const response = await http.get(`api/packages/get-all/with-services?page=0&size=10`);
        const response = await http.get(`api/packages/get-all`);
        this.packages = response.data;
        console.log(this.packages)
      } catch (error) {
        console.error("Error:", error);
      }
    },
  },
  mounted() {
    const userStore = useUserStore()
    if (!userStore.user.username) {
        this.$router.push({ name: 'loginPageRoute' })
    }
    // Initialize any data or perform initial actions here
    // this.filteredShops = this.shops;
    this.fetchShops();
    this.fetchPackages()
  }
};
</script>

<style>
/* Homepage */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

main {
  padding: 20px;
}

.slideshow-container {
  position: relative;
  max-width: 100%;
  margin: auto;
  margin-bottom: 20px;
  overflow: hidden;
  justify-content: center;
  align-items: center;
}

.slides {
  justify-content: center;
  align-items: center;
  display: flex;
  height: 400px; /* Adjust the height as needed */
  overflow-x: auto; /* Enable horizontal scrolling */
  scroll-snap-type: x mandatory; /* Snap to each slide */
}

.slide {
  flex-shrink: 0; /* Prevent resizing of slides */
  margin-right: 10px; /* Add some space between slides */
}

.slide img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center; /* Ensure the center of the image is visible */
}

.title {
  font-size: 2rem;
  padding: 20px;
  font-style: oblique;
  text-align: center;
  /* color: #5A585C; */
}

.search-filter {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.search-filter input[type="text"],
.search-filter select {
  margin-right: 10px;
  padding: 8px;
  border: 1px solid #DD6895;
  border-radius: 5px;
  font-size: 16px;
}

.search-filter button {
  padding: 8px 16px;
  background-color: #DD6895;
  /* color: #FFFFFF; */
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

.shop-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  grid-gap: 20px;
}

.shop-item {
  background-color: #EFD6E1;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease; /* Add transition for smooth hover effect */
}

.shop-item:hover {
  transform: translateY(-5px); /* Move item up on hover */
}

.shop-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.shop-details {
  padding: 10px;
}

.items {
  color: #DD6895;
  font-weight: bold;
  font-style: oblique;
  font-size: 1.2rem;
}

.shop p {
  color: #5A585C;
}

/* Add animation for slideshow */
.slide {
  opacity: 0;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  animation-name: slideAnimation;
  animation-duration: 1s; /* Duration of each slide animation */
  animation-timing-function: cubic-bezier(0.25, 0.1, 0.25, 1); /* Customize the timing function */
  animation-fill-mode: forwards;
}

@keyframes slideAnimation {
  0% {
      opacity: 0;
      transform: translateX(-100%);
  }
  100% {
      opacity: 1;
      transform: translateX(0);
  }
}

.slide:nth-child(1) {
  animation-delay: 0s; /* Delay for the first slide */
}

.slide:nth-child(2) {
  animation-delay: 2s; /* Delay for the second slide */
}

.slide:nth-child(3) {
  animation-delay: 4s; /* Delay for the third slide */
}

/* General styles */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

main {
  padding: 20px;
}

h2 {
  color: #5A585C;
}

.shop-list,
.package-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

.shop-item,
.package-item {
  width: 30%;
  margin-bottom: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s ease;
}

.shop-item:hover,
.package-item:hover {
  transform: translateY(-5px);
}

.shop-item img,
.package-item img {
  width: 100%;
  height: auto;
  object-fit: cover;
}

.shop-details,
.package-details {
  padding: 10px;
}

.shop-details .items,
.package-details .items {
  font-weight: bold;
  color: #DD6895;
}

.shop-details p,
.package-details p {
  color: #5A585C;
}
/* Add styles for package items */
.package-item {
  width: 30%;
  margin-bottom: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s ease;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.package-item:hover {
  transform: translateY(-5px);
}

.package-item img {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.package-details {
  padding: 10px;
  text-align: center;
}

.package-details .items {
  font-weight: bold;
  color: #DD6895;
}

.package-details p {
  color: #5A585C;
}
</style>
