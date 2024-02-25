<template>
    <div class="col-start-1 col-end-3 text-center text-5xl">
      <button @click="fetchGreeting">Fetch Greeting</button>
      <h1 class="text-cyan-500">{{ greeting }}</h1>
    </div>
  </template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      greeting: ''
    };
  },
  methods: {
    fetchGreeting() {
      axios.get('http://localhost:8080/greeting')
        .then(response => {
          this.greeting = response.data;
        })
        .catch(error => {
          console.error("There was an error fetching the greeting:", error);
        });
    }
  }
};
</script>