import React from 'react'
interface VideoProps {
  video: string
}
function MainVideo({ video }: VideoProps) {
  return (
    <div className="h-[100vh]">
      <video
        autoPlay
        loop
        muted
        playsInline
        className=" w-full object-cover   "
      >
        <source src={video} type="video/mp4" />
      </video>
    </div>
  )
}

export default MainVideo
