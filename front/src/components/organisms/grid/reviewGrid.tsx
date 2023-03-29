import React from 'react'

import molecules from '@/components/molecules'

interface Props extends CardMdProps {
  id: number
}

function reviewGrid({ items }: { items: Props[] }) {
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
            onClick={item.onClick}
          />
        )
      })}
    </div>
  )
}

export default reviewGrid
