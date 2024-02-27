<script setup>
import Header from './components/header.vue'
import Footer from './components/footer.vue'
import { useUserStore } from '@/store/userStore';
import { onMounted } from 'vue';

const userStore = useUserStore();

onMounted(() => {
  userStore.fetchUserData();
});
</script>

<template>
  <Header v-if="!$route.meta.hideHeaderFooter" />
  <div class="2xl:w-5/6 pt-32 w-full px-8 mx-auto">
    <router-view></router-view>
  </div>
  <Footer v-if="!$route.meta.hideHeaderFooter" />
</template>
