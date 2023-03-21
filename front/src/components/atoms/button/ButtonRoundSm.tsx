import React from 'react'

import { backgroundColor, txtColor } from './color'

function ButtonRoundSm({
  bgColor,
  textColor,
  innerValue,
  onClick,
}: ButtonProps) {
  return (
    <button
      className={`flex justify-center items-center rounded-[3.13rem] w-[7.5rem] h-12 text-title3-bold ${backgroundColor[bgColor]} ${txtColor[textColor]}`}
      type="button"
      onClick={onClick}
    >
      {innerValue}
    </button>
  )
}

export default ButtonRoundSm
