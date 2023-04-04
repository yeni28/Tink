import React from 'react'

import atoms from '@/components/atoms'

import lineBox1 from '@/pages/recommend/select/pattern/components/atoms/line box1.png'
import lineBox2 from '@/pages/recommend/select/pattern/components/atoms/line box2.png'
import lineBox3 from '@/pages/recommend/select/pattern/components/atoms/line box3.png'

function Level() {
  const onClickHandler = () => {
    console.log('Hello')
  }
  return (
    <div>
      <div className="flex pl-8 relative">
        {/* 첫번째 카테고리 */}
        <div className="w-[13.58rem] h-[20.87rem] relative">
          <img className="w-full h-full" src={lineBox1} />
          <div className="absolute top-0 left-0"> Level Category1</div>
        </div>

        {/* 두번째 카테고리 */}

        <div className="w-[13.58rem] h-[20.87rem] relative">
          <img className="w-full h-full" src={lineBox2} />
          <div className="absolute top-0 left-0"> LEvel Category2</div>
        </div>

        {/* 세번째 카테고리 */}
        <div className="w-[28.46rem] h-[20.88 rem] relative">
          <img className="w-full h-full" src={lineBox3} />
          <div className="absolute top-0 left-0">Level Category3</div>
        </div>

        {/* 다음 버튼 */}
        <div className="absolute -bottom-20 -right-14">
          <atoms.ButtonDoodle innerValue={'다음'} onClick={onClickHandler} />
        </div>
      </div>
    </div>
  )
}

export default Level
