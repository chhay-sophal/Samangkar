<template>
    <div>
      <section class="py-8">
        <div class="container mx-auto">
            <div>
                <h3 class="text-3xl font-bold uppercase">F O R E V E R</h3>
            </div>
            <div class="relative flex flex-col overflow-hidden bg-gray-50 py-6 sm:py-8">
              <div class="w-full items-center mx-auto max-w-screen-lg">
                <div class="group grid w-full grid-cols-2">
                  <div>
                    <div class="pr-12">
                      <p class="peer mb-6 text-gray-800">
                        A ecommerce website was found by 4 young talent student from Cambodia in 2023. The website began its construction in the mid of 2023 and fully finished on June. Forever is considered to be the first website in Cambodia that provivde many choice of wedding planning to user which had made many cambodian grooms and bride more convenience so far
                      </p>
                      <h3 class="mb-4 font-bold uppercase">Our vision</h3>
                      <ul role="list" class="marker:text-sky-400 list-disc pl-5 space-y-3 text-slate-500">
                        <li>A go-to destination for couples embarking on their journey</li>
                        <li>A platform that guides couples through every step of the wedding</li>
                      </ul>
                    </div>
                  </div>
                  <div class="pl-16 relative flex items-end flex-col before:block before:absolute before:h-1/6 before:w-4 before:bg-gray-800 before:bottom-0 before:left-0 before:rounded-lg  before:transition-all group-hover:before:bg-orange-300 overflow-hidden">
                    <div class="absolute top-0 left-0 bg-gray-800 w-4/6 px-12 py-14 flex flex-col justify-center rounded-xl group-hover:bg-gray-700 transition-all ">
                      <span class="block mb-10 font-bold text-orange-300 group-hover:text-orange-300">HERE WE ARE</span>
                      <h2 class="text-white font-bold text-3xl">
                        BIG DAY BEST, LOVE LAST FOREVER
                      </h2>
                    </div>
                    <a class="font-bold text-sm flex mt-2 mb-8 items-center gap-2" href="">
                      <span>
                        <router-link to="/login" title="Contact">SIGN UP</router-link>
                      </span>
                      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M13.5 4.5 21 12m0 0-7.5 7.5M21 12H3" />
                      </svg>
                    </a>
                    <div class="rounded-xl overflow-hidden">
                      <img src="https://foxandloon.com/wp-content/uploads/2023/02/The-Whim-Wedding-01280.jpg" alt="">
                    </div>
                  </div>
                </div>
              </div>
            </div>
        </div>
      </section>
      <section class="page-section py-8">
        <div class="container">
          <div>
              <h3 class="text-xl font-bold uppercase px-4">Our Team</h3>
          </div>
          <div class="py-4 bg-white">  
              <div class="container m-auto px-6 text-gray-500 md:px-12 xl:px-0">
                  <div class="mx-auto grid gap-6 md:w-3/4 lg:w-full lg:grid-cols-3">                     
                    <div class="bg-gray-800 rounded-2xl shadow-xl px-8 py-12 sm:px-12 lg:px-8" v-for="(member, index) in team"
                    :key="index">
                        <div class="xl:mb-12 sm:mb-5 space-y-4">
                            <h3 class="text-2xl font-semibold text-orange-300">{{ member.name }}</h3>
                        </div>
                        <div class="w-full xl:h-96 lg:h-72 md:h-52 sm:h-32 h-80 overflow-hidden">
                          <img
                          class="card-img-top card-img-blk"
                          :src="member.image"
                          :alt="member.name"
                        />   
                        </div>           
                    </div>                       
                  </div>
              </div>
          </div>
        </div>
      </section>
      <section class="page-section py-8">
        <div class="container mx-auto">
          <div class="tex-left">
            <h3 class="text-xl font-bold uppercase px-4">Where we locate</h3>
          </div>
          <div class="flex flex-col md:flex-row mt-8">
            <div class="md:w-2/3 px-4">
              <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d22519.791741260506!2d104.91004270871878!3d11.57221249496199!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3109513dc76a6be3%3A0x9c010ee85ab525bb!2sPhnom%20Penh!5e0!3m2!1sen!2skh!4v1711335557043!5m2!1sen!2skh" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
            <div class="md:w-1/3 px-4 mt-8 md:mt-0">
                <div class="flex justify-center items-center mb-4">
                    <h2 class="text-gray-600 ">Welcome to our office! Nestled in the heart of Pnhom Penh, our vibrant workspace combines modern amenities with a touch of warmth and professionalism. </h2>
                </div>
                <div class="flex justify-center items-center mb-4">
                    <h2 class="text-gray-600 ">Our office is a hub of creativity, collaboration, and innovation. Step inside to experience a dynamic environment where ideas flourish, connections thrive, and success stories are written. Whether you're a client, partner, or visitor, we look forward to welcoming you to our inspiring space </h2>
                </div>
                <div class="flex items-start">
                  <a class="font-bold text-2xl flex mt-2 mb-8 items-center gap-2 text-orange-300" href="">
                    <span>
                      <router-link to="/contact" title="Contact">Contact Us</router-link>
                    </span>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                      <path stroke-linecap="round" stroke-linejoin="round" d="M13.5 4.5 21 12m0 0-7.5 7.5M21 12H3" />
                    </svg>
                  </a>                
                </div>
            </div>
          </div>
        </div>
      </section>
      <section class="page-section py-8">
        <div class="relative z-20 sm:z-auto">
          <div class="tex-left py-8">
            <h3 class="text-xl font-bold uppercase px-4">What we have made so far</h3>
          </div>
          <div class="p-4">  
            <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-2 gap-4">
              <div class="backdrop-blur-sm bg-white/20 p-6 rounded-md shadow-sm cursor-pointer border-2 border-gray-400 hover:border-gray-600 hover:border-2 transition-colors duration-300">
                <h2 class="text-xl font-semibold mb-4 text-gray-800">Shop Register</h2>
                <p class="mt-6 flex items-baseline gap-x-1">
                  <span class="text-4xl font-bold tracking-tight text-orange-400">{{ totalShop }}</span>
                </p>                
              </div>
              <div class="backdrop-blur-sm bg-white/20 p-6 rounded-md shadow-sm cursor-pointer border-2 border-gray-400 hover:border-gray-600 hover:border-2 transition-colors duration-300">
                <h2 class="text-xl font-semibold mb-4 text-gray-800">User Satification</h2>
                <p class="mt-6 flex items-baseline gap-x-1">
                  <span class="text-4xl font-bold tracking-tight text-orange-400">{{ totalReview }}</span>
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
</template>

<script>
  import http from '@/services/httpService';
  export default {
    data() {
      return {
        team: [
          { name: 'Ni det', image: '/Nidate.JPG' },
          { name: 'Sophal', image: '/Sophal.png' },
          { name: 'Kimheak', image: '/Kimheak.JPG' }
        ],
        totalShop: 0,
        totalReview: 0
      }
    },
    created() {
    this.fetchTotalShop(); 
    this.fetchTotalReview();
    },
    methods:{
      async fetchTotalShop() {
        try {
          const response = await http.get(`api/users/USR01`);
          if(response.status === 200){
            this.totalShop = response.data 
          }else{
            console.log('something went wrong');
          }
        } catch (error) {
          console.error(error.message);
        }
      },
      async fetchTotalReview() {
        try {
          const response = await http.get(`api/shops/S01`);
          if(response.status === 200){
            this.totalReview = response.data
          }else{
            console.log('something went wrong');
          }
        } catch (error) {
          console.error(error.message);
        }
      }
    }
  }
</script>
 

  