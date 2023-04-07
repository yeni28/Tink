import React, { useState, useEffect } from 'react'
import { useLocation, useParams } from 'react-router-dom'

import Parser from 'html-react-parser'

import { switchFollow } from './apis/follow'
// import review_detail_dummy from './dummydata'

import { axAuth } from '@/apis/axiosInstance'
import reviewDetailBox from '@/assets/drawings/reviewDetailBox.png'
import shortline from '@/assets/drawings/shortline.png'
import straitline from '@/assets/drawings/straitline.png'

import atoms from '@/components/atoms'
import molecules from '@/components/molecules'

function DetailReviewCommunity() {
  const [review, setReview] = useState<ReviewDetail>()
  const [follow, setFollow] = useState<boolean>(false)
  const [comments, setComments] = useState<CommentProps[]>([])
  const location = useLocation()
  const params = useParams()

  useEffect(() => {
    axAuth({
      url: '/review',
      params: {
        boardId: params.id,
      },
    }).then((res) => {
      setReview(res.data.result)
    })
  }, [])

  // 댓글 삭제
  const deleteComment = (id: number) => {
    setComments(
      comments.filter((comment) => {
        return comment.commentId !== id
      })
    )
  }

  return (
    <div className="pt-[30rem]">
      <div
        className="w-screen h-[30rem] overflow-hidden absolute left-0 top-0 bg-[url('https://source.unsplash.com/random')] bg-no-repeat bg-cover bg-center"
        id="title-image"
        style={{
          backgroundImage: review?.reviewMainImg
            ? review?.reviewMainImg
            : 'https://source.unsplash.com/random',
        }}
      />
      <div className="text-largetitle-bold mb-7" id="title">
        {review?.title}
      </div>
      <div className="flex justify-between" id="profile">
        <div className="flex items-center gap-4" id="profile">
          <atoms.ImageUser
            alt="프로필 사아지인"
            src={review?.thumbnail ? review.thumbnail : ''}
          />
          <span className="text-title3" id="nickname">
            {location.state.author}
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
          <img alt="detailbox" src={reviewDetailBox} />
        </div>
        <div className="absolute mt-[8rem] pl-[24rem]">
          <div className="flex  w-[80rem] " id="information">
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
                    backgroundImage: `url(${review?.patternThumbnail})`,
                  }}
                ></div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="pb-[3rem] pt-[3rem] pl-[9rem]" id="review-content">
        {Parser(review ? (review.content ? review.content : '') : '')}
      </div>
      <img src={straitline}></img>
      <div className="flex-col content-center ml-[9rem]" id="comment-area">
        <span className="text-title2-bold">댓글 </span>
        <span className="text-title2-bold text-red">{review?.commentCnt}</span>
        <div className="mt-4  ">
          <molecules.CommentInput
            comments={comments}
            setComments={(comment) => setComments(comment)}
          />
        </div>
        <div className="mt-5">
          <molecules.CommentList
            comments={comments}
            deleteComment={deleteComment}
          />
        </div>
      </div>
    </div>
  )
}

export default DetailReviewCommunity
