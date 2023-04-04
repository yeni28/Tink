import React from 'react'

import atoms from '@/components/atoms'

import lineBox1 from '@/pages/recommend/select/pattern/components/atoms/line box1.png'
import lineBox2 from '@/pages/recommend/select/pattern/components/atoms/line box2.png'
import lineBox3 from '@/pages/recommend/select/pattern/components/atoms/line box3.png'

const categoryData = [
  { id: 'Clothing', name: '옷', key: 1 },
  { id: 'Accessories ', name: '액세서리', key: 2 },
  { id: 'Home', name: '홈', key: 3 },
  { id: 'Toys and Hobbies', name: '장난감 및 취미', key: 4 },
  { id: 'Pet ', name: '반려동물', key: 5 },
  { id: 'Components ', name: '세부요소', key: 6 },
]

function Category() {
  const onClickHandler = () => {
    console.log('Hello')
  }
  return (
    <div>
      <div className="flex pl-8 relative">
        {/* 첫번째 카테고리 */}
        <div className="w-[13.58rem] h-[20.87rem] relative">
          <img className="w-full h-full" src={lineBox1} />
          <div className="absolute top-2 left-3 p-4">
            {categoryData.map((item) => {
              return (
                <div key={item.key} className="flex items-center my-3 mx-2">
                  <input
                    id={item.id}
                    name={item.name}
                    type="checkbox"
                    className="appearance-none w-4 h-4 border-black border mr-3
     checked:bg-black"
                  />
                  <label htmlFor={item.name}>{item.name}</label>
                </div>
              )
            })}
          </div>
        </div>

        {/* 두번째 카테고리 */}

        <div className="w-[13.58rem] h-[20.87rem] relative">
          <img className="w-full h-full" src={lineBox2} />
          <div className="absolute top-0 left-0">Category2</div>
        </div>

        {/* 세번째 카테고리 */}
        <div className="w-[28.46rem] h-[20.88 rem] relative">
          <img className="w-full h-full" src={lineBox3} />
          <div className="absolute top-0 left-0">Category3</div>
        </div>

        {/* 다음 버튼 */}
        <div className="absolute -bottom-20 -right-14">
          <atoms.ButtonDoodle innerValue={'다음'} onClick={onClickHandler} />
        </div>
      </div>
    </div>
  )
}

export default Category
