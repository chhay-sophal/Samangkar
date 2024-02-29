<template>
    <div class="flex items-center">
        <div class="2xl:w-1/4 lg:w-1/6 w-1/12">
        </div>
        <div class="2xl:w-2/4 lg:w-4/6 sm:w-10/12 w-full flex flex-col items-center">
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
                        <div v-for="shop in userFavorites" :key="shop.name" class="flex-none w-auto bg-green-400 flex justify-center items-center rounded-lg h-96">
                            {{ shop.name }}
                        </div>
                        <div v-for="shop in userFavorites" :key="shop.name" class="flex-none w-auto bg-green-400 flex justify-center items-center rounded-lg h-96">
                            {{ shop.name }}
                        </div>
                        <div v-for="shop in userFavorites" :key="shop.name" class="flex-none w-auto bg-green-400 flex justify-center items-center rounded-lg h-96">
                            {{ shop.name }}
                        </div>
                        <div v-for="shop in userFavorites" :key="shop.name" class="flex-none w-auto bg-green-400 flex justify-center items-center rounded-lg h-96">
                            {{ shop.name }}
                        </div>
                    </div>
            </div>
        </div>
        <div class="2xl:w-1/4 lg:w-1/6 w-1/12">
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/store/userStore'
import { ref } from 'vue'
import http from '@/services/httpService'
import { onMounted } from 'vue'

const userStore = useUserStore()
const user = userStore.getUser
const username = user.username;

const userFavorites = ref([]);
const userCards = ref([]);

const fetchUserFavorites = async () => {
    try {
        const response = await http.get(`api/users/${username}/favorite-shops`);
        userFavorites.value = response.data;
        console.log(userFavorites.value)
    } catch (error) {
        console.error(error);
    }
}

// Fetch data when the component is mounted
onMounted(() => {
    fetchUserFavorites();
});

</script>