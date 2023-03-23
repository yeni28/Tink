import React from 'react'
import { NavLink } from 'react-router-dom'

function RecommendToggle() {
  return (
    <div className="flex justify-center">
      <div className="text-red cursor-pointer justify-self-start inline-block">
        <NavLink to="/recommend/pattern">
          <div>패턴 추천</div>
        </NavLink>
      </div>
      <div className="text-red cursor-pointer justify-self-start inline-block">
        <NavLink to="/recommend/yarn">
          <div>실 추천</div>
        </NavLink>
      </div>
      <div className="text-red cursor-pointer justify-self-start inline-block">
        <NavLink to="/recommend/color">
          <div>색 조합 추천</div>
        </NavLink>
      </div>
    </div>
  )
}

export default RecommendToggle
