import React, { useState, useEffect } from 'react'

import Parser from 'html-react-parser'

import question_detail_dummy from './dummydata'

import straitline from '@/assets/drawings/straitline.png'

import atoms from '@/components/atoms'
import molecules from '@/components/molecules'

function DetailQuestionCommunityt() {
  const [question, setQuestion] = useState<GroupDetail>()
  const [comments, setComments] = useState<CommentProps[]>([])
  // 댓글 삭제
  const deleteComment = (id: number) => {
    setComments(
      comments.filter((comment) => {
        return comment.commentId !== id
      })
    )
  }

  useEffect(() => {
    setQuestion(question_detail_dummy)
  }, [])

  return (
    <div>
      <div>
        <p className="mt-[1.625rem] text-grey text-title1-bold">질문</p>
        <p className="text-largetitle-bold mt-[1.2rem]">{question?.title}</p>
        <div className="text-body mt-[1.2rem]">
          <span className="mr-[1.2rem]">{question?.member?.nickname}</span>
          <span className="text-grey">{question?.createdDate}</span>
        </div>
        <div className="mt-[3.5rem] text-title3 mb-[5.5rem]  ">
          {Parser(question ? (question.content ? question.content : '') : '')}
        </div>
      </div>
      {/* 댓글 */}
      <img src={straitline}></img>
      <div className="flex-col content-center " id="comment-area">
        <span className="text-title2-bold">댓글 </span>
        <span className="text-title2-bold text-red">
          {question?.commentCnt}
        </span>
        <div className="mt-[2rem]">
          <molecules.CommentInput
            comments={comments}
            setComments={(comment) => setComments(comment)}
          />
        </div>
        <div className="mt-[2.1rem]">
          <molecules.CommentList
            comments={comments}
            deleteComment={deleteComment}
          />
        </div>
      </div>
    </div>
  )
}

export default DetailQuestionCommunityt
