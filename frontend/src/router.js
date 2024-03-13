import { createRouter, createWebHistory } from 'vue-router'
import HomeView from './views/user/homeView.vue'
import LoginView from './views/LoginView.vue'
import RegisterUserView from './views/RegisterUserView.vue'
import LogoutView from './views/LogoutView.vue'
import ProfileView from './views/ProfileView.vue'
import FavoriteShopsView from './views/FavoriteShopsView.vue'
import CardsView from './views/CardsView.vue'
import AddUserView from './views/admin/AddUserView.vue'
import OverviewView from './views/admin/OverView.vue'
import AddShopView from './views/admin/AddShop.vue'
import UsersView from './views/admin/UserView.vue'
import Detail from './views/admin/DetialView.vue'
import EditUserView from './views/admin/EditUserView.vue'
import EditShopView from './views/admin/EditShopView.vue'
import EditPackageView from './views/admin/EditPackageView.vue'
import ShopView from './views/admin/ShopView.vue'
import AddPackageView from './views/admin/AddPackage.vue'
import PackageView from './views/admin/PackageView.vue'
import ReviewView from './views/admin/ReviewView.vue'
import ServiceView from './views/user/serviceView.vue'
import ContactView from './views/user/contactView.vue'
import AboutView from './views/user/aboutView.vue'
import AdminServicesView from './views/admin/ServicesView.vue'
import ShopDetail from './views/shop/ShopDetail.vue'
import PackageDetail from './views/shop/PackageDetail.vue'

const routes = [
    {
        path: '/admin',
        name: 'AdminPageRoute',
        component: OverviewView,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/admin/users',
        name: 'UserViewRoute',
        component: UsersView,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/admin/users/detail/:userId',
        name: 'DetialView',
        component: Detail,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/admin/users/add',
        name: 'AddUserViewRoute',
        component: AddUserView,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/admin/users/edit/:userId',
        name: 'EditUserView',
        component: EditUserView,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/admin/shops',
        name: 'ShopView',
        component: ShopView,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/admin/shops/add',
        name: 'AddSopViewRoute',
        component: AddShopView,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/admin/shops/edit/:shopId',
        name: 'EditShopView',
        component: EditShopView,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/admin/services',
        name: 'AdminServicesView',
        component: AdminServicesView,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/admin/packages',
        name: 'PackageView',
        component: PackageView,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/admin/packages/add',
        name: 'AddPackageView',
        component: AddPackageView,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/admin/packages/edit/:packageId',
        name: 'EditPackageView',
        component: EditPackageView,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/admin/reviews',
        name: 'ReviewView',
        component: ReviewView,
        meta: { hideHeaderFooter: true } 
    },
    {
        path: '/',
        name: 'homePageRoute',
        component: HomeView
    },
    {
        name: 'ServiceRoute',
        path: '/services',
        component: ServiceView
    },
    {
        name: 'ContactRoute',
        path: '/contact',
        component: ContactView
    },
    {
        name: 'AboutRoute',
        path: '/about',
        component: AboutView
    },
    {
        path: '/login',
        name: 'loginPageRoute',
        component: LoginView,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        path: '/register',
        name: 'registerPageRoute',
        component: RegisterUserView,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        path: '/logout',
        name: 'logoutPageRoute',
        component: LogoutView,
        meta: { requiresAuth: false, hideHeaderFooter: true}
    },
    {
        path: '/profile',
        name: 'profilePageRoute',
        component: ProfileView,
    },
    {
        path: '/profile/favorite',
        name: 'favoritePageRoute',
        component: FavoriteShopsView,
    },
    {
        path: '/profile/cards',
        name: 'cardsPageRoute',
        component: CardsView,
    },
    {
        path: '/about',
        name: 'aboutPageRout',
        component: ReviewView
    },
    {
        path: '/contact',
        name: 'contactPageRout',
        component: ReviewView
    },
    {
        name: "ShopDetail",
        path: "/shop/detail",
        component: ShopDetail,
    },
    {
        name: "PackageDetail",
        path: "/shop/package/detail",
        component: PackageDetail,
    },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
