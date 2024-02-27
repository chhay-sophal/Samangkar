import { defineStore } from "pinia"

export const useUserStore = defineStore({
    id: 'userStore',
    state: () => ({
        user: {},
        isLoggedIn: false,
    }),
    actions: {
        setUser(userData) {
            this.user = userData
            this.isLoggedIn = true
        },
        clearUser() {
            this.user = {}
            this.isLoggedIn = false
        },
        fetchUserData() {
            const userData = JSON.parse(localStorage.getItem('userStore'));
            this.setUser(userData);
        },
    }
})