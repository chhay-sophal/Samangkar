<template>
    <div class="w-full grid grid-cols-4 items-center justify-center font-serif text-5xl pb-10">
        <div class="pl-5">
          <button @click="goBack" class="text-lg flex items-start border px-4 py-2 hover:bg-slate-400 hover:text-stone-50">Go back</button>
        </div>
        <div class="col-span-2 grow flex items-center justify-center">
          Explore All Services
        </div>
        <div class=""></div>
    </div>

    <section class="shop-list flex flex-col" id="shopList">
      <div class="shop-list">
        <div class="shop-item relative" v-for="(service, index) in services" :key="index">
          <button class="absolute right-2 top-2">
            <svg 
              @click="removeFromCard(service.id)"
              v-if="serviceCarts?.some(card => card.service?.id === service?.id)"
              xmlns="http://www.w3.org/2000/svg" 
              fill="red" 
              viewBox="0 0 24 24" 
              stroke-width="1.5" 
              stroke="currentColor" 
              class="w-6 h-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="M15 12H9m12 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
            </svg>
            <svg 
              @click="addToCard(service.id)"
              v-else
              xmlns="http://www.w3.org/2000/svg" 
              fill="none" 
              viewBox="0 0 24 24" 
              stroke-width="1.5" 
              stroke="currentColor" 
              class="w-6 h-6"
            >
              <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v6m3-3H9m12 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
            </svg>
          </button>
          <router-link :to="`/shop/${service.shopId}/service/${service.id}/details`">
            <div class="">
              <ImageViewer :imageData="service.image" class="flex aspect-square object-fill"/>
            </div>
            <div class="shop-details">
              <div class="items">{{ service.name }}</div>
              <p>{{ service.description }}</p>
            </div>
          </router-link>
        </div>
      </div>
    </section>
</template>

<script>
import http from '@/services/httpService';
import ImageViewer from '@/components/ImageViewer.vue';
import { useUserStore } from "@/store/userStore";

export default {
    components: {
        ImageViewer,
    },
    data() {
        return {
            userId: null,
            services: [],
            serviceCarts: [],
        };
    },
    methods: {
        goBack() {
            this.$router.go(-1); // Go back to the previous route
        },
        async fetchServices() {
            try {
              const response = await http.get(`api/services/get-all`);
              this.services = response.data.filter(s => !s.deletedAt);
              console.log(this.services)
            } catch (error) {
              console.error("Error:", error);
            }
        },
        async updateFavorite(shopId, { isFavorite, favoriteId }) {
            try {
                if (isFavorite) {
                console.log(`Remove from favorites with ID: ${favoriteId}`);
                const response = await http.post(`api/favorites/remove/${favoriteId}`);
                console.log(response.data);
                } else {
                console.log(`Add to favorites, shop ID: ${shopId}`);
                const response = await http.post(`api/favorites/add/${this.userId}/${shopId}`);
                console.log(response.data);
                }
                this.fetchFavorites();
            } catch (error) {
                console.log(error);
            }
        },
        findFavoriteId(shopId) {
            const favorite = this.favorites.find(favoriteShop => favoriteShop.shop.id === shopId);
            return favorite ? { isFavorite: true, favoriteId: favorite.id } : { isFavorite: false, favoriteId: null };
        },
        async fetchUserCarts() {
            try {
              const response = await http.get(`api/cards/get-all/${this.userId}`);
              this.serviceCarts = response.data.filter(card => card.service != null);
              console.log('user carts:');
              console.log(this.serviceCarts);
            } catch (error) {
              console.log(error)
            }
        },
        async addToCard(id) {
            try {
                const response = await http.post(`api/cards/add`, {
                    "userId": this.userId,
                    "serviceId": id,
                    "packageId": null,
                    "quantity": 1,
                });
                
                console.log(response.data);

                this.fetchUserCarts();
            } catch (error) {
                console.log(error);
            }
        },
        async removeFromCard(id) {
            try {
                // Find the first card that has the specified package ID
                const cardToRemove = this.serviceCarts.find(card => card.service.id === id);

                // If a card is found, make an HTTP request to remove it by its ID
                if (cardToRemove) {
                  const response = await http.post(`api/cards/remove/${cardToRemove.id}`);
                  console.log(response.data); // Handle the response as needed
                  this.fetchUserCarts();
                } else {
                  console.log("Card not found"); // Log a message if no matching card is found
                }
            } catch (error) {
                console.log(error);
            }
        },
    },
    mounted() {
        const userStore = useUserStore()
        this.userId = userStore.user.id;

        this.fetchServices();
        this.fetchUserCarts();
    }
}
</script>