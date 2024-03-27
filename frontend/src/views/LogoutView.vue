<template>
  <div class="flex items-center justify-center">
    <div class="rounded-lg bg-gray-200 px-16 py-14">
      <div class="flex justify-center">
        <div class="rounded-full bg-orange-200 p-6">
          <div class="flex h-16 w-16 items-center justify-center rounded-full bg-green-500 p-4">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="h-8 w-8 text-white">
              <path stroke-linecap="round" stroke-linejoin="round" d="M4.5 12.75l6 6 9-13.5" />
            </svg>
          </div>
        </div>
      </div>
      <h3 class="my-4 text-center text-3xl font-semibold text-gray-700">Are you sure you want to log out ?</h3>
      <div class="px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
        <button @click="handleLogOut" type="button" class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-500 text-base font-medium text-white hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 sm:ml-3 sm:w-auto sm:text-sm"> Yes </button>
        <button  @click="goBack" type="button" class="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-200 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"> Cancel </button>
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