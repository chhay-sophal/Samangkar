<template>
    <div class="lg:flex block relative">
        <!-- User detail on the left -->
        <div class="2xl:w-1/5 xl:w-1/4 w-full">
            <div class="p-5 pb-0 flex justify-center">
                <div class="dark:bg-slate-600 bg-gray-100 overflow-hidden p-5 flex items-center rounded-full">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-full h-full">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
                    </svg>
                    <!-- <img class="w-full" src="../components/pictures/default-profile.png" alt=""> -->
                </div>
            </div>
            <div class="flex justify-center text-4xl p-3">
                {{ userStore.getUser.username }}
            </div>
            <div class="flex flex-col items-center gap-2 text-2xl">
                <div class="flex gap-3">
                    <div class="">    
                        {{ userStore.getUser.email }}
                    </div>
                </div>
                <div class="flex flex-col gap-2">
                    <div 
                        @click="handleShowChangeInfoPanel()"
                        class="text-green-400 hover:text-stone-100 border border-green-400 hover:bg-green-400 p-2 rounded-xl text-center"
                    >
                        <button>Change Info</button>
                    </div>
                    <div 
                        @click="showChangePasswordPanel = true"
                        class="text-green-400 hover:text-stone-100 border border-green-400 hover:bg-green-400 p-2 rounded-xl text-center"
                    >
                        <button>Change Password</button>
                    </div>
                </div>
            </div>
            <!-- <button @click="fetchFavorites()">fetch user</button> -->
        </div>

        <!-- User overview on the right -->
        <div class="lg:w-4/5 w-full flex flex-col"  style="height: 80vh;">
            <div class="h-1/2">
                <div class="font-medium h-1/6 p-5 flex items-center">
                    <p class="text-4xl flex-grow">
                        Favorite Shops
                    </p>
                    <button class="text-2xl">
                        <router-link to="/profile/favorite" title="See all" class="font-bold">
                            See all
                        </router-link>
                    </button>
                </div>
                <div class="h-5/6 overflow-x-auto flex items-center relative pb-3">
                    <!-- Shop Cards Container -->
                    <div class="px-4 h-full">
                        <div class="flex space-x-4 h-full text-2xl">
                            <!-- Loop through your shop cards -->
                            <div v-for="shop in userStore.favorites" :key="shop.name" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ shop.name }}
                            </div>
                            <div v-for="shop in userStore.favorites" :key="shop.name" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ shop.name }}
                            </div>
                            <div v-for="shop in userStore.favorites" :key="shop.name" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ shop.name }}
                            </div>
                            <div v-for="shop in userStore.favorites" :key="shop.name" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ shop.name }}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="h-1/2">
                <div class="font-medium h-1/6 p-5 flex items-center">
                    <p class="text-4xl flex-grow">
                        Cards
                    </p>
                    <button class="text-2xl">
                        <router-link to="/profile/cards" title="See all" class="font-bold">
                            See all
                        </router-link>
                    </button>
                </div>
                <div class="h-5/6 overflow-x-auto flex items-center relative pb-3">
                        <!-- Favorite Cards Container -->
                        <div class="px-4 h-full">
                        <div class="flex space-x-4 h-full text-2xl">
                            <!-- Loop through your shop cards -->
                            <div v-for="card in userStore.cards" :key="card.serviceName" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ card.serviceName }}
                            </div>
                            <div v-for="card in userStore.cards" :key="card.serviceName" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ card.serviceName }}
                            </div>
                            <div v-for="card in userStore.cards" :key="card.serviceName" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ card.serviceName }}
                            </div>
                            <div v-for="card in userStore.cards" :key="card.serviceName" class="flex-none w-64 bg-green-400 flex justify-center items-center rounded-lg">
                                {{ card.serviceName }}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Change User Information Box -->
        <div 
            v-if="showChangeInfoPanel"
            class="top-1/2 left-1/2 bg-green-100 xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl sm:h-1/3 h-2/5 -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
        >
            <div class="flex justify-end items-center pr-5">
                <button 
                    @click="handleHideChangeInfoPanel()"
                >
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-8 h-8">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                    </svg>
                </button>
            </div>
            <div class="text-3xl text-center pb-10">
                User Information
            </div>
            <div action="" class="block sm:hidden w-full px-10 pb-10">
                <table class="w-full text-xl block sm:hidden px-10 pb-10">
                    <tr>
                        <td>
                            <label for="username" class="block font-medium text-slate-600">Username</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input
                            type="text"
                            id="username"
                            name="username"
                            required
                            v-model=userInput.username
                            class="mt-1 p-2 block w-full border border-slate-300 rounded-md"
                            >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="email" class="block font-medium text-slate-600">Email</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input
                            type="email"
                            id="email"
                            name="email"
                            required
                            v-model=userInput.email
                            class="mt-1 p-2 block w-full border border-slate-300 rounded-md"
                            >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="password" class="block font-medium text-slate-600">Password</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input
                            type="password"
                            id="password"
                            name="password"
                            v-model=userInput.oldPassword
                            class="mt-1 p-2 block w-full border border-slate-300 rounded-md"
                            >
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="w-full flex justify-center py-3">
                                <button 
                                    @click="changeInfo()"
                                    class="border bg-green-300 hover:bg-green-500 hover:text-stone-100 py-1 px-4 rounded-lg"
                                >
                                    Submit
                                </button>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <div action="" class="hidden sm:block w-full px-10 pb-10">
                <table class="w-full text-xl">
                    <tr>
                        <td>
                            <label for="username" class="block font-medium text-slate-600">Username</label>
                        </td>
                        <td>
                            <input
                            type="text"
                            id="username"
                            name="username"
                            required
                            v-model=userInput.username
                            class="mt-1 p-2 block w-full border border-slate-300 rounded-md"
                            >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="email" class="block font-medium text-slate-600">Email</label>
                        </td>
                        <td>
                            <input
                            type="email"
                            id="email"
                            name="email"
                            required
                            v-model=userInput.email
                            class="mt-1 p-2 block w-full border border-slate-300 rounded-md"
                            >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="password" class="block font-medium text-slate-600">Password</label>
                        </td>
                        <td>
                            <input
                            type="password"
                            id="password"
                            name="password"
                            required
                            v-model=userInput.oldPassword
                            class="mt-1 p-2 block w-full border border-slate-300 rounded-md"
                            >
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="w-full flex justify-center py-3">
                                <button 
                                    @click="changeInfo()"
                                    class="border bg-green-300 hover:bg-green-500 hover:text-stone-100 py-1 px-4 rounded-lg"
                                >
                                    Submit
                                </button>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- Change Password Box -->
        <div 
            v-if="showChangePasswordPanel"
            class="top-1/2 left-1/2 bg-green-100 2xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl sm:h-1/3 h-2/5 -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
        >
            <div class="flex justify-end items-center pr-5">
                <button 
                    @click="handleHideChangePasswordPanel()"
                >
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-8 h-8">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                    </svg>
                </button>
            </div>
            <div class="text-3xl text-center pb-1">
                Change Password
            </div>
            <div action="" class="block sm:hidden w-full px-10 pb-10">
                <table class="w-full text-xl">
                    <tr>
                        <td>
                            <label for="old-password" class="block font-medium text-slate-600">Old password</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input
                            type="password"
                            id="old-password"
                            name="old-password"
                            placeholder="Old password"
                            v-model=userInput.oldPassword
                            class="mt-1 p-2 block w-full border border-slate-300 rounded-md"
                            >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="new-password" class="block font-medium text-slate-600">New password</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input
                            type="password"
                            id="new-password"
                            name="new-password"
                            placeholder="New password"
                            v-model=userInput.newPassword
                            class="mt-1 p-2 block w-full border border-slate-300 rounded-md"
                            >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="confirm-new-password" class="block font-medium text-slate-600">Confirm new password</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input
                            type="password"
                            id="confirm-new-password"
                            name="confirm-new-password"
                            placeholder="Confirm new password"
                            v-model=userInput.confirmNewPassword
                            class="mt-1 p-2 block w-full border border-slate-300 rounded-md"
                            >
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="w-full flex justify-center py-3">
                                <button 
                                    @click="changePassword()"
                                    class="border bg-green-300 hover:bg-green-500 hover:text-stone-100 py-1 px-4 rounded-lg"
                                >
                                    Submit
                                </button>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <div action="" class="hidden sm:block w-full px-10 pb-10">
                <table class="w-full text-xl">
                    <tr>
                        <td>
                            <label for="old-password" class="block font-medium text-slate-600">Old password</label>
                        </td>
                        <td>
                            <input
                            type="password"
                            id="old-password"
                            name="old-password"
                            placeholder="Old password"
                            v-model=userInput.oldPassword
                            class="mt-1 p-2 block w-full border border-slate-300 rounded-md"
                            >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="new-password" class="block font-medium text-slate-600">New password</label>
                        </td>
                        <td>
                            <input
                            type="password"
                            id="new-password"
                            name="new-password"
                            placeholder="New password"
                            v-model=userInput.newPassword
                            class="mt-1 p-2 block w-full border border-slate-300 rounded-md"
                            >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="confirm-new-password" class="block font-medium text-slate-600">Confirm new password</label>
                        </td>
                        <td>
                            <input
                            type="password"
                            id="confirm-new-password"
                            name="confirm-new-password"
                            placeholder="Confirm new password"
                            v-model=userInput.confirmNewPassword
                            class="mt-1 p-2 block w-full border border-slate-300 rounded-md"
                            >
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="w-full flex justify-center py-3">
                                <button 
                                    @click="changePassword()"
                                    class="border bg-green-300 hover:bg-green-500 hover:text-stone-100 py-1 px-4 rounded-lg"
                                >
                                    Submit
                                </button>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <div 
            v-if="showAlert"
            class="bg-red-500 text-stone-100 text-xl font-medium flex justify-center fixed left-1/2 p-3 rounded-lg"
        >
            {{ alertInfo }}
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/store/userStore'
import http from '@/services/httpService'
import { login, logout } from '@/services/authService'
import { ref } from 'vue'

