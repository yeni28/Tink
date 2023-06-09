import React from 'react'

import Doodle from '@/assets/drawings/ButtonDoodle.png'

interface ButtonDoodleProps {
  type?: 'button' | 'submit' | 'reset'
  innerValue: string
  onClick?: () => void
}

function ButtonDoodle({ innerValue, onClick, type }: ButtonDoodleProps) {
  return (
    <button
      className="font-cha text-title2 text-white relative"
      type={type}
      onClick={onClick}
    >
      <img alt="doodle" className="w-[8.44rem] h-[3.94rem] " src={Doodle} />
      <p className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">
        {innerValue}
      </p>
    </button>
  )
}

export default ButtonDoodle
