import React, { useEffect, useState } from 'react'

import { MdOutlineRefresh } from 'react-icons/md'

import { useLocation } from 'react-router-dom'

import { axAuth } from '@/apis/axiosInstance'
import patternLineBox from '@/assets/drawings/patternLIneBox.png'
import atoms from '@/components/atoms'

import organisms from '@/components/organisms'

function FavoriteFirstRecommend() {
  // 전체도안
  const [whole, setWhole] = useState()
  // 12개 도안
  const [array, setArray] = useState()
  const location = useLocation()
  const start = 0
  const end = 12
  useEffect(() => {
    const difficulty = location.state.difficulty
    axAuth({
      url: '/members/favorite/pattern',
      params: { difficulty },
    }).then((res) => {
      console.log(res.data)
      setWhole(res.data)
    })
  }, [])

  const SendFavorite = () => {
    axAuth({
      method: 'POST',
      url: '/members/favorite/patterns',
      data: {},
    })
      // .then((res) => {})
      .catch((err) => console.log(err))
  }

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
