import React from 'react'

import molecules from '@/components/molecules'

interface props {
  datas: {
    id: number
    name: string
    thumbnails: {
      mainImg: string
      thumbImg: string
    }[]
  }[]
}

function Patterns({ datas }: props) {
  return (
    <div className="grid grid-cols-4 gap-x-10 gap-y-10 justify-items-center">
      {datas.map((data, idx) => {
        return (
          <molecules.CardSm
            key={data.id}
            src={data.thumbnails[0].mainImg}
            title={String(idx)}
          />
        )
      })}
    </div>
  )
}

export default Patterns
