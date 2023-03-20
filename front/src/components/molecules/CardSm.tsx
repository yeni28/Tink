import React from 'react'

import { ReactComponent as Heart } from '@/assets/svg/heart.svg'
import { ReactComponent as HeartFill } from '@/assets/svg/heart_fill.svg'
import { atoms } from '@/components/atoms'

interface cardProps {
  isLiked: boolean
  title: string
  userImgUrl: string
  userName: string
}

function CardSm({ isLiked, title, userImgUrl, userName }: cardProps) {
  return (
    <div className="w-[14.25rem] h-[15rem]">
      <div className="relative">
        <atoms.ImageSm
          alt={'random'}
          src={'https://source.unsplash.com/random'}
        />
        {isLiked ? (
          <HeartFill className="absolute bottom-[4px] right-[8px]" />
        ) : (
          <Heart className="absolute bottom-[4px] right-[8px]" />
        )}
      </div>
      <div className="mt-[8px] flex-col gap-[8px]">
        <div className="truncate text-body ">{title}</div>
        <div className="flex gap-[6px] justify-center">
          <img
            alt="userImage"
            className="w-[20px] h-[20px] rounded-full"
            src={userImgUrl}
          />
          <div className="text-footnote text-grey">{userName}</div>
        </div>
      </div>
    </div>
  )
}

export default CardSm
