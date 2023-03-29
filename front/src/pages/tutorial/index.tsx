import React, { useState } from 'react'

import { useNavigate } from 'react-router-dom'

import Card from './components/Card'
import explains from './components/explain'
import { THUMBNAIL_URL, VIDEO_URL } from './components/Media'

import bubble2 from '@/assets/drawings/bubble 2.png'
import mufflerCat from '@/assets/drawings/빨간목도리뜨개냥이.png'

function Tutorial() {
  const navigate = useNavigate()
  const moveToCaston = (category: string) => {
    navigate(`/tutorial/${category}`)
  }

  const [explain, setExplain] = useState('')
  const getExplain = (category: string) => {
    setExplain(category)
  }
  return (
    <div>
      <div className="mt-20">
        <div className="flex justify-between ">
          <Card
            category="caston"
            getExplain={getExplain}
            thumbnail={THUMBNAIL_URL.caston}
            title={'코잡기'}
            video={VIDEO_URL.caston_video}
            onClick={() => moveToCaston('caston')}
          />

          <Card
            category="knit"
            getExplain={getExplain}
            thumbnail={THUMBNAIL_URL.knit}
            title={'겉뜨기'}
            video={VIDEO_URL.knit_video}
            onClick={() => moveToCaston('knit')}
          />

          <Card
            category="purl"
            getExplain={getExplain}
            thumbnail={THUMBNAIL_URL.purl}
            title={'안뜨기'}
            video={VIDEO_URL.purl_video}
            onClick={() => moveToCaston('purl')}
          />
        </div>
      </div>

      <div className="relative">
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
        <div className="w-[53.82rem] h-[25.82rem] absolute -bottom-28 -left-7">
          {explain === 'caston' ? (
            <explains.CastonExplain />
          ) : explain === 'knit' ? (
            <explains.KnitExplain />
          ) : explain === 'purl' ? (
            <explains.PurlExplain />
          ) : (
            <explains.DefaultExpalin />
          )}
        </div>
      </div>
    </div>
  )
}

export default Tutorial
