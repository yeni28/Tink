import React from 'react'

import ColorRecommendationPage from '@/pages/recommend/ColorRecommendationPage'
import PatternRecommendationPage from '@/pages/recommend/PatternRecommendationPage'
import RecommendMainPage from '@/pages/recommend/RecommendMainPage'
import SelectRecommendPage from '@/pages/recommend/SelectRecommendPage'
import YarnRecommendationPage from '@/pages/recommend/YarnRecommendationPage'

const Recommend = [
  {
    path: '/recommend',
    element: <RecommendMainPage />,
  },
  {
    path: '/recommend/select',
    element: <SelectRecommendPage />,
    children: [
      // { index: true, element: <SelectRecommendPage /> },
      { path: 'pattern', element: <PatternRecommendationPage /> },
      { path: 'color', element: <ColorRecommendationPage /> },
      { path: 'yarn', element: <YarnRecommendationPage /> },
    ],
  },
]

export default Recommend
