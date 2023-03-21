import React, { useState } from 'react'

import atoms from '@/components/atoms'

interface cardProps {
  author: string
  isFollow: boolean
  titleImgUrl: string
  title: string
  views: number
  likes: number
}

function CardMd({
  author,
  isFollow,
  titleImgUrl,
  title,
  views,
  likes,
}: cardProps) {
  const [follow, setFollow] = useState(isFollow)
  return (
    <div className="w-[22.25rem]">
      <div className="flex mb-2">
        <p className="text-headline mr-3">{author}</p>
        <div className="flex items-center">
          <div
            className={
              'w-[6px] h-[6px] rounded-lg mr-1 ' +
              (follow ? 'bg-line' : 'bg-red')
            }
          />
          <button
            type="button"
            className={
              'text-headline-bold ' + (follow ? 'text-line' : 'text-red')
            }
            onClick={() => setFollow(!follow)}
          >
            {follow ? '팔로잉' : '팔로우'}
          </button>
        </div>
      </div>
      <atoms.ImageMd src={titleImgUrl} />
      <p className="my-2 text-title2-bold flex justify-center">{title}</p>
      <div className="flex justify-center items-center">
        <span className="text-footnote text-grey mr-1">조회수</span>
        <span className="text-footnote text-grey">{views}</span>
        <div className="w-1 h-1 rounded-lg bg-grey inline-block mx-1" />
        <span className="text-footnote text-grey mr-1">좋아요</span>
        <span className="text-footnote text-grey">{likes}</span>
      </div>
    </div>
  )
}

export default CardMd
