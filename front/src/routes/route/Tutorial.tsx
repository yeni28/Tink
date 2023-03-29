import React from 'react'

import MainTutorial from '@/pages/tutorial'
import CastonTutorial from '@/pages/tutorial/caston'
import KnitTutorial from '@/pages/tutorial/knit'
import PurlTutorial from '@/pages/tutorial/purl'

const Tutorial = [
  {
    path: '/tutorial',
    children: [
      { index: true, element: <MainTutorial /> },
      { path: 'caston', element: <CastonTutorial /> },
      { path: 'knit', element: <KnitTutorial /> },
      { path: 'purl', element: <PurlTutorial /> },
    ],
  },
]

export default Tutorial
