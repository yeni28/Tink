import { useState } from 'react'

function ImageLg({ src, alt, mainValue, subValue, onClick, bgColor }: ImageLg) {
  const [isHovering, setIsHovering] = useState(false)

  const handleMouseOver = () => {
    setIsHovering(true)
  }

  const handleMouseOut = () => {
    setIsHovering(false)
  }

  return (
    <div
      className={`${
        bgColor ? `${bgColor}` : 'bg-white'
      } h-[23.44rem] w-[20.88rem] rounded-[1.25rem] overflow-hidden relative cursor-pointer`}
      onClick={onClick}
      onMouseOut={handleMouseOut}
      onMouseOver={handleMouseOver}
    >
      <img alt={alt} src={src} />
      {isHovering && (
        <div>
          <div className="w-96 h-96 bg-white opacity-70 absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2" />
          <div className="w-full absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 flex flex-col items-center">
            <p className="text-largetitle-bold">{mainValue}</p>
            <p>{subValue}</p>
          </div>
        </div>
      )}
    </div>
  )
}

export default ImageLg
