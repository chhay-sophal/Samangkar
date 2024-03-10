import { defineStore } from "pinia"

export const useAdminStore = defineStore({
    id: 'adminStore',
    state: () => {
        return {
            users: JSON.parse(sessionStorage.getItem('users') || '[]'),
            shops: JSON.parse(sessionStorage.getItem('shops') || '[]'),
            packages: JSON.parse(sessionStorage.getItem('packages') || '[]'),
            reviews: JSON.parse(sessionStorage.getItem('reviews') || '[]'),
        };
    },
    getters: {
        getUsers(state) {
            return state.users
        },
        getUserCount(state) {
            return state.users.length;
        },
        getActiveUsers(state) {
            return state.users.filter(user => !user.deletedAt);
        },
        getInactiveUsers(state) {
            return state.users.filter(user => user.deletedAt);
        },
        getShops(state) {
            return state.shops
        },
        getShopCount(state) {
            return state.shops.length;
        },
        getActiveShops(state) {
            return state.shops.filter(shop => !shop.deletedAt);
        },
        getInActiveShops(state) {
            return state.shops.filter(shop => shop.deletedAt);
        },
        getPackages(state) {
            return state.packages
        },
        getPackageCount(state) {
            return state.packages.length;
        },
        getReviews(state) {
            return state.packages
        },
    },
    actions: {
        setUsers(userData) {
            this.users = userData;
            sessionStorage.setItem('users', JSON.stringify(userData));
        },
        clearUsers() {
            this.users = {};
            sessionStorage.removeItem('users');
        },
        setShops(shopsData) {
            this.shops = shopsData;
            sessionStorage.setItem('shops', JSON.stringify(shopsData));
        },
        clearShops() {
            this.shops = {};
            sessionStorage.removeItem('shops');
        },
        setPackages(packagesData) {
            this.packages = packagesData;
            sessionStorage.setItem('packages', JSON.stringify(packagesData));
        },
        clearPackages() {
            this.packages = {};
            sessionStorage.removeItem('packages');
        },
        setReviews(reviewsData) {
            this.reviews = reviewsData;
            sessionStorage.setItem('reviews', JSON.stringify(reviewsData));
        },
        clearReviews() {
            this.reviews = {};
            sessionStorage.removeItem('reviews');
        },
    }
})