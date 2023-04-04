import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { RouterProvider } from 'react-router-dom'

import { RecoilRoot } from 'recoil'

import routers from '@/routes/routers'
import '@/index.css'
import '@/styles/global.css'

const container = document.getElementById('root') as HTMLElement

createRoot(container).render(
  // <StrictMode>
  <RecoilRoot>
    <RouterProvider router={routers} />
  </RecoilRoot>
  // </StrictMode>
)
