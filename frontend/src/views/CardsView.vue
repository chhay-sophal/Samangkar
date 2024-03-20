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
                    Cards
                </h1>
            </div>
            <div class="w-1/4"></div>
        </div>
        <div class="relative pb-3 mt-10 w-full flex items-center justify-center">
            <!-- Shop Cards Container -->
            <div class="grid lg:grid-cols-4 sm:grid-cols-2 gap-4 h-full text-2xl">
                <!-- Loop through your shop cards -->
                <div v-for="c in cart" :key="c.id" class="flex flex-col w-auto bg-stone-200 justify-center items-center rounded-lg h-96 relative">
                    <button class="absolute right-2 top-2">
                        <svg 
                            @click="removeFromCard(c.id)"
                            xmlns="http://www.w3.org/2000/svg" 
                            fill="red" 
                            viewBox="0 0 24 24" 
                            stroke-width="1.5" 
                            stroke="currentColor" 
                            class="w-6 h-6">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M15 12H9m12 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
                        </svg>
                    </button>
                    <div class="overflow-hidden">
                        <div class="h-full w-full flex items-center justify-center overflow-hidden">
                            <div class="h-full w-full">
                                <ImageViewer v-if="c.service" :imageData="c.service.image" />
                                <ImageViewer v-else :imageData="c.pkg.image" />
                            </div>
                        </div>
                    </div>
                    <div class="">
                        <div v-if="c.service" class="flex flex-col items-center justify-center">
                            <div class="">
                                {{ c.service.name }}
                            </div>
                            <div class="">
                                ${{ c.service.unitPrice }}
                            </div>
                            <div class="">
                                {{ c.service.shopName }}
                            </div>
                        </div>
                        <div v-else class="flex flex-col items-center justify-center">
                            <div class="">
                                {{ c.pkg.name }}
                            </div>
                            <div class="">
                                ${{ c.pkg.price }}
                            </div>
                            <div class="">
                                {{ c.pkg.shopName }}
                            </div>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
    </div>
    <div class="2xl:w-1/4 lg:w-1/6 w-1/12">
    </div>
</template>

<script>
import ImageViewer from '@/components/ImageViewer.vue';
import { useUserStore } from '@/store/userStore';
import http from '@/services/httpService';

export default {
    components: {
        ImageViewer,
    },
    data() {
        return{
            userId: null,
            cart: [],
            packageCarts: [],
            serviceCarts: [],
        }
    },
    methods: {
        async fetchUserCards() {
            try {
            const response = await http.get(`api/cards/get-all/${this.userId}`);
            // this.cards = response.data.filter(response.data.pkg != null);
            this.packageCarts = response.data.filter(card => card.pkg != null);
            this.serviceCarts = response.data.filter(card => card.service != null);
            this.cart = response.data;
            console.log('user carts:');
            console.log(this.packageCarts);
            console.log(this.serviceCarts);
            localStorage.setItem('cart', JSON.stringify(response.data));
            } catch (error) {
            console.log(error)
            }
        },
        async removeFromCard(id) {
            try {
                const response = await http.post(`api/cards/remove/${id}`);
                console.log(response.data); // Handle the response as needed
                this.fetchUserCards();
            } catch (error) {
                console.log(error);
            }
        },
    },
    mounted() {
        const userStore = useUserStore();
        this.userId = userStore.getUser.id;
        this.cart = JSON.parse(localStorage.getItem('cart'));
        console.log(this.cart);
    }
}
</script>