<template>
  <div class="isolate bg-white px-6 py-24 sm:py-32 lg:px-8">
    <div class="absolute inset-x-0 top-[-10rem] -z-10 transform-gpu overflow-hidden blur-3xl sm:top-[-20rem]" aria-hidden="true">
      <div class="relative left-1/2 -z-10 aspect-[1155/678] w-[36.125rem] max-w-none -translate-x-1/2 rotate-[30deg] bg-gradient-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%-40rem)] sm:w-[72.1875rem]" style="clip-path: polygon(74.1% 44.1%, 100% 61.6%, 97.5% 26.9%, 85.5% 0.1%, 80.7% 2%, 72.5% 32.5%, 60.2% 62.4%, 52.4% 68.1%, 47.5% 58.3%, 45.2% 34.5%, 27.5% 76.7%, 0.1% 64.9%, 17.9% 100%, 27.6% 76.8%, 76.1% 97.7%, 74.1% 44.1%)"></div>
    </div>
    <div class="mx-auto max-w-2xl text-center">
      <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">Register</h2>
    </div>
    <form @submit.prevent="handleRegister" class="mx-auto mt-16 max-w-xl sm:mt-20">
      <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
        <div>
          <label for="username" class="block text-sm font-semibold leading-6 text-black">Username</label>
          <div class="mt-2.5">
            <input v-model="username" type="text" name="username"  class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm shadow-gray-500 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-gray-400 sm:text-sm sm:leading-6">
          </div>
        </div>
        <div>
          <label for="email" class="block text-sm font-semibold leading-6 text-black">Email</label>
          <div class="mt-2.5">
            <input v-model="email" type="email" name="email" class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset shadow-gray-500 ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-gray-400 sm:text-sm sm:leading-6">
          </div>
        </div>
        <div class="sm:col-span-2">
          <label for="password" class="block text-sm font-semibold leading-6 text-black">Password</label>
          <div class="mt-2.5">
            <input v-model="password" name="password" class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset shadow-gray-500 ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-gray-400 sm:text-sm sm:leading-6">
          </div>
        </div>
        <div class="sm:col-span-2">
          <label for="confirm password" class="block text-sm font-semibold leading-6 text-black">Confirm Password</label>
          <div class="mt-2.5">
            <input v-model="passwordComfirm" name="password" class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset shadow-gray-500 ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-gray-400 sm:text-sm sm:leading-6">
          </div>
        </div>
        <div class="sm:col-span-2">
          <di v-if="openWarnningAlert" class="text-sm text-red-800">
            <p>{{warnningMsg }}</p>
          </di>
        </div>
        <div v-if="openSuccessAlert" class="sm:col-span-2">
          <div class="text-white text-xs px-6 py-4 border-0 rounded relative mb-4 bg-emerald-400 fade-in-out">
            <span class="inline-block align-middle mr-8">
              Account created <b class="capitalize">successfully!</b>
            </span>
            <button class="absolute bg-transparent text-2xl font-semibold leading-none right-0 top-0 mt-4 mr-6 outline-none focus:outline-none" v-on:click="closeAlert()">
              <span>×</span>
            </button>
          </div>
        </div>
      </div>
      <!-- <div class="mt-10" v-if="btnSignIn"> -->
      <div class="mt-10">
        <button type="submit" class="block w-full rounded-md bg-gray-950 px-3.5 py-2.5 text-center text-sm font-semibold shadow-sm hover:bg-gray-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 text-orange-200">Submit</button>
      </div>
      <!-- <div class="mt-10" v-if="btnNext">
        <button class="block w-full rounded-md bg-gray-950 px-3.5 py-2.5 text-center text-sm font-semibold shadow-sm hover:bg-gray-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 text-orange-200">
          <router-link to="/login" title="Register" class="font-bold">
            <a>Next</a>
          </router-link>
        </button>
      </div> -->
      <div class="flex text-sm">
        <p>
          Already have an account? 
        </p>
        <button class="pl-3 hover:text-green-500">
          <router-link to="/login" title="Login" class="font-bold">
            <a>Log in</a>
          </router-link>
        </button>
      </div>
    </form>
  </div>
  <div 
    v-if="showAlert"
    class="bg-red-500 text-stone-100 text-xl font-medium flex justify-center fixed top-14 left-1/2 p-3 rounded-lg"
  >
    Register failed!
  </div>
</template>

<script>
import { login } from '@/services/authService'
import { useUserStore } from '@/store/userStore';
import http from '@/services/httpService'

export default {
  data() {
    return {
      role: 'USER',
      username: null,
      email: null,
      password: null,
      passwordComfirm: null,
      btnSignIn: true,
      btnNext: false,
      showAlert: false,
      openSuccessAlert: false,
      openWarnningAlert: false,
      warnningMsg: '',
    };
  },
  methods: {
    async handleRegister() {
      try {
        if (this.password.length > 8) {
          if (this.password === this.passwordComfirm) {
            await http.post(`api/auth/register`, {
              'role': this.role,
              'username': this.username,
              'email': this.email,
              'password': this.password
            })
            this.openSuccessAlert = true
            this.username = '',
            this.email = '',
            this.password = '',
            this.passwordComfirm = '',
            this.warnningMsg = '',
            this.btnSignIn = false,
            this.btnNext = true

            setTimeout(() => {
              this.alertOpen = false
            }, 1000);
            this.$router.push({ name: 'loginPageRoute' });
          } else {
            this.openWarnningAlert = true
            this.warnningMsg = 'password does not match'
          }
        } else {
          this.openWarnningAlert = true
          this.warnningMsg = 'Password need to be at least 8 characters!'
        }
      } catch (error) {
        this.showAlert = true
        this.username = null
        this.email = null
        this.password = null
        this.passwordComfirm = null
        console.error('Register failed:', error.message)

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