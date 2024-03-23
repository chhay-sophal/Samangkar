<template>
    <div>
      <!-- Your existing sections -->
      <section class="pb-20">
        <div class="flex w-full items-center pb-10 justify-center">
          <h2 class="text-5xl font-bold dark:text-stone-300">{{ service.name }}</h2>
        </div>
        <div class="items-center justify-center mb-20 w-full" style="height: 700px;">
          <ImageViewer class="flex object-fill" :imageData="service.image" />
        </div>
        <div v-if="service.trending" class="w-full flex flex-col items-center justify-center pb-20">
          <div  class="text-5xl font-bold text-yellow-500">
            Popular Now!
          </div>
        </div>
        <div class="w-full flex flex-col items-center justify-center pb-20">
          <div class="text-5xl font-bold pb-5">
            What is it?
          </div>
          <div class="text-4xl">
            {{ service.description }}
          </div>
        </div>
        <div class="w-full flex pb-20 gap-5">
          <div class="w-1/4"></div>
          <div class="w-1/4 text-5xl font-bold flex items-center justify-center">At just</div>
          <div class="w-1/4 font-bold flex items-center justify-center" style="font-size: 100px;">${{ service.unitPrice }}</div>
          <div class="w-1/4"></div>
        </div>
        <div class="w-full p-20">
          <div class="text-5xl font-bold flex items-center justify-center">Provided by</div>
          <div class="font-bold flex items-center justify-center" style="font-size: 100px;">{{ service.shopName }}</div>
        </div>
        <div class="w-full flex">
          <button 
            v-if="serviceCarts?.some(card => card.service?.id === service.id)" 
            @click="routeToCartView()" 
            class="w-1/2 flex items-center justify-center"
          >
            <div class="h-20 w-1/2 flex items-center justify-center border-2 bg-green-500 text-stone-100">
              <div class="text-xl font-semibold">Added to Cart</div>
            </div>
          </button>
          <button 
            v-else 
            @click="addToCard(service.id)" 
            class="w-1/2 flex items-center justify-center"
          >
            <div class="h-20 w-1/2 flex items-center justify-center border-2 hover:bg-green-500 hover:text-stone-100">
              <div class="text-xl font-semibold">Add to Cart</div>
            </div>
          </button>
          <router-link class="w-1/2 flex items-center justify-center" :to="`/shop/${service.shopId}/details`">
            <div class="h-20 w-1/2 flex items-center justify-center border-2 hover:bg-blue-500 hover:text-stone-100">
              <div class="text-xl font-semibold">
                Contact Provider
              </div>
            </div>
          </router-link>
        </div>
      </section>
    </div>

    <div 
      v-if="showAlert"
      class="bg-green-500 text-stone-100 text-xl font-medium flex justify-center fixed top-28 left-1/2 p-3 rounded-lg"
    >
      Added to cart!
    </div>
  </template>
  
  <script>
  import ImageViewer from "@/components/ImageViewer.vue";
  import http from "@/services/httpService";
  import { useUserStore } from "@/store/userStore";

  export default {
    components: {
      ImageViewer,
    },
    data() {
      return {
        service: {
          // name: "ហែជំនូន",
          // description: "Get our experienced barbers.",
          // price: 25,
          // type: 'flower',
          // image: "https://i.pinimg.com/564x/45/ac/f1/45acf1a37c19801181e80e031d78dfa3.jpg",
        },
        showAlert: false,
        serviceCarts: [],
      };
    },
    methods: {
      async fetchServiceDetails(serviceId) {
        try {
          const response = await http.get(`api/services/details/${serviceId}`);
          this.service = response.data;
          console.log(this.service);
        } catch (error) {
          console.log(error);
        }
      },
      async addToCard(id) {
        try {
          await http.post(`api/cards/add`, {
            "userId": this.userId,
            "serviceId": id,
            "packageId": null,
            "quantity": 1,
          });

          this.fetchUserCards();
          this.showAlert = true

          setTimeout(() => {
            this.showAlert = false;
          }, 2000);
        } catch (error) {
          console.log(error);
          if (!this.userId) {
            this.$router.push({ name: 'loginPageRoute' })
          }
        }
      },
      async fetchUserCards() {
        try {
          const userId = useUserStore().user.id;
          const response = await http.get(`api/cards/get-all/${userId}`);
          this.serviceCarts = response.data.filter(card => card.service != null);
          console.log('user carts:');
          console.log(this.serviceCarts);
        } catch (error) {
          console.log(error)
        }
      },
      routeToCartView() {
          this.$router.push({ path: '/profile/cards' });
      },
    },
    mounted() {
      const userStore = useUserStore()
      this.userId = userStore.user.id;

      const serviceId = this.$route.params.serviceId;
      this.fetchServiceDetails(serviceId);
      this.fetchUserCards();
    },
  };
  </script>
  
  <style scoped>
  .service-detail {
    margin-top: 20px;
  }
  
  .service-info {
    display: flex;
    justify-content: space-between;
  }
  
  .service-details {
    flex: 1;
    padding: 20px;
  }
  
  .service-image {
    flex: 1;
  }
  
  .book-button:hover {
    background-color: #0b7dda;
  }
  
  .dark:text-stone-300 {
    color: #a0aec0;
  }
  </style>
  