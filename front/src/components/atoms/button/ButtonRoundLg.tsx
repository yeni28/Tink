import React from 'react'

import { backgroundColor, txtColor } from './color'

function ButtonRoundLg({
  bgColor,
  textColor,
  innerValue,
  onClick,
}: ButtonProps) {
  return (
    <button
      className={` flex justify-center items-center rounded-[3.13rem] w-[22.88rem] h-20 text-title2-bold ${backgroundColor[bgColor]} ${txtColor[textColor]}`}
      type="button"
      onClick={onClick}
    >
      {innerValue}
    </button>
  )
}

export default ButtonRoundLg
