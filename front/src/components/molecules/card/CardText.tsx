import React from 'react'

import atoms from '@/components/atoms'

function CardText({ onClick }: { onClick: () => void }) {
  return (
    <div
      className=" w-[35.38rem] h-[14.5rem] p-[1.87rem] rounded-[1.55rem] bg-white"
      onClick={onClick}
    >
      <div>
        <div className="mb-[1rem]">
          <atoms.TitleCommunity title={'장덕동 뜨개원 구해요!'} />
        </div>
        <div className="mb-[1.75rem]">
          <atoms.BodyCommunity
            body={
              '장덕동에서 뜨개구리를 함께 뜨는 뜨개구리 모임 입니다.뜨개질을 마친 후엔 장인 족발집에서 회식을 할 예정입니다.'
            }
          />
        </div>
      </div>
      <div className="flex" style={{ justifyContent: 'space-between' }}>
        <atoms.UsernameCommunity username={'개굴조아'} />
        <div className="flex" style={{ justifyItems: 'center' }}>
          <div className="mr-[1rem]">
            <atoms.ViewCommunity view={30} />
          </div>
          <div>
            <atoms.CommentCountCommunity comment={5} />
          </div>
        </div>
      </div>
    </div>
  )
}

export default CardText
