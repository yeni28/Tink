import React from 'react'

import { backgroundColor, txtColor } from './color'

function ButtonTag({ bgColor, textColor, innerValue, onClick }: ButtonProps) {
  return (
    <button
      className={`flex justify-center items-center rounded-[3.13rem] text-title3 px-[0.88rem] py-[0.31rem] ${backgroundColor[bgColor]} ${txtColor[textColor]}`}
      type="button"
      onClick={onClick}
    >
      {innerValue}
    </button>
  )
}

export default ButtonTag
