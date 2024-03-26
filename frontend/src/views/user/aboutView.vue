<template>
    <div>
      <!-- Best decoration -->
      <section class="bg-gray-100 py-8">
        <div class="container mx-auto px-4">
            <div class="text-center">
                <h3 class="text-3xl font-bold uppercase">F O R E V E R</h3>
            </div>
            <!-- <div class="flex flex-col md:flex-row mt-8">
                <div class="md:w-1/2 p-4">
                  <p class="mb-4">A ecommerce website was founded by 4 young talented students from Cambodia in 2023. The website began its construction in the mid of 2023 and was fully finished in June.</p>
                  <p class="mb-4">Forever is considered to be the first website in Cambodia that provides many choices of wedding planning to users, which has made many Cambodian grooms and brides more convenient so far.</p>
                  <img src="https://qodeinteractive.com/wp-content/uploads/2023/04/Castella_mala_grafika_img.png" alt="" class="w-full rounded-lg">
                </div>
                <div class="md:w-1/2 px-4 mt-8 md:mt-0">
                  <img src="https://th.bing.com/th/id/R.861abc25f468a0d3d8f167d3d9f474ea?rik=XcRf6xkD2a8DxA&pid=ImgRaw&r=0" alt="" class="w-full rounded-lg">
                </div>
            </div> -->
            <div class="relative flex min-h-screen flex-col justify-center overflow-hidden bg-gray-50 py-6 sm:py-12">
              <div class="w-full items-center mx-auto max-w-screen-lg">
                <div class="group grid w-full grid-cols-2">
                  <div>
                    <div class="pr-12">
                      <p class="peer mb-6 text-gray-400">
                        Simply Air Conditioning London are fixed system heating and air conditioning installation specialists. Because we’ve tested all heating and air conditioning unit manufacturers before using them there are no hidden surprises for our customers. We only supply air conditioning units from the leading manufacturers so any system we fit will be of the highest quality. We also thoroughly test every type of unit before supplying..
                      </p>
                      <p class="mb-6 text-gray-400">
                      We also provide tailored Air Conditioning installation packages. The Air Conditioning systems we install are all inverter driven therefor are energy efficient, stylish & silent both providing cooling and heating. At Zero Degree AC Limited we only use the best brands.
                      </p>
                      <h3 class="mb-4 font-semibold text-xl text-gray-400">Conditioning installation packages</h3>
                      <ul role="list" class="marker:text-sky-400 list-disc pl-5 space-y-3 text-slate-500">
                        <li>5 cups chopped Porcini mushrooms</li>
                        <li>1/2 cup of olive oil</li>
                        <li>3lb of celery</li>
                      </ul>
                    </div>
                  </div>
                  <div class="pl-16 relative flex items-end flex-col before:block before:absolute before:h-1/6 before:w-4 before:bg-blue-500 before:bottom-0 before:left-0 before:rounded-lg  before:transition-all group-hover:before:bg-orange-300 overflow-hidden">
                    <div class="absolute top-0 left-0 bg-dark w-4/6 px-12 py-14 flex flex-col justify-center rounded-xl group-hover:bg-sky-600 transition-all ">
                      <span class="block mb-10 font-bold group-hover:text-orange-300">HERE WE ARE</span>
                      <h2 class="text-white font-bold text-3xl">
                      What started as a tiny team mostly dedicated to Air Quality has grown.
                      </h2>
                    </div>
                    <a class="font-bold text-sm flex mt-2 mb-8 items-center gap-2" href="">
                      <span>MORE ABOUT US</span>
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
      <section class="page-section-pr bg-dark">
        <div class="container p-4">
          <div class="row d-flex flex-column">
            <div class="bg-dark">
              <h5 class="p-4 text-light text-center">"BIG DAY BEST, LOVE LAST FOREVER"</h5>
              <p class="text-light p-4">FORVER Will make your big day more fantastic than ever,  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat</p>
            </div>
          </div>
        </div>
      </section>
      <!-- our team section -->
      <section class="page-section bg-light" id="portfolio">
        <div class="container">
          <div class="text-center">
              <h3 class="section-heading p-4">Our team</h3>
          </div>
          <div class="grid grid-cols-3 gap-4">
            <div
                class="col-md-3 col-sm-6 mb-4"
                v-for="(member, index) in team"
                :key="index"
            >
                <div class="card-body p-2" style="width: 100%">
                    <div class="flex justify-center items-center">
                        <a href="#">
                            <img
                                class="card-img-top card-img-blk"
                                :src="member.image"
                                :alt="member.name"
                            />
                        </a>
                    </div>
                    <div class="card-body p-2">
                      <div class="row">
                        <div class="flex justify-center items-center">
                          <a href="#"><h5 class="card-title">{{ member.name }}</h5></a>
                        </div>
                      </div>
                      <div class="flex justify-center items-center">
                        <div class="grid grid-cols-3"> 
                            <div class="col-span-1"> 
                              icon here 
                            </div>
                            <div class="col-span-2"> 
                                <a href="#"><h5 class="card-title">{{ member.email }}</h5></a>
                            </div>
                        </div>
                      </div>
                    </div>
                </div>
            </div>
          </div>
      </div>
      </section>
      <!-- Our Office -->
      <section class="page-section" id="portfolio">
        <div class="container mx-auto">
          <div class="tex-left">
            <h3 class="section-heading px-4">Our Head Office</h3>
          </div>
          <div class="flex flex-col md:flex-row mt-8">
            <div class="md:w-2/3 px-4">
              <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d22519.791741260506!2d104.91004270871878!3d11.57221249496199!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3109513dc76a6be3%3A0x9c010ee85ab525bb!2sPhnom%20Penh!5e0!3m2!1sen!2skh!4v1711335557043!5m2!1sen!2skh" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
            <div class="md:w-1/3 px-4 mt-8 md:mt-0">
                <div class="flex flex-col justify-center items-center">
                    <div class="mb-4">
                        <h5 class="section-subheading text-muted ">Welcome to our office! Nestled in the heart of Pnhom Penh, our vibrant workspace combines modern amenities with a touch of warmth and professionalism. Our office is a hub of creativity, collaboration, and innovation. Step inside to experience a dynamic environment where ideas flourish, connections thrive, and success stories are written. Whether you're a client, partner, or visitor, we look forward to welcoming you to our inspiring space</h5>
                    </div>
                </div>
            </div>
          </div>
        </div>
      </section>
    </div>
