import React, { useState, useEffect } from 'react'

import {
  categoryData,
  clothingData,
  accessoriesData,
  homeData,
  petData,
  toysData,
  componentsData,
} from './CategoryData'

import atoms from '@/components/atoms'

import lineBox1 from '@/pages/recommend/select/pattern/components/atoms/line box1.png'
import lineBox2 from '@/pages/recommend/select/pattern/components/atoms/line box2.png'
import lineBox3 from '@/pages/recommend/select/pattern/components/atoms/line box3.png'

function Category() {
  const [category, setCategory] = useState('')
  const [subCategory, setSubCategory] = useState<any>()

  const setSubCategoryData = (category: string) => {
    if (category) {
      if (category === 'Clothing') {
        setSubCategory(clothingData)
      } else if (category === 'Accessories') {
        setSubCategory(accessoriesData)
      } else if (category === 'Home') {
        setSubCategory(homeData)
      } else if (category === 'Toys and Hobbies') {
        setSubCategory(toysData)
      } else if (category === 'Pet') {
        setSubCategory(petData)
      } else {
        setSubCategory(componentsData)
      }
    }
  }
  useEffect(() => {
    setSubCategoryData(category)
    console.log(category)
    console.log(subCategory)
  }, [category])
  const onClickHandler = () => {
    console.log('Hello')
  }
  return (
    <div>
      <div className="flex pl-8 relative">
        {/* 첫번째 카테고리 */}
        <div className="w-[13.58rem] h-[20.87rem] relative">
          <img className="w-full h-full" src={lineBox1} />
          <div className="absolute top-2 left-3 p-4">
            {categoryData.map((item) => {
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
              subCategory.map((item: any) => {
                return (
                  <div
                    key={item.key}
                    className="flex items-center my-3 mx-2"
                    onClick={() => {
                      console.log('clicked')
                    }}
                  >
                    <input
                      className="appearance-none w-4 h-4 border-black border mr-3 checked:bg-black"
                      id={item.id}
                      // name="category"
                      type="checkbox"
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
        </div>

        {/* 다음 버튼 */}
        <div className="absolute -bottom-20 -right-14">
          <atoms.ButtonDoodle innerValue={'다음'} onClick={onClickHandler} />
        </div>
      </div>
    </div>
  )
}

export default Category
