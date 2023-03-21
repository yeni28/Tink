import React from 'react'

function ButtonSquareRound({
  btnType,
  bgColor,
  textColor,
  innerValue,
  onClick,
}: ButtonProps) {
  const backgroundColor = { red: 'bg-red', black: 'bg-black' }

  const txtColor = { white: 'text-white', black: 'text-black' }

  const buttonType = {
    squareSm: 'rounded-[0.63rem] w-24 h-10 text-headline-bold',
    squareMd1: 'rounded-[0.63rem] w-[10.63rem] h-12 text-title3-bold font-pop',
    squareMd2: 'rounded-[0.63rem] w-[11.25rem] h-12 text-title3-bold font-pop',
    roundSm: 'rounded-[3.13rem] w-[7.5rem] h-12 text-title3-bold',
    roundLg: 'rounded-[3.13rem] w-[22.88rem] h-20 text-title2-bold',
  }

  return (
    <button
      className={` flex justify-center items-center ${buttonType[btnType]} ${backgroundColor[bgColor]} ${txtColor[textColor]}`}
      type="button"
      onClick={onClick}
    >
      {innerValue}
    </button>
  )
}

export default ButtonSquareRound
