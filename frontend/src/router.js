import { createRouter, createWebHistory } from 'vue-router'
import HomeView from './views/HomeView.vue'
import LoginView from './views/LoginView.vue'
import LogoutView from './views/LogoutView.vue'
import ProfileView from './views/ProfileView.vue'

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
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;