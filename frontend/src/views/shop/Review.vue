<template>
    <div>
      <!-- Your existing sections -->
  
      <!-- Review Section -->
      <section class="review-section">
        <h2 class="text-2xl p-5 dark:text-stone-300">Shop Reviews</h2>
        <div class="review-list">
          <div v-for="(review, index) in shopReviews" :key="index" class="review-item">
            <div class="flex items-center">
              <div class="avatar">
                <img :src="review.userAvatar" alt="User Avatar">
              </div>
              <div class="user-info">
                <h3>{{ review.userName }}</h3>
                <p>{{ review.date }}</p>
              </div>
              <div class="star-rating">
                <span v-for="n in 5" :key="n" :class="{ 'text-yellow-500': n <= review.rating, 'text-gray-300': n > review.rating }">&#9733;</span>
              </div>
            </div>
            <p>{{ review.comment }}</p>
          </div>
        </div>
      </section>
  
      <!-- Comment Form -->
      <section class="comment-form">
        <h2 class="text-2xl p-5 dark:text-stone-300">Leave a Review</h2>
        <form @submit.prevent="submitComment" class="flex flex-col">
          <div class="mb-4">
            <label for="comment" class="block mb-1">Comment</label>
            <textarea v-model="newComment" id="comment" rows="4" class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:border-blue-500" required></textarea>
          </div>
          <div class="mb-4">
            <label for="rating" class="block mb-1">Rating</label>
            <select v-model="newRating" id="rating" class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:border-blue-500" required>
              <option value="">Select Rating</option>
              <option v-for="n in 5" :key="n" :value="n">{{ n }}</option>
            </select>
          </div>
          <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition duration-300">Submit</button>
        </form>
      </section>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        shopReviews: [
          { userName: "User1", userAvatar: "avatar1.jpg", date: "March 15, 2024", comment: "Great service! Will definitely come back again.", rating: 5 },
          { userName: "User2", userAvatar: "avatar2.jpg", date: "March 12, 2024", comment: "Average experience. Could be better.", rating: 3 },
          { userName: "User3", userAvatar: "avatar3.jpg", date: "March 10, 2024", comment: "Absolutely fantastic! Highly recommend this shop.", rating: 5 },
          // Add more reviews as needed
        ],
        newComment: '',
        newRating: null
      };
    },
    methods: {
      submitComment() {
        if (this.newComment && this.newRating) {
          const newReview = {
            userName: "User", // Replace with the actual username of the logged-in user
            userAvatar: "avatar.jpg", // Replace with the actual avatar URL of the logged-in user
            date: new Date().toLocaleDateString(), // Use the current date
            comment: this.newComment,
            rating: parseInt(this.newRating)
          };
          this.shopReviews.push(newReview);
          // Reset form fields after submission
          this.newComment = '';
          this.newRating = null;
        } else {
          alert("Please fill in both the comment and rating fields.");
        }
      }
    }
  };
  </script>
  
  
  <style scoped>
  .review-section {
    margin-top: 20px;
  }
  
  .review-item {
    border: 1px solid #cbd5e0;
    border-radius: 8px;
    padding: 20px;
    margin-bottom: 10px;
  }
  
  .avatar img {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    margin-right: 10px;
  }
  
  .user-info h3 {
    margin: 0;
    font-size: 1.2rem;
  }
  
  .user-info p {
    margin: 0;
    color: #718096;
  }
  
  .star-rating {
    margin-left: auto;
  }
  
  .star-rating span {
    font-size: 1.2rem;
  }
  
  .dark:text-stone-300 {
    color: #a0aec0;
  }
  
  .text-yellow-500 {
    color: #f6e05e;
  }
  
  .text-gray-300 {
    color: #cbd5e0;
  }
  </style>
  