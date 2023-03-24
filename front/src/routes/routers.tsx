import { createBrowserRouter } from 'react-router-dom'

import RootLayout from './RootLayout'

import { Recommend } from './route'

import CampaignMainPage from '@/pages/campaign/CampaignMainPage'
import CommunityMainPage from '@/pages/community/CommunityMainPage'
import HomePage from '@/pages/home/HomePage'
import MyPageMainPage from '@/pages/mypage'
import TutorialMainPage from '@/pages/tutorial/TutorialMainPage'

const routers = createBrowserRouter([
  {
    element: <RootLayout />,
    children: [
      {
        path: '/',
        element: <HomePage />,
      },
      ...Recommend,
      {
        path: '/community',
        element: <CommunityMainPage />,
      },
      {
        path: '/tutorial',
        element: <TutorialMainPage />,
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
