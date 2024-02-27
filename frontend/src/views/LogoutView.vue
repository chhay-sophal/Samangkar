<template>
    <div class="col-span-2 bg-slate-400 dark:bg-slate-800 text-gray-100 w-96 h-96 rounded-lg p-10 grid grid-rows-5 items-center text-xl">
        <div class="flex items-center justify-center row-span-3 w-full">
            <div class="w-32">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" class=" fill-gray-100">
                    <path d="M320 32c0-9.9-4.5-19.2-12.3-25.2S289.8-1.4 280.2 1l-179.9 45C79 51.3 64 70.5 64 92.5V448H32c-17.7 0-32 14.3-32 32s14.3 32 32 32H96 288h32V480 32zM256 256c0 17.7-10.7 32-24 32s-24-14.3-24-32s10.7-32 24-32s24 14.3 24 32zm96-128h96V480c0 17.7 14.3 32 32 32h64c17.7 0 32-14.3 32-32s-14.3-32-32-32H512V128c0-35.3-28.7-64-64-64H352v64z"/>
                </svg>
            </div>
        </div>
        <div class="">
            Are you sure that you want to log out?
        </div>
        <div class="flex justify-center gap-5">
            <button 
                @click="goBack"
                class="border rounded-xl p-5 py-2 border-gray-100 hover:text-slate-800 hover:bg-slate-100"
            >
                Go back
            </button>
            <button 
                @click="handleLogOut"
                class="border rounded-xl p-5 py-2 border-red-700 bg-red-500 hover:bg-red-700 text-gray-100"
            >
                Logout
            </button>
        </div>
    </div>
</template>

<script>
import { logout } from '@/services/authService';

export default {
    methods: {
        async handleLogOut() {
            try {
                logout();
                // Redirect or perform any other action after successful logout
                this.$router.push({ name: 'homePageRoute' });
            } catch (error) {
                console.error('Logout failed:', error.message);
            }
        },
        goBack() {
            // Navigate back to the previous route
            this.$router.go(-1);
        },
    },
};
</script>