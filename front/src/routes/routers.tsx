import { createBrowserRouter } from 'react-router-dom'

import RootLayout from './RootLayout'

import { Recommend, Community, Tutorial } from './route'

import CampaignMainPage from '@/pages/campaign/CampaignMainPage'
import HomePage from '@/pages/home/HomePage'
import MyPageMainPage from '@/pages/mypage'

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
      ...Tutorial,
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
