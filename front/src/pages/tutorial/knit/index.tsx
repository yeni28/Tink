import React, { useEffect, useRef } from 'react'

import gsap from 'gsap'
import { ScrollTrigger } from 'gsap/ScrollTrigger'

import purl from '@/assets/sprite/purl_sprite.png'

gsap.registerPlugin(ScrollTrigger)
function KnitTutorial() {
  const imgRef = useRef(null)
  return (
    <div>
      {/* 이미지 영역 */}
      <section className="h-screen bg-pink">
        <div className="h-full mx-auto w-full bg-no-repeat bg-center bg-[url('@/assets/sprite/purl_sprite.png')]"></div>
        <img
          ref={imgRef}
          alt="purl_sprite"
          className="purlSprite"
          src={purl}
        ></img>
      </section>
    </div>
  )
}

export default KnitTutorial
