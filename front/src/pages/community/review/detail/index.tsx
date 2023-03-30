import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'

import { switchFollow } from './apis/follow'
import review_detail_dummy from './dummydata'

import reviewDetailBox from '@/assets/drawings/reviewDetailBox.png'
import shortline from '@/assets/drawings/shortline.png'

import atoms from '@/components/atoms'

function DetailReviewCommunity() {
  const [review, setReview] = useState<ReviewDetail>()
  const [follow, setFollow] = useState<boolean>(false)
  const params = useParams()

  useEffect(() => {
    setReview(review_detail_dummy)
    if (review) setFollow(review.user.isFollow)
  }, [])

  return (
    <div className="pt-[30rem]">
      <div
        className="w-screen h-[30rem] overflow-hidden absolute left-0 top-0 bg-[url('https://source.unsplash.com/random')] bg-no-repeat bg-cover bg-center"
        id="title-image"
      />
      <div className="text-largetitle-bold mb-7" id="title">
        {review?.title}
      </div>
      <div className="flex justify-between" id="profile">
        <div className="flex items-center gap-4" id="profile">
          <atoms.ImageUser
            alt="프로필 사아지인"
            src={review ? review?.user.profileImage : ''}
          />
          <span className="text-title3" id="nickname">
            {review?.user.nickname}
          </span>
        </div>
        <atoms.ButtonSquareSm
          bgColor={follow ? 'black' : 'red'}
          innerValue={follow ? '팔로잉' : '+ 팔로우'}
          textColor="white"
          onClick={() => switchFollow(follow, setFollow)}
        />
      </div>
      <div className="flex flex-col items-center">
        <div>
          <img alt="detailbox" className="relative" src={reviewDetailBox} />
        </div>
        <div className=" justify-center absolute mt-[8rem]">
          <div className="flex" id="information">
            <div className="grid grid-cols-2 gap-10">
              {/* 실 이름 */}
              <div className="flex">
                <div className="mr-[1rem]" id="info-name">
                  <p className="text-headline-bold">실 이름</p>
                </div>
                <div id="info-content">
                  <div className="content-center">
                    <p className="w-[12rem] text-center text-body">
                      {review?.yarnName}
                    </p>
                    <img alt="하단 선" className="w-[12rem]" src={shortline} />
                  </div>
                </div>
              </div>
              {/* 사용한 바늘 */}
              <div className="flex">
                <div className="mr-[1rem]" id="info-name">
                  <p className="text-headline-bold">사용한 바늘</p>
                </div>
                <div id="info-content">
                  <div className="content-center">
                    <p className="w-[12rem] text-center text-body">
                      {review?.needle}
                    </p>
                    <img alt="하단 선" className="w-[12rem]" src={shortline} />
                  </div>
                </div>
              </div>

              {/* 실 길이/무게 */}
              <div className="flex">
                <div className="mr-[1rem]" id="info-name">
                  <p className="text-headline-bold">실 길이/무게</p>
                </div>
                <div id="info-content">
                  <div className="content-center">
                    <p className="w-[12rem] text-center text-body">
                      {review?.yarnLength} m / {review?.yarnWeight} g
                    </p>
                    <img alt="하단 선" className="w-[12rem]" src={shortline} />
                  </div>
                </div>
              </div>
              {/* 소요기간 */}
              <div className="flex">
                <div className="mr-[1rem]" id="info-name">
                  <p className="text-headline-bold">소요기간</p>
                </div>
                <div id="info-content">
                  <div className="content-center">
                    <p className="w-[12rem] text-center text-body">
                      {review?.time}
                    </p>
                    <img alt="하단 선" className="w-[12rem]" src={shortline} />
                  </div>
                </div>
              </div>
            </div>
            {/* 도안 */}
            <div className="flex ml-12">
              <div className="mr-[1rem]" id="info-name">
                <p className="text-headline-bold">도안</p>
              </div>
              <div id="info-content">
                <div
                  className="w-[9rem] h-[9rem] rounded-[1rem] bg-cover bg-center"
                  style={{
                    backgroundImage: `url(${review?.pattern.patternImage})`,
                  }}
                ></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default DetailReviewCommunity
