import React, { useState, useRef, useEffect } from 'react'
import { UseFormRegister } from 'react-hook-form'

import Search from './Search'

interface Pattern {
  patternId: number | null
  name: string
}

function Material({
  register,
  setPattern,
  pattern,
}: {
  register: UseFormRegister<ReviewPost>
  setPattern: React.Dispatch<React.SetStateAction<Pattern>>
  pattern: Pattern
}) {
  const searchRef = useRef<HTMLDivElement | null>(null)
  const [searchState, setSearchState] = useState<boolean>(false)

  useEffect(() => {
    const handleOutsideClose = (e: { target: any }) => {
      // useRef current에 담긴 엘리먼트 바깥을 클릭 시 드롭메뉴 닫힘
      if (
        searchState &&
        searchRef.current &&
        !searchRef.current.contains(e.target)
      ) {
        setSearchState(false)
      }
    }
    document.addEventListener('click', handleOutsideClose)

    return () => document.removeEventListener('click', handleOutsideClose)
  }, [searchState])

  return (
    <div
      className="flex justify-between w-[60rem] h-[15rem] px-14 py-5"
      id="material"
    >
      <div className="flex flex-col gap-5">
        <div className="flex w-80 justify-between">
          <label className="text-headline" htmlFor="yarnName">
            실 이름
          </label>
          <input
            {...register('yarnName')}
            className="focus: outline-none"
            id="yarnName"
            type="text"
          />
        </div>
        <div className="flex w-80 justify-between">
          <label className="text-headline" htmlFor="yarnLength">
            실 길이
          </label>
          <input
            {...register('yarnLength')}
            className="focus: outline-none"
            id="yarnLength"
            step="0.01"
            type="number"
          />
        </div>
        <div className="flex w-80 justify-between">
          <label className="text-headline" htmlFor="yarnWeight">
            실 무게
          </label>
          <input
            {...register('yarnWeight')}
            className="focus: outline-none"
            id="yarnWeight"
            step="0.01"
            type="number"
          />
        </div>
      </div>
      <div className="flex flex-col gap-5">
        <div className="flex w-80 justify-between">
          <label className="text-headline" htmlFor="needle">
            사용한 바늘
          </label>
          <input
            {...register('needle')}
            className="focus: outline-none"
            id="needle"
            type="text"
          />
        </div>
        <div className="flex w-80 justify-between">
          <label className="text-headline" htmlFor="time">
            소요 기간
          </label>
          <input
            {...register('time')}
            className="focus: outline-none"
            id="time"
            type="text"
          />
        </div>
        <div ref={searchRef}>
          <div className="flex w-80 justify-between mb-3">
            <label className="text-headline" htmlFor="pattern">
              도안
            </label>
            <input
              {...register('patternId')}
              readOnly
              className="focus: outline-none cursor-pointer"
              id="pattern"
              type="text"
              value={pattern.name}
              onClick={() => setSearchState(true)}
            />
          </div>
          {searchState && (
            <Search setPattern={setPattern} setSearchState={setSearchState} />
          )}
        </div>
      </div>
    </div>
  )
}

export default Material
