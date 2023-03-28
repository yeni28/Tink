import { createBrowserRouter } from 'react-router-dom'

import RootLayout from './RootLayout'

import { Recommend, Community } from './route'

import CampaignMainPage from '@/pages/campaign/CampaignMainPage'
import HomePage from '@/pages/home/HomePage'
import MyPageMainPage from '@/pages/mypage'
import MainTutorial from '@/pages/tutorial'

const routers = createBrowserRouter([
  {
    element: <RootLayout />,
    children: [
      {
        path: '/',
        element: <HomePage />,
      },
      ...Recommend,
      ...Community,

      {
        path: '/tutorial',
        element: <MainTutorial />,
      },
      {
        path: '/campaign',
        element: <CampaignMainPage />,
      },
      {
        path: '/mypage',
        element: <MyPageMainPage />,
      },
    ],
  },
])

export default routers
