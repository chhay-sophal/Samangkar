<template>
    <div class="w-full grid grid-cols-4 items-center justify-center font-serif text-5xl pb-10">
        <div class="pl-5">
          <button @click="goBack" class="text-lg flex items-start border px-4 py-2 hover:bg-slate-400 hover:text-stone-50">Go back</button>
        </div>
        <div class="col-span-2 grow flex items-center justify-center">
          Explore All Shops
        </div>
        <div class=""></div>
    </div>

    <section class="shop-list flex flex-col" id="shopList">
      <div class="shop-list">
        <div class="shop-item relative" v-for="(shop, index) in shops" :key="index">
          <button class="absolute right-2 top-2">
            <svg 
              xmlns="http://www.w3.org/2000/svg" 
              :fill="favorites?.some(favorite => favorite.shop.id === shop.id) ? 'red' : 'white'" 
              viewBox="0 0 24 24" 
              stroke-width="1.5" 
              stroke="currentColor" 
              class="w-6 h-6"
              @click="updateFavorite(shop.id, findFavoriteId(shop.id))"
            >
              <path stroke-linecap="round" stroke-linejoin="round" d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z" />
            </svg>
          </button>
          <router-link :to="`/shop/${shop.id}/details`">
            <div class="">
              <ImageViewer :imageData="shop.imageUrl" class="flex aspect-square object-fill"/>
            </div>
            <div class="shop-details">
              <div class="items">{{ shop.name }}</div>
              <p>{{ shop.description }}</p>
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
            shops: [],
            favorites: [],

        };
    },
    methods: {
        goBack() {
            this.$router.go(-1); // Go back to the previous route
        },
        async fetchShops() {
            try {
                const response = await http.get(`api/shops/get-all`);
                this.shops = response.data.filter(s => !s.deletedAt);
                console.log(this.shops);
            } catch (error) {
                console.error("Error:", error);
            }
        },
        async fetchFavorites() {
            try {
                const response = await http.get(`api/favorites/get-all/${this.userId}`);
                this.favorites = response.data;
                console.log(this.favorites);
                console.log('hsldf')
            } catch (error) {
                console.log(error)
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
    },
    mounted() {
        const userStore = useUserStore()
        this.userId = userStore.user.id;

        this.fetchShops();
        this.fetchFavorites();
    }
}
</script>