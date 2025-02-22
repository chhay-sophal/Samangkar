<template>
    <div>
      <!-- Your existing sections -->
      <section class="pb-20">
        <div class="flex w-full items-center pb-10 justify-center">
          <h2 class="text-5xl font-bold dark:text-stone-300">{{ pkg.name }}</h2>
        </div>
        <div class="items-center justify-center mb-20 w-full" style="height: 700px;">
          <ImageViewer class="flex object-fill" :imageData="pkg.image" />
        </div>
        <div v-if="pkg.trending" class="w-full flex flex-col items-center justify-center pb-20">
          <div  class="text-5xl font-bold text-yellow-500">
            Popular Now!
          </div>
        </div>
        <div class="w-full flex flex-col items-center justify-center pb-20">
          <div class="text-5xl font-bold pb-5">
            What is it?
          </div>
          <div class="text-4xl">
            {{ pkg.description }}
          </div>
        </div>
        <div class="w-full flex flex-col items-center justify-center pb-20">
          <div class="text-5xl font-bold pb-10">
            What does it include?
          </div>
          <div 
            class="w-full flex"
            v-for="(service, index) in pkg.services" 
            :key="index"
          >
            <div v-if="index % 2 != 0" class="w-1/2 h-96 flex">
              <div class="">
                <ImageViewer :imageData="service.image" />
              </div>
            </div>
            <div class="w-1/2 flex flex-col items-center justify-center gap-10">
              <div class="text-5xl font-semibold">{{ service.name }}</div>
              <div class="text-5xl font-semibold">${{ service.unitPrice }}</div>
              <router-link :to="`/shop/${service.shopId}/service/${service.id}/details`" >
                <div class="text-2xl">See details</div>
              </router-link>
            </div>
            <div v-if="index % 2 == 0" class="w-1/2 h-96 flex">
              <div class="">
                <ImageViewer :imageData="service.image" />
              </div>
            </div>
          </div>
        </div>
        <div class="w-full flex pb-20 gap-5">
          <div class="w-1/4"></div>
          <div class="w-1/4 text-5xl font-bold flex items-center justify-center pr-10">At just</div>
          <div class="w-1/4 font-bold flex items-center justify-center" style="font-size: 100px;">${{ pkg.price }}</div>
          <div class="w-1/4"></div>
        </div>
        <div class="w-full pb-20">
          <div class="text-5xl font-bold flex items-center justify-center">Provided by</div>
          <div class="font-bold flex items-center justify-center" style="font-size: 100px;">{{ pkg.shopName }}</div>
        </div>
        <div class="w-full flex">
          <button 
            v-if="pkgCarts?.some(card => card.pkg?.id === pkg.id)" 
            @click="routeToCartView()" 
            class="w-1/2 flex items-center justify-center"
          >
            <div class="h-20 w-1/2 flex items-center justify-center border-2 bg-green-500 text-stone-100">
              <div class="text-xl font-semibold">Added to Cart</div>
            </div>
          </button>
          <button 
            v-else 
            @click="addToCard(pkg.id)" 
            class="w-1/2 flex items-center justify-center"
          >
            <div class="h-20 w-1/2 flex items-center justify-center border-2 hover:bg-green-500 hover:text-stone-100">
              <div class="text-xl font-semibold">Add to Cart</div>
            </div>
          </button>
          <router-link class="w-1/2 flex items-center justify-center" :to="`/shop/${pkg.shopId}/details`">
            <div class="h-20 w-1/2 flex items-center justify-center border-2 hover:bg-blue-500 hover:text-stone-100">
              <div class="text-xl font-semibold">
                Contact Provider
              </div>
            </div>
          </router-link>
        </div>
      </section>
    </div>
</template>

<script>
import ImageViewer from '@/components/ImageViewer.vue';
import http from '@/services/httpService';
import { useUserStore } from "@/store/userStore";

export default {
  components: {
    ImageViewer,
  },
  data() {
    return {
      userId: null,
      pkg: {},
    };
  },
  methods: {
    async fetchPackageDetails(packageId) {
      try {
        const response = await http.get(`api/packages/get/${packageId}`);
        this.pkg = response.data;
        console.log(this.pkg);
      } catch (error) {
        console.log(error);
      }
    },
    async addToCard(id) {
      try {
        await http.post(`api/cards/add`, {
          "userId": this.userId,
          "serviceId": null,
          "packageId": id,
          "quantity": 1,
        });

        this.fetchUserCards();
        this.showAlert = true

        setTimeout(() => {
          this.showAlert = false;
        }, 2000);
      } catch (error) {
        console.log(error);
        if (!this.userId) {
          this.$router.push({ name: 'loginPageRoute' })
        }
      }
    },
  },
  mounted() {
    const userStore = useUserStore()
    this.userId = userStore.user.id;

    const pkgId = this.$route.params.packageId;
    this.fetchPackageDetails(pkgId);
  },
};
</script>

<style scoped>
.package-detail {
  padding: 50px 0;
}

.container {
  max-width: 800px;
  margin: 0 auto;
}

.sub-heading {
  text-align: center;
  font-size: 2em;
  margin-bottom: 30px;
}

.package-image img {
  width: 100%;
  height: auto;
  border-radius: 5px;
}

.package-info {
  padding: 0 20px;
}

.package-info h3 {
  font-size: 1.8em;
  margin-bottom: 10px;
}

.package-price {
  font-size: 1.5em;
  margin-bottom: 20px;
}

.package-info p {
  font-size: 1.2em;
  line-height: 1.6;
  margin-bottom: 30px;
}

.contact-button {
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  font-size: 1.2em;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.contact-button:hover {
  background-color: #0056b3;
}
</style>
