<template>
    <div class="w-full grid grid-cols-4 items-center justify-center font-serif text-5xl pb-10">
        <div class="pl-5">
          <button @click="goBack" class="text-lg flex items-start border px-4 py-2 hover:bg-slate-400 hover:text-stone-50">Go back</button>
        </div>
        <div class="col-span-2 grow flex items-center justify-center">
          Explore All Packages
        </div>
        <div class=""></div>
    </div>

    <section class="shop-list flex flex-col" id="shopList">
      <div class="shop-list">
        <div class="shop-item relative" v-for="(pkg, index) in packages" :key="index">
          <button class="absolute right-2 top-2">
            <svg 
              @click="removeFromCard(pkg.id)"
              v-if="packageCarts?.some(card => card.pkg?.id === pkg?.id)"
              xmlns="http://www.w3.org/2000/svg" 
              fill="red" 
              viewBox="0 0 24 24" 
              stroke-width="1.5" 
              stroke="currentColor" 
              class="w-6 h-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="M15 12H9m12 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
            </svg>
            <svg 
              @click="addToCard(pkg.id)"
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
          <router-link :to="`/shop/${pkg.shopId}/package/${pkg.id}/details`">
            <div class="">
              <ImageViewer :imageData="pkg.image" class="flex aspect-square object-fill"/>
            </div>
            <div class="shop-details">
              <div class="items">{{ pkg.name }}</div>
              <p>{{ pkg.description }}</p>
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
            packages: [],
            packageCarts: [],
        };
    },
    methods: {
        goBack() {
            this.$router.go(-1); // Go back to the previous route
        },
        async fetchPackages() {
            try {
              const response = await http.get(`api/packages/get-all`);
              this.packages = response.data.filter(pkg => !pkg.deletedAt);
              console.log(this.packages)
            } catch (error) {
              console.error("Error:", error);
            }
        },
        async fetchUserCarts() {
            try {
              const response = await http.get(`api/cards/get-all/${this.userId}`);
              this.packageCarts = response.data.filter(card => card.pkg != null);
              console.log('user carts:');
              console.log(this.packageCarts);
            } catch (error) {
              console.log(error)
            }
        },
        async addToCard(id) {
            try {
                const response = await http.post(`api/cards/add`, {
                    "userId": this.userId,
                    "serviceId": null,
                    "packageId": id,
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
                const cardToRemove = this.packageCarts.find(card => card.pkg.id === id);

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

        this.fetchPackages();
        this.fetchUserCarts();
    }
}
</script>