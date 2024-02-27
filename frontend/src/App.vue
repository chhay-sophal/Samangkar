<template>
  <Header v-if="!$route.meta.hideHeaderFooter" />
    <router-view></router-view>
  <Footer v-if="!$route.meta.hideHeaderFooter" />
</template>

<script>
import Header from './components/header.vue'
import Footer from './components/footer.vue'
import { useUserStore } from '@/store/userStore'
import { onMounted } from 'vue';

export default{
  components: { Header, Footer },
  data() {
    return {
      user: {},
      greeting: null,
    };
  },
  setup() {
    const userStore = useUserStore();

    onMounted(() => {
      userStore.fetchUserData();
    });
  }
};
</script>