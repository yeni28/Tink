import { createBrowserRouter } from 'react-router-dom'

import RootLayout from './RootLayout'

import CampaignMainPage from '@/pages/campaign/CampaignMainPage'
import CommunityMainPage from '@/pages/community/CommunityMainPage'
import HomePage from '@/pages/home/HomePage'
import MyPageMainPage from '@/pages/mypage/MyPageMainPage'
import RecommendMainPage from '@/pages/recommend/RecommendMainPage'
import TutorialMainPage from '@/pages/tutorial/TutorialMainPage'

const routers = createBrowserRouter([
  {
    element: <RootLayout />,
    children: [
      {
        path: '/',
        element: <HomePage />,
      },
      {
        path: '/community/review',
        element: <CommunityMainPage />,
      },
      {
        path: '/recommend',
        element: <RecommendMainPage />,
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
