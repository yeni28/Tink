import React from 'react'

import FirstRecommend from '@/pages/recommend/first'
import FavoriteFirstRecommend from '@/pages/recommend/first/favorite'
import MainRecommend from '@/pages/recommend/main'
import SelectRecommend from '@/pages/recommend/select'
import ColorSelectRecommend from '@/pages/recommend/select/color'
import ResultColorSelectRecommend from '@/pages/recommend/select/color/result'
import PatternSelectRecommend from '@/pages/recommend/select/pattern'
import YarnSelectRecommend from '@/pages/recommend/select/yarn'
const Recommend = [
  {
    path: '/recommend',
    element: <MainRecommend />,
  },
  {
    path: '/recommend/select',
    element: <SelectRecommend />,
    children: [
      { path: 'pattern', element: <PatternSelectRecommend /> },
      { path: 'yarn', element: <YarnSelectRecommend /> },
      {
        path: 'color',
        element: <ColorSelectRecommend />,
      },
      {
        path: 'color/result',
        element: <ResultColorSelectRecommend />,
      },
    ],
  },
  {
    path: '/recommend/first',
    children: [
      { index: true, element: <FirstRecommend /> },
      { path: 'favorite', element: <FavoriteFirstRecommend /> },
    ],
  },
]

export default Recommend
