import React from 'react'

function ImageMd({ src, alt }: imgProps): JSX.Element {
  return (
    <div>
      <img
        alt={alt}
        className="object-cover rounded-3xl w-[356px] h-[218px]"
        src={src}
      />
    </div>
  )
}

export default ImageMd
