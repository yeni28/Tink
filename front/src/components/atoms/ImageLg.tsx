import React from 'react'

function ImageLg({ src, alt }: ImageProps) {
  return (
    <div>
      <div className="h-[23.45rem] w-[20.88rem] rounded-[1.25rem] overflow-hidden hover:opacity-25">
        <img alt={alt} src={src} />
      </div>
    </div>
  )
}

export default ImageLg
