<template>
    <div class="lg:flex block">
        <!-- User detail on the left -->
        <div class="2xl:w-1/5 xl:w-1/4 w-full">
            <div class="p-5 pb-0 flex justify-center">
                <div class="dark:bg-slate-600 bg-gray-100 overflow-hidden p-5 flex items-center rounded-full">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-full h-full">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
                    </svg>
                    <!-- <img class="w-full" src="../components/pictures/default-profile.png" alt=""> -->
                </div>
            </div>
            <div class="flex justify-center text-4xl p-3">
                {{ user.username }}
            </div>
            <div class="flex flex-col items-center gap-2 text-2xl">
                <div class="flex gap-3">
                    <div class="">{{ user.email }}</div>
                </div>
                <div class="flex flex-col gap-2">
                    <div class="text-green-400 hover:text-stone-100 border border-green-400 hover:bg-green-400 p-2 rounded-xl text-center">
                        <button>Change Info</button>
                    </div>
                    <div class="text-green-400 hover:text-stone-100 border border-green-400 hover:bg-green-400 p-2 rounded-xl text-center">
                        <button>Change Password</button>
                    </div>
                </div>
            </div>
            <!-- <button @click="fetchFavorites()">fetch user</button> -->
        </div>

        <!-- User overview on the right -->
        <div class="lg:w-4/5 w-full flex flex-col"  style="height: 80vh;">
            <div class="h-1/2">
                <div class="font-medium h-1/6 p-5 flex items-center">
                    <p class="text-4xl flex-grow">
                        Favorite Shops
                    </p>
                    <button class="text-2xl">
                        <router-link to="/profile/favorite" title="See all" class="font-bold">
                            See all
                        </router-link>
                    </button>
                </div>
                <div class="h-5/6 overflow-x-auto flex items-center relative pb-3">
                    <!-- Shop Cards Container -->
                    <div class="px-4 h-full">
                        <div class="flex space-x-4 h-full text-2xl">
                            <!-- Loop through your shop cards -->
                            <div v-for="shop in userFavorites" :key="shop.name" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ shop.name }}
                            </div>
                            <div v-for="shop in userFavorites" :key="shop.name" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ shop.name }}
                            </div>
                            <div v-for="shop in userFavorites" :key="shop.name" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ shop.name }}
                            </div>
                            <div v-for="shop in userFavorites" :key="shop.name" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ shop.name }}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="h-1/2">
                <div class="font-medium h-1/6 p-5 flex items-center">
                    <p class="text-4xl flex-grow">
                        Cards
                    </p>
                    <button class="text-2xl">
                        <router-link to="/profile/cards" title="See all" class="font-bold">
                            See all
                        </router-link>
                    </button>
                </div>
                <div class="h-5/6 overflow-x-auto flex items-center relative pb-3">
                        <!-- Favorite Cards Container -->
                        <div class="px-4 h-full">
                        <div class="flex space-x-4 h-full text-2xl">
                            <!-- Loop through your shop cards -->
                            <div v-for="card in userCards" :key="card.serviceName" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ card.serviceName }}
                            </div>
                            <div v-for="card in userCards" :key="card.serviceName" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ card.serviceName }}
                            </div>
                            <div v-for="card in userCards" :key="card.serviceName" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ card.serviceName }}
                            </div>
                            <div v-for="card in userCards" :key="card.serviceName" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ card.serviceName }}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
const username = user.username

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

const fetchUserCards = async () => {
    try {
        const response = await http.get(`api/users/${username}/cards`);
        userCards.value = response.data;
        console.log(userCards.value)
    } catch (error) {
        console.error(error);
    }
}

// Fetch data when the component is mounted
onMounted(() => {
    fetchUserFavorites();
    fetchUserCards();
});

</script>

<script>
export default {
    data() {
        return {
            userFavorites: null,
            userCards: null,
        }
    },
    methods: {
        // fetchFavorites() {
        //     const username = this.user.username;
        //     console.log(username)


        //     http.get(`favorites/search/findByUser_Username?username=${username}`)
        //         .then(response => {
        //             console.log(response.data); // Assuming the data you want is in the response
        //         })
        //         .catch(error => {
        //             console.error(error);
        //         });
        // },
    },
    mounted() {

    },
    computed() {

    },
}
</script>