import React from 'react'

import { useRecoilValue } from 'recoil'
import { axAuth } from '@/apis/axiosInstance'
import atoms from '@/components/atoms'

import {
  categoryState,
  keywordState,
  levelState,
} from '@/pages/recommend/select/pattern'

function NextButton() {
  const categoryList = useRecoilValue(categoryState)
  const keywordList = useRecoilValue(keywordState)
  const level = useRecoilValue(levelState)

  const onClickHandler = () => {
    if (categoryList.length == 0) {
      alert('카테고리를 선택해주세요!')
      return
    } else if (keywordList.length == 0) {
      alert('키워드를 선택해주세요!')
      return
    } else if (!level) {
      alert('난이도를 선택해주세요!')
    } else {
      const selectedCategory = categoryList.map((item: any) => {
        return item.id
      })
      const selectedKeyword = keywordList.map((item: any) => {
        return item.id
      })

      const filter = {
        category: selectedCategory,
        keyword: selectedKeyword,
        difficulty: level,
      }

      console.log(filter);
      
    }
  }

  return (
    <div className="absolute -bottom-20 -right-14">
      <atoms.ButtonDoodle innerValue={'다음'} onClick={onClickHandler} />
    </div>
  )
}

export default NextButton
