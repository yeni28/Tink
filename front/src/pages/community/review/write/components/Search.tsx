import React, { useRef, useState } from 'react'
import { AiOutlineSearch } from 'react-icons/ai'

import { axAuth, axBase } from '@/apis/axiosInstance'

interface Pattern {
  patternId: number | null
  name: string
}

interface Props {
  setPattern: React.Dispatch<React.SetStateAction<Pattern>>
  setSearchState: React.Dispatch<React.SetStateAction<boolean>>
}

function Search({ setPattern, setSearchState }: Props) {
  const [results, setResults] = useState<Pattern[]>([])
  const input = useRef<HTMLInputElement>(null)

  const onSubmit = () => {
    const filter = input.current ? input.current.value.trim() : false
    if (filter) {
      axAuth({
        url: '/review/patternList',
        params: {
          filter: filter,
        },
      })
        .then((res) => {
          const result: Pattern[] = res.data.result
          setResults(result.slice(0, 5))
        })
        .catch((err) => console.log)
    }
  }

  const handleKeyPress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      onSubmit()
    }
  }
  const searchResult = results.map((pattern) => {
    return (
      <div
        key={pattern.patternId}
        className="cursor-pointer"
        onClick={() => {
          setPattern(pattern)
          setSearchState(false)
        }}
      >
        {pattern.name}
      </div>
    )
  })

  return (
    <div className="w-80 py-3 px-5 bg-white border-[1px] border-black rounded-md absolute">
      <div className="flex items-center">
        <input
          ref={input}
          className="w-full border-b-2 focus: outline-none mr-2"
          placeholder="도안 이름 검색"
          type="text"
          onKeyUp={handleKeyPress}
        />
        <AiOutlineSearch className="cursor-pointer w-7" onClick={onSubmit} />
      </div>
      <div id="searchResult">
        {results.length ? searchResult : '검색 결과가 없습니다.'}
      </div>
    </div>
  )
}

export default Search
