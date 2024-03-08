import axios from 'axios';
import http from '@/services/httpService'
import { useUserStore } from '@/store/userStore'

const API_BASE_URL = 'http://localhost:8080'; // Your Spring Boot backend URL

export const login = async (username, password) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/api/auth/login`, { username, password }, { withCredentials: true })
    const token = response.data.accessToken
    localStorage.setItem('token', token)

    // Fetch user profile
    const userData = await http.get(`api/auth/profile`)
    const userStore = useUserStore()
    userStore.setUser(userData.data)
    console.log(userStore.getUser)
  } catch (error) {
    throw error;
  }
};

export const logout = () => {
  localStorage.removeItem('token')
  sessionStorage.removeItem('favorites')
  sessionStorage.removeItem('cards')

  const userStore = useUserStore()
  userStore.clearUser()
};

export const getToken = () => {
  return localStorage.getItem('token')
};
