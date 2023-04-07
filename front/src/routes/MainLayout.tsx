import { Outlet } from 'react-router-dom'

import organisms from '@/components/organisms'

export default function MainLayout() {
  return (
    <>
      <organisms.Navbar />
      <Outlet />
    </>
  )
}
