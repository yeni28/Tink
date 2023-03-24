import React from 'react'
import { NavLink } from 'react-router-dom'

import spotlight from '@/assets/drawings/spotlight.gif'
import yarn from '@/assets/drawings/yarn.png'
import redMufflerKnitCat from '@/assets/drawings/빨간목도리뜨개냥이.png'
import organisms from '@/components/organisms'

function RecommendMainPage() {
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
          src: 'https://images.unsplash.com/photo-1677274207822-e726bbba46e3?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY3OTQ3MDE3Mg&ixlib=rb-4.0.3&q=80&w=1080',
          onClick: () => console.log('카드를 눌러봤다'),
        }}
        second={{
          alt: 'imageLg',
          mainValue: '실',
          src: 'https://images.unsplash.com/photo-1677796503531-8bc3388d987e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY3OTQ3MDE1NA&ixlib=rb-4.0.3&q=80&w=1080',
          onClick: () => console.log('카드를 눌러봤다'),
        }}
        third={{
          alt: 'imageLg',
          mainValue: '색 조합',
          src: 'https://images.unsplash.com/photo-1677643492253-5f994feaf015?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY3OTYxODEwOQ&ixlib=rb-4.0.3&q=80&w=1080',
          onClick: () => console.log('카드를 눌러봤다'),
        }}
      />
    </div>
  )
}

export default RecommendMainPage
