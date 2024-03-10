import { createRouter, createWebHistory } from 'vue-router'
import HomeView from './views/user/homeView.vue'
import LoginView from './views/LoginView.vue'
import LogoutView from './views/LogoutView.vue'
import ProfileView from './views/ProfileView.vue'
import FavoriteShopsView from './views/FavoriteShopsView.vue'
import CardsView from './views/CardsView.vue'
import AdminView from './views/admin/homeView.vue'
import SideBarView from './components/AdminSidebar.vue'
import AddUserView from './views/admin/AddUserView.vue'
import OverView from './views/admin/OverView.vue'
import AddShop from './views/admin/AddShop.vue'
import AddAdmin from './views/admin/AddAdmin.vue'
// frontend\src\components\sideBar.vue
import User from './views/admin/UserView.vue'
import Detail from './views/admin/DetialView.vue'
import EditUserView from './views/admin/EditUserView.vue'
import EditShopView from './views/admin/EditShopView.vue'
import EditPackageView from './views/admin/EditPackageView.vue'
import ShopView from './views/admin/ShopView.vue'
import AddPackage from './views/admin/AddPackage.vue'
import PackageView from './views/admin/PackageView.vue'
import ReviewView from './views/admin/ReviewView.vue'

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
    // {
    //     name: 'DashboardPageRoute',
    //     path: '/admin',
    //     component: AdminView,
    //     meta: { requiresAuth: false, hideHeaderFooter: true } 
    // },
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
        path: '/admin',
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
        path: '/user/detail/:userId',
        component: Detail,
        meta: { hideHeaderFooter: true } 
    },
    {
        name: 'EditUserView',
        path: '/user/edit/:userId',
        component: EditUserView,
        meta: { hideHeaderFooter: true } 
    },
    {
        name: 'ShopView',
        path: '/shop',
        component: ShopView,
        meta: { hideHeaderFooter: true } 
    },
    {
        name: 'EditShopView',
        path: '/shop/edit/:shopId',
        component: EditShopView,
        meta: { hideHeaderFooter: true } 
    },
    {
        name: 'AddPackageView',
        path: '/addpackage',
        component: AddPackage,
        meta: { hideHeaderFooter: true } 
    },
    ,{
        name: 'PackageView',
        path: '/package',
        component: PackageView,
        meta: { hideHeaderFooter: true } 
    },
    {
        name: 'EditPackageView',
        path: '/package/edit/:packageId',
        component: EditPackageView,
        meta: { hideHeaderFooter: true } 
    },
    {
        name: 'ReviewView',
        path: '/review',
        component: ReviewView,
        meta: { hideHeaderFooter: true } 
    },
    {
        name: 'aboutPageRout',
        path: '/about',
        component: ReviewView
    },
    {
        name: 'contactPageRout',
        path: '/contact',
        component: ReviewView
    },
    {
        name: 'servicesPageRout',
        path: '/services',
        component: ReviewView
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;