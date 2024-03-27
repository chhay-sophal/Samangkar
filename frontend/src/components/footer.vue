<template>
  <footer class="footer w-full bg-slate-200 dark:bg-stone-800 text-stone-800 dark:text-slate-200">
    <div class="">
      <p>© 2024 SamAngKar. All rights reserved.</p>
      <ul class="footer-links">
          <router-link class="px-2" to="/" title="Home">Home</router-link>
          <router-link class="px-2" to="/about" title="About">About</router-link>
          <router-link class="px-2" to="/services" title="Services">Services</router-link>
          <router-link class="px-2" to="/contact" title="Contact">Contact</router-link>
      </ul>
    </div>
  </footer>
</template>
  
<script>
export default {
  name: 'Footer'
}
</script>
  
  <!-- Scoped styles (if necessary) -->
  <style scoped>
  /* Scoped styles specific to this component */
  </style>
  