<script setup>
import Header from './components/header.vue';
import Footer from './components/footer.vue';
import HomeView from './views/HomeView.vue';
</script>

<template>
  <Header v-if="!$route.meta.hideHeaderFooter" />
    <router-view></router-view>
  <Footer v-if="!$route.meta.hideHeaderFooter" />
  <!-- <Header />
  <div class="min-w-full relative my-10 col-span-2">
    <router-view></router-view>
  </div>
  <Footer /> -->
</template>