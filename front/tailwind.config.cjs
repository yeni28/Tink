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
      fontSize: {
        caption2: [
          '11px',
          { fontWeight: '400', lineHeight: '13px', letterSpacing: '0.07px' },
        ],
        'caption2-bold': [
          '11px',
          { fontWeight: '600', lineHeight: '13px', letterSpacing: '0.06px' },
        ],
        caption1: ['12px', { fontWeight: '400', lineHeight: '16px' }],
        'caption1-bold': ['12px', { fontWeight: '500', lineHeight: '16px' }],
        footnote: [
          '13px',
          { fontWeight: '400', lineHeight: '18px', letterSpacing: '-0.08px' },
        ],
        'footnote-bold': [
          '13px',
          { fontWeight: '600', lineHeight: '18px', letterSpacing: '-0.08px' },
        ],
        subheadline: [
          '15px',
          { fontWeight: '400', lineHeight: '20px', letterSpacing: '-0.24px' },
        ],
        'subheadline-bold': [
          '15px',
          { fontWeight: '600', lineHeight: '20px', letterSpacing: '-0.5px' },
        ],
        callout: [
          '16px',
          { fontWeight: '400', lineHeight: '21px', letterSpacing: '-0.32px' },
        ],
        'callout-bold': [
          '16px',
          { fontWeight: '600', lineHeight: '21px', letterSpacing: '-0.32px' },
        ],

        body: [
          '17px',
          { fontWeight: '400', lineHeight: '22px', letterSpacing: '-0.41px' },
        ],
        'body-bold': [
          '17px',
          { fontWeight: '600', lineHeight: '22px', letterSpacing: '-0.41px' },
        ],

        headline: [
          '17px',
          { fontWeight: '600', lineHeight: '22px', letterSpacing: '-0.41px' },
        ],
        'headline-bold': [
          '17px',
          { fontWeight: '600', lineHeight: '22px', letterSpacing: '-0.41px' },
        ],

        title3: [
          '20px',
          { fontWeight: '400', lineHeight: '25px', letterSpacing: '0.38px' },
        ],
        'title3-bold': [
          '20px',
          { fontWeight: '600', lineHeight: '25px', letterSpacing: '0.38px' },
        ],
        title2: [
          '22px',
          { fontWeight: '400', lineHeight: '28px', letterSpacing: '0.35px' },
        ],
        'title2-bold': [
          '22px',
          { fontWeight: '700', lineHeight: '28px', letterSpacing: '0.35px' },
        ],
        title1: [
          '28px',
          { fontWeight: '400', lineHeight: '34px', letterSpacing: '0.36px' },
        ],
        'title1-bold': [
          '28px',
          { fontWeight: '700', lineHeight: '34px', letterSpacing: '0.36px' },
        ],
        largetitle: [
          '34px',
          { fontWeight: '400', lineHeight: '41px', letterSpacing: '0.37px' },
        ],
        'largetitle-bold': [
          '34px',
          { fontWeight: '700', lineHeight: '41px', letterSpacing: '0.37px' },
        ],
        supertitle: [
          '48px',
          { fontWeight: '400', lineHeight: '57px', letterSpacing: '0.37 px' },
        ],
        'supertitle-bold': [
          '48px',
          { fontWeight: '700', lineHeight: '57px', letterSpacing: '0.37 px' },
        ],
      },
    },
  },
  plugins: [],
}
