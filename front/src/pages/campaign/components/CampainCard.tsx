import React from 'react'

function CampainCard({
  boardId,
  title,
  content,
  period,
  organizer,
  ImgUrl,
  onClick,
  Link,
}: CardCampainProps) {
  return (
    <div onClick={onClick}>
      <div
        className="w-[30rem] h-[15rem]  rounded-[.5rem]  bg-cover"
        style={{ backgroundImage: `url(${ImgUrl})` }}
      ></div>
      <p className="text-title2-bold mt-[1rem]">{title}</p>
      <p className="text-body mt-[.5rem]">{content}</p>
      <p className="text-body mt-[.3rem] text-grey">{period}</p>
      <p className="text-headline-bold mt-[.5rem]">{organizer}</p>
    </div>
  )
}

export default CampainCard
