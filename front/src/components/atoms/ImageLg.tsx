import React from 'react'

<<<<<<< HEAD
function ImageLg({ src, alt }: imageProps) {
  return (
    <div>
      <div className="h-[23.45rem] w-[20.88rem] rounded-[1.25rem] overflow-hidden hover:opacity-25">
        <img alt={alt} src={src} />
      </div>
      <div className="bg-black invisible hover:visible"></div>
=======
interface ImageLgProps {
  src: string
  alt?: string
}

function ImageLg({ src, alt }: ImageLgProps) {
  return (
    <div>
      <div className="h-[23.45rem] w-[20.88rem] rounded-[1.25rem] overflow-hidden">
        <img alt={alt} className="w-full h-full" src={src} />
      </div>
>>>>>>> e9b114d89eb74204e69852db211cbf9edf48d1f2
    </div>
  )
}

export default ImageLg
