<template>
    <div>
      <section class="bg-gray-100 py-8">
        <div class="container mx-auto">
            <div>
                <h3 class="text-3xl font-bold uppercase">F O R E V E R</h3>
            </div>
            <div class="relative flex flex-col overflow-hidden bg-gray-50 py-6 sm:py-8">
              <div class="w-full items-center mx-auto max-w-screen-lg">
                <div class="group grid w-full grid-cols-2">
                  <div>
                    <div class="pr-12">
                      <p class="peer mb-6 text-gray-400">
                        A ecommerce website was found by 4 young talent student from Cambodia in 2023. The website began its construction in the mid of 2023 and fully finished on June. Forever is considered to be the first website in Cambodia that provivde many choice of wedding planning to user which had made many cambodian grooms and bride more convenience so far
                      </p>
                      <h3 class="mb-4 font-semibold text-xl text-gray-400">Our vision</h3>
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
                        <!-- FIX THIS ROUTE, WRONG REDIRECT  -->
                        <router-link to="/login" title="Contact">SIGN UP</router-link>
                      </span>
                      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M13.5 4.5 21 12m0 0-7.5 7.5M21 12H3" />
                      </svg>
                    </a>
                    <div class="rounded-xl overflow-hidden">
                      <img src="https://th.bing.com/th/id/R.861abc25f468a0d3d8f167d3d9f474ea?rik=XcRf6xkD2a8DxA&pid=ImgRaw&r=0" alt="">
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
                    <div class="bg-slate-200 rounded-2xl shadow-xl px-8 py-12 sm:px-12 lg:px-8" v-for="(member, index) in team"
                    :key="index">
                        <div class="mb-12 space-y-4">
                            <h3 class="text-2xl font-semibold text-orange-300">{{ member.name }}</h3>
                        </div>
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
                    <h5 class="section-subheading text-muted ">Welcome to our office! Nestled in the heart of Pnhom Penh, our vibrant workspace combines modern amenities with a touch of warmth and professionalism. Our office is a hub of creativity, collaboration, and innovation. Step inside to experience a dynamic environment where ideas flourish, connections thrive, and success stories are written. Whether you're a client, partner, or visitor, we look forward to welcoming you to our inspiring space</h5>
                </div>
                <div class="flex items-start">
                  <a class="font-bold text-sm flex mt-2 mb-8 items-center gap-2" href="">
                    <span>
                      <!-- FIX THIS ROUTE, WRONG REDIRECT  -->
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
          <div class="tex-left">
            <h3 class="text-xl font-bold uppercase px-4">Cilents Satifications</h3>
          </div>
          <div class="py-4 bg-white">  
              <div class="container m-auto px-6 text-gray-500 md:px-12 xl:px-0">
                <div class="mx-auto grid gap-6 md:w-3/4 lg:w-full lg:grid-cols-3">
                  <!-- FIX USER REVIEWS MIN 3  -->
                  <div class="flex">
                    <div class="flex max-w-md bg-white shadow-lg rounded-lg overflow-hidden">
                      <div class="w-2 bg-gray-800"></div>
                      <div class="flex items-center px-2 py-3">
                        <!-- also Fetch user image if possible -->
                        <img class="w-12 h-12 object-cover rounded-full" src="">
                        <div class="mx-3">
                          <h2 class="text-xl font-semibold text-gray-800"></h2>
                          <p class="text-gray-600">UserName <a href="#" class="text-blue-500">user review here</a>.</p>
                        </div>
                      </div>
                    </div>
                  </div>
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
        // dak pic us png wherever pic u desired
        team: [
          { name: 'Ni det', image: '/Nidate.JPG' },
          { name: 'Sophal', image: '/Sophal.png' },
          { name: 'Kimheak', image: '/Kimheak.JPG' }
        ]
      }
    },
    created() {
    this.fetchReviews(); 
    },
    methods:{
      async fetchReviews() {
        try {
          const response = await http.get(`api/users/reviews/all`);
          console.log(response);
        } catch (error) {
          console.error(error.message);
        }
      },
    }
  }
</script>
 

  