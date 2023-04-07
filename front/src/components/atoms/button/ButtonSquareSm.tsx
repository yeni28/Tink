import React from 'react'

import { backgroundColor, txtColor } from './color'

function ButtonSquareSm({
  bgColor,
  textColor,
  innerValue,
  onClick,
}: ButtonProps) {
  return (
    <button
      className={` flex justify-center items-center rounded-[0.63rem] w-24 h-10 text-headline-bold ${backgroundColor[bgColor]} ${txtColor[textColor]}`}
      type="button"
      onClick={onClick}
    >
      {innerValue}
    </button>
  )
}

export default ButtonSquareSm
