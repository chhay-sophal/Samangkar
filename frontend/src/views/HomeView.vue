<template>
    <div class="col-start-1 col-end-3 text-center text-5xl">
      <!-- <button @click="fetchData">Fetch Greeting</button> -->
      <h1 class="text-cyan-500">{{ greeting }}</h1>
    </div>
  </template>

<script>
import axios from 'axios';
import http from '@/services/httpService';
import { getToken, removeToken } from '@/services/authService';

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
        const response = await http.get(''); // Replace '/api/data' with your actual API endpoint
        this.greeting = response.data;
        console.log('Data:', response.data);
      } catch (error) {
        console.error('Error fetching data:', error.message);
      }
    },
  },
};
</script>