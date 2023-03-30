import React, { useEffect, useRef } from 'react'

import gsap from 'gsap'
import { ScrollTrigger } from 'gsap/ScrollTrigger'

import tuto from '@/styles/tutorial.module.css'

gsap.registerPlugin(ScrollTrigger)
const rows = 1
const columns = 8
const missingImages = 0 // The number of missing images in the last column
const frame_count = rows * columns - missingImages - 1

const imageWidth = 15360 // This is an approximate value
const imageHeight = 1080 // This is an approximate value
const horizDiff = imageWidth / columns
const vertDiff = imageHeight / rows

function KnitTutorial() {
  const imageViewer = useRef(null)
  const imageScene = useRef(null)
  useEffect(() => {
    gsap.set(imageViewer.current, { width: horizDiff, height: vertDiff })
    const setPos = gsap.quickSetter(imageViewer.current, 'background-position')

    const obj = { num: 0 }
    gsap.to(obj, {
      num: frame_count,
      ease: 'steps(' + frame_count + ')',
      scrollTrigger: {
        trigger: imageScene.current,
        start: 'top top',
        end: '+=' + imageHeight,
        pin: true,
        anticipatePin: 1,
        scrub: true,
        markers: true,
      },
      onUpdate: () =>
        setPos(`
      ${-Math.round(Math.floor(obj.num / rows) * horizDiff)}px
      ${-Math.round((obj.num % rows) * vertDiff)}px
    `),
    })
  })

  return (
    <div>
      {/* 이미지 영역 */}
      <section
        ref={imageScene}
        className="w-full h-screen bg-pink absolute left-0 top-30 "
      >
        <div ref={imageViewer} className={tuto.imageBox}></div>
        {/* <div ref={imageViewer}></div> */}
      </section>

      {/* 3d model 영역 */}
      {/* <section className="w-full h-screen bg-red absolute left-0 top-100"></section> */}
    </div>
  )
}

export default KnitTutorial
