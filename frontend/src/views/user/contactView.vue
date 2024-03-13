<template>
  <div class="contact">
    <div class="contact-info">
      <h2>Contact Us</h2>
      <div class="team">
        <img
          src="https://i.fbcd.co/products/original/872706a08d201dc5e37c525ac9e2281a621b64a002d302cac603a9ba1b238fe7.jpg"
          alt="Team"
        />
        <div class="team-details">
          <p style="font-size: 1.2rem;">Forever</p>
          <p style="font-style:oblique;">Location: https://maps.app.goo.gl/k7aszcUPk5qhJC3g9</p>
          <p>Email: Forever@gmail.com</p>
        </div>
      </div>
    </div>
    <div class="contact-form">
      <h2>Get in Touch</h2>
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="name">Name:</label>
          <input type="text" id="name" v-model="formData.name" required />
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" id="email" v-model="formData.email" required />
        </div>
        <div class="form-group">
          <label for="message">Message:</label>
          <textarea id="message" v-model="formData.message" required></textarea>
        </div>
        <button type="submit">Send</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Contact",
  data() {
    return {
      formData: {
        name: "",
        email: "",
        message: "",
      },
    };
  },
  methods: {
    submitForm() {
      // Handle form submission logic here
      console.log("Form submitted:", this.formData);
      // Reset form data after submission
      this.formData = {
        name: "",
        email: "",
        message: "",
      };
    },
  },
};
</script>

<style scoped>
.contact {
  max-width: 1200px;
  margin: auto;
  padding: 20px;
}

.contact-info {
  margin-bottom: 40px;
}

.contact-info h2 {
  margin-bottom: 20px;
}

.team {
  display: flex;
  align-items: center;
}

.team img {
  width: 100px;
  height: auto;
  margin-right: 20px;
}

.team-details p {
  margin-bottom: 10px;
}

.contact-form {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 5px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="email"],
textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button[type="submit"] {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #0056b3;
}
</style>
