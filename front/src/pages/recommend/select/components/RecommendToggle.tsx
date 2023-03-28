import { useEffect, useState } from 'react'
import { NavLink, useLocation } from 'react-router-dom'

import style from '@/styles/pages/recommend/select/components/RecommendToggle.module.css'

function RecommendToggle() {
  const [isSelecteds, setIsSelecteds] = useState({
    pattern: false,
    yarn: false,
    color: false,
  })

  const toggles = [
    { key: 'pattern', isSelected: isSelecteds.pattern, name: '패턴 추천' },
    { key: 'yarn', isSelected: isSelecteds.yarn, name: '실 추천' },
    { key: 'color', isSelected: isSelecteds.color, name: '색 조합 추천' },
  ]

  const location = useLocation()

  useEffect(() => {
    if (location.pathname.includes('/recommend/select/pattern')) {
      setIsSelecteds({
        pattern: true,
        yarn: false,
        color: false,
      })
    } else if (location.pathname.includes('/recommend/select/yarn')) {
      setIsSelecteds({
        pattern: false,
        yarn: true,
        color: false,
      })
    } else if (location.pathname.includes('/recommend/select/color')) {
      setIsSelecteds({
        pattern: false,
        yarn: false,
        color: true,
      })
    }
  }, [location.pathname])

  return (
    <div className="flex justify-center gap-44">
      {toggles.map((toggle, idx) => {
        return (
          <div key={`${toggle.key}-${idx}`} className="cursor-pointer">
            <NavLink to={`/recommend/select/${toggle.key}`}>
              {toggle.isSelected ? (
                <button
                  className="w-[7.31rem] h-[2.94rem] bg-black text-white rounded-[3.13rem]"
                  type="button"
                >
                  {toggle.name}
                </button>
              ) : (
                <div className="flex justify-center items-center w-[7.31rem] h-[2.94rem]">
                  {toggle.name}
                </div>
              )}
            </NavLink>
          </div>
        )
      })}
    </div>
  )
}

export default RecommendToggle
