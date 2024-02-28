<template>
    <header class="fixed left-0 top-0 z-40 border-b w-full">
        <nav
            class="mx-auto flex items-center justify-between px-8 3xl:w-5/6"
            aria-label="Global"
        >
            <div class="flex lg:flex-1 items-center gap-10">
                <h1
                    class="text-left text-4xl text-primary hover:text-secondary"
                >
                    <router-link to="/" title="Homepage" class="font-bold">
                        Samangkar
                    </router-link>
                </h1>
            </div>
            <div class="justify-end flex py-5">
                <router-link v-if="!user" to="/login" title="Login">
                    <div class="icon-container">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-10 h-10">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 9V5.25A2.25 2.25 0 0 1 10.5 3h6a2.25 2.25 0 0 1 2.25 2.25v13.5A2.25 2.25 0 0 1 16.5 21h-6a2.25 2.25 0 0 1-2.25-2.25V15M12 9l3 3m0 0-3 3m3-3H2.25" />
                        </svg>
                    </div>
                </router-link>
                <router-link v-if="user && !hideProfileIcon" to="/profile" title="Profile">
                    <div class="icon-container">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-10 h-10">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                        </svg>
                    </div>
                </router-link>
                <router-link v-if="user && !hideLogOutIcon" to="/logout" title="Logout">
                    <div class="icon-container">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-10 h-10">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 9V5.25A2.25 2.25 0 0 0 13.5 3h-6a2.25 2.25 0 0 0-2.25 2.25v13.5A2.25 2.25 0 0 0 7.5 21h6a2.25 2.25 0 0 0 2.25-2.25V15m3 0 3-3m0 0-3-3m3 3H9" />
                        </svg>
                    </div>
                </router-link>
            </div>
        </nav>
    </header>
</template>

<script>
import { useDark, useToggle } from "@vueuse/core"
import { useUserStore } from '@/store/userStore'
import { computed } from 'vue'
import http from '@/services/httpService'
import { login, logout } from "@/services/authService"

export default {
    data() {
        return {
            hideProfileIcon: false,
            hideLogOutIcon: false,
        }
    },
    methods: {
    },
    watch: {
        // Watch for changes on the $route object
        '$route'(to, from) {
            // Check if the current route's path is '/profile' (or adjust the path as needed)
            this.hideProfileIcon = to.path === '/profile';
            this.hideLogOutIcon = to.path !== '/profile';
        }
    },
    mounted() {
        http.get('')
        .then (Response => {
            console.log(Response)
        })
        .catch (Error => {
            console.log(Error)
            logout()
            this.$router.push({ name: 'homePageRoute' })
        })

        // Also check when the component is first mounted
        this.hideProfileIcon = this.$route.path === '/profile';
        this.hideLogOutIcon = this.$route.path !== '/profile';
    },
    created() {
        const userStore = useUserStore();
        this.user = userStore.getUser;
    }
}
</script>
