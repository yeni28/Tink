import React, { useEffect, useState } from 'react'

import { NavLink, useLocation } from 'react-router-dom'

function CommunityToggle() {
  const [isSelecteds, setIsSelecteds] = useState({
    review: false,
    group: false,
    question: false,
  })

  const toggles = [
    { key: 'review', isSelected: isSelecteds.review, name: '자랑글' },
    { key: 'group', isSelected: isSelecteds.group, name: '소모임' },
    { key: 'question', isSelected: isSelecteds.question, name: '질문' },
  ]

  const location = useLocation()

  useEffect(() => {
    if (location.pathname === '/community/review/list') {
      setIsSelecteds({
        review: true,
        group: false,
        question: false,
      })
    } else if (location.pathname === '/community/group/list') {
      setIsSelecteds({
        review: false,
        group: true,
        question: false,
      })
    } else if (location.pathname === '/community/question/list') {
      setIsSelecteds({
        review: false,
        group: false,
        question: true,
      })
    }
  }, [location.pathname])

  return (
    <div className="flex gap-44">
      {toggles.map((toggle, idx) => {
        return (
          <div key={`${toggle.key}-${idx}`} className="cursor-pointer">
            <NavLink to={`/community/${toggle.key}/list`}>
              {toggle.isSelected ? (
                <button
                  className="w-[7.31rem] h-[2.94rem]  text-red"
                  type="button"
                >
                  {toggle.name}
                </button>
              ) : (
                <div className="flex items-center w-[7.31rem] h-[2.94rem]">
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

export default CommunityToggle
