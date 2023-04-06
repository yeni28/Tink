import React, { useState, useRef, useEffect } from 'react'

import review_list_dummy from './dummydata'
import { sortList } from './sortList'

import { axAuth } from '@/apis/axiosInstance'
import organisms from '@/components/organisms'

function ListReviewCommunity() {
  const [selected, setSelected] = useState<string>(sortList[0].sortName)
  const [isSelectOpen, setSelectOpen] = useState<boolean>(false)
  const toggleRef = useRef<HTMLDivElement | null>(null)

  useEffect(() => {
    const handleOutsideClose = (e: { target: any }) => {
      // useRef current에 담긴 엘리먼트 바깥을 클릭 시 드롭메뉴 닫힘
      if (
        isSelectOpen &&
        toggleRef.current &&
        !toggleRef.current.contains(e.target)
      ) {
        setSelectOpen(false)
      }
    }
    document.addEventListener('click', handleOutsideClose)

    return () => document.removeEventListener('click', handleOutsideClose)
  }, [isSelectOpen])

  useEffect(() => {
    axAuth({
      url: '/review/search',
      params: {
        filter: selected,
      },
    }).then((res) => {
      console.log(res.data)
    })
  }, [selected])

  return (
    <div className="mt-3">
      <div ref={toggleRef} className="flex justify-end relative mb-10">
        <div
          className="cursor-pointer"
          onClick={() => setSelectOpen(!isSelectOpen)}
        >
          <span className="">{selected}</span>
          {isSelectOpen ? (
            <span className="rotate-180">▴</span>
          ) : (
            <span>▾</span>
          )}
        </div>
        {isSelectOpen && (
          <div className="absolute top-7 py-2 w-24 border-solid border border-black bg-white">
            <ul className="flex flex-col items-center w-full gap-1">
              {sortList.map((sort) => {
                return (
                  <li
                    key={sort.id}
                    className="cursor-pointer hover:underline underline-offset-4"
                    onClick={() => {
                      setSelected(sort.sortName)
                      setSelectOpen(false)
                    }}
                  >
                    <span>{sort.sortName}</span>
                  </li>
                )
              })}
            </ul>
          </div>
        )}
      </div>
      <organisms.reviewGrid items={review_list_dummy} />
    </div>
  )
}

export default ListReviewCommunity
