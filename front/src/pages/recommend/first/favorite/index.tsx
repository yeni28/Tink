import React from 'react'

import { MdOutlineRefresh } from 'react-icons/md'

import patternLineBox from '@/assets/drawings/patternLIneBox.png'
import atoms from '@/components/atoms'

import organisms from '@/components/organisms'

function FavoriteFirstRecommend() {
  return (
    <div className="relative flex justify-center ">
      <div className="absolute -right-36">
        <atoms.ButtonDoodle
          innerValue="다음"
          onClick={() => console.log('다음으로')}
        />
      </div>
      {/* line box 이미지 영역 */}
      <img
        alt="linebox"
        className="absolute -top-11 right-5"
        src={patternLineBox}
        width="103%"
      />

      {/* 패턴영역 */}
      <div className="bg-white px-4 pt-5  mb-16">
        {/* <organisms.Patterns /> */}
        {/* 하단 영역 */}
        <div className="mt-[2rem] flex justify-center pb-12">
          {/* 버튼 */}
          <button
            type="button"
            className={
              'flex justify-center gap-1 items-center rounded-[3.13rem] text-title3 px-[1.625rem] py-[0.625rem] bg-red text-white'
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
