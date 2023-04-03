import React from 'react'

import MyPageMainPage from '@/pages/mypage'
import MyAll from '@/pages/mypage/all'
import MyGroup from '@/pages/mypage/group'
import MyQuestion from '@/pages/mypage/question'
import MyReview from '@/pages/mypage/review'

const Mypage = [
  {
    path: '/mypage',
    element: <MyPageMainPage />,
    children: [
      {
        path: 'all',
        element: <MyAll />,
      },
      {
        path: 'group',
        element: <MyGroup />,
      },
      {
        path: 'question',
        element: <MyQuestion />,
      },
      {
        path: 'review',
        element: <MyReview />,
      },
    ],
  },
]

export default Mypage
