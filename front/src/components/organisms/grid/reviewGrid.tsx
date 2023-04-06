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
      {items.map((item) => {
        return (
          <molecules.CardMd
            key={item.id}
            author={item.author}
            isFollow={item.isFollow}
            likes={item.likes}
            title={item.title}
            titleImgUrl={item.titleImgUrl}
            views={item.views}
            onClick={() => navigate(`/community/review/detail/${item.id}`)}
          />
        )
      })}
    </div>
  )
}

export default ReviewGrid
