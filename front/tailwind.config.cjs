/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
        mint: '#CBECE4',
        red: '#E78078',
        pink: '#EBC6CE',
        beige: '#FFF7EC',
        white: '#FFFFFF',
        warn: '#EB2B1D',
        line: '#DFDBD2',
        black: '#252525',
        link: '#7694FE',
        grey: '#767676',
      },
      fontFamily: {
        sans: ['Pretendard'],
        pop: ['Poppins'],
        cha: ['KCC-Chassam'],
      },
    },
  },
  plugins: [],
}
