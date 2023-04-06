import React from 'react'
import Slider from 'react-slick'
import 'slick-carousel/slick/slick.css'
import 'slick-carousel/slick/slick-theme.css'

import p1 from '@/assets/slides/p1.png'
import p2 from '@/assets/slides/p2.png'
import p3 from '@/assets/slides/p3.png'
import p4 from '@/assets/slides/p4.png'
import p5 from '@/assets/slides/p5.png'
import p6 from '@/assets/slides/p6.png'

function PatternSlide() {
  const settings = {
    dots: false,
    infinite: true,
    arrows: false,

    slidesToShow: 5,
    slidesToScroll: 1,
    speed: 1300,
    autoplaySpeed: 1300,
    autoplay: true,
    // easing: 'linear',
    cssEase: 'linear',
  }
  return (
    <section className="page-carousel">
      <Slider {...settings}>
        <div>
          <img
            alt=""
            className="w-[17rem]  rounded-[17rem] rounded-[30px] "
            src={p1}
          />
        </div>
        <div>
          <img
            alt=""
            className="w-[17rem]  rounded-[17rem] rounded-[30px] "
            src={p2}
          />
        </div>
        <div>
          <img
            alt=""
            className="w-[17rem]  rounded-[17rem] rounded-[30px] "
            src={p3}
          />
        </div>
        <div>
          <img
            alt=""
            className="w-[17rem]  rounded-[17rem] rounded-[30px] "
            src={p4}
          />
        </div>
        <div>
          <img
            alt=""
            className="w-[17rem]  rounded-[17rem] rounded-[30px] "
            src={p5}
          />
        </div>
        <div>
          <img
            alt=""
            className="w-[17rem] rounded-[17rem] rounded-[30px] "
            src={p6}
          />
        </div>
      </Slider>
    </section>
  )
}

export default PatternSlide
