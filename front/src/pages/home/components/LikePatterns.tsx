import React from 'react'
import Slider from 'react-slick'
import 'slick-carousel/slick/slick.css'
import 'slick-carousel/slick/slick-theme.css'
import { useNavigate } from 'react-router-dom'

function LikePatterns({ likePatterns }: { likePatterns: LikePattern[] }) {
  const navigate = useNavigate()

  const settings = {
    dots: false,
    infinite: true,
    arrows: false,

    slidesToShow: 4,
    slidesToScroll: 1,
    speed: 1500,
    autoplaySpeed: 1600,
    autoplay: true,
    // easing: 'linear',
    cssEase: 'linear',
    responsive: [
      {
        breakpoint: 1400, //화면 사이즈 960px
        settings: {
          slidesToShow: 3,
        },
      },
      {
        breakpoint: 1143, //화면 사이즈 768px
        settings: {
          slidesToShow: 3,
        },
      },
    ],
  }
  return (
    <Slider {...settings}>
      {likePatterns &&
        likePatterns.map((likepattern: any) => {
          return (
            <div key={likepattern.id}>
              <div
                className="relative h-[15rem] w-[15rem] rounded-[2rem]  bg-center bg-cover hover:scale-105 duration-500 cursor-pointer"
                style={{
                  backgroundImage: `url(${likepattern?.thumbnails?.[0]?.mainImg})`,
                }}
                onClick={() => navigate(`/pattern/${likepattern.id}`)}
              ></div>
              {/* <p> {likepattern.name}</p> */}
            </div>
          )
        })}
    </Slider>
  )
}

export default LikePatterns
