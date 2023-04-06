import React from 'react'

import PatternSlide from '@/pages/home/components/PatternSlide'
import Slide from '@/pages/home/components/Slide'
function MainPage() {
  return (
    <div>
      {/* section1 */}
      <div id="silder">
        <Slide />
        <button className=" absolute text-headline-bold text-white left-[60rem] bottom-[4rem]">
          SCROLL
        </button>
      </div>
      {/* section2 */}
      <div className="mt-[6rem]">
        <p className="text-title1-bold text-center mb-[2rem]">
          {' '}
          어떤 도안을 좋아하시나요?{' '}
        </p>
        <div className="mb-[2rem]">
          <PatternSlide />
        </div>
      </div>
    </div>
  )
}

export default MainPage
