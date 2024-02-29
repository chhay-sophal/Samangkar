import { createRouter, createWebHistory } from 'vue-router'
import HomeView from './views/HomeView.vue'
import LoginView from './views/LoginView.vue'
import LogoutView from './views/LogoutView.vue'
import ProfileView from './views/ProfileView.vue'
import FavoriteShopsView from './views/FavoriteShopsView.vue'
import CardsView from './views/CardsView.vue'

const routes = [
    {
        name: 'homePageRoute',
        path: '/',
        component: HomeView
    },
    {
        name: 'loginPageRoute',
        path: '/login',
        component: LoginView,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        name: 'logoutPageRoute',
        path: '/logout',
        component: LogoutView,
        meta: { requiresAuth: false, hideHeaderFooter: true}
    },
    {
        name: 'profilePageRoute',
        path: '/profile',
        component: ProfileView,
    },
    {
        name: 'favoritePageRoute',
        path: '/profile/favorite',
        component: FavoriteShopsView,
    },
    {
        name: 'cardsPageRoute',
        path: '/profile/cards',
        component: CardsView,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;