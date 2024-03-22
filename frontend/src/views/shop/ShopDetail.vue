<template>
  <div class="font-serif">
    <!-- Header -->
    <div class="text-7xl font-bold w-full flex justify-center pb-20">{{ shop.name }}</div>
    <div class="w-full">
      <ImageViewer :imageData="shop.imageUrl" />
    </div>
    <div class="w-full flex flex-col items-center justify-center p-16 ">
      <div class="text-6xl font-bold pb-10">About Us</div>
      <div class="text-2xl">{{ shop.description }}</div>
    </div>

    <!-- What do we offer -->
    <div v-if="services.length || packages.length" class="w-full flex flex-col items-center justify-center p-16 ">
      <div class="text-6xl font-bold pb-10">What do we offer?</div>

      <!-- Services -->
      <div v-if="services.length" class="w-full">
        <div class="text-5xl font-bold w-full flex items-center justify-center p-10">Services</div>
        <div 
          class="w-full flex p-10"
          v-for="(service, index) in services" 
          :key="index"
        >
          <div v-if="index % 2 != 0" class="w-1/2 h-96 flex">
            <div class="">
              <ImageViewer :imageData="service.image" />
            </div>
          </div>
          <div class="w-1/2 flex flex-col items-center justify-center gap-10">
            <div class="text-5xl font-semibold">{{ service.name }}</div>
            <div class="text-5xl font-semibold">${{ service.unitPrice }}</div>
            <router-link :to="`/shop/${service.shopId}/service/${service.id}/details`" >
              <div class="text-2xl">See details</div>
            </router-link>
          </div>
          <div v-if="index % 2 == 0" class="w-1/2 h-96 flex">
            <div class="">
              <ImageViewer :imageData="service.image" />
            </div>
          </div>
        </div>
        <div class="text-2xl w-full flex items-center justify-center p-5">
          <button class="w-1/4 p-2 bg-stone-300 hover:bg-stone-500 hover:text-stone-50">See More</button>
        </div>
      </div>

      <!-- Packages -->
      <div v-if="packages.length" class="w-full">
        <div class="text-5xl font-bold w-full flex items-center justify-center p-10">Packages</div>
        <div 
          class="w-full flex p-10"
          v-for="(pkg, index) in packages" 
          :key="index"
        >
          <div v-if="index % 2 != 0" class="w-1/2 h-96 flex">
            <div class="">
              <ImageViewer :imageData="pkg.image" />
            </div>
          </div>
          <div class="w-1/2 flex flex-col items-center justify-center gap-10">
            <div class="text-5xl font-semibold">{{ pkg.name }}</div>
            <div class="text-5xl font-semibold">${{ pkg.price }}</div>
            <router-link :to="`/shop/${pkg.shopId}/package/${pkg.id}/details`" >
              <div class="text-2xl">See details</div>
            </router-link>
          </div>
          <div v-if="index % 2 == 0" class="w-1/2 h-96 flex">
            <div class="">
              <ImageViewer :imageData="pkg.image" />
            </div>
          </div>
        </div>
        <div class="text-2xl w-full flex items-center justify-center p-5">
          <button class="w-1/4 p-2 bg-stone-300 hover:bg-stone-500 hover:text-stone-50">See More</button>
        </div>
      </div>
    </div>

    <!-- Contact Us -->
    <div id="contact-us" class="w-full flex flex-col items-center justify-center py-16">
      <div class="text-6xl font-bold pb-10">Contact Us</div>
      <div v-for="contact in contacts" class="flex flex-col gap-10 w-full justify-between items-center">
        <!-- Phone Number -->
        <div v-if="contact.contactType == 'Phone Number'" class="flex h-52 w-full p-5 border-2 hover:bg-slate-200 gap-10">
          <div class="flex items-center justify-center w-52">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-20">
              <path stroke-linecap="round" stroke-linejoin="round" d="M2.25 6.75c0 8.284 6.716 15 15 15h2.25a2.25 2.25 0 0 0 2.25-2.25v-1.372c0-.516-.351-.966-.852-1.091l-4.423-1.106c-.44-.11-.902.055-1.173.417l-.97 1.293c-.282.376-.769.542-1.21.38a12.035 12.035 0 0 1-7.143-7.143c-.162-.441.004-.928.38-1.21l1.293-.97c.363-.271.527-.734.417-1.173L6.963 3.102a1.125 1.125 0 0 0-1.091-.852H4.5A2.25 2.25 0 0 0 2.25 4.5v2.25Z" />
            </svg>
          </div>
          <div 
          class="grow flex lg:flex-row flex-col justify-center items-center">
            <div
              class="text-2xl font-bold"
            >
              {{ contact.url }}
            </div>
          </div>
        </div>

        <!-- Email -->
        <div v-if="contact.contactType == 'Email'" class="flex h-52 w-full p-5 border-2 hover:bg-slate-200 gap-10">
          <div class="flex items-center justify-center w-52">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-20">
              <path stroke-linecap="round" stroke-linejoin="round" d="M21.75 6.75v10.5a2.25 2.25 0 0 1-2.25 2.25h-15a2.25 2.25 0 0 1-2.25-2.25V6.75m19.5 0A2.25 2.25 0 0 0 19.5 4.5h-15a2.25 2.25 0 0 0-2.25 2.25m19.5 0v.243a2.25 2.25 0 0 1-1.07 1.916l-7.5 4.615a2.25 2.25 0 0 1-2.36 0L3.32 8.91a2.25 2.25 0 0 1-1.07-1.916V6.75" />
            </svg>
          </div>
          <div 
          class="grow flex lg:flex-row flex-col justify-center items-center">
            <div
              class="text-2xl font-bold"
            >
              {{ contact.url }}
            </div>
          </div>
        </div>

        <!-- Facebook -->
        <div v-if="contact.contactType == 'Facebook'" class="flex h-52 w-full p-5 border-2 hover:bg-slate-200 gap-10">
          <div class="flex items-center justify-center w-52">
            <img class="size-full object-contain" src="@\components\pictures\facebook.png" alt="">
          </div>
          <div 
          class="grow flex lg:flex-row flex-col justify-center items-center">
              <a :href="contact.url" @click.prevent="openLink(contact.url)" class="text-2xl font-bold">
                {{ extractUsername('Facebook', contact.url) }}
              </a>
          </div>
        </div>

        <!-- Instagram -->
        <div v-if="contact.contactType == 'Instagram'" class="flex h-52 w-full p-5 border-2 hover:bg-slate-200 gap-10">
          <div class="flex items-center justify-center w-52">
            <img class="size-full object-contain" src="@\components\pictures\instagram.png" alt="">
          </div>
          <div 
          class="grow flex lg:flex-row flex-col justify-center items-center">
            <div>
              <a :href="contact.url" @click.prevent="openLink(contact.url)" class="text-2xl font-bold">
                {{ extractUsername('Instagram', contact.url) }}
              </a>
            </div>
          </div>
        </div>

        <!-- Tik Tok -->
        <div v-if="contact.contactType == 'Tik Tok'" class="flex h-52 w-full p-5 border-2 hover:bg-slate-200 gap-10">
          <div class="flex items-center justify-center w-52">
            <img class="size-full object-contain" src="@\components\pictures\tiktok.png" alt="">
          </div>
          <div 
          class="grow flex lg:flex-row flex-col justify-center items-center">
            <div>
              <a :href="contact.url" @click.prevent="openLink(contact.url)" class="text-2xl font-bold">
                {{ extractUsername('Tik Tok', contact.url) }}
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Review Section -->
    <section class="w-full">
      <h2 class="text-3xl p-5 dark:text-stone-300">Shop Reviews</h2>
      <div class="w-full flex flex-col gap-3">
        <div
          v-for="(review, index) in reviews"
          :key="index"
          class="rounded-xl p-3 border-2"
        >
          <div class="flex items-center w-full">
            <div class="size-12 rounded-full mr-3 overflow-hidden">
              <ImageViewer :imageData="review.user.profile" alt="User Avatar" />
            </div>
            <div class="grow">
              <div class="flex">
                <div class="pr-2 font-bold">{{ review.user.username }}: </div>
                <div class="font-semibold">{{ review.title }}</div>
              </div>
              <p>{{ formatDate(review.createdAt) }}</p>
            </div>
            <div class="text-lg">
              <span
                v-for="n in 5"
                :key="n"
                :class="{
                  'text-yellow-500': n <= review.stars,
                  'text-gray-300': n > review.stars,
                }"
              >
                &#9733;
              </span>
            </div>
          </div>
          <div class="pt-2">
            {{ review.description }}
          </div>
        </div>
      </div>
    </section>

    <!-- Comment Form -->
    <section class="mt-10">
      <div class="container">
        <h2 class="text-3xl p-5 dark:text-stone-300">Add Your Review</h2>
        <form @submit.prevent="submitReview" class="w-full flex flex-col gap-2">
          <div class="flex flex-col">
            <label for="rating">Stars:</label>
            <select id="rating" v-model="newRating.stars" class="h-fit p-3 border-2 dark:bg-custom-dark">
              <option value="1">1 Star</option>
              <option value="2">2 Stars</option>
              <option value="3">3 Stars</option>
              <option value="4">4 Stars</option>
              <option value="5">5 Stars</option>
            </select>
          </div>
          <div class="flex flex-col">
            <label for="comment">Title:</label>
            <input
              id="comment"
              v-model="newRating.title"
              class="border-2 p-3 dark:bg-custom-dark"
            ></input>
          </div>
          <div class="flex flex-col">
            <label for="comment">Descriptions:</label>
            <textarea
              id="comment"
              v-model="newRating.description"
              class="border-2 p-3 dark:bg-custom-dark"
            ></textarea>
          </div>
          <button type="submit" class="h-10" style="background-color: #DD6895 ; color:white ;margin-bottom:10px">Submit</button>
        </form>
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
      userId: null,
      searchText: "",
      shop: {
        name: "Sample Shop",
        image: "https://via.placeholder.com/300",
        description:
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla vestibulum diam eget nunc consectetur condimentum. Sed sed lacus quis libero elementum egestas.",
      },
      contacts: [],
      services: [],
      packages: [],
      packageCarts: [],
      serviceCarts: [],
      newRating: {
        stars: null,
        title: '',
        description: '',
      },
      reviews: [],
    };
  },
  computed: {
    filteredPackages() {
      if (!this.searchText) return this.packages;
      const searchRegex = new RegExp(this.searchText.trim(), "i");
      return this.packages.filter(
        (pkg) => searchRegex.test(pkg.name) || searchRegex.test(pkg.details)
      );
    },
    moreReviews() {
      return this.reviews.slice(
        this.displayedReviews.length,
        this.displayedReviews.length + 3
      );
    },
  },
  methods: {
    submitComment() {
      if (this.newComment && this.newRating) {
        const newReview = {
          userName: "User", // Replace with the actual username of the logged-in user
          userAvatar: "avatar.jpg", // Replace with the actual avatar URL of the logged-in user
          date: new Date().toLocaleDateString(), // Use the current date
          comment: this.newComment,
          rating: parseInt(this.newRating),
        };
        this.reviews.push(newReview);
        // Reset form fields after submission
        this.newComment = "";
        this.newRating = null;
      } else {
        alert("Please fill in both the comment and rating fields.");
      }
    },
    submitComment() {
      // Submit comment logic, add new comment to shopReviews and reset newComment and newRating
      this.reviews.push({
        comment: this.newComment,
        rating: this.newRating,
      });
      this.newComment = "";
      this.newRating = null;
    },
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
        this.contacts.sort((a, b) => {
          // Additional checks added to ensure contactTypeId exists and is a string
          const contactTypeA = a.contactTypeId?.toString() ?? "";
          const contactTypeB = b.contactTypeId?.toString() ?? "";
          return contactTypeA.localeCompare(contactTypeB);
        });
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
        this.packages = response.data.filter((pkg) => pkg.services.length > 0);
        console.log(this.packages);
      } catch (error) {
        console.log(error);
      }
    },
    async fetchUserCards() {
      try {
        const response = await http.get(`api/cards/get-all/${this.userId}`);
        // this.cards = response.data.filter(response.data.pkg != null);
        this.packageCarts = response.data.filter((card) => card.pkg != null);
        this.serviceCarts = response.data.filter(
          (card) => card.service != null
        );
        // this.cards = response.data;
        console.log("user carts:");
        console.log(this.packageCarts);
        console.log(this.serviceCarts);
      } catch (error) {
        console.log(error);
      }
    },
    async addToCard(productType, id) {
      try {
        if (productType == "package") {
          await http.post(`api/cards/add`, {
            userId: this.userId,
            serviceId: null,
            packageId: id,
            quantity: 1,
          });
        } else if (productType == "service") {
          console.log(id);
          console.log(this.userId);
          await http.post(`api/cards/add`, {
            userId: this.userId,
            serviceId: id,
            packageId: null,
            quantity: 1,
          });
        } else {
          console.log("Product type not found!");
        }

        this.fetchUserCards();
      } catch (error) {
        console.log(error);
      }
    },
    async removeFromCard(productType, id) {
      try {
        let cardToRemove = null;
        if (productType == "package") {
          // Find the first card that has the specified package ID
          cardToRemove = this.packageCarts.find((card) => card.pkg.id === id);
        } else if (productType == "service") {
          // Find the first card that has the specified package ID
          cardToRemove = this.serviceCarts.find(
            (card) => card.service.id === id
          );
        } else {
          console.log("Product type not found!");
          return;
        }

        // If a card is found, make an HTTP request to remove it by its ID
        if (cardToRemove) {
          const response = await http.post(
            `api/cards/remove/${cardToRemove.id}`
          );
          console.log(response.data); // Handle the response as needed
          this.fetchUserCards();
        } else {
          console.log("Card not found"); // Log a message if no matching card is found
        }
      } catch (error) {
        console.log(error);
      }
    },
    async fetchReviews(shopId) {
      try {
        const response = await http.get(`api/users/reviews/all/${shopId}`);
        this.reviews = response.data;
        console.log(this.reviews);
      } catch (error) {
        console.error("Error:", error);
      }
    },
    formatDate(dateString) {
      // Convert the date string to a Date object
      const date = new Date(dateString);

      // Check if the date is valid
      if (isNaN(date.getTime())) {
        // Handle invalid date (optional)
        return 'Invalid Date';
      }

      // Format options including the day of the week
      const options = { 
        weekday: 'long', // full name of the day of the week
        year: 'numeric', 
        month: 'long', 
        day: 'numeric', 
        hour: 'numeric', 
        minute: 'numeric', 
        second: 'numeric' 
      };

      // Format the date as desired
      const formattedDate = date.toLocaleString('en-US', options);

      return formattedDate;
    },
    async submitReview() {
      try {
        const response = await http.post(`api/users/reviews/insert`, {
          'userId': this.userId,
          'shopId': this.shop.id,
          'title': this.newRating.title,
          'description': this.newRating.description,
          'stars': this.newRating.stars,
        });
        console.log(response.data);
        this.fetchReviews(this.shop.id);
        this.newRating.title = '';
        this.newRating.description = '';
        this.newRating.stars = null;
      } catch (error) {
        console.log(error);
      }
    },
    extractUsername(platform, url) {
      if (platform == 'Facebook') {
        const match = url.match(/\/([^\/?#]+)$/);
        return match ? match[1] : url;
      } else if (platform == 'Instagram') {
        const match = url.match(/\/([^\/?#]+)\?/);
        return match ? match[1] : null;
      } else if (platform == 'Tik Tok') {
        const match = url.match(/@([^/?#]+)/);
        return match ? match[1] : null;
      } else {
        return;
      }
    }
  },
  mounted() {
    const shopId = this.$route.params.shopId;
    const userStore = useUserStore();
    this.userId = userStore.user.id;

    this.fetchShopsDetails(shopId);
    this.fetchContacts(shopId);
    this.fetchServices(shopId);
    this.fetchPackages(shopId);
    this.fetchReviews(shopId);
    this.fetchUserCards();
  },
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

  .contact-info h3,
  .services h3 {
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
