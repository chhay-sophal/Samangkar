<template>
    <div class="bg-blue-300 flex items-center justify-center min-h-96 text-7xl">
      <p class="">This is home page</p>
      <h1 v-if="user" class="text-cyan-500">{{ user.username }}</h1>
    </div>
</template>

<script>
  import { useUserStore } from '@/store/userStore'

  export default {
    data() {
      return {
      };
    },
    created() {
      const userStore = useUserStore();
      this.user = userStore.getUser;
    }
  };
</script>