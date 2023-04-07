import React from 'react'

function ImageMd({ src, alt }: ImageProps): JSX.Element {
  return (
    <div>
      <img
        alt={alt}
        className="object-cover rounded-3xl w-[356px] h-[218px] "
        referrerPolicy="no-referrer"
        src={src}
      />
    </div>
  )
}

export default ImageMd
