<template>
    <div>
      <!-- Best decoration -->
      <section class="page-section-pr">
        <div class="container p-4">
          <div class="text-left">
            <h3 class="section-heading text-uppercase">F O R E V E R</h3>
          </div>
          <div class="row">
            <div class="col-md-4 p-4">
              <p>A ecommerce website was found by 4 young talent student from Cambodia in 2023. The website began its construction in the mid of 2023 and fully finished on June. </p>
              <p>Forever is considered to be the first website in Cambodia that provivde many choice of wedding planning to user which had made many cambodian grooms and bride more convenience so far.</p>
            </div>
            <div class="col-md-4 offset-md-2">
              <div class="card-body" >
                <img src="https://th.bing.com/th/id/R.861abc25f468a0d3d8f167d3d9f474ea?rik=XcRf6xkD2a8DxA&pid=ImgRaw&r=0" alt="" class="card-img">
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
          <h3 class="section-heading text-uppercase p-4">Our team</h3>
        </div>
        <div class="page">
          <div
            class="col-md-3 col-sm-6 mb-4"
            v-for="(member, index) in team"
            :key="index"
          >
            <div class="card-body p-2" style="width: 100%,">
              <a href="#"
                ><img
                  class="card-img-top card-img-blk"
                  :src="member.image"
                  :alt="member.name"
              /></a>
              <div class="card-body">
                <a href="#"
                  ><h5 class="card-title">{{ member.name }}</h5></a
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
      <!-- Our Office -->
      <section class="page-section" id="portfolio">
        <div class="container">
          <div class="row justify-content-center">
            <div class="row">
              <div class="tex-left py-2">
                <h3 class="section-heading text-uppercase py-2">Our Office</h3>
                <h3 class="section-subheading text-muted">Explore shop that has the most stunning decoration</h3>
              </div>
            </div>
            <div class="col-md-6 col-sm-6 mb-4">
              <div class="card-body p-2" style="width:70%;">
                <a href="#"><img class="card-img-top card-img-blk" src="/image.png" alt="Card image cap"></a>
              </div>
            </div>
            <div class="col-md-5 offset-md-1 col-sm-6 mb-4 d-flex flex-column">
              <div class="row align-self-start">
                <div class="tex-left py-2">
                  <h4 class="section-heading text-uppercase py-2">Get in touch with us</h4>
                  <h3 class="section-subheading text-muted">Explore shop that has the most stunning decoration</h3>
                </div>
                <div class="col-md-4 d-flex flex-column">
                  <span class="py-4"><i class="fa-solid fa-phone fa-lg"></i></span>
                  <span class="py-4"><i class="fas fa-envelope fa-lg"></i></span>
                  <span class="py-4"><i class="fab fa-tiktok fa-lg"></i></span>
                  <span class="py-4"><i class="fab fa-instagram fa-lg"></i></span>
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
          { name: 'Ni det', image: 'https://z-p3-scontent.fpnh5-3.fna.fbcdn.net/v/t39.30808-6/329052155_1850690681965274_1978295266539606826_n.jpg?stp=cp6_dst-jpg&_nc_cat=100&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeFCZ9fMg8AcjinFOAv6oHUGIVqTHl-Bpj4hWpMeX4GmPqMZnMi_zzHc1sc_Zd03ueI4DJVwxnxPlKbO5A08infW&_nc_ohc=dgQ0z2V8y2gAX8l-rcF&_nc_zt=23&_nc_ht=z-p3-scontent.fpnh5-3.fna&oh=00_AfDaRr-S3apW9wNg6oP998Z34GwuH4qzK3lrc2x_qtk2Mw&oe=65F566E3' },
          { name: 'Sophal', image: 'https://z-p3-scontent.fpnh5-3.fna.fbcdn.net/v/t39.30808-6/431014663_1406884703534782_7426384464290656983_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeFb8RO8QfNVn2etRyPpS_p7p0ol2HLIyR6nSiXYcsjJHkNAxFjRG9wUFAzujhudP7iQx0fpwVfbGpVmZ-bne6Rz&_nc_ohc=osULpzuBLtEAX8u-5jq&_nc_zt=23&_nc_ht=z-p3-scontent.fpnh5-3.fna&oh=00_AfDRRlhueEmsmryXe4EfY0YA165Qgh9KX56xo8B48cHo3A&oe=65F65416' },
          { name: 'Kimheak', image: 'https://z-p3-scontent.fpnh5-3.fna.fbcdn.net/v/t39.30808-6/347625404_1462685914540306_3404721810023646087_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeEaQHPj0yBMTDDFRQ8YqQ5uwvmJQ6Svsp3C-YlDpK-ynQjHJvAVgPZ370svZ-W1j_14yKFWEl9fs3fPHs5UVMLe&_nc_ohc=7wgtbA3FEXEAX83GKAi&_nc_zt=23&_nc_ht=z-p3-scontent.fpnh5-3.fna&oh=00_AfDDZDiXPCPye_DEjzjWcq5AXEr-aMZJ1G0H-_3xl-e6bA&oe=65F545BA' }
        ]
      }
    }
  }
  </script>
  
  
  <style scoped>
  .page{
    display: flex;
    justify-content: center;
  align-items: center;
  }
/* Styles for the About Section */
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

/* Styles for the Vision Section */
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
  /* margin-left: 20px; */
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

/* Styles for Our Office Section */
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
  object-position: center; /* Ensure the center of the image is visible */
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
  