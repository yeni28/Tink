import React from 'react'

interface TitleProps {
  title: string
  subtitle?: string
}

function TitleBlock({ title, subtitle }: TitleProps) {
  return (
    <div
      className="mt-[2.63rem] mb-16 flex flex-col
     items-center"
    >
      <p className="mb-5 font-pop text-supertitle-bold">{title}</p>
      <p className="text-title3">{subtitle}</p>
    </div>
  )
}

export default TitleBlock
