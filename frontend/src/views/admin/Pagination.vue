<template>
    <div class="pagination">
      <button @click="prevPage" :disabled="first">Previous</button>
      <span>Page {{ currentPage }} of {{ totalPages }}</span>
      <button @click="nextPage" :disabled="last">Next</button>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      currentPage: Number,
      totalPages: Number,
      first: Boolean,
      last: Boolean,
    },
    methods: {
      prevPage() {
        this.$emit('page-change', this.currentPage - 1);
      },
      nextPage() {
        this.$emit('page-change', this.currentPage + 1);
      }
    }
  }
  </script>
  
  <style scoped>
  .pagination {
    margin-top: 20px;
    text-align: center;
  }
  
  button {
    margin: 0 5px;
    padding: 5px 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  button:disabled {
    background-color: #ccc;
    cursor: not-allowed;
  }
  </style>
  