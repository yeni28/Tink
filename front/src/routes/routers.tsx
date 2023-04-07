import { createBrowserRouter } from 'react-router-dom'

import FullLayout from './FullLayout'
import MainLayout from './MainLayout'
import RootLayout from './RootLayout'

import { Recommend, Community, Tutorial, Mypage, Pattern } from './route'

import CampaignMainPage from '@/pages/campaign/CampaignMainPage'
import Main from '@/pages/home'
import Login from '@/pages/login'
import TutorialMainPage from '@/pages/tutorial'

const routers = createBrowserRouter([
  {
    element: <MainLayout />,
    children: [
      {
        path: '/',
        element: <Main />,
      },
    ],
  },
  {
    element: <RootLayout />,
    children: [
      {
        path: '/tutorial',
        element: <TutorialMainPage />,
      },
      ...Recommend,
      ...Community,
      ...Mypage,
      ...Pattern,
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
