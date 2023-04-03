import React, { useEffect, useState } from 'react'

import { NavLink, useLocation, useParams } from 'react-router-dom'

import DrawingLine from '@/assets/drawings/drawingline.png'
import Plus from '@/assets/drawings/plus.png'

function MypageToggle() {
  const [isSelecteds, setIsSelecteds] = useState({
    all: false,
    review: false,
    question: false,
    group: false,
  })
  const toggles = [
    { key: 'all', isSelected: isSelecteds.all, name: '전체보기' },
    { key: 'review', isSelected: isSelecteds.review, name: '자랑글' },
    { key: 'question', isSelected: isSelecteds.question, name: '질문' },
    { key: 'group', isSelected: isSelecteds.group, name: '소모임' },
  ]

  const location = useLocation()
  const params = useParams()
  useEffect(() => {
    if (location.pathname.includes(`/mypage/${params.id}/all`)) {
      setIsSelecteds({
        all: true,
        review: false,
        question: false,
        group: false,
      })
    } else if (location.pathname.includes(`/mypage/${params.id}/review`)) {
      setIsSelecteds({
        all: false,
        review: true,
        question: false,
        group: false,
      })
    } else if (location.pathname.includes(`/mypage/${params.id}/question`)) {
      setIsSelecteds({
        all: false,
        review: false,
        question: true,
        group: false,
      })
    } else if (location.pathname.includes(`/mypage/${params.id}/group`)) {
      setIsSelecteds({
        all: false,
        review: false,
        question: false,
        group: true,
      })
    }
  }, [location.pathname])

  return (
    <div className="mb-11">
      <div className="flex items-center justify-between">
        <div className="flex items-center ">
          {toggles.map((toggle, idx) => {
            return (
              <div key={`${toggle.key}-${idx}`} className="cursor-pointer">
                <NavLink to={`/mypage/${params.id}/${toggle.key}`}>
                  {toggle.isSelected ? (
                    <button
                      className=" mr-10 text-title3-bold text-red"
                      type="button"
                    >
                      {toggle.name}
                    </button>
                  ) : (
                    <div className="mr-10 text-title3-bold items-center ">
                      {toggle.name}
                    </div>
                  )}
                </NavLink>
              </div>
            )
          })}
        </div>
      </div>
      <div>
        <img alt="line" src={DrawingLine} />
      </div>
    </div>
  )
}

export default MypageToggle
