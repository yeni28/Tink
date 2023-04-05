import React, { useEffect, useState } from 'react'

import { BsFire, BsFillHeartFill } from 'react-icons/bs'

import DrawingLine from '@/assets/drawings/drawingline.png'

import { RiArrowLeftSFill, RiArrowRightSFill } from 'react-icons/ri'
import { useParams } from 'react-router-dom'
import Slider from 'react-slick'

import '@/styles/pages/pattern/detail/slick.css'
import '@/styles/pages/pattern/detail/slick-theme.css'
import { axBase } from '@/apis/axiosInstance'

function DetailPattern() {
  const [details, setDetails] = useState<PatternDetail>()

  // 도안 id 가져오기
  const params = useParams()
  const patternId = params.id

  // 비동기 통신
  useEffect(() => {
    axBase({
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

  return (
    <>
      <header
        className="mt-[2.63rem] mb-16 flex flex-col
  items-center"
      >
        <p className="mb-5 font-pop text-supertitle-bold">{details?.name}</p>
        <p className="mb-5 font-pop text-supertitle-bold">{'Turtle Dove II'}</p>
        <div className="bg-black px-9 w-[26rem] h-[2.3125rem] flex items-center justify-between rounded-[1.25rem]">
          <div className="text-red text-title2-bold flex items-center">
            <BsFire className="mr-1" />
            <span className="mr-3">난이도</span>
            <span className="text-white text-title2">
              {details?.difficultyAvg ? details.difficultyAvg : 0} /10
            </span>
          </div>
          <div className="text-red text-title2-bold flex items-center">
            <BsFillHeartFill className="mr-2" />
            <span className="mr-3">좋아요</span>
            <span className="text-white text-title2">
              {details?.patternLikesCount ? details.patternLikesCount : 0}
            </span>
          </div>
        </div>
      </header>
      {/* <div id="images">
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
      </div> */}
      <div className="mb-10" id="images">
        <Slider {...settings}>
          <div className="w-[23.1875rem] h-[23.1875rem]">
            <img
              alt="thumbnails"
              className="w-full h-full object-cover rounded-[1.25rem] cursor-pointer"
              src="http://img2.sbs.co.kr/img/seditor/VD/2022/12/07/BtN1670378838343-1280-0.jpg"
              onClick={() =>
                imgTab(
                  'http://img2.sbs.co.kr/img/seditor/VD/2022/12/07/BtN1670378838343-1280-0.jpg'
                )
              }
            />
          </div>
          <div className="w-[23.1875rem] h-[23.1875rem]">
            <img
              alt="thumbnails"
              className="w-full h-full object-cover rounded-[1.25rem] cursor-pointer"
              src="http://img2.sbs.co.kr/img/seditor/VD/2022/12/07/BtN1670378838343-1280-0.jpg"
              onClick={() =>
                imgTab(
                  'http://img2.sbs.co.kr/img/seditor/VD/2022/12/07/BtN1670378838343-1280-0.jpg'
                )
              }
            />
          </div>
          <div className="w-[23.1875rem] h-[23.1875rem]">
            <img
              alt="thumbnails"
              className="w-full h-full object-cover rounded-[1.25rem] cursor-pointer"
              src="http://img2.sbs.co.kr/img/seditor/VD/2022/12/07/BtN1670378838343-1280-0.jpg"
              onClick={() =>
                imgTab(
                  'http://img2.sbs.co.kr/img/seditor/VD/2022/12/07/BtN1670378838343-1280-0.jpg'
                )
              }
            />
          </div>
          <div className="w-[23.1875rem] h-[23.1875rem]">
            <img
              alt="thumbnails"
              className="w-full h-full object-cover rounded-[1.25rem] cursor-pointer"
              src="http://img2.sbs.co.kr/img/seditor/VD/2022/12/07/BtN1670378838343-1280-0.jpg"
              onClick={() =>
                imgTab(
                  'http://img2.sbs.co.kr/img/seditor/VD/2022/12/07/BtN1670378838343-1280-0.jpg'
                )
              }
            />
          </div>
          <div className="w-[23.1875rem] h-[23.1875rem]">
            <img
              alt="thumbnails"
              className="w-full h-full object-cover rounded-[1.25rem] cursor-pointer"
              src="http://img2.sbs.co.kr/img/seditor/VD/2022/12/07/BtN1670378838343-1280-0.jpg"
              onClick={() =>
                imgTab(
                  'http://img2.sbs.co.kr/img/seditor/VD/2022/12/07/BtN1670378838343-1280-0.jpg'
                )
              }
            />
          </div>
          <div className="w-[23.1875rem] h-[23.1875rem]">
            <img
              alt="thumbnails"
              className="w-full h-full object-cover rounded-[1.25rem] cursor-pointer"
              src="http://img2.sbs.co.kr/img/seditor/VD/2022/12/07/BtN1670378838343-1280-0.jpg"
              onClick={() =>
                imgTab(
                  'http://img2.sbs.co.kr/img/seditor/VD/2022/12/07/BtN1670378838343-1280-0.jpg'
                )
              }
            />
          </div>
        </Slider>
      </div>
      <img alt="line" referrerPolicy="no-referrer" src={DrawingLine} />
      <div id="info">
        <table className="w-full">
          <tr>
            <td>
              <th className="text-title2-bold">뜨개 분류</th>
              <tr className="text-title2">1</tr>
              <tr className="text-title2">2</tr>
              <tr className="text-title2">3</tr>
            </td>
            <td>
              <th className="text-title2-bold">게이지</th>
              <tr className="text-title2">1</tr>
              <tr className="text-title2">2</tr>
              <tr className="text-title2">2</tr>
            </td>
            <td>
              <th className="text-title2-bold">바늘 정보</th>
              <tr className="text-title2">US </tr>
              <tr className="text-title2">2</tr>
              <tr className="text-title2">3</tr>
            </td>
          </tr>
          <tr>
            <td>
              <th className="text-title2-bold">실 정보</th>
              <tr className="text-title2">1</tr>
              <tr className="text-title2">2</tr>
              <tr className="text-title2">3</tr>
            </td>
            <td>
              <th className="text-title2-bold">카테고리</th>
              <tr className="text-title2">1</tr>
              <tr className="text-title2">2</tr>
              <tr className="text-title2">3</tr>
            </td>
          </tr>
        </table>
      </div>
      <img alt="line" referrerPolicy="no-referrer" src={DrawingLine} />
    </>
  )
}

export default DetailPattern
