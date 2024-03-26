<template>
  <div class="contact">
    <div class="contact-info">
      <div class="relative z-20 sm:z-auto">
        <div class="mx-auto max-w-container px-4 pb-16 sm:px-6 lg:px-8">
          <div class="relative mx-auto grid max-w-[40rem] grid-cols-1 gap-8 lg:max-w-none lg:grid-cols-3">
            <div class="flex">
              <div class="p-0.5">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M7.5 7.5h-.75A2.25 2.25 0 004.5 9.75v7.5a2.25 2.25 0 002.25 2.25h7.5a2.25 2.25 0 002.25-2.25v-7.5a2.25 2.25 0 00-2.25-2.25h-.75m0-3l-3-3m0 0l-3 3m3-3v11.25m6-2.25h.75a2.25 2.25 0 012.25 2.25v7.5a2.25 2.25 0 01-2.25 2.25h-7.5a2.25 2.25 0 01-2.25-2.25v-.75" />
                  </svg>
              </div>
              <div class="ml-6">
                <h2 class="text-sm font-semibold leading-6 text-slate-900">Upload File</h2>
                <p class="mt-2 text-sm leading-6 text-slate-700">In the case of file upload, Browser reads the file, and for URL upload, it sends the URL to the server, returns HTML data, and then views it in the Output section.</p>
              </div>
            </div>
            <div class="flex">
              <div class="p-0.5">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                      <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 6.75h16.5M3.75 12H12m-8.25 5.25h16.5" />
                  </svg>
              </div>
              <div class="ml-6">
                <h2 class="text-sm font-semibold leading-6 text-slate-900">Enter Information</h2>
                <p class="mt-2 text-sm leading-6 text-slate-700"> Wrong widen drawn ample eat off doors money. Offending belonging promotion provision an be oh consulted ourselves it.</p>
              </div>
            </div>
            <div class="flex">
              <div class="p-0.5">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                      <path stroke-linecap="round" stroke-linejoin="round" d="M19.5 12c0-1.232-.046-2.453-.138-3.662a4.006 4.006 0 00-3.7-3.7 48.678 48.678 0 00-7.324 0 4.006 4.006 0 00-3.7 3.7c-.017.22-.032.441-.046.662M19.5 12l3-3m-3 3l-3-3m-12 3c0 1.232.046 2.453.138 3.662a4.006 4.006 0 003.7 3.7 48.656 48.656 0 007.324 0 4.006 4.006 0 003.7-3.7c.017-.22.032-.441.046-.662M4.5 12l3 3m-3-3l-3 3" />
                  </svg>
              </div>
              <div class="ml-6">
                <h2 class="text-sm font-semibold leading-6 text-slate-900">Fast Transfer</h2>
                <p class="mt-2 text-sm leading-6 text-slate-700">Lose away off why half led have near bed. At engage simple father of period others except. My giving do summer of though narrow marked at.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="contact-form">
      <div class="bg-gray-300 py-16 sm:py-24 lg:py-32">
        <div class="mx-auto grid max-w-7xl grid-cols-1 gap-10 px-6 lg:grid-cols-12 lg:gap-8 lg:px-8">
          <div class="max-w-xl text-3xl font-bold tracking-tight text-white sm:text-4xl lg:col-span-7">
            <h2 class="inline sm:block lg:inline xl:block">Are you a bussiness owner and want to collaborate with us ?</h2>
            <p class="inline sm:block lg:inline xl:block">Make an inquiry Now</p>
          </div>
          <form class="w-full max-w-md lg:col-span-5 lg:pt-2" @submit.prevent="submitEmail">
            <div class="flex gap-x-4">
              <label for="email-address" class="sr-only">Email address</label>
              <input name="email" type="email" v-model="email" required class="min-w-0 flex-auto rounded-md border-2 bg-gray-200 px-3.5 py-2 text-black shadow-sm ring-1 ring-inset ring-white/10 placeholder:text-black/75 focus:ring-2 focus:ring-inset focus:ring-white sm:text-sm sm:leading-6" placeholder="Enter your email">
              <button type="submit" class="flex-none rounded-md bg-gray-800 px-3.5 py-2.5 text-sm font-semibold text-zinc-50 shadow-sm hover:bg-gray-950 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-white">Subscribe</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import http from '@/services/httpService';
  export default {
    name: "Contact",
    data() {
      return {
        email: '',
      };
    },
    methods: {
      async submitEmail() {
        if(this.isValidEmail(this.email)){
          try {
            const response = await http.post('api/shops/mail', { email: this.email });
            console.log(response);
            if(response.status === 200){
              console.log('success')
              this.email=''
            }else{
              console.log('something went wrong');
            }
          } catch (error) {
            console.error('Failed to submit form data', error.message);
          }
        }else{
          console.log('invalid email');
          return false;
        }
      },
      isValidEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
      }
    }
  };
</script>


