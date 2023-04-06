import React, { useEffect, useState, useRef } from 'react'
import { BsFire, BsFillHeartFill } from 'react-icons/bs'

import { RiArrowLeftSFill, RiArrowRightSFill } from 'react-icons/ri'

import { useParams } from 'react-router-dom'

import Slider from 'react-slick'

import { axAuth, axBase } from '@/apis/axiosInstance'

import DrawingLine from '@/assets/drawings/drawingline.png'
import atoms from '@/components/atoms'

import '@/styles/pages/pattern/detail/slick.css'
import '@/styles/pages/pattern/detail/slick-theme.css'

function DetailPattern() {
  const [details, setDetails] = useState<PatternDetail>()
  const [knitSort, setKnitSort] = useState<'대바늘' | '코바늘'>('대바늘')
  const [difficulty, setDifficulty] = useState<number>(0)
  const [patternCount, setPatternCount] = useState<number>(0)
  const [isLike, setIsLike] = useState<boolean>(false)
  const [isLogin, setIsLogin] = useState<boolean>(false)
  const [clickVote, setClickVote] = useState<boolean>(false)
  const [voteDifficulty, setVoteDifficulty] = useState<
    1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10
  >(4)

  // 도안 id 가져오기
  const params = useParams()
  const patternId = params.id

  // 비동기 통신
  useEffect(() => {
    // 로그인 여부 설정
    if (localStorage.getItem('accessToken')) setIsLogin(true)

    axAuth({
      url: '/patterns',
      params: { patternId },
    })
      .then((res) => setDetails(res.data.result))
      .catch((err) => console.log(err))
  }, [])

  // 이미지 클릭시 새창으로 열기
  const imgTab = (url: string) =>
    window.open(url, '_blank', 'noopener, noreferrer')

  // slick setting
  const settings = {
    dots: true,
    infinite: false,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 3,
    nextArrow: <RiArrowRightSFill />,
    prevArrow: <RiArrowLeftSFill />,
  }

  useEffect(() => {
    if (details) {
      // 뜨개분류
      for (const needle of details.needles) {
        if (needle.knitting !== null) {
          setKnitSort('대바늘')
          break
        } else if (needle.crochet !== null) {
          setKnitSort('코바늘')
          break
        }
      }

      // 초기 난이도 설정
      setDifficulty(details.difficultyAvg)

      // 좋아요 설정
      setPatternCount(details.patternLikesCount)
      details.patternLikeCheck === 1 ? setIsLike(true) : setIsLike(false)
    }
  }, [details])

  const follow = () => {
    axAuth({
      url: '/patterns/like',
      params: {
        patternId: details?.id,
      },
    }).catch((err) => console.log(err))
  }

  const voteRef = useRef<HTMLDivElement | null>(null)

  useEffect(() => {
    const handleOutsideClose = (e: { target: any }) => {
      // useRef current에 담긴 엘리먼트 바깥을 클릭 시 드롭메뉴 닫힘
      if (clickVote && voteRef.current && !voteRef.current.contains(e.target)) {
        setClickVote(false)
        setVoteDifficulty(4)
      }
    }
    document.addEventListener('click', handleOutsideClose)

    return () => document.removeEventListener('click', handleOutsideClose)
  }, [clickVote])

  const onSubmit = () => {
    axAuth({
      method: 'post',
      url: '/patterns/level',
      data: { patternId, difficultyNum: voteDifficulty },
    }).catch((err) => console.log)
  }

  return (
    <>
      <header className="mt-[2.63rem] mb-16 flex flex-col items-center">
        <p className="mb-5 font-pop text-supertitle-bold">{details?.name}</p>
        <div className="bg-black px-9 w-[26rem] h-[2.3125rem] flex items-center justify-between rounded-[1.25rem]">
          <div className="text-red text-title2-bold flex items-center">
            <BsFire className="mr-1" />
            <span className="mr-3">난이도</span>
            <span className="text-white text-title2">{difficulty} /10</span>
          </div>
          <div className="text-red text-title2-bold flex items-center">
            <BsFillHeartFill className="mr-2" />
            <span className="mr-3">좋아요</span>
            <span className="text-white text-title2">{patternCount}</span>
          </div>
        </div>
      </header>
      <div id="images">
        <Slider {...settings}>
          {details?.thumbnails.map((thumbImg, idx) => {
            return (
              <div
                key={`thumbnail-${idx}`}
                className="w-[23.1875rem] h-[23.1875rem]"
              >
                <img
                  alt="thumbnails"
                  className="w-full h-full object-cover rounded-[1.25rem] cursor-pointer"
                  src={thumbImg.thumbImg}
                  onClick={() => imgTab(thumbImg.mainImg)}
                />
              </div>
            )
          })}
        </Slider>
      </div>
      <img alt="line" referrerPolicy="no-referrer" src={DrawingLine} />
      <div className="px-5 flex flex-col gap-8" id="info">
        <div className="flex justify-between" id="upper">
          <div id="up-col-1">
            <p className="text-title2-bold mb-1">뜨개 분류</p>
            <p className="text-title2">{knitSort}</p>
          </div>
          <div id="up-col-2">
            <p className="text-title2-bold mb-1">게이지</p>
            <p className="text-title2">
              {details?.gauge}코 {details?.rowGauge}단
            </p>
          </div>
          <div id="up-col-3">
            <p className="text-title2-bold mb-1">바늘 정보</p>
            {details?.needles.map((needle, idx) => {
              return (
                <p key={`needle-${idx}`}>
                  US {needle.us} - {needle.prettyMetric}
                </p>
              )
            })}
          </div>
        </div>
        <div className="flex justify-between" id="lower">
          <div id="low-col-1">
            <p className="text-title2-bold mb-1">실 정보</p>
            <p className="text-title2">{knitSort}</p>
          </div>
          <div id="low-col-2">
            <p className="text-title2-bold mb-1">카테고리</p>
            <p className="text-title2">
              {details?.category.parentCategory
                ? `${details?.category.parentCategory} > `
                : ''}
              {details?.category.categoryName}
            </p>
          </div>
          <div id="low-col-3">
            <p className="text-title2-bold mb-1">다운로드 링크</p>
            <p className="text-title2">{details?.downloadUrl}</p>
          </div>
        </div>
      </div>
      <img alt="line" referrerPolicy="no-referrer" src={DrawingLine} />
      {isLogin && (
        <div className="flex justify-center gap-4 mt-14">
          <div ref={voteRef}>
            <atoms.ButtonSquareMd2
              bgColor="black"
              innerValue="난이도 평가"
              textColor="white"
              onClick={() => setClickVote(true)}
            />
            {clickVote && (
              <div className="w-[11.25rem] py-3 px-5 mt-2 bg-white border-[1px] border-black rounded-md absolute flex flex-col">
                <label htmlFor="1" onClick={() => setVoteDifficulty(1)}>
                  <input id="1" name="vote" type="radio" value="1" /> 1 - 매우
                  쉬움
                </label>
                <label htmlFor="2" onClick={() => setVoteDifficulty(2)}>
                  <input id="2" name="vote" type="radio" value="2" /> 2 - 쉬움
                </label>
                <label htmlFor="3" onClick={() => setVoteDifficulty(3)}>
                  <input id="3" name="vote" type="radio" value="3" /> 3
                </label>
                <label htmlFor="4" onClick={() => setVoteDifficulty(4)}>
                  <input
                    defaultChecked
                    id="4"
                    name="vote"
                    type="radio"
                    value="4"
                  />{' '}
                  4 - 중간
                </label>
                <label htmlFor="5" onClick={() => setVoteDifficulty(5)}>
                  <input id="5" name="vote" type="radio" value="5" /> 5
                </label>
                <label htmlFor="6" onClick={() => setVoteDifficulty(6)}>
                  <input id="6" name="vote" type="radio" value="6" /> 6
                </label>
                <label htmlFor="7" onClick={() => setVoteDifficulty(7)}>
                  <input id="7" name="vote" type="radio" value="7" /> 7
                </label>
                <label htmlFor="8" onClick={() => setVoteDifficulty(8)}>
                  <input id="8" name="vote" type="radio" value="8" /> 8 - 어려움
                </label>
                <label htmlFor="9" onClick={() => setVoteDifficulty(9)}>
                  <input id="9" name="vote" type="radio" value="9" /> 9
                </label>
                <label htmlFor="10" onClick={() => setVoteDifficulty(10)}>
                  <input id="10" name="vote" type="radio" value="10" /> 10 -
                  매우 어려움
                </label>
                <div className="flex justify-end mt-2" onClick={onSubmit}>
                  <atoms.ButtonSquareSm
                    bgColor="black"
                    innerValue="제출하기"
                    textColor="white"
                  />
                </div>
              </div>
            )}
          </div>
          <atoms.ButtonSquareMd2
            bgColor="red"
            innerValue="좋아요"
            textColor="white"
            onClick={follow}
          />
        </div>
      )}
    </>
  )
}

export default DetailPattern
