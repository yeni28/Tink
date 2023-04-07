import React, { Dispatch, SetStateAction, useState } from 'react'

import molecules from '@/components/molecules'

interface props {
  setSelecteds: React.Dispatch<
    React.SetStateAction<{ pattern: number; categoryId: number }[]>
  >
  setIsCheck: Dispatch<SetStateAction<boolean[]>>
  isCheck: boolean[]
  selecteds: { pattern: number; categoryId: number }[]
  datas: {
    patternId: number
    name: string
    patternThumbnails: {
      mainImg: string
      thumbImg: string
    }[]
  }[]
}

function FavoritePatterns({
  datas,
  setSelecteds,
  selecteds,
  isCheck,
  setIsCheck,
}: props) {
  const onClickHandler = (data: any, idx: number) => {
    if (!isCheck[idx]) {
      setSelecteds([
        ...selecteds,
        { pattern: data.patternId, categoryId: data.categoryId },
      ])

      setIsCheck(
        isCheck.map((el, index) => {
          if (index === idx) {
            return !el
          } else {
            return el
          }
        })
      )
      return
    }
    return
  }
  return (
    <div className="grid grid-cols-4 gap-x-10 gap-y-10 justify-items-center">
      {datas.map((data, idx) => {
        return (
          <div
            key={data.patternId}
            className={
              isCheck[idx]
                ? 'opacity-20 cursor-pointer'
                : 'opacity-100 cursor-pointer'
            }
            onClick={() => {
              onClickHandler(data, idx)
            }}
          >
            <molecules.CardSm
              src={data.patternThumbnails[0].thumbImg}
              title={data.name}
            />
          </div>
        )
      })}
    </div>
  )
}

export default FavoritePatterns
