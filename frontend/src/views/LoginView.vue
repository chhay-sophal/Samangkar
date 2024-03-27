<template>
  <div class="flex">
    <div class="grid grid-cols-3 m-auto">
      <div class="col-span-3 lg:col-span-1">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-full">
          <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
        </svg>
        <div class="lg:hidden block">
          <div class="col-span-2 flex flex-col justify-center gap-5">
            <div class="flex flex-col justify-end">
              <h1 class="text-center text-4xl mb-5">Log In</h1>
            </div>
            <div class="text-2xl">
              <form @submit.prevent="handleLogin" class="flex flex-col gap-5">
                <div class="flex justify-center gap-3">
                  <label for="username" class=" w-28">Username</label>
                  <input 
                    v-model="username"
                    type="text" class="border-2 rounded-lg p-1 px-2 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
                  >
                </div>
                <div class="flex justify-center gap-3">
                  <label for="Password" class=" w-28">Password</label>
                  <div class="flex flex-col">
                      <input
                          v-model="password"
                          type="password" class="border-2 rounded-lg p-1 px-2 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
                      >
                      <div class="flex justify-end text-sm">
                        <RouterLink to="/forgot-password">
                          <a>Forgot password?</a>
                        </RouterLink>
                      </div>
                  </div>
                </div>
                <div class="col-span-3 flex flex-col items-center justify-center w-full gap-2">
                  <button 
                    type="submit"
                    class="border-2 rounded-lg p-2 hover:bg-slate-600 hover:text-gray-100 w-32"
                  >
                    Log In
                  </button>
                  <div class="flex text-sm">
                    Don't have an account?
                    <button class="pl-3 hover:text-green-500">
                      <router-link to="/register" title="Register" class="font-bold">
                        <a>Create new account</a>
                      </router-link>
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <div class="lg:block hidden col-span-2">
        <div class="flex flex-col justify-center gap-5">
          <div class="flex flex-col justify-end">
            <h1 class="text-center text-4xl mb-5">Log In</h1>
          </div>
          <div class="text-2xl">
            <form @submit.prevent="handleLogin" class="flex flex-col gap-5">
              <div class="flex justify-center gap-3">
                <label for="username" class=" w-28">Username</label>
                <input 
                  v-model="username"
                  type="text" class="border-2 rounded-lg p-1 px-2 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
                >
              </div>
              <div class="flex justify-center gap-3">
                <label for="Password" class=" w-28">Password</label>
                <div class="flex flex-col">
                    <input
                        v-model="password"
                        type="password" class="border-2 rounded-lg p-1 px-2 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
                    >
                    <div class="flex justify-end text-sm">
                      <RouterLink to="/forgot-password">
                          <a>Forgot password?</a>
                        </RouterLink>
                    </div>
                </div>
              </div>
              <div class="col-span-3 flex flex-col items-center justify-center w-full gap-2">
                <button 
                  type="submit"
                  class="border-2 rounded-lg p-2 hover:bg-slate-600 hover:text-gray-100 w-32"
                >
                  Log In
                </button>
                <div class="flex text-sm">
                  Don't have an account? 
                  <button class="pl-3 hover:text-green-500">
                    <router-link to="/register" title="Register" class="font-bold">
                      <a>Create new account</a>
                    </router-link>
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div 
    v-if="showAlert"
    class="bg-red-500 text-stone-100 text-xl font-medium flex justify-center fixed top-14 left-1/2 p-3 rounded-lg"
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