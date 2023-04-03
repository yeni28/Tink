import React, { useEffect } from 'react'

import { useNavigate } from 'react-router-dom'

import axios from 'axios'

import { useRecoilState } from 'recoil'

import { HOST, REDIRECT_URI } from './OAuth'

import { LoginState } from '@/components/atoms/state/LoginState'

function Login() {
  const navigate = useNavigate()

  const [isLogin, setIsLogin] = useRecoilState<boolean>(LoginState)
  const token = window.location.href.split('?token=')[1]

  useEffect(() => {
    if (token) localStorage.setItem('tinktoken', token)
    if (localStorage.getItem('tinktoken')) setIsLogin(true)
  }, [])

  useEffect(() => {
    ;(async () => {
      // const PARAMS = new URL(document.location).searchParams
      // const KAKAO_CODE = PARAMS.get('code')

      const backHost = `${HOST}/tink/oauth2/authorization/google?redirect_url=${REDIRECT_URI}`
      await axios({
        url: backHost,
        method: 'GET',
      })
        .then((res) => {
          console.log(res)
          const token = res.headers.authorization
          window.localStorage.setItem('token', token)
          // 로컬스토리지에 토큰이 있으면 메인페이지로 이동
          // 없으면 로그인 페이지로 리다이렉트
          if (token) {
            navigate('/main')
          } else {
            navigate('/')
          }
        })
        .catch((err) => {
          console.log(err)
        })
    })()
  }, [])
  return <div>Login</div>
}

export default Login
