import React from 'react'
import { AiOutlineComment } from 'react-icons/ai'

function CommentCountCommunity({ comment }: CommunityProps) {
  return (
    <div className="flex  text-grey items-center">
      <AiOutlineComment size="1.3rem" />
      <div className="text-headline ml-[.2rem]">{comment}</div>
    </div>
  )
}

export default CommentCountCommunity
