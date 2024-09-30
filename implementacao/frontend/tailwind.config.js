/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: ["class"],
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: "#3b82f6", // Azul (mantido)
        secondary: "#6b7280", // Cinza (mantido)
        danger: "#ef4444", // Vermelho (mantido)
      },
      fontFamily: {
        sans: ["Inter", "sans-serif"], // Fonte mantida
      },
    },
  },
  plugins: [],
};
