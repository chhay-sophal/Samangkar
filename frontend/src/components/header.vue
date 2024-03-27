<template>
  <header
    class="fixed left-0 top-0 z-40 border-b w-full bg-gray-50 dark:bg-neutral-900"
  >
    <nav
      class="mx-auto flex items-center justify-between px-8 3xl:w-5/6"
      aria-label="Global"
    >
      <div class="flex lg:flex-1 items-center gap-10 ">
        <h1 class="text-left text-4xl text-primary hover:text-secondary">
          <router-link to="/" title="Homepage" class="font-bold">
            <img
              class="logo h-20"
              src="https://i.fbcd.co/products/original/872706a08d201dc5e37c525ac9e2281a621b64a002d302cac603a9ba1b238fe7.jpg"
              alt="Logo"
            />
          </router-link>
        </h1>
      </div>
      <div class="justify-center flex-grow text-xl">
        <nav class="nav-links flex gap-5">
          <router-link to="/" title="Home">Home</router-link>
          <router-link to="/about" title="About">About</router-link>
          <router-link to="/services" title="Services">Services</router-link>
          <router-link to="/contact" title="Contact">Contact</router-link>
        </nav>
      </div>
      <div class="justify-end flex py-5">
        <router-link
          v-if="!user || Object.keys(user).length === 0"
          to="/login"
          title="Login"
        >
          <div class="icon-container">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke-width="1.5"
              stroke="currentColor"
              class="w-10 h-10"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="M8.25 9V5.25A2.25 2.25 0 0 1 10.5 3h6a2.25 2.25 0 0 1 2.25 2.25v13.5A2.25 2.25 0 0 1 16.5 21h-6a2.25 2.25 0 0 1-2.25-2.25V15M12 9l3 3m0 0-3 3m3-3H2.25"
              />
            </svg>
          </div>
        </router-link>
        <router-link
          v-if="Object.keys(user.role !== 'ADMIN').length > 0 && !hideProfileIcon"
          to="/profile"
          title="Profile"
        >
          <div class="icon-container">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke-width="1.5"
              stroke="currentColor"
              class="w-10 h-10"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"
              />
            </svg>
          </div>
        </router-link>
        <router-link
          v-else-if="user.role == 'ADMIN'"
          to="/admin"
          title="Dashboard"
        >
          <div class="icon-container">
            <svg 
              xmlns="http://www.w3.org/2000/svg" 
              viewBox="0 0 448 512"
              stroke-width="1.5"
              stroke="currentColor"
              class="w-10 h-10"
            >
              <path d="M96 128a128 128 0 1 0 256 0A128 128 0 1 0 96 128zm94.5 200.2l18.6 31L175.8 483.1l-36-146.9c-2-8.1-9.8-13.4-17.9-11.3C51.9 342.4 0 405.8 0 481.3c0 17 13.8 30.7 30.7 30.7H162.5c0 0 0 0 .1 0H168 280h5.5c0 0 0 0 .1 0H417.3c17 0 30.7-13.8 30.7-30.7c0-75.5-51.9-138.9-121.9-156.4c-8.1-2-15.9 3.3-17.9 11.3l-36 146.9L238.9 359.2l18.6-31c6.4-10.7-1.3-24.2-13.7-24.2H224 204.3c-12.4 0-20.1 13.6-13.7 24.2z"/>
            </svg>
          </div>
        </router-link>
        <router-link v-if="!hideLogOutIcon" to="/logout" title="Logout">
          <div class="icon-container">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke-width="1.5"
              stroke="currentColor"
              class="w-10 h-10"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="M15.75 9V5.25A2.25 2.25 0 0 0 13.5 3h-6a2.25 2.25 0 0 0-2.25 2.25v13.5A2.25 2.25 0 0 0 7.5 21h6a2.25 2.25 0 0 0 2.25-2.25V15m3 0 3-3m0 0-3-3m3 3H9"
              />
            </svg>
          </div>
        </router-link>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { useUserStore } from '@/store/userStore' 
import { logout } from '@/services/authService';
import { ref, onMounted } from 'vue'
import http from '@/services/httpService'

const userStore = useUserStore()
const user = userStore.user
console.log(user)

const userFavorites = ref(userStore.favorites)

const fetchUserFavorites = async () => {
    try {
        const response = await http.get(`api/favorites/get-all/${user.id}`)
        userStore.setFavorites(response.data)
    } catch (error) {
        console.error(error)
        logout()
    }
}

// Fetch data when the component is mounted
onMounted(() => {
    if (!userFavorites.value.length) {
        fetchUserFavorites();
    }
});
</script>

<script>
import { useUserStore } from "@/store/userStore";

export default {
  data() {
    return {
      hideProfileIcon: false,
      hideLogOutIcon: false,
    };
  },
  methods: {
    updateVisibility(path) {
      this.hideProfileIcon = path === "/profile" || path === "/shop-owner/profile";
      this.hideLogOutIcon = !this.hideProfileIcon;
    }
  },
  watch: {
    $route(to, from) {
      this.updateVisibility(to.path);
    },
  },
  mounted() {
    this.updateVisibility(this.$route.path);
  },
};
</script>
