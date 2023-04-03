import { useEffect } from 'react'

import { atom } from 'recoil'

import { recoilPersist } from 'recoil-persist'

const { persistAtom } = recoilPersist()

export const LoginState = atom<boolean>({
  key: 'LoginState',
  default: false,
  // atom을 위한 Attom Effect 배열
  effects_UNSTABLE: [persistAtom],
})
