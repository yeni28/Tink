import React, { useState, useEffect } from 'react'

import { useRecoilState } from 'recoil'

import {
  ageData,
  attributeData,
  fitData,
  genderData,
  neckData,
  otherData,
  patternData,
  sleeveData,
  constructionData,
} from './AttributeData'

import NextButton from '../molecules/NextButton'

import atoms from '@/components/atoms'
import { keywordState } from '@/pages/recommend/select/pattern'

import lineBox1 from '@/pages/recommend/select/pattern/components/atoms/line box1.png'
import lineBox2 from '@/pages/recommend/select/pattern/components/atoms/line box2.png'
import lineBox3 from '@/pages/recommend/select/pattern/components/atoms/line box3.png'

interface tagProps {
  id: string
  name: string
  key: number
}

function Attribute() {
  const [category, setCategory] = useState('')
  const [subCategory, setSubCategory] = useState<any>()

  const setSubCategoryData = (category: string) => {
    if (category) {
      if (category === 'Age or Size') {
        setSubCategory(ageData)
      } else if (category === 'Fit') {
        setSubCategory(fitData)
      } else if (category === 'Gender') {
        setSubCategory(genderData)
      } else if (category === 'Neck') {
        setSubCategory(neckData)
      } else if (category === 'Sleeve') {
        setSubCategory(sleeveData)
      } else if (category === 'Pattern') {
        setSubCategory(patternData)
      } else if (category === 'Construction') {
        setSubCategory(constructionData)
      } else {
        setSubCategory(otherData)
      }
    }
  }
  useEffect(() => {
    setSubCategoryData(category)
  }, [category])

  const [tagList, setTagList] = useRecoilState(keywordState)
  const [isChecked, setIsChecked] = useState(false)
  const checkedItemHandler = (value: any, isChecked: boolean) => {
    if (isChecked) {
      setTagList((prev: any) => [...prev, value])
      return
    }

    if (!isChecked && tagList.includes(value)) {
      setTagList(tagList.filter((item: any) => item !== value))
      return
    }

    return
  }

  const checkHandler = (e: React.ChangeEvent<HTMLInputElement>, value: any) => {
    setIsChecked(!isChecked)
    checkedItemHandler(value, e.target.checked)
  }

  return (
    <div>
      <div className="flex pl-8 relative">
        {/* 첫번째 카테고리 */}
        <div className="w-[13.58rem] h-[20.87rem] relative">
          <img className="w-full h-full" src={lineBox1} />
          <div className="absolute top-2 left-3 p-4">
            {attributeData.map((item: tagProps) => {
              return (
                <div key={item.key} className="flex items-center my-3 mx-2">
                  <input
                    className="appearance-none w-4 h-4 border-black border mr-3 checked:bg-black"
                    id={item.id}
                    name="category"
                    type="radio"
                    onClick={() => {
                      setCategory(item.id)
                    }}
                  />
                  <label htmlFor={item.id}>{item.name}</label>
                </div>
              )
            })}
          </div>
        </div>

        {/* 두번째 카테고리 */}

        <div className="w-[13.58rem] h-[20.87rem] relative">
          <img className="w-full h-full" src={lineBox2} />
          <div className="absolute top-2 left-3 p-4">
            {subCategory &&
              subCategory.map((item: tagProps) => {
                return (
                  <div key={item.key} className="flex items-center my-3 mx-2">
                    <input
                      checked={tagList.includes(item)}
                      className="appearance-none w-4 h-4 border-black border mr-3 checked:bg-black"
                      id={item.id}
                      type="checkbox"
                      onChange={(e) => checkHandler(e, item)}
                    />
                    <label htmlFor={item.id}>{item.name}</label>
                  </div>
                )
              })}
          </div>
        </div>

        {/* 세번째 카테고리 */}
        <div className="w-[28.46rem] h-[20.88 rem] relative">
          <img className="w-full h-full" src={lineBox3} />
          <div className="absolute top-0 left-0"> </div>
        </div>

        {/* 다음 버튼 */}
        <NextButton />
      </div>
    </div>
  )
}

export default Attribute
