<template>
  <div class="flex">
    <div class="grid grid-cols-3 m-auto">
      <div class="col-span-3 lg:col-span-1">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-full">
            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 9V5.25A2.25 2.25 0 0 0 13.5 3h-6a2.25 2.25 0 0 0-2.25 2.25v13.5A2.25 2.25 0 0 0 7.5 21h6a2.25 2.25 0 0 0 2.25-2.25V15m3 0 3-3m0 0-3-3m3 3H9" />
        </svg>
        <div class="lg:hidden block">
          <div class="col-span-2 flex flex-col justify-center gap-5">
            <div class="flex flex-col justify-end">
              <h1 class="text-center text-4xl mb-5">Log In</h1>
            </div>
            <div class="text-2xl text-center">
              Are you sure that you want to log out?
            </div>
            <div class="flex justify-center gap-5 text-2xl">
              <button 
                  @click="goBack"
                  class="border rounded-xl p-5 py-2 border-gray-100 hover:text-slate-800 hover:bg-slate-100"
              >
                  Go back
              </button>
              <button 
                  @click="handleLogOut"
                  class="border rounded-xl p-5 py-2 border-red-500 hover:text-slate-100 hover:bg-red-500"
              >
                  Logout
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="lg:flex hidden col-span-2 justify-center">
        <div class="flex flex-col justify-center gap-8">
          <div class="flex flex-col justify-end">
            <h1 class="text-center text-4xl">Log Out</h1>
          </div>
          <div class="text-2xl text-center">
              Are you sure that you want to log out?
          </div>
          <div class="flex justify-center gap-5 text-2xl">
            <button 
                @click="goBack"
                class="border rounded-xl p-5 py-2 border-gray-100 hover:text-slate-800 hover:bg-slate-100"
            >
                Go back
            </button>
            <button 
                @click="handleLogOut"
                class="border rounded-xl p-5 py-2 border-red-500 hover:text-slate-100 hover:bg-red-500"
            >
                Logout
            </button>
          </div>
        </div>
      </div>
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