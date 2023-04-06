import React from 'react'

import { useNavigate } from 'react-router-dom'

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
  const navigate = useNavigate()
  return (
    <div className="grid grid-cols-4 gap-x-10 gap-y-10 justify-items-center">
      {datas.map((data, idx) => {
        return (
          <div
            key={data.id}
            className="cursor-pointer"
            onClick={() => navigate(`/pattern/${data.id}`)}
          >
            <molecules.CardSm
              src={data.thumbnails[0].mainImg}
              title={data.name}
            />
          </div>
        )
      })}
    </div>
  )
}

export default Patterns
