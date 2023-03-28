import React from 'react'

import Write from '../../components/CommunityWrite'

import DrawingLine from '@/assets/drawings/drawingline.png'
import InputBox from '@/assets/drawings/inputLineBox.png'
import StraitLine from '@/assets/drawings/straitline.png'

function WriteQuestionCommunity() {
  return (
    <div>
      <img alt="line" src={DrawingLine} />
      <div className="justify-items-center w-full">
        <img alt="border" className="m-auto" src={InputBox} width="70%" />

        <div className="absolute top-[53%] left-[50%] -translate-x-1/2 -translate-y-1/2 w-[30%]">
          <Write />
        </div>
      </div>
    </div>
  )
}

export default WriteQuestionCommunity
