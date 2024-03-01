<template>
    <div class="flex-none w-auto bg-green-400 flex justify-center items-center rounded-lg h-96 relative">
        <div class="absolute top-2 right-2">
        <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="red"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            v-if="isFavorite"
            @click="toggleFavorite"
            class="w-6 h-6 cursor-pointer"
        >
            <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z"
            />
        </svg>
        <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            v-else
            @click="toggleFavorite"
            class="w-6 h-6 cursor-pointer"
        >
            <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z"
            />
        </svg>
        </div>
        {{ shop.name }}
    </div>
</template>

<script>
export default {
    props: {
        shop: Object, // Assuming you pass the shop object as a prop
        isFavorite: Boolean,
    },
    methods: {
        toggleFavorite() {
            // Implement logic to toggle the favorite status
            this.$emit('toggle-favorite', this.shop); // Emit an event to toggle favorite in the parent component
        },
    },
};
</script>