import { useState, useEffect } from 'react'
import { Outlet } from 'react-router-dom'

import Detail from '@/routes/detail/Detail'

export default function RootLayout() {
  return (
    <>
      <Detail>
        <Outlet />
      </Detail>
    </>
  )
}
