// authService.js
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080'; // Your Spring Boot backend URL

export const login = async (username, password) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/api/auth/login`, { username, password }, { withCredentials: true });
    const token = response.data.accessToken;
    console.log(token);
    localStorage.setItem('token', token);
    return token;
  } catch (error) {
    throw error;
  }
};

// export const storeToken = (token) => {
//     localStorage.setItem('accessToken', token);
//   };

export const removeToken = () => {
  localStorage.removeItem('token');
};

export const getToken = () => {
  return localStorage.getItem('token');
};
