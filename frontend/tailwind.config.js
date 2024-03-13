/** @type {import('tailwindcss').Config} */
export default {
  content: [
    './src/**/*.{html,js,ts,vue}',
  ],
  theme: {
    extend: {
      colors: {
        'custom-dark': '#16171d',
      },
      aspectRatio: {
        '9/16': '9 / 16', // Adding a 9:16 aspect ratio
      },
    },
  },
  plugins: [
    require('@tailwindcss/aspect-ratio'),
  ],
}

