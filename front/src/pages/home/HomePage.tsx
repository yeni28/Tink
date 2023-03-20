import { useState } from 'react'

import { molecules } from '@/components/molecules'

import '@/styles/HomePage.css'

function App() {
  return (
    <div className="App">
      <molecules.CardSm
        isLiked={false}
        title={'White Mountains Light Even Longer Hello World!'}
        userName={'Midori Hirose'}
        userImgUrl={
          'https://i.natgeofe.com/k/ad9b542e-c4a0-4d0b-9147-da17121b4c98/MOmeow1_4x3.png'
        }
      />
    </div>
  )
}

export default App
