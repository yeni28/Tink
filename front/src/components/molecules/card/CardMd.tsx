import React, { useState } from 'react'

import atoms from '@/components/atoms'

function CardMd({
  nickname,
  patternThumnail,
  title,
  hit,
  liked,
  onClick,
}: CardMdProps) {
  return (
    <div className="w-[22.25rem]">
      <div className="flex mb-2">
        <p className="text-headline mr-3">{nickname}</p>
        <div className="flex items-center"></div>
      </div>
      <div className="cursor-pointer " onClick={onClick}>
        <atoms.ImageMd src={patternThumnail} />
      </div>
      <p className="my-2 text-title2-bold flex justify-center">{title}</p>
      <div className="flex justify-center items-center">
        <span className="text-footnote text-grey mr-1">조회수</span>
        <span className="text-footnote text-grey">{hit}</span>
        <div className="w-1 h-1 rounded-lg bg-grey inline-block mx-1" />
        <span className="text-footnote text-grey mr-1">좋아요</span>
        <span className="text-footnote text-grey">{liked}</span>
      </div>
    </div>
  )
}

export default CardMd
