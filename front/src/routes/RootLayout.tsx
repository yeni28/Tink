import { Outlet } from 'react-router-dom'

import organisms from '@/components/organisms'

import Detail from '@/routes/detail/Detail'

export default function RootLayout() {
  console.log('=^._.^= ∫')

  console.log(`
  {\___/}
  ( • ㅁ•)
  / >🐰
  `)
  console.log(`
    /l、
   ﾞ（ﾟ､ ｡７
    l、ﾞ~ヽ
    じしf_,)ノ
  `)
  console.log(`
   A____A
  |・ㅅ・| 
  |っ　ｃ| 
  |　　　| 
  |　　　| 
  |　　　| 
  |　　　|
  |　　　|
   U￣￣U
   `)
  console.log(`
  GITHUB
  
  `)
  return (
    <>
      <organisms.Navbar />
      <Detail>
        <organisms.Header />
        <Outlet />
      </Detail>
    </>
  )
}
