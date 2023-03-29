import React, { useState } from 'react'

interface cardProps {
  category: string
  title: string
  thumbnail: string
  video: string
  onClick: () => void
  getExplain: (category: string) => void
}

function Card({
  category,
  title,
  thumbnail,
  video,
  onClick,
  getExplain,
}: cardProps) {
  const handleMouseOver = (e: React.MouseEvent<HTMLVideoElement>) => {
    e.currentTarget.play()
    getExplain(category)
  }

  const handleMouseOut = (e: React.MouseEvent<HTMLVideoElement>) => {
    e.currentTarget.pause()
    getExplain('')
  }
  return (
    <div
      className="w-[19.16rem] h-[21.5rem] relative cursor-pointer rounded-[1.25rem] overflow-hidden hover:scale-105 duration-500"
      onClick={onClick}
    >
      <div className="absolute top-0 w-full h-full rounded-[1.25rem] pointer-events-none box-border z-[2] " />
      {/* <div className="w-96 h-96 bg-white opacity-70 absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2" /> */}
      <img
        alt="image"
        className="absolute top-0  object-cover w-full h-full z-[1] rounded-[1.25rem]"
        src={thumbnail}
      />
      <div>
        <div className="w-96 h-96 bg-white opacity-50 absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 z-[3]" />
        <div className="w-full absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 flex flex-col items-center z-[4]">
          <p className="text-pop text-title1-bold ">{title}</p>
        </div>
      </div>
      <video
        loop
        muted
        className="z-10 absolute top-0 object-cover w-full min-w-full min-h-full opacity-0 hover:opacity-100  rounded-[1.25rem] "
        preload="none"
        style={{ aspectRatio: 19.16 / 21.5 }}
        onMouseOut={handleMouseOut}
        onMouseOver={handleMouseOver}
      >
        <source src={video} type="video/mp4" />
      </video>
    </div>
  )
}

export default Card
