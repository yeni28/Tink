import React from 'react'

import { backgroundColor, txtColor } from './color'

function ButtonSquareMd2({
  bgColor,
  textColor,
  innerValue,
  onClick,
}: ButtonProps) {
  return (
    <button
      className={` flex justify-center items-center rounded-[0.63rem] w-[11.25rem] h-12 text-title3-bold font-pop ${backgroundColor[bgColor]} ${txtColor[textColor]}`}
      type="button"
      onClick={onClick}
    >
      {innerValue}
    </button>
  )
}

export default ButtonSquareMd2
