import { useEffect, useState } from 'react'
import { NavLink, useLocation } from 'react-router-dom'

import atoms from '@/components/atoms'
import style from '@/styles/pages/recommend/select/components/RecommendToggle.module.css'

function RecommendToggle() {
  const [isSelecteds, setIsSelecteds] = useState({
    isPatternSelected: true,
    isYarnSelected: false,
    isColorSelected: false,
  })

  return (
    <div className="flex justify-center gap-44">
      <div className="cursor-pointer">
        <NavLink to="/recommend/select/pattern">
          {isSelecteds.isPatternSelected ? (
            <button className={style.toggleButton} type="button">
              패턴 추천
            </button>
          ) : (
            <div className={style.unSelected}>패턴 추천</div>
          )}
        </NavLink>
      </div>
      <div className="cursor-pointer">
        <NavLink to="/recommend/select/yarn">
          <div className="flex justify-center items-center w-[7.31rem] h-[2.94rem]">
            실 추천
          </div>
        </NavLink>
      </div>
      <div className="cursor-pointer">
        <NavLink to="/recommend/select/color">
          <div className="flex justify-center items-center w-[7.31rem] h-[2.94rem]">
            색 조합 추천
          </div>
        </NavLink>
      </div>
    </div>
  )
}

export default RecommendToggle
