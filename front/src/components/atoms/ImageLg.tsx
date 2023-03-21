import React from 'react'

function ImageLg({ src, alt }: imgProps) {
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
