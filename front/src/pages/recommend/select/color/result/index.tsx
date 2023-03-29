import { useState, useEffect } from 'react'
import { useLocation } from 'react-router-dom'

import { extractColors } from 'extract-colors'
import { FinalColor } from 'extract-colors/lib/types/Color'

import underline1 from '@/assets/drawings/underline1.png'
import colorBox from '@/pages/recommend/select/color/result/components/extractedColorBox'
import resultColor from '@/pages/recommend/select/color/result/components/resultColorBox'

function ResultColorSelectRecommend() {
  const location = useLocation()
  const imageData = location.state.propImage[0]
  const imageSrc = URL.createObjectURL(imageData)
  const [colors, setColors] = useState<FinalColor[]>([])
  const [selectColor, setSelectColor] = useState<string>('')

  useEffect(() => {
    extractColors(imageSrc).then((extractedColors) => {
      extractedColors.sort((aColor, bColor) => {
        return bColor.area - aColor.area
      })
      const fourColors = extractedColors.slice(0, 4)
      setColors(fourColors)
      setSelectColor(fourColors[0].hex)
    })
  }, [])

  return (
    <div className="flex flex-col items-center mt-16 pb-56">
      <img alt="userImage" className="h-[23.06rem] mb-36" src={imageSrc} />
      <div className="flex flex-col items-center mb-32">
        <p className="text-title1">사진에서 추출한 색상</p>
        <img alt="underline" className="w-1/4 mb-10" src={underline1} />
        <div className="flex gap-5">
          {colorBox(colors, (color) => setSelectColor(color))}
        </div>
      </div>
      <div className="flex flex-col items-center">
        <p className="text-title1">어울리는 색상 조합</p>
        <img alt="underline" className="w-1/4 mb-10" src={underline1} />
        <div className="flex gap-5">{resultColor(selectColor)}</div>
      </div>
    </div>
  )
}

export default ResultColorSelectRecommend
