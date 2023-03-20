import { useState } from 'react'

import atoms from '@/components/atoms'
import '@/styles/HomePage.css'

function App() {
  return (
    <div className="App">
      <atoms.ImageLg
        alt="imageLg"
        src="https://images.unsplash.com/photo-1679214523091-a9f9697ed10c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80"
      />
    </div>
  )
}

export default App
