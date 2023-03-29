import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'

import { switchFollow } from './apis/follow'
import review_detail_dummy from './dummydata'

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
      <div id="information"></div>
    </div>
  )
}

export default DetailReviewCommunity
