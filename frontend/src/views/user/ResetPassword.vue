<template>
    <div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
        <div class="flex flex-col justify-end">
            <h1 class="text-center text-4xl mb-5">Reset Password</h1>
        </div>
      <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <form class="space-y-6" @submit.prevent="handleResetPassword()">
            <div>
                <label for="New Password" class="block text-sm font-bold text-orange-300">Please enter new password</label>
                <div class="mt-2">
                <input v-model="newPassword" type="password" class="px-4 block w-full rounded-md border-0 bg-white/5 py-1.5 text-gray-600 shadow-sm ring-1 ring-inset ring-black-500 focus:ring-2 focus:ring-inset focus:ring-balck sm:text-sm sm:leading-6">
                </div>
            </div>
            <div>
                <label for="Confirm Password" class="block text-sm font-bold text-orange-300">Confirm Password</label>
                <div class="mt-2">
                <input v-model="newConfirmPassword" type="password" class="px-4 block w-full rounded-md border-0 bg-white/5 py-1.5 text-gray-600 shadow-sm ring-1 ring-inset ring-black-500 focus:ring-2 focus:ring-inset focus:ring-balck sm:text-sm sm:leading-6">
                </div>
            </div>
            <div v-if="OpenWarningAlert" class="flex text-sm font-bold text-white px-6 py-4 border-0 rounded relative mb-4 bg-red-400 fade-in-out">
                <p>
                {{ alertMsg }}
                </p>
            </div>
            <div>
                <button type="submit" class="flex w-full justify-center rounded-md bg-gray-950 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-gray-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-red-500">Reset</button>
            </div>
            <div v-if="OpenSuccessAlert" class="flex text-sm text-white px-6 py-4 border-0 rounded relative mb-4 bg-emerald-400 fade-in-out">
                <p>
                Password Reset successfully
                </p>
                <button class="pl-3 hover:text-green-500">
                <router-link to="/login" title="Register" class="font-bold">
                    <a>Log in</a>
                </router-link>
                </button>
            </div>
        </form>
      </div>
    </div>
</template>
<script>
    import http from '@/services/httpService';
    export default {
    data() {
        return {
            token: null,
            newPassword: null,
            newConfirmPassword: null,
            OpenSuccessAlert: false,
            OpenWarningAlert: false,
            alertMsg: ''
        };
    },
    methods: {
        async handleResetPassword() {
            if (this.newPassword.length > 8) {
                if (this.newPassword !== this.newConfirmPassword) {
                    this.OpenWarningAlert = true
                    this.alertMsg = 'Password does not match'
                } else {
                    // const token = this.$route.params.token;
                    // console.log(token)
                    const response = await http.post(`api/auth/reset-password?token=${this.token}&newPassword=${this.newPassword}`);

                    //make request 
                    // const respons = await 
                    if(response.status === 200){
                        this.OpenSuccessAlert = true
                        this.alertMsg = 'Changed password successfully'

                        setTimeout(() => {
                            this.OpenWarningAlert = false;
                        }, 2000);

                        this.$router.push({ name: 'profilePageRoute' })

                    }else{
                        this.OpenWarningAlert = true
                        this.alertMsg = 'something went wrong'

                        setTimeout(() => {
                            this.OpenWarningAlert = false;
                        }, 2000);
                    }
                }
                 
            }else{
                this.OpenWarningAlert = true
                this.alertMsg = 'Password must be atleast 8 characters'
            }
            
          
        }
    },
    // mounted() {
    //     // const token = this.$route.params.token;
    //     // console.log(token)
    // },
    created() {
        // Get the URL parameters
        const params = new URLSearchParams(window.location.search);
        // Extract the token value
        this.token = params.get('token');
        console.log(this.token)
    }
};
</script>