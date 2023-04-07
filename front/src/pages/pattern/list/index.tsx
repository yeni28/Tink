import React, { useEffect, useState } from 'react'

import { useLocation } from 'react-router-dom'

import { axAuth } from '@/apis/axiosInstance'
import patternLineBox from '@/assets/drawings/patternLIneBox.png'
import atoms from '@/components/atoms'

import organisms from '@/components/organisms'
import {
  HEADER_TITLE,
  HEARDER_SUBTITLE,
} from '@/components/organisms/header/HeaderConstants'

function ListPattern() {
  const location = useLocation()
  const [yarnInfo, setYarnInfo] = useState([])

  useEffect(() => {
    const url = '/recommend/yarn'
    const data = { ...location.state.yarnInfo, patternId: 0 }
    console.log(data)
    axAuth({ method: 'post', url, data }).then((res) => {
      setYarnInfo(res.data.result)
      console.log(res)
    })
  }, [])
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
        {/* <img
          alt="linebox"
          className="absolute -top-11 right-5"
          src={patternLineBox}
          width="103%"
        /> */}

        {/* 패턴영역 */}
        <div className="bg-white px-4 pt-5  mb-16 rounded-2xl">
          <organisms.Patterns datas={yarnInfo} />
          {/* 하단 영역 */}
          <div className="mt-[2rem] flex justify-center pb-12"></div>
        </div>
      </div>
    </>
  )
}

export default ListPattern
