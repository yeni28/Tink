import React, { useState } from 'react'

import { atom, useRecoilValue } from 'recoil'

import Attribute from './components/organisms/Attribute'
import Category from './components/organisms/Category'
import Level from './components/organisms/Level'

import atoms from '@/components/atoms'

import lace from '@/pages/recommend/select/pattern/components/atoms/lace_down.png'

export const categoryState = atom<any>({
  key: 'categoryAtom',
  default: [],
})

export const keywordState = atom<any>({
  key: 'keywordAtom',
  default: [],
})

export const levelState = atom<any>({
  key: 'levelAtom',
  default: '',
})

function PatternSelectRecommend() {
  const categoryList = useRecoilValue(categoryState)
  const keywordList = useRecoilValue(keywordState)
  // const level = useRecoilValue(levelState)

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

          {/* 선택 카테고리 영역 */}
          <div className="absolute top-2 right-[8rem] p-4 w-[25rem]">
            <div className="min-h-[8rem] relative">
              <p className="text-title3-bold">선택 카테고리</p>
              <img className="absolute top-4" src={lace} />
              <div className="mt-5 flex flex-wrap gap-2">
                {categoryList.map((categoryItem: any) => {
                  return (
                    <atoms.ButtonTag
                      key={categoryItem.key}
                      bgColor="red"
                      innerValue={categoryItem.name}
                      textColor="white"
                    />
                  )
                })}
              </div>
            </div>

            {/* 선택 키워드 영역 */}
            <div className="mt-3 relative">
              <p className="text-title3-bold">선택 키워드</p>
              <img className="absolute top-4" src={lace} />
              <div className="mt-5 flex flex-wrap gap-2">
                {keywordList.map((keywordItem: any) => {
                  return (
                    <atoms.ButtonTag
                      key={keywordItem.key}
                      bgColor="red"
                      innerValue={keywordItem.name}
                      textColor="white"
                    />
                  )
                })}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default PatternSelectRecommend
