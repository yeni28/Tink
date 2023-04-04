import React, { useState } from 'react'

import Attribute from './components/organisms/Attribute'
import Category from './components/organisms/Category'
import Level from './components/organisms/Level'

import atoms from '@/components/atoms'

import lace from '@/pages/recommend/select/pattern/components/atoms/lace_down.png'

function PatternSelectRecommend() {
  const [option, setOption] = useState({
    category: true,
    attribute: false,
    level: false,
  })
  const toggles = [
    { key: 'category', isSelected: option.category, name: '카테고리' },
    { key: 'attribute', isSelected: option.attribute, name: '속성' },
    { key: 'level', isSelected: option.level, name: '난이도' },
  ]

  const changeOption = (name: string) => {
    if (name === 'category') {
      setOption({
        category: true,
        attribute: false,
        level: false,
      })
    } else if (name === 'attribute') {
      setOption({
        category: false,
        attribute: true,
        level: false,
      })
    } else {
      setOption({
        category: false,
        attribute: false,
        level: true,
      })
    }
  }

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
                  onClick={() => changeOption(option.key)}
                >
                  {option.name}
                </button>
              )
            })}
          </div>

          {/* 선택영역 */}
          {option.attribute ? (
            <Attribute />
          ) : option.category ? (
            <Category />
          ) : (
            <Level />
          )}

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
