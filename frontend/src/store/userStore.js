import { defineStore } from "pinia"
import http from "@/services/httpService"

export const useUserStore = defineStore({
    id: 'userStore',
    state: () => {
        return {
            user: JSON.parse(localStorage.getItem('userStore') || '{}'),
            favorites: JSON.parse(sessionStorage.getItem('favorites') || '[]'),
            cards: JSON.parse(sessionStorage.getItem('cards') || '[]'),
        };
    },
    getters: {
        getUser(state) {
            return state.user
        }
    },
    actions: {
        setUser(userData) {
            this.user = userData
            localStorage.setItem('userStore', JSON.stringify(userData))
        },
        clearUser() {
            this.user = {}
            localStorage.removeItem('userStore')
        },
        setFavorites(data) {
            this.favorites = data
            sessionStorage.setItem('favorites', JSON.stringify(this.favorites))
        },
        setCards(data) {
            this.cards = data
            sessionStorage.setItem('cards', JSON.stringify(this.cards))
        },
    }
})