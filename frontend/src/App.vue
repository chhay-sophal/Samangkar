<script setup>
import Header from './components/header.vue';
import Footer from './components/footer.vue';
import HomeView from './views/HomeView.vue';
</script>

<template>
  <Header />
  <div class="min-w-full relative m-10">
    <router-view></router-view>
  </div>
  <Footer />
</template>