import React from 'react'
import { NavLink, useNavigate } from 'react-router-dom'

import spotlight from '@/assets/drawings/spotlight.gif'
import yarn from '@/assets/drawings/yarn.png'
import redMufflerKnitCat from '@/assets/drawings/빨간목도리뜨개냥이.png'
import advancedhaCat from '@/assets/drawings/상냥이 배경.png'
import middleCat from '@/assets/drawings/중냥이 배경.png'
import newbieCat from '@/assets/drawings/하냥이 배경.png'
import organisms from '@/components/organisms'

function FirstRecommend() {
  const navigate = useNavigate()
  return (
    <div className="relative">
      <figure className="absolute top-28 -right-44 w-[10%] h-[10%]">
        <img alt="spotlight" src={spotlight} />
      </figure>
      <figure className="absolute -bottom-32 -left-52 w-[15%] h-[15%]">
        <img alt="yarn" src={yarn} />
      </figure>
      <figure className="absolute -bottom-28 -right-72 w-[20%] h-[20%]">
        <img alt="빨간목도리뜨개냥이" src={redMufflerKnitCat} />
      </figure>
      <organisms.ariThreeCard
        first={{
          alt: 'imageLg',
          mainValue: '하',
          subValue: '(뜨개를 처음 시작한다)',
          src: newbieCat,
          onClick: () => {
            navigate('/recommend/first/favorite', {
              state: { difficulty: 'low' },
            })
          },
          bgColor: 'bg-[#5498f3]',
        }}
        second={{
          alt: 'imageLg',
          mainValue: '중',
          subValue: '(기본적인 뜨개 방법을 알고 있다)',
          src: middleCat,
          onClick: () => {
            navigate('/recommend/first/favorite', {
              state: { difficulty: 'middle' },
            })
          },
          bgColor: 'bg-[#7BACED]',
        }}
        third={{
          alt: 'imageLg',
          mainValue: '상',
          subValue: '(어떤 도안이든 보고 만들 수 있다)',
          src: advancedhaCat,
          onClick: () => {
            navigate('/recommend/first/favorite', {
              state: { difficulty: 'high' },
            })
          },
          bgColor: 'bg-mint',
        }}
      />
    </div>
  )
}

export default FirstRecommend
