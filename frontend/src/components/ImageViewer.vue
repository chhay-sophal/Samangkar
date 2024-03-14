<template>
    <div class="w-full h-full">
      <img :src="getUserImageUrl(imageData)" alt="Image" class="w-full h-full object-cover">
    </div>
  </template>
  
  <script>
  export default {
    props: {
      imageData: {
        type: String,
        required: true
      }
    },
    methods: {
        getUserImageUrl(imageData) {
            try {
                const byteString = atob(imageData);
                const mimeString = "image/jpg"; // Adjust mime type as needed
                const arrayBuffer = new ArrayBuffer(byteString.length);
                const intArray = new Uint8Array(arrayBuffer);
                for (let i = 0; i < byteString.length; i++) {
                    intArray[i] = byteString.charCodeAt(i);
                }
                const blob = new Blob([arrayBuffer], { type: mimeString });
                return URL.createObjectURL(blob);
            } catch (error) {
                console.error('Error getting image data:', error)
            }
        },
    },
  };
  </script>