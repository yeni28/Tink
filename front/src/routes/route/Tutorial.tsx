import React from 'react'

import CastonTutorial from '@/pages/tutorial/caston'
import KnitTutorial from '@/pages/tutorial/knit'
import PurlTutorial from '@/pages/tutorial/purl'

const Tutorial = [
  {
    path: '/tutorial',
    children: [
      { path: 'caston', element: <CastonTutorial /> },
      { path: 'knit', element: <KnitTutorial /> },
      { path: 'purl', element: <PurlTutorial /> },
    ],
  },
]

export default Tutorial
