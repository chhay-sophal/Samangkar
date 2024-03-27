<template>
  <div>
    <nav>
      <div class="sidebaricon" @click="toggleSidebar">
        <i class="fas fa-bars"></i>
      </div>

      <div class="sidebar flex flex-col" :class="{ 'active': isSidebarActive }" id="sidebar">
        <div class="sidebar-header relative">
          <div class="back-icon" @click="hideSidebar">
            <i class="fas fa-arrow-left"></i>
          </div>
          <router-link to="/" class="absolute flex items-start">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="m2.25 12 8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25" />
            </svg>
          </router-link>
          <h3><i class="fas fa-chart-line"></i> Dashboard </h3>
        </div>
        <div class="p-5 flex justify-center items-center">
            <div class="dark:bg-slate-600 bg-gray-100 overflow-hidden flex items-center justify-center rounded-full w-20 h-20">
                <img v-if="user.profile" :src="getUserImageUrl(user.profile)" alt="">
                <svg v-else xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-full h-full">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
                </svg>
            </div>
            <div class="grow flex items-center justify-center font-bold text-xl overflow-visible">{{ user.username }}</div>
        </div>
        <hr class="pb-3"/>
        <ul class="grow flex flex-col w-full text-xl font-medium">
          <router-link v-for="url in urls" :to="url.path" class="px-5 py-3 hover:bg-slate-500">{{ url.name }}</router-link>
        </ul>
        <div class="flex flex-col text-xl font-medium">
          <div class="flex w-full px-5 py-3 hover:bg-green-600">
            <button @click="showChangePasswordPanel = true" class="grow text-left">
              Change Password
            </button>
          </div>
          <div class="flex w-full px-5 py-3 hover:bg-green-600">
            <button @click="handleShowChangeInfoPanel()" class="grow text-left">
              Change Info
            </button>
          </div>
          <div class="flex w-full px-5 py-3 hover:bg-green-600">
            <button @click="openFileInput()" class="grow text-left">
              <input type="file" ref="fileInput" style="display: none" @change="handleFileChange">
              <button>Change Profile</button>
            </button>
          </div>
          <div class="flex w-full px-5 py-3 hover:bg-red-600">
            <RouterLink class="grow text-left" to="/logout" title="Logout">
              Log out
            </RouterLink>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 9V5.25A2.25 2.25 0 0 0 13.5 3h-6a2.25 2.25 0 0 0-2.25 2.25v13.5A2.25 2.25 0 0 0 7.5 21h6a2.25 2.25 0 0 0 2.25-2.25V15m3 0 3-3m0 0-3-3m3 3H9" />
            </svg>
          </div>
        </div>
      </div>
    </nav>

    <!-- Choose profile picture -->
    <div
        v-if="selectedImage"
        class="top-1/2 left-1/2 dark:bg-stone-700 xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl sm:h-1/2 h-2/5 -translate-x-1/2 -translate-y-1/2 fixed flex flex-col gap-5 justify-center items-center"
    >
        <div class="flex w-full justify-end items-center pr-5 dark:text-stone-600">
            <button 
                @click="this.selectedImage = null"
            >
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-8 h-8">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                </svg>
            </button>
        </div>
        <div class="w-3/4 rounded-md overflow-hidden">
            <img class="w-full" :src="selectedImage" alt="Profile Picture">
        </div>
        <button @click="uploadImage" class="w-1/4 rounded-md text-xl text-stone-800 hover:text-stone-200 bg-pink-300 hover:bg-pink-600">Save</button>
    </div>

    <!-- Change User Information Box -->
    <div 
        v-if="showChangeInfoPanel"
        class="top-1/2 left-1/2 bg-green-100 xl:w-1/3 lg:w-1/2 sm:w-3/4 w-5/6 rounded-xl sm:h-1/3 h-2/5 -translate-x-1/2 -translate-y-1/2 fixed flex flex-col justify-center"
    >
        <div class="flex justify-end items-center pr-5 dark:text-stone-600">
            <button 
                @click="handleHideChangeInfoPanel()"
            >
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-8 h-8">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                </svg>
            </button>
        </div>
        <div class="text-3xl text-center pb-10 dark:text-stone-600">
            User Information
        </div>
        <div action="" class="block sm:hidden w-full px-10 pb-10 dark:text-stone-600">
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
        <div action="" class="hidden sm:block w-full px-10 pb-10 dark:text-stone-600">
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
        <div class="flex justify-end items-center pr-5 dark:text-stone-600">
            <button 
                @click="handleHideChangePasswordPanel()"
            >
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2.5" stroke="currentColor" class="w-8 h-8">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                </svg>
            </button>
        </div>
        <div class="text-3xl text-center pb-1 dark:text-stone-600">
            Change Password
        </div>
        <div action="" class="block sm:hidden w-full px-10 pb-10 dark:text-stone-600">
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
        <div action="" class="hidden sm:block w-full px-10 pb-10 dark:text-stone-600">
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

    <!-- Alert box -->
    <div 
        v-if="showAlert"
        class="bg-red-500 text-stone-100 text-xl font-medium flex justify-center fixed left-1/2 p-3 rounded-lg"
    >
        {{ alertInfo }}
    </div>
  </div>
