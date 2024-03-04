<template>
  <Header v-if="!$route.meta.hideHeaderFooter" />
  <div class="3xl:w-5/6 pt-32 w-full px-8 mx-auto">
    <router-view></router-view>
  </div>
  <Footer v-if="!$route.meta.hideHeaderFooter" />
</template>

<script>
import Header from './components/header.vue'
import Footer from './components/footer.vue'

export default {
  name: 'App',
  components: { Header, Footer },
}
</script>