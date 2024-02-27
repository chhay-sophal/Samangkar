import { defineStore } from "pinia"

export const useUserStore = defineStore({
    id: 'userStore',
    state: () => {
        return {
            user: JSON.parse(localStorage.getItem('userStore')),
        };
    },
    getters: {
        getUser() {
            return this.user
        }
    },
    actions: {
        setUser(userData) {
            this.user = userData
            this.isLoggedIn = true
        },
        clearUser() {
            this.user = null
        },
    }
})