import React, { useEffect, useState } from 'react'
import { Outlet, useParams } from 'react-router-dom'

import MypageToggle from './components/MypageToggle'

import { axAuth } from '@/apis/axiosInstance'
import atoms from '@/components/atoms'
import {
  HEADER_TITLE,
  HEARDER_SUBTITLE,
} from '@/components/organisms/header/HeaderConstants'

function MyPageMainPage() {
  const [member, setMember] = useState<Mypage>()
  const params = useParams()
  const memberId = params.id

  useEffect(() => {
    let url
    if (memberId === 'user') url = '/members/mypage/info'
    else url = `/members/info/${memberId}`
    axAuth({ url })
      .then((res) => {
        setMember(res.data.result)
      })
      .catch((err) => console.log(err))
  }, [])

  const follow = () => {
    axAuth({
      url: '/members/follow',
      data: { toId: memberId },
    })
      .then((res) => {
        if (member !== undefined) {
          setMember({ ...member, follow: res.data.result.follow })
        }
      })
      .catch((err) => console.log(err))
  }

  return (
    <div>
      <header
        className="mt-[2.63rem] mb-16 flex flex-col
        items-center"
      >
        <p className="mb-5 font-pop text-supertitle-bold">
          {HEADER_TITLE.MY_PAGE}
        </p>
        <p className="text-title3">{HEARDER_SUBTITLE.MY_PAGE.USER_INFO}</p>
      </header>
      <MypageToggle />
      <div className="flex">
        <div className="mr-[4.5rem] flex flex-col items-center">
          <img
            alt="프로필 이미지"
            className="w-[8.19rem] h-[8.19rem] rounded-full mb-6"
            referrerPolicy="no-referrer"
            src={member?.thumbnailInfoDsl?.thumbImg}
          />
          <p className="text-title1-bold mb-3">{member?.nickname}</p>
          <div className="mb-3">
            <span className="mr-2 text-grey">팔로워</span>
            <span>{member?.follower}</span>
            <span className="mx-2 text-grey">|</span>
            <span className="mr-2 text-grey">팔로잉</span>
            <span>{member?.follows}</span>
          </div>
          {memberId === 'user' ? (
            <div className="w-[10.63rem]"></div>
          ) : (
            <atoms.ButtonSquareMd1
              bgColor={member?.follow ? 'black' : 'red'}
              innerValue={member?.follow ? '팔로잉' : '팔로우'}
              textColor="white"
              onClick={follow}
            />
          )}
        </div>
        <div>
          <Outlet />
        </div>
      </div>
    </div>
  )
}

export default MyPageMainPage
