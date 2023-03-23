import { useEffect, useState } from 'react'
import { Outlet, useLocation } from 'react-router-dom'

import molecules from '@/components/molecules'
import organisms from '@/components/organisms'

function SelectRecommendPage() {
  return (
    <div>
      <molecules.RecommendToggle />
      <h3> 추천 선택 페이지 </h3>
      <p>바뀌고 있나요?</p>
      <Outlet />
    </div>
  )
}

export default SelectRecommendPage
