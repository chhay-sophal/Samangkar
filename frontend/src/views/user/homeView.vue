<template>
    <div>
      <div class="main-content">
        <div class="hompage">
          <h1>HomePage</h1>

        </div>

      </div>
    </div>
  </template>
  
  <script>
    import Footer from './../../components/footer.vue'; // Adjust the path as per your project structure
  
  export default {
    name: 'HomePage',
    components: {
        Footer // Register the Sidebar component
    }
  }
  </script>
  <style>
  body{
    
  }
  .hompage{
    padding:5% 5%;
    color: black;
    background-color: rgb(253, 251, 214);
  }
</style>
  
  