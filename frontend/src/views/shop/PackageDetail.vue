<template>
  <div class="w-full flex items-center justify-center font-bold text-4xl">Package Details</div>
  <div class="package-detail">
    <div class="container">
      <div class="grid grid-cols-3">
        <div class="col-md-6 col-span-2">
          <div class="package-image">
            <div class=" w-96">
              <ImageViewer :imageData="pkg.image" />
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="flex flex-col gap-2">
            <div class="text-3xl font-bold">{{ pkg.name }}</div>
            <div class="text-xl"><span class="font-semibold">Descriptions:</span> {{ pkg.description }}</div>
            <div class="package-price"><span class="font-semibold">Price:</span> ${{ pkg.price }}</div>
            <p>{{ pkg.details }}</p>
            <router-link class="contact-button" :to="`/shop/${pkg.shop.id}/details`" v-if="pkg.shop">Contact Us</router-link>
          </div>
        </div>
      </div>
      <section class="packages">
        <div class="container">
          <div class="w-full flex items-center justify-center font-semibold text-3xl p-3">List of Services</div>
          <div class="grid grid-cols-3 gap-2">
            <div class="package-item" v-for="(service, index) in pkg.services" :key="index">
              <div class=""><ImageViewer :imageData="service.image" /></div>
              <div class="package-details">
                <h3>{{ service.name }}</h3>
                <div class="package-price">${{ service.unitPrice }}</div>
                <p>{{ service.description }}</p>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import ImageViewer from '@/components/ImageViewer.vue';
import http from '@/services/httpService';

export default {
  components: {
    ImageViewer,
  },
  data() {
    return {
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
  },
  mounted() {
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
