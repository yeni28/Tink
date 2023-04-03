import { createBrowserRouter } from 'react-router-dom'

import FullLayout from './FullLayout'
import RootLayout from './RootLayout'

import { Recommend, Community, Tutorial, Mypage } from './route'

import CampaignMainPage from '@/pages/campaign/CampaignMainPage'
import HomePage from '@/pages/home/HomePage'
import Login from '@/pages/login'
import TutorialMainPage from '@/pages/tutorial'

const routers = createBrowserRouter([
  {
    element: <RootLayout />,
    children: [
      {
        path: '/',
        element: <HomePage />,
      },
      {
        path: '/tutorial',
        element: <TutorialMainPage />,
      },
      ...Recommend,
      ...Community,
      ...Mypage,
      // ...Tutorial,

      {
        path: '/campaign',
        element: <CampaignMainPage />,
      },
      {
        path: '/login',
        element: <Login />,
      },
    ],
  },
  {
    element: <FullLayout />,
    children: [...Tutorial],
  },
])

export default routers
