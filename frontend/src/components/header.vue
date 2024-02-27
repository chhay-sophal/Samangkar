<template>
    <header class="fixed left-0 top-0 z-40 border-b w-full col-span-full">
        <nav
            class="mx-auto flex items-center justify-between px-8"
            aria-label="Global"
        >
            <div class="flex lg:flex-1 items-center gap-10">
                <h1
                    class="text-left font-bold text-4xl text-primary hover:text-secondary"
                >
                    <router-link to="/" title="Homepage">
                        Samangkar
                    </router-link>
                </h1>
            </div>
            <div class="justify-end">
                <router-link v-if="!isLoggedIn" to="/login" title="Login">
                    <div class="icon-container">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-8 h-8">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 9V5.25A2.25 2.25 0 0 1 10.5 3h6a2.25 2.25 0 0 1 2.25 2.25v13.5A2.25 2.25 0 0 1 16.5 21h-6a2.25 2.25 0 0 1-2.25-2.25V15M12 9l3 3m0 0-3 3m3-3H2.25" />
                        </svg>
                    </div>
                </router-link>
                <router-link v-if="isLoggedIn" to="/logout" title="Logout">
                    <div class="icon-container">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-8 h-8">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 9V5.25A2.25 2.25 0 0 0 13.5 3h-6a2.25 2.25 0 0 0-2.25 2.25v13.5A2.25 2.25 0 0 0 7.5 21h6a2.25 2.25 0 0 0 2.25-2.25V15m3 0 3-3m0 0-3-3m3 3H9" />
                        </svg>
                    </div>
                </router-link>
            </div>
        </nav>
    </header>
</template>

<script setup>
import { useDark, useToggle } from "@vueuse/core"
import { useUserStore } from '@/store/userStore';
import { computed } from 'vue';

const isDark = useDark();
const toggleDark = useToggle(isDark)

const userStore = useUserStore()
const user = computed(() => userStore.user)
const isLoggedIn = computed(() => userStore.isLoggedIn)
</script>

<script>
export default {
    data() {
        return {
            showTooltipText: false,
        }
    },
    methods: {
        showTooltip(text) {
           this.showTooltipText = true
        },
        hideTooltip() {
            this.showTooltipText = false
        },
    }
}
</script>
