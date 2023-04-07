import React from 'react'
import { useNavigate } from 'react-router-dom'

import color from './components/color.jpg'
import pattern from './components/pattern.jpg'
import yarn2 from './components/yarn.jpg'

import spotlight from '@/assets/drawings/spotlight.gif'
import yarn from '@/assets/drawings/yarn.png'
import redMufflerKnitCat from '@/assets/drawings/빨간목도리뜨개냥이.png'
import organisms from '@/components/organisms'

function RecommendMainPage() {
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
          mainValue: '도안',
          src: pattern,
          onClick: () => navigate('/recommend/select/pattern'),
        }}
        second={{
          alt: 'imageLg',
          mainValue: '실',
          src: yarn2,
          onClick: () => navigate('/recommend/select/yarn'),
        }}
        third={{
          alt: 'imageLg',
          mainValue: '색 조합',
          src: color,
          onClick: () => navigate('/recommend/select/color'),
        }}
      />
    </div>
  )
}

export default RecommendMainPage