const userStore = useUserStore()
</script>

<script>
export default {
    data() {
        return {
            showChangeInfoPanel: false,
            showChangePasswordPanel: false,
            userInput: {
                username: ref(null),
                email: ref(null),
                oldPassword: ref(null),
                newPassword: ref(null),
                confirmNewPassword: ref(null),
            },
            showAlert: false,
            alertInfo: '',
        }
    },
    methods: {
        handleShowChangeInfoPanel() {
            const userStore = useUserStore()

            this.showChangeInfoPanel = true
            this.userInput.username = userStore.getUser.username
            this.userInput.email = userStore.getUser.email
        },
        handleHideChangeInfoPanel() {
            this.showChangeInfoPanel = false
            this.userInput.username = null
            this.userInput.email = null
            this.userInput.oldPassword = null
        },
        handleHideChangePasswordPanel() {
            this.showChangePasswordPanel = false
            this.userInput.oldPassword = null
            this.userInput.newPassword = null
            this.userInput.confirmNewPassword = null
        },
        async changeInfo() {
            try {
                const userStore = useUserStore()
                const userId = userStore.getUser.id
                const oldUsername = userStore.getUser.username
                const newUsername = this.userInput.username
                const newEmail = this.userInput.email
                const confirmPassword = this.userInput.oldPassword

                const passwordMatched = await http.post(`api/auth/check-password`, { "username": oldUsername, "password": confirmPassword })

                if (passwordMatched.data == true) {
                    await http.post(`/api/users/update/${userId}`, { "username": newUsername, "email": newEmail })
                    await login(newUsername, confirmPassword)
                    this.handleHideChangeInfoPanel()
                } else {
                    this.showAlert = true
                    this.alertInfo = 'Password incorrect!'
                    this.userInput.oldPassword = null

                    setTimeout(() => {
                    this.showAlert = false;
                    }, 2000);
                }
            } catch {
                throw error;
            }
        },
        async changePassword() {
            try {
                const userStore = useUserStore()
                const userId = userStore.getUser.id
                const oldUsername = userStore.getUser.username
                const inputOldPassword = this.userInput.oldPassword
                const inputNewPassword = this.userInput.newPassword
                const inputConfirmNewPassword = this.userInput.confirmNewPassword

                if (inputOldPassword && inputNewPassword && inputConfirmNewPassword) {
                    if (inputNewPassword == inputConfirmNewPassword) {
                        const passwordMatched = await http.post(`api/auth/check-password`, { "username": oldUsername, "password": inputOldPassword })

                        console.log(passwordMatched.data)
                        if (passwordMatched.data == true) {
                            await http.post(`/api/users/change-password/${userId}`, { "oldPassword": inputOldPassword, "newPassword": inputNewPassword })
                            logout()
                            this.handleHideChangePasswordPanel()
                            this.$router.push({ name: 'loginPageRoute' });
                        } else {
                            this.showAlert = true
                            this.alertInfo = 'Old password incorrect!'

                            this.userInput.oldPassword = null
                            this.userInput.newPassword = null
                            this.userInput.confirmNewPassword = null

                            setTimeout(() => {
                            this.showAlert = false;
                            }, 2000);
                        }
                    } else {
                        this.showAlert = true
                        this.alertInfo = 'New passwords are not matched!'

                        this.userInput.oldPassword = null
                        this.userInput.newPassword = null
                        this.userInput.confirmNewPassword = null

                        setTimeout(() => {
                        this.showAlert = false;
                        }, 3000);
                    }
                } else {
                    this.showAlert = true
                    this.alertInfo = 'Please input all fields!'

                    this.userInput.oldPassword = null
                    this.userInput.newPassword = null
                    this.userInput.confirmNewPassword = null

                    setTimeout(() => {
                    this.showAlert = false;
                    }, 2000);
                }
            } catch {
                throw error;
            }
        },
    },
    mounted() {
    },
    computed() {
    },
}
</script>