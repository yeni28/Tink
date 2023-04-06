import React from 'react'

import CampainCard from './components/CampainCard'
import { CAMPAINS } from './components/dummy'
function CampaignMainPage() {
  return (
    <div>
      {CAMPAINS.map((item) => {
        return (
          <CampainCard
            key={item.boardId}
            ImgUrl={item.ImgUrl}
            boardId={item.boardId}
            content={item.content}
            organizer={item.organizer}
            period={item.period}
            title={item.title}
            onClick={() => void {}}
          />
        )
      })}
    </div>
  )
}

export default CampaignMainPage
