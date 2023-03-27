import React from 'react'

import { MdOutlineRefresh } from 'react-icons/md'

import patternLineBox from '@/assets/drawings/patternLineBox.png'
import organisms from '@/components/organisms'

function FavoriteFirstRecommend() {
  return (
    <div className="relative flex justify-center ">
      {/* line box 이미지 영역 */}

      <img
        alt="linebox"
        className="absolute bottom-6 right-0"
        src={patternLineBox}
        width="103%"
      />

      {/* 패턴영역 */}
      <div className="bg-white px-4 pt-5  mb-16">
        <organisms.Patterns />
        {/* 하단 영역 */}
        <div className="mt-[4.65rem] flex justify-center pb-12">
          {/* 버튼 */}
          <button
            type="button"
            className={
              'flex justify-center gap-1 items-center rounded-[3.13rem] text-title3 px-[0.88rem] py-[0.31rem] bg-red text-white'
            }
            onClick={() => console.log('refresh')}
          >
            다른 도안
            <MdOutlineRefresh />
          </button>
        </div>
      </div>
    </div>
  )
}

export default FavoriteFirstRecommend
