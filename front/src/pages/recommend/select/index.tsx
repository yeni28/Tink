import { Outlet } from 'react-router-dom'

import RecommendToggle from '@/pages/recommend/select/components/RecommendToggle'

function SelectRecommend() {
  return (
    <div>
      <RecommendToggle />
      <h3> 추천 선택 페이지 </h3>
      <p>바뀌고 있나요?</p>
      <Outlet />
    </div>
  )
}

export default SelectRecommend
