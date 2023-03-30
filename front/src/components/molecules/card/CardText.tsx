import React from 'react'

import atoms from '@/components/atoms'

interface Props extends Omit<CardTextProps, 'boardId'> {
  onClick: () => void
}

function CardText(Props: Props) {
  const { commentCnt, content, hit, nickname, onClick, title } = Props
  return (
    <div
      className=" w-[35.38rem] h-[14.5rem] p-[1.87rem] rounded-[1.55rem] bg-white"
      onClick={onClick}
    >
      <div>
        <div className="mb-[1rem]">
          <atoms.TitleCommunity title={title} />
        </div>
        <div className="mb-[1.75rem]">
          <atoms.BodyCommunity content={content} />
        </div>
      </div>
      <div className="flex" style={{ justifyContent: 'space-between' }}>
        <atoms.UsernameCommunity username={nickname} />
        <div className="flex" style={{ justifyItems: 'center' }}>
          <div className="mr-[1rem]">
            <atoms.ViewCommunity view={hit} />
          </div>
          <div>
            <atoms.CommentCountCommunity comment={commentCnt} />
          </div>
        </div>
      </div>
    </div>
  )
}

export default CardText
