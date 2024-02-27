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
    <router-view></router-view>
  <Footer v-if="!$route.meta.hideHeaderFooter" />
</template>
