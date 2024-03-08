<template>
  <div>
    <Sidebar />
    <div class="main-content">
      <h1>DashBoard</h1>
      <h3 style="text-align: center; font-size: large;text-decoration:underline;">Table User</h3>      
      <!-- Table -->
      <table class="data-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in items" :key="index">
            <td>{{ item.id }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.email }}</td>
            <td>
              <button @click="editItem(index)" class="edit-btn">Edit</button>
              <button @click="deleteItem(index)" class="delete-btn">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>

      <!-- Button to add more items -->
      <button @click="addItem" class="add-btn">Add More</button>
    </div>
  </div>
</template>

<script>
import Sidebar from './../../components/sideBar.vue'; // Adjust the path as per your project structure

export default {
  name: 'HomePage',
  components: {
    Sidebar // Register the Sidebar component
  },
  data() {
    return {
      items: [
        { id: 1, name: 'John Doe', email: 'john@example.com' },
        { id: 2, name: 'Jane Doe', email: 'jane@example.com' }
      ]
    };
  },
  methods: {
    editItem(index) {
      // Logic to edit item
      console.log('Edit item:', this.items[index]);
    },
    deleteItem(index) {
      // Logic to delete item
      this.items.splice(index, 1);
    },
    addItem() {
      // Logic to add item
      const newItem = {
        id: this.items.length + 1,
        name: 'New Item',
        email: 'new@example.com'
      };
      this.items.push(newItem);
    }
  }
};
</script>

<style scoped>
.main-content {
  margin-left: 250px; /* Adjust based on the width of your sidebar */
  padding: 20px;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
}

.data-table th, .data-table td {
  padding: 8px;
  border-bottom: 1px solid #ddd;
  text-align: left;
}

.data-table th {
  background-color: #f2f2f2;
}

.edit-btn, .delete-btn, .add-btn {
  padding: 6px 12px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.edit-btn {
  background-color: #4CAF50;
  color: white;
  margin-right: 5px;
}

.delete-btn {
  background-color: #f44336;
  color: white;
  margin-right: 5px;
}

.add-btn {
  background-color: #008CBA;
  color: white;
}
</style>
