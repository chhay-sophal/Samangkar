<template>
  <div class="grid grid-cols-2 m-auto col-span-2">
    <div class="">
      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-full">
        <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
      </svg>
    </div>
    <div class="flex flex-col justify-center gap-5">
      <div class="flex flex-col justify-end">
        <h1 class="text-center text-5xl mb-5">Log In</h1>
      </div>
      <div class="text-3xl">
        <form @submit.prevent="handleLogin" class="grid grid-cols-3 gap-5">
          <div class="flex flex-col justify-center">
            <label for="username">Username</label>
          </div>
          <div class="col-span-2">
            <input 
              v-model="username"
              type="text" class="border-2 rounded-lg p-3 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
            >
          </div>
          <div class="flex flex-col justify-center">
            <label for="Password">Password</label>
          </div>
          <div class="col-span-2">
            <input 
              v-model="password"
              type="password" class="border-2 rounded-lg p-3 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
            >
          </div>
          <div class="col-span-3 flex justify-center">
            <button 
              type="submit"
              class="border-2 rounded-lg p-3 hover:bg-slate-600 hover:text-gray-100"
            >
              Log In
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { login } from '@/services/authService';

export default {
  data() {
    return {
      username: null,
      password: null,
    };
  },
  methods: {
    async handleLogin() {
      try {
        const token = await login(this.username, this.password);
        console.log('Login successful! Token:', token);
        // Redirect or perform any other action after successful login
        this.$router.push({ name: 'homePageRoute' });
      } catch (error) {
        console.error('Login failed:', error.message);
        // Handle login failure (e.g., show an error message)
      }
    },
  }
};
</script>