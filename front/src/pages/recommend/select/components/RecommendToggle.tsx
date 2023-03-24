import { useEffect, useState } from 'react'
import { NavLink, useLocation } from 'react-router-dom'

import atoms from '@/components/atoms'

function RecommendToggle() {
  const [isSelecteds, setIsSelecteds] = useState({
    isPatternSelected: false,
    isYarnSelected: false,
    isColorSelected: false,
  })

  return (
    <div className="flex justify-center gap-44">
      <div className="cursor-pointer justify-self-start inline-block">
        <NavLink to="/recommend/select/pattern">
          {isSelecteds.isPatternSelected ? (
            <button className="w-[7.31rem] h-[2.94rem]" type="button">
              패턴 추천
            </button>
          ) : (
            <div className="w-[7.31rem] h-[2.94rem]">패턴 추천</div>
          )}
        </NavLink>
      </div>
      <div className="cursor-pointer justify-self-start inline-block">
        <NavLink to="/recommend/select/yarn">
          <div className="w-[7.31rem] h-[2.94rem]">실 추천</div>
        </NavLink>
      </div>
      <div className="cursor-pointer justify-self-start inline-block">
        <NavLink to="/recommend/select/color">
          <div className="w-[7.31rem] h-[2.94rem]">색 조합 추천</div>
        </NavLink>
      </div>
    </div>
  )
}

export default RecommendToggle
