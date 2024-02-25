import { createRouter, createWebHistory } from 'vue-router'
import HomeView from './views/HomeView.vue'
import LoginView from './views/LoginView.vue'

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
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;