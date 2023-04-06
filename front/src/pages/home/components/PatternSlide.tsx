import React from 'react'
import Slider from 'react-slick'
import 'slick-carousel/slick/slick.css'
import 'slick-carousel/slick/slick-theme.css'

import p1 from '@/assets/slides/p1.png'
import p10 from '@/assets/slides/p10.png'
import p2 from '@/assets/slides/p2.png'
import p3 from '@/assets/slides/p3.png'
import p4 from '@/assets/slides/p4.png'
import p5 from '@/assets/slides/p5.png'
import p6 from '@/assets/slides/p6.png'
import p7 from '@/assets/slides/p7.png'
import p8 from '@/assets/slides/p8.png'
import p9 from '@/assets/slides/p9.png'

function PatternSlide() {
  const settings = {
    dots: false,
    infinite: true,
    arrows: false,

    slidesToShow: 6,
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
          <img alt="" className="w-[17rem] rounded-[30px] " src={p1} />
        </div>
        <div>
          <img alt="" className="w-[17rem] rounded-[30px] " src={p2} />
        </div>
        <div>
          <img alt="" className="w-[17rem] rounded-[30px] " src={p3} />
        </div>
        <div>
          <img alt="" className="w-[17rem] rounded-[30px] " src={p4} />
        </div>
        <div>
          <img alt="" className="w-[17rem] rounded-[30px] " src={p5} />
        </div>
        <div>
          <img alt="" className="w-[17rem] rounded-[30px] " src={p6} />
        </div>
        <div>
          <img alt="" className="w-[17rem] rounded-[30px] " src={p7} />
        </div>
        <div>
          <img alt="" className="w-[17rem] rounded-[30px] " src={p8} />
        </div>
        <div>
          <img alt="" className="w-[17rem] rounded-[30px] " src={p9} />
        </div>
        <div>
          <img alt="" className="w-[17rem] rounded-[30px] " src={p10} />
        </div>
      </Slider>
    </section>
  )
}

export default PatternSlide
