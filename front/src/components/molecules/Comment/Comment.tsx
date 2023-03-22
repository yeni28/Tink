import React, { Dispatch, SetStateAction } from 'react'

import atoms from '@/components/atoms'

export default function Comment() {
  return (
    <div className="flex items-center">
      <atoms.ImageUser
        alt={'user'}
        src={'https://source.unsplash.com/random'}
      />
      <atoms.InputComment />
    </div>
  )
}
