import { useState } from 'react'

import atoms from '@/components/atoms'
import { molecules } from '@/components/molecules'

import '@/styles/HomePage.css'

function App() {
  return (
    <div className="App">
      <atoms.ImageLg
        alt="imageLg"
        src="https://images.unsplash.com/photo-1679214523091-a9f9697ed10c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80"
      />
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

export default HomePage
