import React from 'react'

interface imageProps {
  src: string
  alt?: string
  width?: number
  height?: number
}

function ImageSm({ src, alt = 'image small' }: imageProps) {
  return (
    <img
      alt={alt}
      className="object-cover w-[14.25rem] h-[11.625rem] rounded-[1.25rem]"
      src={src}
    />
  )
}

export default ImageSm
