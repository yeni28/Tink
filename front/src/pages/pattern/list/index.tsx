import React from 'react'

import patternLineBox from '@/assets/drawings/patternLIneBox.png'
import atoms from '@/components/atoms'

import organisms from '@/components/organisms'
import {
  HEADER_TITLE,
  HEARDER_SUBTITLE,
} from '@/components/organisms/header/HeaderConstants'

function ListPattern() {
  return (
    <>
      <header
        className="mt-[2.63rem] mb-16 flex flex-col
        items-center"
      >
        <p className="mb-5 font-pop text-supertitle-bold">
          {HEADER_TITLE.PATTERN}
        </p>
        <p className="text-title3">{HEARDER_SUBTITLE.PATTERN.RESULT}</p>
      </header>
      <div className="relative flex justify-center ">
        {/* line box 이미지 영역 */}
        <img
          alt="linebox"
          className="absolute -top-11 right-5"
          src={patternLineBox}
          width="103%"
        />

        {/* 패턴영역 */}
        <div className="bg-white px-4 pt-5  mb-16">
          <organisms.Patterns />
          {/* 하단 영역 */}
          <div className="mt-[2rem] flex justify-center pb-12">
            {/* 버튼 */}
            <div>
              페이지네이션 url이랑 페이지번호를 패턴영역으로 넘겨주면 패턴영역
              <br />
              내에서 비동기통신으로 리스트업
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default ListPattern
