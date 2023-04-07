import React from 'react'

import { useRecoilState } from 'recoil'

import NextButton from '../molecules/NextButton'

import { levelState } from '@/pages/recommend/select/pattern'
import lineBox1 from '@/pages/recommend/select/pattern/components/atoms/line box1.png'
import lineBox2 from '@/pages/recommend/select/pattern/components/atoms/line box2.png'
import lineBox3 from '@/pages/recommend/select/pattern/components/atoms/line box3.png'

interface tagProps {
  id: string
  name: string
  key: number
}

const levelData = [
  { id: 'low', name: '초보자', key: 1 },
  { id: 'middle', name: '중급자', key: 2 },
  { id: 'high', name: '상급자', key: 3 },
]

function Level() {
  const [level, setLevel] = useRecoilState(levelState)

  return (
    <div>
      <div className="flex pl-8 relative">
        {/* 첫번째 카테고리 */}
        <div className="w-[13.58rem] h-[20.87rem] relative">
          <img className="w-full h-full" src={lineBox1} />
          <div className="absolute top-2 left-3 p-4">
            {levelData.map((item: tagProps) => {
              return (
                <div key={item.key} className="flex items-center my-3 mx-2">
                  <input
                    className="appearance-none w-4 h-4 border-black border mr-3 checked:bg-black"
                    id={item.id}
                    name="category"
                    type="radio"
                    onClick={() => {
                      setLevel(item.id)
                    }}
                  />
                  <label htmlFor={item.id}>{item.name}</label>
                </div>
              )
            })}
          </div>
        </div>

        {/* 두번째 카테고리 */}

        <div className="w-[13.58rem] h-[20.87rem] relative">
          <img className="w-full h-full" src={lineBox2} />
          <div className="absolute top-0 left-0"> </div>
        </div>

        {/* 세번째 카테고리 */}
        <div className="w-[28.46rem] h-[20.88 rem] relative">
          <img className="w-full h-full" src={lineBox3} />
          <div className="absolute top-0 left-0"></div>
        </div>

        {/* 다음 버튼 */}
        <NextButton />
      </div>
    </div>
  )
}

export default Level
