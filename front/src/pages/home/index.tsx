import React, { useEffect, useState } from 'react'

import { useNavigate } from 'react-router-dom'

import { useRecoilState } from 'recoil'

import LikePatterns from './components/LikePatterns'

import { axAuth, axBase } from '@/apis/axiosInstance'
import { LoginState } from '@/apis/state/LoginState'

import atoms from '@/components/atoms'

import BestPatterns from '@/pages/home/components/BestPatterns'
import PatternSlide from '@/pages/home/components/PatternSlide'
import Slide from '@/pages/home/components/Slide'
function MainPage() {
  const [isLoggedIn, setIsLoggedIn] = useState(false)

  const navigate = useNavigate()
  const [member, setMember] = useState<Member>()
  const [bestPatterns, setBestPatterns] = useState<BestPattern[]>([])
  const [likePatterns, setLikePatterns] = useState<LikePattern[]>([])
  useEffect(() => {
    if (localStorage.getItem('accessToken')) setIsLoggedIn(true)
    axBase({
      url: '/patterns/best',
    })
      .then((res) => {
        setBestPatterns(res.data.result)
        console.log('이 아래는 주간 베스트패턴')
        console.log(res)
      })
      .catch((err) => console.log(err))
    axAuth({
      url: '/members/mypage/info',
    })
      .then((res) => {
        setMember(res.data.result)
      })
      .catch((err) => console.log(err))
    axAuth({
      url: '/members/favorite/patterns',
    })
      .then((res) => {
        setLikePatterns(res.data.result)
        console.log('이 아래는 선호 패턴')
        console.log(res)
      })
      .catch((err) => console.log(err))
  }, [])

  return (
    <div>
      {/* section1 */}
      <div id="silder">
        <Slide />
      </div>
      {/* section2 */}
      <div className="mt-[6rem]">
        <div>
          {isLoggedIn ? (
            <div className=" ml-[14vw] mx-auto mb-[3rem]">
              <div className="flex w-[100rem]">
                <div>
                  <div className="flex mb-[.5rem]">
                    <p className="text-title3-bold text-red">취향을 분석한</p>
                    <p className="text-title3 text-red">
                      나만의 추천 도안을 확인해보세요.
                    </p>
                  </div>
                  <span className="text-title1-bold">{member?.nickname} </span>
                  <span className="text-title1">님이 좋아하실 도안이에요!</span>
                </div>
                <div className="ml-[38vw]">
                  <atoms.ButtonSquareMd1
                    bgColor="red"
                    innerValue="더 많은 추천"
                    textColor="white"
                    onClick={() => {
                      navigate('/recommend')
                    }}
                  />
                </div>
              </div>
              <div className="mt-[2rem]  w-[85rem]">
                <LikePatterns likePatterns={likePatterns} />
              </div>
            </div>
          ) : (
            <div>
              {/* <p className="text-largetitle-bold ">BEST 도안</p> */}
              <div className=" ml-[14vw] mx-auto mb-[3rem]">
                <div className="flex w-[100rem]">
                  <div>
                    <div className="flex mb-[.5rem]">
                      <p className="text-title3-bold text-red">발견의 기쁨,</p>
                      <p className="text-title3 text-red">
                        인기도안을 확인해보세요.
                      </p>
                    </div>
                    <span className="text-title1-bold">취향을 분석해 </span>
                    <span className="text-title1">
                      꼭 맞는 도안을 추천드려요!
                    </span>
                  </div>
                  <div className="ml-[38vw]">
                    <atoms.ButtonSquareMd1
                      bgColor="red"
                      innerValue="나를 위한 추천"
                      textColor="white"
                      onClick={() => {
                        navigate('/recommend')
                      }}
                    />
                  </div>
                </div>
                <div className="mt-[2rem] w-[85rem]">
                  <BestPatterns bestPatterns={bestPatterns} />
                </div>
              </div>
              {/* 도안 */}
            </div>
          )}
        </div>
      </div>
    </div>
  )
}

export default MainPage
