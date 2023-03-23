import React from 'react'

import spotlight from '@/assets/drawings/spotlight.gif'
import yarn from '@/assets/drawings/yarn.png'
import redmufflerknitcat from '@/assets/drawings/빨간목도리뜨개냥이.png'
import organisms from '@/components/organisms'

function RecommendMainPage() {
  return (
    <div>
      <figure className="absolute bottom-1 right-3 inline-block">
        <img
          alt="빨간목도리뜨개냥이"
          className="w-[25%] h-[25%]"
          src={redmufflerknitcat}
        />
      </figure>
      <figure className="absolute bottom-28 left-12">
        <img alt="yarn" className="w-[25%] h-[25%]" src={yarn} />
      </figure>
      <figure className="absolute top-28 left-12">
        <img alt="spotlight" className="w-[50%] h-[50%]" src={spotlight} />
      </figure>
      <organisms.TitleBlock
        subtitle="키워드를 선택하면 맞춤 도안을 추천해드려요!"
        title="Recommend"
      />
      <organisms.ariThreeCard
        first={{
          alt: 'imageLg',
          mainValue: '도안',
          src: 'https://images.unsplash.com/photo-1677274207822-e726bbba46e3?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY3OTQ3MDE3Mg&ixlib=rb-4.0.3&q=80&w=1080',
          subValue: '(기본적인 뜨개 방법을 알고 있다)',
          onClick: () => console.log('카드를 눌러봤다'),
        }}
        second={{
          alt: 'imageLg',
          mainValue: '도안',
          src: 'https://images.unsplash.com/photo-1677796503531-8bc3388d987e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY3OTQ3MDE1NA&ixlib=rb-4.0.3&q=80&w=1080',
          subValue: '(기본적인 뜨개 방법을 알고 있다)',
          onClick: () => console.log('카드를 눌러봤다'),
        }}
        third={{
          alt: 'imageLg',
          mainValue: '도안',
          src: 'https://source.unsplash.com/random',
          subValue: '(기본적인 뜨개 방법을 알고 있다)',
          onClick: () => console.log('카드를 눌러봤다'),
        }}
      />
    </div>
  )
}

export default RecommendMainPage
