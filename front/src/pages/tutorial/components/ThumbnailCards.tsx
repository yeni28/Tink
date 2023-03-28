import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'

import Card from './Card'

import { THUMBNAIL_URL, VIDEO_URL } from './Media'

function ThumbnailCards() {
  const navigate = useNavigate()
  const moveToCaston = (category: string) => {
    navigate(`/tutorial/${category}`)
  }
  return (
    <div className="flex justify-between ">
      <Card
        thumbnail={THUMBNAIL_URL.caston}
        title={'코잡기'}
        video={VIDEO_URL.caston_video}
        onClick={() => moveToCaston('caston')}
      />

      <Card
        thumbnail={THUMBNAIL_URL.knit}
        title={'겉뜨기'}
        video={VIDEO_URL.knit_video}
        onClick={() => moveToCaston('knit')}
      />

      <Card
        thumbnail={THUMBNAIL_URL.purl}
        title={'안뜨기'}
        video={VIDEO_URL.purl_video}
        onClick={() => moveToCaston('purl')}
      />
    </div>
  )
}

export default ThumbnailCards
