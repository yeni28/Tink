import { Outlet } from 'react-router-dom'

import RecommendToggle from '@/pages/recommend/select/components/RecommendToggle'

function SelectRecommend() {
  return (
    <div>
      <RecommendToggle />
      <Outlet />
    </div>
  )
}

export default SelectRecommend
