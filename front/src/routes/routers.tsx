import { createBrowserRouter } from 'react-router-dom'

import FullLayout from './FullLayout'
import RootLayout from './RootLayout'

import { Recommend, Community, Tutorial } from './route'

import CampaignMainPage from '@/pages/campaign/CampaignMainPage'
import HomePage from '@/pages/home/HomePage'
import Login from '@/pages/login'
import MyPageMainPage from '@/pages/mypage'
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
      // ...Tutorial,

      {
        path: '/campaign',
        element: <CampaignMainPage />,
      },
      {
        path: '/mypage',
        element: <MyPageMainPage />,
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
