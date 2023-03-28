import React from 'react'

import Card from './Card'

import { THUMBNAIL_URL, VIDEO_URL } from './Media'

const moveToCaston = () => {
  console.log('hello')
}

function ThumbnailCards() {
  return (
    <div className="flex justify-between">
      <Card
        thumbnail={THUMBNAIL_URL.caston}
        title={'코잡기'}
        video={VIDEO_URL.caston_video}
        onClick={moveToCaston}
      />
      <Card
        thumbnail={THUMBNAIL_URL.knit}
        title={'겉뜨기'}
        video={VIDEO_URL.knit_video}
        onClick={moveToCaston}
      />
      <Card
        thumbnail={THUMBNAIL_URL.purl}
        title={'안뜨기'}
        video={VIDEO_URL.purl_video}
        onClick={moveToCaston}
      />
    </div>
  )
}

export default ThumbnailCards
