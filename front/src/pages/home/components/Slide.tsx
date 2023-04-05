import React from 'react'
import { useNavigate } from 'react-router-dom'

import Slider from 'react-slick'
import 'slick-carousel/slick/slick.css'
import 'slick-carousel/slick/slick-theme.css'

import main_first from '@/assets/videos/main_first.mp4'
import main_second from '@/assets/videos/main_sec.mp4'
import main_third from '@/assets/videos/main_thr.mp4'
import atoms from '@/components/atoms'
import MainVideo from '@/pages/home/components/MainVideo'

function Slide() {
  const navigate = useNavigate()
  const url = 'https://www.youtube.com/watch?v=EK2mecE8Roo&t=1s'

  const settings = {
    dots: true,
    infinite: true,
    fade: true,
    arrows: false,

    slidesToShow: 1,
    slidesToScroll: 1,
    autoplaySpeed: 6800,
    autoplay: true,
    dotsClass: 'test-css',
  }
  return (
    <section className="page-carousel">
      <Slider {...settings}>
        <div className=" w-[100%]  h-[100%] relative">
          <div className="w-full h-[102%] absolute left-0 top-0 bg-black opacity-60 z-30">
            <p className="top-[10rem] left-[5rem] absolute text-supertitle-bold opacity-100 text-white z-40">
              3D 튜토리얼을 통해
              <br />
              쉽게 이해해요.
            </p>
          </div>
          <div className="top-[50rem] left-[5rem] absolute z-50 opacity-70 hover:opacity-100">
            <atoms.ButtonRoundLg
              bgColor="white"
              innerValue="바로가기"
              textColor="black"
              onClick={() => navigate('/tutorial')}
            />
          </div>
          <MainVideo video={main_third} />
        </div>
        <div className=" w-[100%]  h-[100%] relative">
          <div className="w-[100%] h-[102%] absolute left-0 top-0 bg-black opacity-60 z-30">
            <p className="top-[10rem] left-[5rem] absolute text-supertitle-bold opacity-100 text-white z-40">
              코리코 니트와 함께 <br />
              영상으로 배우는 뜨개.
            </p>
          </div>
          <MainVideo video={main_first} />
          <div className="top-[50rem] left-[5rem] absolute  opacity-70 z-50 hover:opacity-100">
            <atoms.ButtonRoundLg
              bgColor="white"
              innerValue="바로가기"
              textColor="black"
              onClick={() => {
                window.open(url)
              }}
            />
          </div>
        </div>

        <div className=" h-[100%] relative">
          <div className="w-full h-[102%] absolute left-0 top-0 bg-black opacity-60 z-30">
            <p className="top-[10rem] left-[5rem] absolute text-supertitle-bold opacity-100 text-white z-40">
              뜨개질을 쉽고 편하게, <br />
              나만을 위한 맞춤 뜨개 공간 Tink
            </p>
          </div>
          <MainVideo video={main_second} />
        </div>
      </Slider>
    </section>
  )
}

export default Slide
