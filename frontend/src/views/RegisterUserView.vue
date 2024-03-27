<template>
  <!-- <div class="flex">
    <div class="grid grid-cols-3 m-auto">
      <div class="col-span-3 lg:col-span-1">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-full">
          <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
        </svg>
        <div class="lg:hidden block">
          <div class="col-span-2 flex flex-col justify-center gap-5">
            <div class="flex flex-col justify-end">
              <h1 class="text-center text-4xl mb-5">Register</h1>
            </div>
            <div class="text-2xl">
              <form @submit.prevent="handleRegister" class="flex flex-col gap-5">
              <div class="flex justify-center gap-3">
                <label for="username" class="w-52">Username</label>
                <input 
                  v-model="username"
                  type="text" class="border-2 rounded-lg p-1 px-2 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
                >
              </div>
              <div class="flex justify-center gap-3">
                <label for="email" class="w-52">Email</label>
                <input 
                  v-model="email"
                  type="email" class="border-2 rounded-lg p-1 px-2 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
                >
              </div>
              <div class="flex justify-center gap-3">
                <label for="Password" class="w-52">Password</label>
                <div class="flex flex-col">
                    <input
                        v-model="password"
                        type="password" class="border-2 rounded-lg p-1 px-2 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
                    >
                </div>
              </div>
              <div class="flex justify-center gap-3">
                <label for="Password" class="w-52">Confirm Password</label>
                <div class="flex flex-col">
                    <input
                        v-model="passwordComfirm"
                        type="password" class="border-2 rounded-lg p-1 px-2 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
                    >
                </div>
              </div>
              <div class="col-span-3 flex flex-col items-center justify-center w-full gap-2">
                <button 
                  type="submit"
                  class="border-2 rounded-lg p-2 hover:bg-slate-600 hover:text-gray-100 w-32"
                >
                  Register
                </button>
                <div class="flex text-sm">
                  <p>
                    Already have an account? 
                  </p>
                  <button class="pl-3 hover:text-green-500">
                    <router-link to="/login" title="Register" class="font-bold">
                      <a>Log in</a>
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
            <h1 class="text-center text-4xl mb-5">Register</h1>
          </div>
          <div class="text-2xl">
            <form @submit.prevent="handleRegister" class="flex flex-col gap-5">
              <div class="flex justify-center gap-3">
                <label for="username" class="w-52">Username</label>
                <input 
                  v-model="username"
                  type="text" class="border-2 rounded-lg p-1 px-2 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
                >
              </div>
              <div class="flex justify-center gap-3">
                <label for="email" class="w-52">Email</label>
                <input 
                  v-model="email"
                  type="email" class="border-2 rounded-lg p-1 px-2 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
                >
              </div>
              <div class="flex justify-center gap-3">
                <label for="Password" class="w-52">Password</label>
                <div class="flex flex-col">
                    <input
                        v-model="password"
                        type="password" class="border-2 rounded-lg p-1 px-2 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
                    >
                </div>
              </div>
              <div class="flex justify-center gap-3">
                <label for="Password" class="w-52">Confirm Password</label>
                <div class="flex flex-col">
                    <input
                        v-model="passwordComfirm"
                        type="password" class="border-2 rounded-lg p-1 px-2 dark:bg-custom-dark dark:border-gray-700 dark:text-white"
                    >
                </div>
              </div>
              <div class="col-span-3 flex flex-col items-center justify-center w-full gap-2">
                <button 
                  type="submit"
                  class="border-2 rounded-lg p-2 hover:bg-slate-600 hover:text-gray-100 w-32"
                >
                  Register
                </button>
                <div class="flex text-sm">
                  <p>
                    Already have an account? 
                  </p>
                  <button class="pl-3 hover:text-green-500">
                    <router-link to="/login" title="Register" class="font-bold">
                      <a>Log in</a>
                    </router-link>
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div> -->
  <div class="isolate bg-white px-6 py-24 sm:py-32 lg:px-8">
    <div class="absolute inset-x-0 top-[-10rem] -z-10 transform-gpu overflow-hidden blur-3xl sm:top-[-20rem]" aria-hidden="true">
      <div class="relative left-1/2 -z-10 aspect-[1155/678] w-[36.125rem] max-w-none -translate-x-1/2 rotate-[30deg] bg-gradient-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%-40rem)] sm:w-[72.1875rem]" style="clip-path: polygon(74.1% 44.1%, 100% 61.6%, 97.5% 26.9%, 85.5% 0.1%, 80.7% 2%, 72.5% 32.5%, 60.2% 62.4%, 52.4% 68.1%, 47.5% 58.3%, 45.2% 34.5%, 27.5% 76.7%, 0.1% 64.9%, 17.9% 100%, 27.6% 76.8%, 76.1% 97.7%, 74.1% 44.1%)"></div>
    </div>
    <div class="mx-auto max-w-2xl text-center">
      <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">Sign In to Continue</h2>
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
      <div class="mt-10" v-if="btnSignIn">
        <button type="submit" class="block w-full rounded-md bg-gray-950 px-3.5 py-2.5 text-center text-sm font-semibold shadow-sm hover:bg-gray-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 text-orange-200">Submit</button>
      </div>
      <div class="mt-10" v-if="btnNext">
        <button class="block w-full rounded-md bg-gray-950 px-3.5 py-2.5 text-center text-sm font-semibold shadow-sm hover:bg-gray-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 text-orange-200">
          <router-link to="/login" title="Register" class="font-bold">
            <a>Next</a>
          </router-link>
        </button>
      </div>
      <div class="flex text-sm">
        <p>
          Already have an account? 
        </p>
        <button class="pl-3 hover:text-green-500">
          <router-link to="/login" title="Register" class="font-bold">
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
            setTimeout(() => {
              this.alertOpen = false
            }, 2000);
            this.btnSignIn = false
            this.btnNext = true
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