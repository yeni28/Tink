import React, { useState } from 'react'

import CastonExplain from './components/CastonExplain'
import KnitExplain from './components/KnitExplain'
import PurlExplain from './components/PurlExplain'
import ThumbnailCards from './components/ThumbnailCards'

import bubble2 from '@/assets/drawings/bubble 2.png'
import mufflerCat from '@/assets/drawings/빨간목도리뜨개냥이.png'

function Tutorial() {
  return (
    <div>
      <div className="mt-20">
        <ThumbnailCards />
      </div>

      <div className="flex relative mt-8">
        {/* 말풍선 이미지 */}
        <div className="w-[53.82rem] h-[25.82rem]">
          <img alt="말풍선" className="w-full h-full" src={bubble2} />
        </div>

        {/* 고양이 이미지 */}
        <div className="w-[9.94rem] h-[10.13rem] absolute top-28 right-28">
          <img
            alt="빨간목도리냥이"
            className="w-full h-full"
            src={mufflerCat}
          />
        </div>
      </div>

      {/* 설명문 */}
      {/* <div>
        {explain === 'caston' ? (
          <CastonExplain />
        ) : explain === 'knit' ? (
          <KnitExplain />
        ) : explain === 'purl' ? (
          <PurlExplain />
        ) : (
          ''
        )}
      </div> */}
    </div>
  )
}

export default Tutorial
