<template>
  <div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-sm">
      <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight to-blue-500">Sign in to continue</h2>
    </div>
    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
      <form class="space-y-6" @submit.prevent="handleLogin">
        <div>
          <label for="username" class="block text-sm font-medium leading-6 text-balance">Username</label>
          <div class="mt-2">
            <input v-model="username" type="text" class="px-4 block w-full rounded-md border-0 bg-white/5 py-1.5 text-gray-600 shadow-sm ring-1 ring-inset ring-black-500 focus:ring-2 focus:ring-inset focus:ring-balck sm:text-sm sm:leading-6">
          </div>
        </div>
        <div>
          <div class="flex items-center justify-between">
            <label for="password" class="block text-sm font-medium leading-6 text-black">Password</label>
            <router-link to="/forgot-password" class="text-sm">
              <div class="font-bold text-orange-300 hover:text-orange-500">Forgot password?</div>
            </router-link>
          </div>
          <div class="mt-2">
            <input v-model="password" type="password" class="px-4 block w-full rounded-md border-0 bg-white/5 py-1.5 text-black shadow-sm ring-1 ring-inset ring-black-500 focus:ring-2 focus:ring-inset focus:ring-black sm:text-sm sm:leading-6">
          </div>
        </div>
        <div>
          <button type="submit" class="flex w-full justify-center rounded-md bg-gray-950 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-gray-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-red-500">Sign in</button>
        </div>
      </form>
      <p class="mt-10 text-center text-sm text-gray-400">
        Not a member?
        <router-link to="/register" title="Register" class="font-bold">
          <a href="#" class="font-bold text-orange-300 hover:text-orange-500">Sign Up</a>
        </router-link>
      </p>
    </div>
  </div>
  <div 
    v-if="showAlert"
    class="bg-gray-500 text-stone-100 text-xl font-medium flex justify-center fixed top-14 left-1/2 p-3 rounded-lg"
  >
    Login failed!
  </div>
</template>

<script>
import { login } from '@/services/authService'
import { useUserStore } from '@/store/userStore';
import { RouterLink } from 'vue-router';

export default {
  data() {
    return {
      username: null,
      password: null,
      showAlert: false,
    };
  },
  methods: {
    async handleLogin() {
      try {
        await login(this.username, this.password)
        this.$router.push({ name: 'profilePageRoute' })
      } catch (error) {
        this.showAlert = true
        this.username = null
        this.password = null
        console.error('Login failed:', error.message)
        setTimeout(() => {
          this.showAlert = false;
        }, 2000);
      }
    },
  },
  mounted() {
    const userStore = useUserStore()
    if (userStore.user.username) {
      this.$router.push({ name: 'homePageRoute' })
    }
  }
};
</script>