import React from 'react'
import { AiOutlineEye } from 'react-icons/ai'

function ViewCommunity({ view }: CommunityProps) {
  return (
    <div className="flex text-grey items-center">
      <AiOutlineEye size="1.4rem" />
      <div className="text-headline ml-[.2rem]">{view}</div>
    </div>
  )
}

export default ViewCommunity
