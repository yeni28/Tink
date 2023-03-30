import { Outlet } from 'react-router-dom'

import organisms from '@/components/organisms'

export default function FullLayout() {
  return (
    <>
      <organisms.Navbar />
      <div className="pt-[6.25rem] w-[72rem] mx-auto">
        <organisms.Header />
      </div>

      <Outlet />
    </>
  )
}