</template>
  
<script>
  export default {
    data() {
      return {
        team: [
          { name: 'Ni det', image: 'https://static.vecteezy.com/system/resources/thumbnails/008/442/086/small_2x/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg', email : 'nidet@samagkar.com' },
          { name: 'Sophal', image: 'https://static.vecteezy.com/system/resources/thumbnails/008/442/086/small_2x/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg',email : 'sophal@samagkar.com'  },
          { name: 'Kimheak', image: 'https://static.vecteezy.com/system/resources/thumbnails/008/442/086/small_2x/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg',email : 'kimheak@samagkar.com'  }
        ]
      }
    },
  }
</script>
 
<style scoped>
  .page{
    display: flex;
    justify-content: center;
  align-items: center;
  }
  .page-section-pr {
    padding: 50px 0;
    justify-content: center;
    align-items: center;
  }

  .page-section-pr h3 {
    font-size: 36px;
    color: #333;
    margin-bottom: 20px;
  }

  .page-section-pr p {
    font-size: 16px;
    color: #666;
  }

  .page-section-pr img {
    max-width: 100%;
    height: auto;
  }

  .page-section-pr.bg-dark {
    background-color: #343a40;
    color: #fff;
  }

  .page-section-pr.bg-dark h5 {
    font-size: 24px;
    margin-bottom: 20px;
  }

  .page-section-pr.bg-dark p {
    font-size: 16px;
    color: #ccc;
  }

  /* Styles for Our Team Section */
  .page-section.bg-light {
    background-color: #f8f9fa;
    padding: 50px 0;
  }

  .page-section.bg-light h3 {
    font-size: 36px;
    color: #333;
    margin-bottom: 20px;
  }

  .page-section.bg-light .card-body {
    padding: 0;
    justify-content: center;
    align-items: center;

  }

  .page-section.bg-light .card-img-top {
    max-width: 100%;
    height: 200px;
    justify-content: center;
    align-items: center;
  }

  .page-section.bg-light .card-title {
    font-size: 18px;
    color: #333;
  }

  .page-section {
    padding: 50px 0;
  }

  .page-section h3 {
    font-size: 36px;
    color: #333;
    margin-bottom: 20px;
  }

  .page-section .section-subheading {
    font-size: 16px;
    color: #666;
  }

  .page-section .card-img-top {
    max-width: 100%;
    height: 400;
    object-fit: cover;
    object-position: center; 
  }

  .page-section .fa-lg {
    font-size: 24px;
    color: #333;
  }

  .page-section a {
    color: #333;
    text-decoration: none;
  }

  .page-section a:hover {
    text-decoration: underline;
  }
</style>
  