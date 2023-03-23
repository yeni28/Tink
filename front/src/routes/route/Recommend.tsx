import React from 'react'

import ColorRecommendationPage from '@/pages/recommend/ColorRecommendationPage'
import PatternRecommendationPage from '@/pages/recommend/PatternRecommendationPage'
import RecommendMainPage from '@/pages/recommend/RecommendMainPage'
import YarnRecommendationPage from '@/pages/recommend/YarnRecommendationPage'

const Recommend = [
  {
    path: '/recommend',
    element: <RecommendMainPage />,
  },
  {
    path: '/recommend/color',
    element: <ColorRecommendationPage />,
  },
  {
    path: '/recommend/pattern',
    element: <PatternRecommendationPage />,
  },
  {
    path: '/recommend/yarn',
    element: <YarnRecommendationPage />,
  },
]

export default Recommend
