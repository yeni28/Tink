import React from 'react'
import { Outlet } from 'react-router-dom'

import molecules from '@/components/molecules'

function SelectRecommendPage() {
  return (
    <div>
      <molecules.RecommendToggle />
      <h3> Hello World!</h3>
    </div>
  )
}

export default SelectRecommendPage
