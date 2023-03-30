import { useState, useRef } from 'react'

import atoms from '@/components/atoms'
import molecules from '@/components/molecules'
import organisms from '@/components/organisms'

function HomePage() {
  // 댓글 Props
  const [comments, setComments] = useState<CommentProps[]>([])
  // 댓글 삭제
  const deleteComment = (id: number) => {
    setComments(
      comments.filter((comment) => {
        return comment.commentId !== id
      })
    )
  }
  return (
    <div className="HomePage">
      <molecules.CommentInput
        comments={comments}
        setComments={(comment) => setComments(comment)}
      />
      <molecules.CommentList
        comments={comments}
        deleteComment={deleteComment}
      />
    </div>
  )
}

export default HomePage
