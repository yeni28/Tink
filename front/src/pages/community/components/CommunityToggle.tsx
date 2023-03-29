import React, { useEffect, useState } from 'react'

import { NavLink, useLocation } from 'react-router-dom'

import DrawingLine from '@/assets/drawings/drawingline.png'
import Plus from '@/assets/drawings/plus.png'

function CommunityToggle() {
  const [isSelecteds, setIsSelecteds] = useState({
    review: false,
    group: false,
    question: false,
  })
  const [writeLink, setWriteLink] = useState('')
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
      setWriteLink('review')
    } else if (location.pathname === '/community/group/list') {
      setIsSelecteds({
        review: false,
        group: true,
        question: false,
      })
      setWriteLink('group')
    } else if (location.pathname === '/community/question/list') {
      setIsSelecteds({
        review: false,
        group: false,
        question: true,
      })
      setWriteLink('question')
    }
  }, [location.pathname])

  return (
    <div>
      <div className="flex items-center justify-between">
        <div className="flex items-center ">
          {toggles.map((toggle, idx) => {
            return (
              <div key={`${toggle.key}-${idx}`} className="cursor-pointer">
                <NavLink to={`/community/${toggle.key}/list`}>
                  {toggle.isSelected ? (
                    <button
                      className=" mr-10 text-title3-bold text-red"
                      type="button"
                    >
                      {toggle.name}
                    </button>
                  ) : (
                    <div className="mr-10   text-title3-bold items-center ">
                      {toggle.name}
                    </div>
                  )}
                </NavLink>
              </div>
            )
          })}
        </div>
        {/* 툴팁포함 글쓰기 버튼 */}
        <div>
          <NavLink to={`/community/${writeLink}/write`}>
            <div className="relative flex flex-col items-center group">
              <img
                alt="plus"
                className="w-[2rem] h-[2rem] cursor-pointer :hover "
                src={Plus}
              />
              <div className="absolute bottom-5 flex flex-col items-center hidden mb-5 group-hover:flex">
                <span className="relative rounded-md z-10 pl-[1rem] pt-[.6rem] text-xs leading-none text-white whitespace-no-wrap bg-black w-[4rem] h-[2rem]  ">
                  글쓰기
                </span>
                <div className="w-3 h-3 -mt-2 rotate-45 bg-black"></div>
              </div>
            </div>
          </NavLink>
        </div>
      </div>
      <div>
        <img alt="line" src={DrawingLine} />
      </div>
    </div>
  )
}

export default CommunityToggle
