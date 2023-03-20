import { createBrowserRouter } from 'react-router-dom'

import RootLayout from './RootLayout'

import HomePage from '@/pages/home/HomePage'

const routers = createBrowserRouter([
  {
    element: <RootLayout />,
    children: [
      {
        path: '/',
        element: <HomePage />,
      },
    ],
  },
])

export default routers
