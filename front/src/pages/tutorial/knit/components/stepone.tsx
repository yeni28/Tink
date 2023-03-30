import React, { useEffect, useRef } from 'react'

import {
  Environment,
  OrbitControls,
  PerspectiveCamera,
} from '@react-three/drei'

import { useFrame } from '@react-three/fiber'
import gsap from 'gsap'
import * as THREE from 'three'

import type { OrbitControls as OrbitControlsImpl } from 'three-stdlib'

import { Knit_1_complete } from '@/assets/tutorial/knit/step_one/Knit_1_complete'

import { angleToRadians } from '@/utills/angle'

function Stepone() {
  // Code to move around camera
  const orbitControlRef = useRef<OrbitControlsImpl>(null)
  useFrame((state) => {
    if (orbitControlRef.current) {
      const { x, y } = state.mouse
      orbitControlRef.current.setAzimuthalAngle(-x * angleToRadians(45))
      orbitControlRef.current.setPolarAngle(y + 0.5 + angleToRadians(90 - 60))
      orbitControlRef.current.update()
    }
  })

  // Animation
  const ballRef = useRef<THREE.Mesh>(null)
  useEffect(() => {
    if (ballRef.current) {
      const timeline = gsap.timeline()
      // xaxis motion
      timeline.to(
        ballRef.current.position,
        {
          x: 1,
          duration: 2,
          ease: 'power2.out',
        },
        '<'
      )
      timeline.to(ballRef.current.position, {
        y: 0.5,
        duration: 0.5,
        ease: 'power4.in',
      })

      const coefficient = 0.8
      for (let i = 1; i <= 4; i++) {
        // Going up
        timeline.to(
          ballRef.current.position,
          {
            y: Math.pow(coefficient, i) * 1.5,
            duration: 0.2,
            ease: 'power2.out',
          },
          '>'
        )

        // Going down
        timeline.to(
          ballRef.current.position,
          {
            y: 0.5,
            duration: 0.2,
            ease: 'power2.in',
          },
          '>'
        )
      }
    }
  }, [ballRef.current])
  return (
    <>
      <PerspectiveCamera makeDefault position={[0, 1, 5]} />
      <OrbitControls
        ref={orbitControlRef}
        maxPolarAngle={angleToRadians(80)}
        minPolarAngle={angleToRadians(40)}
      />
      {/* circle */}
      {/* <mesh ref={ballRef} castShadow position={[-2, 1.5, 0]}>
        <sphereGeometry args={[0.5, 32, 32]} />
        <meshStandardMaterial color="#FFFFFF" metalness={0.5} roughness={0.2} />
      </mesh> */}

      {/* car */}
      <Knit_1_complete />
      {/* floor */}
      {/* <mesh receiveShadow rotation={[-angleToRadians(90), 0, 0]}>
        <planeGeometry args={[20, 20]} />
        <meshStandardMaterial color="#60FC4E" />
      </mesh> */}
      {/* ambient Light */}
      <ambientLight args={['#ffffff', 0.25]} />

      {/* direnctional Light */}
      <spotLight
        castShadow
        args={['#ffffff', 1.5, 7, angleToRadians(45), 0.3]}
        position={[-3, 1, 0]}
      />

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
