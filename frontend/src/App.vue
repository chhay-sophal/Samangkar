<script setup>
import Header from './components/header.vue';
import Footer from './components/footer.vue';
</script>

<template>
  <Header v-if="!$route.meta.hideHeaderFooter" />
    <router-view></router-view>
  <Footer v-if="!$route.meta.hideHeaderFooter" />
</template>