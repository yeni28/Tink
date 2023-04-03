import React, { useState } from 'react'

import question_list_dummy from './dummydata'
import { sortList } from './sortList'

import organisms from '@/components/organisms'

function ListQuestionCommunity() {
  const [selected, setSelected] = useState<string>(sortList[0].sortName)
  const [isSelectOpen, setSelectOpen] = useState<boolean>(false)
  return (
    <div
      className="mt-3"
      onClick={() => {
        isSelectOpen && setSelectOpen(false)
      }}
    >
      <div className="flex justify-end relative mb-10">
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
      <organisms.questionGrid items={question_list_dummy} />
    </div>
  )
}

export default ListQuestionCommunity
