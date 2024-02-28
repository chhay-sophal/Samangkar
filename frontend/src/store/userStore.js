import { defineStore } from "pinia"

export const useUserStore = defineStore({
    id: 'userStore',
    state: () => {
        return {
            user: JSON.parse(localStorage.getItem('userStore')),
            favorites: JSON.parse(localStorage.getItem('favorites')),
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
        },
        clearUser() {
            this.user = null
        },
        setFavorite(data) {
            this.favorites = data
            localStorage.setItem('favorites', JSON.stringify(this.favorites))
        }
    }
})