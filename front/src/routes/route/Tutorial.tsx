import React from 'react'

import CastonTutorial from '@/pages/tutorial/caston'
import KnitTutorial from '@/pages/tutorial/knit'
import KnitStepFour from '@/pages/tutorial/knit/components/StepFour'
import KnitStepThree from '@/pages/tutorial/knit/components/StepThree'
import KnitStepTwo from '@/pages/tutorial/knit/components/StepTwo'

import PurlTutorial from '@/pages/tutorial/purl'
import PurlStepFour from '@/pages/tutorial/purl/components/StepFour'
import PurlStepThree from '@/pages/tutorial/purl/components/StepThree'
import PurlStepTwo from '@/pages/tutorial/purl/components/StepTwo'

const Tutorial = [
  {
    path: '/tutorial',
    children: [
      { path: 'caston', element: <CastonTutorial /> },
      {
        path: 'knit',
        element: <KnitTutorial />,
      },
      { path: 'knit/2', element: <KnitStepTwo /> },
      { path: 'knit/3', element: <KnitStepThree /> },
      { path: 'knit/4', element: <KnitStepFour /> },

      { path: 'purl', element: <PurlTutorial /> },
      { path: 'purl/2', element: <PurlStepTwo /> },
      { path: 'purl/3', element: <PurlStepThree /> },
      { path: 'purl/4', element: <PurlStepFour /> },
    ],
  },
]

export default Tutorial
