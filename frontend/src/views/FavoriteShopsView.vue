<template>
    <div class="w-full flex flex-col items-center">
        <div class="w-full flex justify-center items-center">
            <div class="w-1/4">
                <button class="w-auto rounded-lg border border-green-400 hover:bg-green-400 text-green-400 hover:text-stone-100 flex justify-center">
                    <router-link to="/profile" title="See all" class="font-bold">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-10">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 19.5 8.25 12l7.5-7.5" />
                        </svg>
                    </router-link>
                </button>
            </div>
            <div class="w-2/4 flex justify-center">
                <h1 class="text-5xl">
                    Favorites
                </h1>
            </div>
            <div class="w-1/4"></div>
        </div>
        <div class="h-5/6 overflow-x-auto relative pb-3 mt-10 w-full">
            <!-- Shop Cards Container -->
            <div class="grid lg:grid-cols-3 sm:grid-cols-2 gap-4 h-full text-2xl">
                <!-- Loop through your shop cards -->
                <button v-for="c in favorites" :key="c.name" :shop="c" class="flex-none w-auto bg-stone-200 flex justify-center items-center rounded-lg h-96 relative overflow-hidden">
                    <button 
                        @click="removeFavorite(c.id)"
                        class="absolute top-2 right-2 flex items-center gap-1 border py-1 px-2 rounded-lg hover:bg-red-500 text-red-400 hover:text-stone-100 border-red-400"
                    >
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="3" stroke="currentColor" class="w-6 h-6">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M5 12h14" />
                        </svg>
                        <p class="text-xl font-semibold">Remove</p>
                    </button>
                    <router-link class="flex flex-col h-full" :to="`/shop/${c.shop.id}/details`">
                        <div class="h-3/4">
                            <div class="h-full w-full flex items-center justify-center">
                                <div class="h-full w-full">
                                    <ImageViewer :imageData="c.shop.imageUrl" />
                                </div>
                            </div>
                        </div>
                        <div class="flex items-center justify-center h-1/4">
                            {{ c.shop.name }}
                        </div>
                    </router-link>
                </button>
            </div>
        </div>
    </div>
    <div class="2xl:w-1/4 lg:w-1/6 w-1/12">
    </div>
</template>

<script>
import { useUserStore } from '@/store/userStore';
import http from '@/services/httpService';
import ImageViewer from '@/components/ImageViewer.vue';

export default {
    components: {
        ImageViewer,
    },
    data() {
        return {
            userId: null,
            favorites: [],
        }
    },
    methods: {
        async fetchFavorites() {
            try {
                const response = await http.get(`api/favorites/get-all/${this.userId}`)
                this.favorites = response.data;
                localStorage.setItem('favorites', JSON.stringify(response.data));
            } catch (error) {
                console.error(error)
            }
        },
        async removeFavorite(favId) {
            const response = await http.post(`api/favorites/remove/${favId}`);
            console.log(response.data);
            this.fetchFavorites();

        },
    },
    mounted() {
        const userStore = useUserStore();
        this.userId = userStore.getUser.id;
        this.favorites = JSON.parse(localStorage.getItem('favorites'));
        console.log(this.favorites);
    },
}
</script>