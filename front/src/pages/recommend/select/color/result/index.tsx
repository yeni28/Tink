import { useState, useEffect } from 'react'
import { useLocation } from 'react-router-dom'

import { extractColors } from 'extract-colors'
import { FinalColor } from 'extract-colors/lib/types/Color'

import underline1 from '@/assets/drawings/underline1.png'
import yarn from '@/assets/drawings/yarn.png'
import MufflerCat from '@/assets/drawings/빨간목도리뜨개냥이.png'
import NaughtyCat from '@/assets/drawings/털실 던지는 못된 냥이.png'
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
    <div className="flex flex-col items-center mt-16 pb-56 relative">
      <img alt="userImage" className="h-[23.06rem] mb-36" src={imageSrc} />

      {/* 사진에서 추출한 색상 */}
      <div className="flex flex-col items-center mb-32">
        <p className="text-title1">사진에서 추출한 색상</p>
        <img alt="underline" className="w-1/4 mb-10" src={underline1} />
        <div className="flex gap-5">
          {colorBox(colors, (color) => setSelectColor(color))}
        </div>
      </div>

      {/* 어울리는 색상 조합 */}
      <div className="flex flex-col items-center">
        <p className="text-title1">어울리는 색상 조합</p>
        <img alt="underline" className="w-1/4 mb-10" src={underline1} />
        <div className="flex gap-5">{resultColor(selectColor)}</div>
      </div>

      {/* 이미지영역 */}

      {/* 실 이미지 */}
      <div className="absolute -top-52 -right-[16rem] w-[12.75rem]">
        <img src={yarn} />
      </div>

      <div className="absolute w-[13rem] top-1/2 -left-64">
        <img src={NaughtyCat} />
      </div>

      <div className="absolute w-[13rem] bottom-0 -right-64">
        <img src={MufflerCat} />
      </div>
    </div>
  )
}

export default ResultColorSelectRecommend
