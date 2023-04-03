import React, { useEffect, useRef } from 'react'

import {
  Environment,
  OrbitControls,
  PerspectiveCamera,
  OrthographicCamera,
} from '@react-three/drei'

import { useFrame } from '@react-three/fiber'
import gsap from 'gsap'
import * as THREE from 'three'

import type { OrbitControls as OrbitControlsImpl } from 'three-stdlib'

import { Knit2 } from '@/pages/tutorial/knit/components/Knit2'

import { Knit_1_complete } from '@/pages/tutorial/knit/components/Knit_1_complete'

import { angleToRadians } from '@/utills/angle'

function Stepone() {
  // Code to move around camera
  const orbitControlRef = useRef<OrbitControlsImpl>(null)
  // useFrame((state) => {
  //   if (orbitControlRef.current) {
  //     const { x, y } = state.mouse
  //     // orbitControlRef.current.setAzimuthalAngle(-x * angleToRadians(45))
  //     // orbitControlRef.current.setPolarAngle(y + angleToRadians(45))
  //     orbitControlRef.current.update()
  //   }
  // })

  return (
    <>
      <OrthographicCamera makeDefault position={[0, 1, -2]} zoom={150} />
      {/* <OrbitControls
        ref={orbitControlRef}
        maxPolarAngle={angleToRadians(90)}
        minPolarAngle={angleToRadians(0)}
      /> */}
      <Knit_1_complete />
      {/* <Knit2 /> */}

      {/* ambient Light */}
      <ambientLight args={['#ffffff', 0.25]} />

      {/* direnctional Light */}
      <spotLight
        castShadow
        args={['#ffffff', 1.5, 7, angleToRadians(45), 0.3]}
        position={[-3, 1, 0]}
      />
      <OrbitControls />

      {/* environment */}
      <Environment>
        <mesh>
          <sphereGeometry args={[30, 100, 100]} />
          <meshBasicMaterial color="#ffffff" side={THREE.BackSide} />
        </mesh>
      </Environment>
    </>
  )
}
export default Stepone
