import React, { useEffect } from 'react'

import { useNavigate } from 'react-router-dom'

import axios from 'axios'

import { useRecoilState } from 'recoil'

import { HOST, REDIRECT_URI } from './OAuth'

import { LoginState } from '@/components/atoms/state/LoginState'

function Login() {
  const navigate = useNavigate()

  const [isLogin, setIsLogin] = useRecoilState<boolean>(LoginState)
  // const token = window.location.href.split('?token=')[1]

  // useEffect(() => {
  //   if (token) localStorage.setItem('tinktoken', token)
  //   if (localStorage.getItem('tinktoken')) setIsLogin(true)
  // }, [])

  useEffect(() => {
    ;(async () => {
      const PARAMS = new URL(document.location.toString()).searchParams
      // 최초 가입자인지 확인(DB에 저장된 선호 조사 확인 결과)
      const IS_CHECK = PARAMS.get('isCheck')
      // 로그인 확인
      const ACCESS_TOKEN = PARAMS.get('accessToken')

      if (IS_CHECK) localStorage.setItem('isCheck', IS_CHECK)

      console.log({ IS_CHECK })
      console.log({ ACCESS_TOKEN })

      const backHost = `${HOST}/oauth2/authorization/google?redirect_uri=${REDIRECT_URI}`

      if (localStorage.getItem('isCheck')) {
        navigate('/main')
      } else {
        navigate('/recommend/first')
      }
      await axios({
        url: backHost,
        method: 'GET',
      })
        .then((res) => {
          console.log(res)
          // const token = res.headers.authorization
          // window.localStorage.setItem('token', token)
          // 로컬스토리지에 토큰이 있으면 메인페이지로 이동
          // 없으면 로그인 페이지로 리다이렉트
          // if (token) {
          //   navigate('/main')
          // } else {
          //   navigate('/')
          // }
        })
        .catch((err) => {
          console.log(err)
        })
    })()
  }, [])
  return <div>Login</div>
}

export default Login
