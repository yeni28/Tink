import React from 'react'

import cat from './components/cat_in_basket.png'
import InputForm from './components/InputForm'

import yarnLineBox from '@/assets/drawings/yarnLineBox.png'

function YarnSelectRecommend() {
  return (
    <div className="relative">
      {/* form */}

      {/* line box */}
      <div className="absolute top-12 left-36 ">
        <img src={yarnLineBox} />

        {/* input form */}
        <div className="absolute top-16 left-40 text-center ">
          <InputForm />
        </div>
      </div>

      <div className="absolute -right-48 top-[28rem] w-[21.5rem] h-[14.3rem]">
        <img className="w-full" src={cat} />
      </div>
    </div>
  )
}

export default YarnSelectRecommend
