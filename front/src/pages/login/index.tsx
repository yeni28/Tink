import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

import axios from 'axios'
import { useRecoilState } from 'recoil'

import { HOST, REDIRECT_URI } from './OAuth'

import { axAuth } from '@/apis/axiosInstance'
import { LoginState } from '@/components/atoms/state/LoginState'

function Login() {
  const navigate = useNavigate()

  const [isLogin, setIsLogin] = useRecoilState<boolean>(LoginState)

  useEffect(() => {
    const PARAMS = new URL(document.location.toString()).searchParams
    // 최초 가입자인지 확인(DB에 저장된 선호 조사 확인 결과)
    const IS_CHECK = PARAMS.get('isCheck')
    // 로그인 확인
    const ACCESS_TOKEN = PARAMS.get('accessToken')

    if (IS_CHECK) localStorage.setItem('isCheck', IS_CHECK)
    if (ACCESS_TOKEN) localStorage.setItem('accessToken', ACCESS_TOKEN)

    axAuth({
      url: '/members/refresh',
    }).then((res) => {
      const REFRESH_TOKEN = res.data.result.refreshToken
      if (IS_CHECK) localStorage.setItem('refreshToken', REFRESH_TOKEN)
    })

    if (localStorage.getItem('isCheck') === 'true') {
      navigate('/')
    } else {
      navigate('/recommend/first')
    }
  }, [])
  return <div>Login</div>
}

export default Login
