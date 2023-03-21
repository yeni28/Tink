import React from 'react'

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
    </div>
  )
}

export default ImageLg
