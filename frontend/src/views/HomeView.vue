<template>
    <div class="bg-blue-300 flex items-center justify-center min-h-96 text-7xl">
      <p class="">This is home page</p>
      <h1 class="text-cyan-500">{{ greeting }} {{ user.username }}</h1>
    </div>
</template>

<script setup>
  import { useUserStore } from '@/store/userStore';
  import { computed } from 'vue';
  const userStore = useUserStore();
  const user = computed(() => userStore.user);
</script>

<script>
  import http from '@/services/httpService'

  export default {
    data() {
      return {
        greeting: null,
      };
    },
    mounted() {
      this.fetchData();
    },
    methods: {
      async fetchData() {
        try {
          const response = await http.get('')
          this.greeting = response.data
        } catch (error) {
          console.error('Error fetching data:', error.message)
        }
      },
    },
  };
</script>