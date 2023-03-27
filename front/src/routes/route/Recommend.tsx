import React from 'react'

import FirstRecommend from '@/pages/recommend/first'
import MainRecommend from '@/pages/recommend/main'
import SelectRecommend from '@/pages/recommend/select'
import ColorRecommend from '@/pages/recommend/select/color'
import PatternRecommend from '@/pages/recommend/select/pattern'
import YarnRecommend from '@/pages/recommend/select/yarn'

const Recommend = [
  {
    path: '/recommend',
    element: <MainRecommend />,
  },
  {
    path: '/recommend/select',
    element: <SelectRecommend />,
    children: [
      // { index: true, element: <SelectRecommendPage /> },
      { path: 'pattern', element: <PatternRecommend /> },
      { path: 'color', element: <ColorRecommend /> },
      { path: 'yarn', element: <YarnRecommend /> },
    ],
  },
  {
    path: '/recommend/first',
    children: [
      { index: true, element: <FirstRecommend /> },
      { path: 'favorite', element: <FirstRecommend /> },
    ],
  },
]

export default Recommend
