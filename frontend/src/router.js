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
import RequestView from './views/admin/RequestView.vue'
import ServiceView from './views/user/serviceView.vue'
import ContactView from './views/user/contactView.vue'
import AboutView from './views/user/aboutView.vue'
import AdminServicesView from './views/admin/ServicesView.vue'
import ShopDetail from './views/shop/ShopDetail.vue'
import PackageDetail from './views/shop/PackageDetail.vue'
import ShopDashboard from './views/shop-owner/ShopDashboard.vue'
import ShopOwnerProfile from './views/shop-owner/ShopOwnerProfile.vue'
import ServiceDetail from './views/shop/ServiceDetail.vue'
import Review from './views/shop/Review.vue'
import AllShopView from './views/user/AllShopView.vue'
import AllServiceView from './views/user/AllServiceView.vue'
import AllPackageView from './views/user/AllPackageView.vue'
import ForgotPasswordVue from './views/user/ForgotPassword.vue'
import ResetPasswordVue from './views/user/ResetPassword.vue'

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
        path: '/admin/request',
        name: 'RequestView',
        component: RequestView,
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
        path: '/forgot-password',
        name: 'forgotPassword',
        component: ForgotPasswordVue,
        meta: { requiresAuth: false, hideHeaderFooter: true } 
    },
    {
        path: '/reset-password:token?',
        name: 'resetPassword',
        component: ResetPasswordVue,
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
        path: '/profile/favorites',
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
        path: "/shop/:shopId/details",
        component: ShopDetail,
    },
    {
        name: "PackageDetail",
        path: "/shop/:shopId/package/:packageId/details",
        component: PackageDetail,
    },
    {
        path: "/shop-owner/shop/:shopId",
        name: "ShopDashboard",
        component: ShopDashboard
    },
    {
        path: "/shop-owner/profile",
        name: "ShopOwnerProfile",
        component: ShopOwnerProfile
    },
    {
        path: "/shop/:shopId/service/:serviceId/details",
        name: "ServiceDetail",
        component: ServiceDetail
    },
    {
        path: "/shop/review",
        name: "Review",
        component: Review
    },
    {
        name: "AllShopView",
        path: "/shop/all",
        component: AllShopView,
    },
    {
        name: "AllServiceView",
        path: "/shop/all/service/all",
        component: AllServiceView,
    },
    {
        name: "AllPackageView",
        path: "/shop/all/package/all",
        component: AllPackageView,
    },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
