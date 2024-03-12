<template>
  <header
    class="fixed left-0 top-0 z-40 border-b w-full bg-gray-50 dark:bg-neutral-900"
  >
    <nav
      class="mx-auto flex items-center justify-between px-8 3xl:w-5/6 bg-white"
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
          <router-link to="/">Home</router-link>
          <router-link to="/about">About</router-link>
          <router-link to="/services">Services</router-link>
          <router-link to="/contact">Contact</router-link>
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
          v-if="Object.keys(user).length > 0 && !hideProfileIcon"
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
import { useUserStore } from "@/store/userStore";

const userStore = useUserStore();
const user = userStore.user;
console.log(user);
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
  methods: {},
  watch: {
    $route(to, from) {
      this.hideProfileIcon = to.path === "/profile";
      this.hideLogOutIcon = to.path !== "/profile";
    },
  },
  mounted() {
    this.hideProfileIcon = this.$route.path === "/profile";
    this.hideLogOutIcon = this.$route.path !== "/profile";
  },
};
</script>
