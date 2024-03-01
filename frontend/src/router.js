import { createRouter, createWebHistory } from 'vue-router'
import HomeView from './views/user/homeView.vue'
import LoginView from './views/LoginView.vue'
import AdminView from './views/admin/homeView.vue'
import SideBarView from './components/sideBar.vue'
import AddUserView from './views/admin/AddUserView.vue'
import OverView from './views/admin/OverView.vue'
import AddShop from './views/admin/AddShop.vue'
import AddAdmin from './views/admin/AddAdmin.vue'
// frontend\src\components\sideBar.vue
import User from './views/admin/UserView.vue'
import Detail from './views/admin/DetialView.vue'
import Edit from './views/admin/EditView.vue'
import ShopView from './views/admin/ShopView.vue'
import AddPackage from './views/admin/AddPackage.vue'
import PackageView from './views/admin/PackageView.vue'
import ReviewView from './views/admin/ReviewView.vue'

const routes = [
    {
        name: 'HomePageRoute',
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
        name: 'DashboardPageRoute',
        path: '/admin',
        component: AdminView,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        name: 'SidebarPageRoute',
        path: '/sidebar',
        component: SideBarView,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        name: 'AddUserViewRoute',
        path: '/add',
        component: AddUserView,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        name: 'OverViewRoute',
        path: '/overview',
        component: OverView,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        name: 'AddSopViewRoute',
        path: '/addshop',
        component: AddShop,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        name: 'AddAdminViewRoute',
        path: '/addadmin',
        component: AddAdmin,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        name: 'UserViewRoute',
        path: '/user',
        component: User,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        name: 'DetialView',
        path: '/user/detail',
        component: Detail,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        name: 'EditView',
        path: '/user/edit',
        component: Edit,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        name: 'ShopView',
        path: '/shop',
        component: ShopView,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },{
        name: 'AddPackageView',
        path: '/addpackage',
        component: AddPackage,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    ,{
        name: 'PackageView',
        path: '/package',
        component: PackageView,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    } ,{
        name: 'ReviewView',
        path: '/review',
        component: ReviewView,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;