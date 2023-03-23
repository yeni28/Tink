import React from 'react'

interface CommentProps {
  id: number
  value: string
}

const CommentList = (comment: CommentProps) => {
  return (
    <li>
      <div>{comment.value}</div>
    </li>
  )
}

export default CommentList
