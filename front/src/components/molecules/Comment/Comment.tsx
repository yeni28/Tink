import React, { useState, Dispatch, SetStateAction } from 'react'

import atoms from '@/components/atoms'

import { CommentProps } from '@/pages/home/HomePage'

interface Props {
  setComments: React.Dispatch<React.SetStateAction<string>>
}

const Comment = (
  // comment: CommentProps['comment'],
  // isDelete: CommentProps['isDelete']
  props: Props
) => {
  const [testcomment, setTestComment] = useState('')

  const onChange = (event: React.ChangeEvent<HTMLInputElement>) =>
    setTestComment(event.target.value)

  const onSubmit = (e) => {
    event.preventDefault()
    setComments(event.target.value)
  }

  return (
    <div className="flex items-center">
      <atoms.ImageUser
        alt={'user'}
        src={'https://source.unsplash.com/random'}
      />
      {/* <atoms.InputComment /> */}
      <div className="w-[48.8rem] h-[3rem] ml-[1rem]  pl-2 rounded-[.5rem] border-[.1rem] border-black flex bg-white items-center ">
        <form>
          <input
            className=" w-[43rem] text-title3  bg-transparent	 focus:outline-0"
            placeholder="따뜻한 댓글은 작성자에게 큰 힘이 됩니다 :D"
            type="text"
            value={testcomment}
            onChange={onChange}
          />
        </form>
        <button className=" w-[9rem] ml-[2rem] " onSubmit={onSubmit}>
          <span className="text-red text-title3-bold "> 입력 </span>
        </button>
      </div>
    </div>
  )
}

export default Comment
