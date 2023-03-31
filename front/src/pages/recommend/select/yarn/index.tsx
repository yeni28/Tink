import React from 'react'

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
    </div>
  )
}

export default YarnSelectRecommend
