import React from 'react'

function ImageUser({ src, alt }: ImageProps) {
  return (
    <div>
      <div
        className="h-[2.8rem] w-[2.88rem] rounded-[2rem]  bg-center bg-cover "
        style={{ backgroundImage: `url(${src})` }}
      >
        {/* <img alt={alt} src={src} /> */}
      </div>
    </div>
  )
}

export default ImageUser
