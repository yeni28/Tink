import React from 'react'

import { pattern_dummy } from './PatternsDummy'

import molecules from '@/components/molecules'

function Patterns() {
  return (
    <div className="grid grid-cols-4 gap-x-12 gap-y-10 justify-items-center">
      {pattern_dummy.map((item) => {
        return (
          <molecules.CardSm
            key={item.id}
            isLiked={false}
            src={item.src}
            title={item.title}
            userImgUrl={item.userImgUrl}
            userName={item.userName}
          />
        )
      })}
    </div>
  )
}

export default Patterns
