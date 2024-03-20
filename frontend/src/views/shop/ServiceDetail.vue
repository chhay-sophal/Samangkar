<template>
    <div>
      <!-- Your existing sections -->
      <section class="service-detail">
        <div class="flex items-center justify-between">
          <h2 class="text-2xl p-5 dark:text-stone-300">Service Details</h2>
        </div>
        <div class="service-info">
          <div class="service-image">
            <ImageViewer :imageData="service.imageUrl" class="aspect-square object-fill"/>
          </div>
          <div class="service-details">
            <h3 class="text-xl font-bold">{{ service.name }}</h3>
            <p>{{ service.description }}</p>
            <p><strong>Price:</strong> ${{ service.price }}</p>
            <p><strong>Type:</strong> {{ service.type }} </p>
            <button class="book-button p-3 rounded-lg mt-5">Book Now</button>
          </div>
        </div>
      </section>
    </div>
  </template>
  
  <script>
  import ImageViewer from "@/components/ImageViewer.vue";
  
  export default {
    components: {
      ImageViewer,
    },
    data() {
      return {
        service: {
          name: "ហែជំនូន",
          description: "Get our experienced barbers.",
          price: 25,
          type: 'flower',
          imageUrl: "https://i.pinimg.com/564x/45/ac/f1/45acf1a37c19801181e80e031d78dfa3.jpg",
        },
      };
    },
  };
  </script>
  
  <style scoped>
  .service-detail {
    margin-top: 20px;
  }
  
  .service-info {
    display: flex;
    justify-content: space-between;
  }
  
  .service-details {
    flex: 1;
    padding: 20px;
  }
  
  .service-image {
    flex: 1;
  }
  
  .book-button:hover {
    background-color: #0b7dda;
  }
  
  .dark:text-stone-300 {
    color: #a0aec0;
  }
  </style>
  