import React from 'react'

interface ImageLgProps {
  src: string
  alt?: string
}

function ImageLg({ src, alt }: ImageLgProps) {
  return (
    <div>
      <img
        alt={alt}
        height="375px"
        src={src}
        style={{ borderRadius: '20px' }}
        width="334px"
      />
    </div>
  )
}

export default ImageLg
