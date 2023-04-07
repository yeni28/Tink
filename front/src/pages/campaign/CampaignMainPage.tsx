import React from 'react'

import CampainCard from './components/CampainCard'
import { CAMPAINS } from './components/dummy'

import drawingline from '@/assets/drawings/drawingline.png'
function CampaignMainPage() {
  return (
    <div>
      <img alt="" className="mb-[2rem]" src={drawingline} />
      <div className="grid grid-cols-2 gap-x-1 gap-y-10 justify-items-center mb-[5rem] cursor-pointer">
        {CAMPAINS.map((item) => {
          return (
            <CampainCard
              key={item.boardId}
              ImgUrl={item.ImgUrl}
              Link={item.Link}
              boardId={item.boardId}
              content={item.content}
              organizer={item.organizer}
              period={item.period}
              title={item.title}
              onClick={() => window.open(`${item.Link}`, '_blank')}
            />
          )
        })}
      </div>
    </div>
  )
}

export default CampaignMainPage
