import React from 'react'

import { FinalColor } from 'extract-colors/lib/types/Color'

function extractedColorbox(
  colors: FinalColor[],
  setSelectColor: (color: string) => void
) {
  return (
    <>
      {colors.map((color: FinalColor, idx: number) => {
        return (
          <div key={`${color.hex}-${idx}`}>
            <div
              className="w-44 h-44 mb-2 cursor-pointer"
              style={{ backgroundColor: color.hex }}
              onClick={() => setSelectColor(color.hex)}
            ></div>
            <p className="text-center">{color.hex}</p>
          </div>
        )
      })}
    </>
  )
}

export default extractedColorbox
