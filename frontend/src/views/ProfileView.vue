<template>
    <div class="flex">
        <div class="bg-blue-300 2xl:w-1/5 xl:w-1/4 w-1/3">
            <div class="p-5 pb-0 flex justify-center">
                <div class="bg-slate-600 overflow-hidden p-5 flex items-center rounded-full">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-full h-full">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
                    </svg>
                    <!-- <img class="w-full" src="../components/pictures/default-profile.png" alt=""> -->
                </div>
            </div>
            <div class="flex justify-center text-4xl p-3">
                {{ user.username }}
            </div>
            <div class="flex flex-col items-center gap-2 text-2xl">
                <div class="flex gap-3">
                    <div class="">Email: </div>
                    <div class="">{{ user.email }}</div>
                </div>
                <div class="flex gap-3">
                    <div class="">Email: </div>
                    <div class="">{{ user.email }}</div>
                </div>
            </div>
        </div>
        <div class="bg-green-300 w-4/5">

        </div>
    </div>
</template>

<script setup>
  import { useUserStore } from '@/store/userStore';
  import { computed } from 'vue';
  const userStore = useUserStore();
  const user = computed(() => userStore.user);
</script>