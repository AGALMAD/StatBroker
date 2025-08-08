/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        background: "#121212",
        surface: "#1E1E1E",
        primaryText: "#E0E0E0",
        secondaryText: "#B0B0B0",
        primary: "#4CAF50",
        successBright: "#00E676",
        secondaryBlue: "#03A9F4",
        accentOrange: "#FF9800",
        dangerBright: "#FF1A1A",
        error: "#F44336",
        successSoft: "#81C784",
      },
    },
  },
  plugins: [require("@tailwindcss/forms")],
};
