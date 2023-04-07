import React, { useRef, useLayoutEffect, Suspense } from 'react'
import { useNavigate } from 'react-router-dom'

import { Canvas } from '@react-three/fiber'
import gsap from 'gsap'
import { ScrollTrigger } from 'gsap/ScrollTrigger'

import { Knit1 } from './components/model/Knit1'

import ThreeModel from '../components/ThreeModel'

import snailCat from '@/assets/drawings/털실에깔린냥이.png'
import atoms from '@/components/atoms'
import explain_box from '@/pages/tutorial/components/atoms/explain_box.png'

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
  const explainBox = useRef(null)
  const comp = useRef()

  useLayoutEffect(() => {
    gsap.set(imageViewer.current, { width: horizDiff, height: vertDiff })
    const setPos = gsap.quickSetter(imageViewer.current, 'background-position')

    const obj = { num: 0 }
    const ctx = gsap.context(() => {
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
          // markers: true,
        },
        onUpdate: () =>
          setPos(`
        ${-Math.round(Math.floor(obj.num / rows) * horizDiff)}px
        ${-Math.round((obj.num % rows) * vertDiff)}px
      `),
      })
      gsap.to(explainBox.current, {
        x: '40vw',
        ease: 'power2',
        scrollTrigger: {
          trigger: explainBox.current,
          start: 'top top',
          end: '+=' + 1000,
          scrub: true,
          // markers: true,
        },
      })
    }, comp)
    return () => ctx.revert()
  }, [])

  const navigate = useNavigate()

  return (
    <div>
      {/* 이미지 영역 */}

      <section ref={imageScene} className="w-full h-full relative">
        <div
          ref={imageViewer}
          className="w-auto h-full aspect-video
  mx-auto 
  bg-no-repeat bg-cover  bg-left bg-[url('@/assets/sprite/knit1.png')]"
        ></div>
        <div ref={explainBox} className="relative mt-20 w-[300px]">
          <img src={explain_box} />
          <div className="absolute top-6 left-20">
            <p className="font-cha text-body">
              바늘을 아래에서 위로 올려주라냥
            </p>
            <p className="font-cha text-title2-bold">3d로 돌려보라냥</p>
          </div>
        </div>
      </section>

      {/* 3d model 영역 */}
      <section className="w-full h-screen relative ">
        <Canvas shadows className="h-screen">
          <Suspense fallback={null}>
            <ThreeModel model={<Knit1 />} />
          </Suspense>
        </Canvas>
        <div className="w-[20.625rem] h-[12rem] bg-pink bg-opacity-50 rounded-3xl py-8 px-6 absolute bottom-20 left-20">
          <p className="text-title3">스크롤 : 축소 / 확대</p>
          <p className="text-title3">드래그 : 카메라 회전</p>
          <p className="text-title3"> Shift + 드래그 : 위치 이동</p>
          <div className="w-[7rem] absolute bottom-4 right-4">
            <img src={snailCat} />
          </div>
        </div>
        <div className="absolute bottom-20 right-20">
          <atoms.ButtonDoodle
            innerValue=">  다음"
            onClick={() => navigate('/tutorial/knit/2')}
          />
        </div>
      </section>
    </div>
  )
}

export default KnitTutorial