</template>

<script>
import { useUserStore } from '@/store/userStore'
import http from '@/services/httpService'
import { login, logout } from '@/services/authService'
import { ref, onMounted } from 'vue'
import ImageViewer from '@/components/ImageViewer.vue'
import { RouterLink } from 'vue-router'

export default {
  components: {
      ImageViewer,
  },
  data() {
    return {
      user: [],
      isSidebarActive: false,
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
      selectedImage: null,
      imageUrl: null,
      urls: [
        {
          name: 'Overview',
          path: '/admin'
        },
        {
          name: 'Users',
          path: '/admin/users'
        },
        {
          name: 'Shops',
          path: '/admin/shops'
        },
        {
          name: 'Services',
          path: '/admin/services'
        },
        {
          name: 'Packages',
          path: '/admin/packages'
        },
        {
          name: 'Reviews',
          path: '/admin/reviews'
        },
        {
          name: 'Requests',
          path: '/admin/request'
        },
      ]
    };
  },
  methods: {
    toggleSidebar() {
      this.isSidebarActive = !this.isSidebarActive;
    },
    hideSidebar() {
      this.isSidebarActive = false;
    },
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
                const userStore = useUserStore()
                this.user = userStore.user;
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
    openFileInput() {
        this.selectedImage = null
        this.$refs.fileInput.value = null
        this.$refs.fileInput.click()
    },
    handleFileChange(event) {
        const file = event.target.files[0];

        if (file) {
            const reader = new FileReader();

            reader.onload = (e) => {
                this.selectedImage = e.target.result;
            };

            reader.readAsDataURL(file);
        }
    },
    uploadImage() {
        const userStore = useUserStore()
        const user = userStore.getUser
        if (!this.selectedImage) {
            console.error('No image selected')
            return;
        }

        const formData = new FormData();
        formData.append('file', this.dataURItoBlob(this.selectedImage), 'image.jpg')

        http.post(`api/users/${user.id}/image/upload`, formData)
            .then(response => {
                userStore.clearUser()
                userStore.setUser(response.data)
                const imageData = userStore.getUser.profile

                this.getUserImageUrl(imageData)

                console.log('Image uploaded successfully:', response.data)
                this.selectedImage = null
                this.user = userStore.user;
            })
            .catch(error => {
                console.error('Error uploading image:', error)
            });
    },
    dataURItoBlob(dataURI) {
        // Convert a data URI to a Blob
        const byteString = atob(dataURI.split(',')[1]);
        const mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
        const arrayBuffer = new ArrayBuffer(byteString.length);
        const uint8Array = new Uint8Array(arrayBuffer);

        for (let i = 0; i < byteString.length; i++) {
            uint8Array[i] = byteString.charCodeAt(i);
        }

        return new Blob([arrayBuffer], { type: mimeString });
    },
    getUserImageUrl(imageData) {
        try {
            const byteString = atob(imageData);
            const mimeString = "image/jpg"; // Adjust mime type as needed
            const arrayBuffer = new ArrayBuffer(byteString.length);
            const intArray = new Uint8Array(arrayBuffer);
            for (let i = 0; i < byteString.length; i++) {
                intArray[i] = byteString.charCodeAt(i);
            }
            const blob = new Blob([arrayBuffer], { type: mimeString });
            return URL.createObjectURL(blob);
        } catch (error) {
            console.error('Error getting image data:', error)
        }
    },
  },
  mounted() {
    const userStore = useUserStore()
    this.user = userStore.user;

  }
};
</script>

<style scoped>
/* Your CSS styles go here */
.menu{
  padding-left:20px;
  padding-top: 10px;
}
.menu:hover{
  background-color: rgb(76, 70, 86);
  transition: ease;
}
.sidebaricon {
  display: none;
}

.sidebar {
  position: fixed;
  left: 0;
  top: 0;
  height: 100%;
  width: 240px; /* Default width for larger screens */
  background-color: #2f3542; /* Dark background color */
  color: #fff;
  transition: width 0.3s ease;
}

.back-icon {
  display: none;
}

/* Responsive styles */


.sidebar.active {
  left: 0; /* Move the sidebar into view when active */
}

.sidebar-header {
  padding: 20px;
  text-align: center;
  background-color: #242b38;
}

.sidebar-header h3 {
  margin: 0;
  font-size: 1.2rem;
}

.sidebar-list {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.sidebar-list li {
  padding: 10px 20px;
  border-bottom: 1px solid #39465e; /* Darker border color */
}

.sidebar-list li:last-child {
  border-bottom: none;
}

.sidebar-list li a {
  color: #fff;
  text-decoration: none;
  display: flex;
  align-items: center;
  transition: background-color 0.3s ease; /* Smooth hover effect */
}

.sidebar-list li a:hover {
  background-color: #39465e; /* Darker background color on hover */
}

.sidebar-list li a i {
  margin-right: 10px;
}

.sidebar-icon {
  position: fixed;
  top: 20px;
  right: 20px;
  cursor: pointer;
  z-index: 1000; /* Ensure it's above the sidebar */
}

/* Font Awesome icons */
.fa-bars {
  font-size: 24px;
}
</style>
