import React from 'react'

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
]

export default Recommend
