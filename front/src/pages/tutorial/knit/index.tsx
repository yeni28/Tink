import React, { useEffect, useRef, useState, useCallback } from 'react'

import { gsap } from 'gsap'
import { ScrollTrigger } from 'gsap/ScrollTrigger'

function KnitTutorial() {
  const divRef = useRef<any>(null)
  const [height, setHeight] = useState(0)

  useEffect(() => {
    if (divRef.current) {
      setHeight(divRef.current.offsetHeight)
    }

    gsap.registerPlugin(ScrollTrigger)
    gsap.utils.toArray<any>('section').forEach((section, i) => {
      ScrollTrigger.create({
        trigger: section,
        start: 'top 250',
        end: 'bottom bottom',
        markers: true,
        pin: true,
        pinSpacing: false,
      })
    })
    ScrollTrigger.create({
      snap: 'labels',
    })
  }, [])

  return (
    <div className="h-screen">
      <section
        ref={divRef}
        className="w-full h-full relative flex justify-center items-center bg-orange-400"
      >
        <h1>section01</h1>
      </section>
      <section
        ref={divRef}
        className="w-full h-full relative flex justify-center items-center bg-green-200"
      >
        <h1>section02</h1>
      </section>
      <section
        ref={divRef}
        className="w-full h-full relative flex justify-center items-center bg-fuchsia-300"
      >
        <h1>section03</h1>
      </section>
      <section
        ref={divRef}
        className="w-full h-full relative flex justify-center items-center bg-blue-200"
      >
        <h1>section04</h1>
      </section>
      <section
        ref={divRef}
        className="w-full h-full relative flex justify-center items-center bg-amber-300"
      >
        <h1>section05</h1>
      </section>
    </div>
  )
}

export default KnitTutorial
