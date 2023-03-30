import React from 'react'

import Write from '../../components/CommunityWrite'

import DrawingLine from '@/assets/drawings/drawingline.png'
import InputBox from '@/assets/drawings/inputlinebox.png'
import StraitLine from '@/assets/drawings/straitline.png'
import atoms from '@/components/atoms'

function WriteGroupCommunity() {
  return (
    <div>
      <img alt="line" src={DrawingLine} />
      <div className="flex flex-col items-center">
        <img alt="border" className="relative" src={InputBox} width="75%" />

        <div className="mt-[6rem] flex justify-center absolute  w-[42rem]">
          <Write />
        </div>
      </div>
      <div className="fixed top-[24rem] right-[6rem]">
        <atoms.ButtonDoodle
          innerValue="올리기"
          onClick={() => console.log('ButtonDoodle')}
        />
      </div>
    </div>
  )
}

export default WriteGroupCommunity
