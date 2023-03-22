import { Outlet } from 'react-router-dom'

import organisms from '@/components/organisms'

import Detail from '@/routes/detail/Detail'

export default function RootLayout() {
  return (
    <>
      <organisms.Navbar />
      <Detail>
        <Outlet />
      </Detail>
    </>
  )
}
