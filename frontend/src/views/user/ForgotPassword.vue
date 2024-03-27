<template>
    <div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
      <div class="sm:mx-auto sm:w-full sm:max-w-sm">
        <p class="mt-4 text-left font-bold text-orange-300">Please enter email</p>
      </div>
      <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <form class="space-y-6" @submit.prevent="handleCheckUser()">
          <div>
            <label for="email" class="block text-sm font-medium leading-6 text-balance">Email</label>
            <div class="mt-2">
              <input v-model="email" type="email" class="px-4 block w-full rounded-md border-0 bg-white/5 py-1.5 text-gray-600 shadow-sm ring-1 ring-inset ring-black-500 focus:ring-2 focus:ring-inset focus:ring-balck sm:text-sm sm:leading-6">
            </div>
          </div>
          <div>
            <button type="submit" class="flex w-full justify-center rounded-md bg-gray-950 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-gray-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-red-500">Submit</button>
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
            email: null,
        };
    },
    methods: {
        async handleCheckUser() {
            console.log("click")
          try {
            const response = await http.post('api/auth/forgot-password', { email: this.email });
            if(response.status === 200){
                
            }else{
                console.log("something went wrong");
            }
          } catch (error) {
            console.error();
          }
        },
    },
    };
</script>