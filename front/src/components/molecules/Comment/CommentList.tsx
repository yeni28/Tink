import React from 'react'
type Props = {
  comments: CommentProps[]
  deleteComment: (id: number) => void
}

const CommentList = ({ comments, deleteComment }: Props) => {
  return (
    <li className="list-none">
      <div>
        {comments.map((comment, idx) => {
          return (
            <div key={comment.id} className="w-[48.8rem]">
              <div className="flex place-content-between	">
                <div className="flex">
                  {/* 유저 이미지 */}
                  <div className="h-[2.8rem] w-[2.88rem] rounded-[2rem] overflow-hidden">
                    <img alt="user_image" src={comment.userImage} />
                  </div>
                  {/* 네임/시간/댓글 */}
                  <div>
                    <span className="text-title3-bold ml-2 ">
                      {comment.username}
                    </span>
                    <span className="text-grey text-body  ml-2 ">
                      {comment.create_time}
                    </span>
                    <p className="text-body ml-2 mt-1">{comment.value}</p>
                  </div>
                </div>
                <button
                  className="text-red text-body-bold "
                  onClick={() => deleteComment(comment.id)}
                >
                  <span> 삭제 </span>
                </button>
              </div>
            </div>
          )
        })}
      </div>
    </li>
  )
}

export default CommentList
