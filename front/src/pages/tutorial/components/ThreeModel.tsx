import React, { useRef } from 'react'

import {
  Environment,
  OrbitControls,
  OrthographicCamera,
} from '@react-three/drei'

import * as THREE from 'three'

import type { OrbitControls as OrbitControlsImpl } from 'three-stdlib'

import { angleToRadians } from '@/utills/angle'

function ThreeModel({ model }: any) {
  // Code to move around camera
  const orbitControlRef = useRef<OrbitControlsImpl>(null)

  return (
    <>
      <OrthographicCamera makeDefault position={[0, 1, -2]} zoom={150} />
      {model}
      <ambientLight args={['#ffffff', 0.25]} />
      <spotLight
        castShadow
        args={['#ffffff', 1.5, 7, angleToRadians(45), 0.3]}
        position={[-3, 1, 0]}
      />
      <OrbitControls />
      <Environment>
        <mesh>
          <sphereGeometry args={[30, 100, 100]} />
          <meshBasicMaterial color="#ffffff" side={THREE.BackSide} />
        </mesh>
      </Environment>
    </>
  )
}
export default ThreeModel
