<template>
    <div class="flex">
        <!-- User detail on the left -->
        <div class="bg-blue-300 2xl:w-1/5 xl:w-1/4 w-1/3" style="height: 80vh;">
            <div class="p-5 pb-0 flex justify-center">
                <div class="bg-slate-600 overflow-hidden p-5 flex items-center rounded-full">
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
            </div>
            <button @click="fetchFavorites()">fetch user</button>
        </div>

        <!-- User overview on the right -->
        <div class="w-4/5 flex flex-col">
            <div class="bg-green-300 h-1/2 p-5">
                <p class="text-4xl font-medium">
                    Favorite Shops
                </p>
            </div>
            <div class="bg-yellow-300 h-1/2 p-5">
                <p class="text-4xl font-medium">
                    Cards
                </p>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/store/userStore'
import { ref } from 'vue'
import http from '@/services/httpService'

const userStore = useUserStore()
const user = userStore.getUser

const userFavorites = ref(null)
const userCards = ref(null)

const fetchFavorites = async () => {
  const username = user.username;

  try {
    const response = await http.get(`api/users/${username}/favorite-shops`);
    userFavorites.value = response.data;
    console.log(userFavorites.value)
  } catch (error) {
    console.error(error);
  }


  try {
    const response = await http.get(`api/users/${username}/cards`);
    userCards.value = response.data;
    console.log(userCards.value)
  } catch (error) {
    console.error(error);
  }};

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