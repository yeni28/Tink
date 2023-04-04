import React, { useState } from 'react'

import Category from './organisms/Category'

import atoms from '@/components/atoms'

import lace from '@/pages/recommend/select/pattern/components/atoms/lace_down.png'

function PatternSelectRecommend() {
  const [option, setOption] = useState({
    category: true,
    attribute: false,
    level: false,
  })
  const toggles = [
    { key: 'pattern', isSelected: option.category, name: '카테고리' },
    { key: 'yarn', isSelected: option.attribute, name: '속성' },
    { key: 'color', isSelected: option.level, name: '난이도' },
  ]

  return (
    <div>
      <div className="mt-[6.5rem] ">
        <div className="flex relative ">
          {/* 토글영역 */}
          <div className="flex flex-col gap-3 pl-9">
            {toggles.map((option) => {
              return (
                <button
                  key={option.key}
                  type="button"
                  className={`w-[5.7rem] h-[2.38rem] rounded-[3.13rem] text-pop text-headline 
                  ${
                    option.isSelected
                      ? 'bg-grey text-white'
                      : 'border-grey border text-grey'
                  }`}
                >
                  {option.name}
                </button>
              )
            })}
          </div>

          {/* 선택영역 */}
          <Category />

          {/* 선택 완료 영역 */}
          <div className="absolute -top-20 -right-36 p-4">
            <p className="text-title2-bold">선택 키워드</p>
            <img src={lace} />
            <div className="mt-2">
              <atoms.ButtonTag
                bgColor="red"
                innerValue="상의"
                textColor="white"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default PatternSelectRecommend
