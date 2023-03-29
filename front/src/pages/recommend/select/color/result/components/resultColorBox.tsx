import React, { useRef } from 'react'

import { analogue } from 'simpler-color'

function resultColorBox(color: string) {
  const colors = []
  if (color !== '') {
    const brandColor = color
    let n = -2
    while (n < 3) {
      colors.push(analogue(brandColor, n))
      n++
    }
  }

  return (
    <>
      {colors.map((color: string, idx: number) => {
        return (
          <div key={`${color}-${idx}`}>
            <div
              className="w-44 h-44 mb-2"
              style={{ backgroundColor: color }}
            ></div>
            <p className="text-center">{color}</p>
          </div>
        )
      })}
    </>
  )
}

export default resultColorBox
