import React from 'react'

import { useNavigate } from 'react-router-dom'

import molecules from '@/components/molecules'

interface Props extends Omit<CardMdProps, 'onClick'> {
  boardId: number
}

function ReviewGrid({ items }: { items: Props[] }) {
  const navigate = useNavigate()

  return (
    <div className="grid grid-cols-3 gap-x-8 gap-y-10 justify-items-center">
      {items.map((item, idx) => {
        return (
          <molecules.CardMd
            key={`review-${item.boardId}-${idx}`}
            hit={item.hit}
            liked={item.liked}
            nickname={item.nickname}
            patternThumnail={item.patternThumnail}
            title={item.title}
            onClick={() =>
              navigate(`/community/review/detail/${item.boardId}`, {
                state: { author: item.nickname },
              })
            }
          />
        )
      })}
    </div>
  )
}

export default ReviewGrid
