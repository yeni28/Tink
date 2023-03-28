import React, { useState } from 'react'

interface cardProps {
  title: string
  thumbnail: string
  video: string
  onClick: () => void
}

function Card({ title, thumbnail, video, onClick }: cardProps) {
  const [isHovering, setIsHovering] = useState(false)

  const handleMouseOver = (e: React.MouseEvent<HTMLVideoElement>) => {
    e.currentTarget.play()
  }

  const handleMouseOut = (e: React.MouseEvent<HTMLVideoElement>) => {
    e.currentTarget.pause()
  }
  return (
    <div
      className="w-[19.16rem] h-[21.5rem] relative cursor-pointer rounded-[1.25rem] overflow-hidden"
      onClick={onClick}
    >
      <div className="absolute top-0 w-full h-full rounded-[1.25rem] pointer-events-none box-border z-[2] " />
      {/* <div className="w-96 h-96 bg-white opacity-70 absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2" /> */}
      {/* <div className="w-full absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 flex flex-col items-center">
          <p className="text-largetitle-bold">{title}</p>
        </div> */}
      <img
        alt="image"
        className="absolute top-0  object-cover w-full h-full z-[1] rounded-[1.25rem]"
        src={thumbnail}
      />
      <video
        loop
        muted
        className="z-10 absolute top-0 object-cover w-full min-w-full min-h-full opacity-0 hover:opacity-100  rounded-[1.25rem]"
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
