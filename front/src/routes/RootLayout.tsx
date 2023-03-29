import { useEffect } from 'react'
import { useLocation, Outlet } from 'react-router-dom'

import gsap from 'gsap'
import ScrollTrigger from 'gsap/ScrollTrigger'

import organisms from '@/components/organisms'

import Detail from '@/routes/detail/Detail'

export default function RootLayout() {
  const location = useLocation()
  useEffect(() => {
    ScrollTrigger.getAll().forEach((instance) => {
      instance.kill()
    })
    // This in case a scroll animation is active while the route is updated
    gsap.killTweensOf(window)
  }, [location])
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
