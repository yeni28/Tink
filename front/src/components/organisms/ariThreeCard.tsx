import React from 'react'

import atoms from '@/components/atoms'

interface ariThreeCardProps {
  first: ImageLg
  second: ImageLg
  third: ImageLg
}

function ariThreeCard({ first, second, third }: ariThreeCardProps) {
  return (
    <div className="flex justify-between">
      <atoms.ImageLg
        alt={first?.alt}
        bgColor={first?.bgColor}
        mainValue={first.mainValue}
        src={first.src}
        subValue={first?.subValue}
        onClick={first?.onClick}
      />
      <atoms.ImageLg
        alt={second?.alt}
        bgColor={second?.bgColor}
        mainValue={second.mainValue}
        src={second.src}
        subValue={second?.subValue}
        onClick={second?.onClick}
      />
      <atoms.ImageLg
        alt={third?.alt}
        bgColor={third?.bgColor}
        mainValue={third.mainValue}
        src={third.src}
        subValue={third?.subValue}
        onClick={third?.onClick}
      />
    </div>
  )
}

export default ariThreeCard
