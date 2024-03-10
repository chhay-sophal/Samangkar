// httpService.js
import axios from 'axios';
import { getToken, logout } from '@/services/authService';

const instance = axios.create({
  baseURL: 'http://localhost:8080', // Your Spring Boot backend URL
  timeout: 10000, // Adjust the timeout as needed
});

// Add a request interceptor
instance.interceptors.request.use(
  (config) => {
    const accessToken = getToken();
    if (accessToken) {
      config.headers['Authorization'] = `Bearer ${accessToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Add a response interceptor
instance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    const { status } = error.response;

    // Handle token expiration or invalid token
    if (status === 401) {
      console.error('Unauthorized request. Token might be expired or invalid.');
      // Optionally, redirect to the login page or refresh the token
      // logout(); // Clear the invalid token
      // Redirect or refresh token logic can be added here
    }

    return Promise.reject(error);
  }
);

export default instance;
