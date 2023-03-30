import React, { useState, Dispatch, SetStateAction, KeyboardEvent } from 'react'

import atoms from '@/components/atoms'

interface Props {
  comments: CommentProps[]
  setComments: (comment: CommentProps[]) => void
}

function Comment({ comments, setComments }: Props) {
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault()
    if (event !== null) {
      return
    }
  }

  const [newcomment, setNewComment] = useState('')
  const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value.trim()
    if (inputValue === '') {
      return
    } else {
      setNewComment(event.target.value)
    }
  }
  // 시간
  const [createtime, setCreatetime] = useState<number>()
  const getTime = (createdAt: number) => {
    const SEC = 1000
    const MIN = SEC * 60
    const HOUR = MIN * 60
    const DAY = HOUR * 24
    const WEEK = DAY * 7
    const MON = DAY * 30

    const cur = Date.now() - createdAt
    const seconds = `${cur / SEC}초 전`
    const minutes = Number(cur / MIN) >= 1 ? `${cur / MIN}분 전` : false
    const hours = Number(cur / HOUR) >= 1 ? `${cur / HOUR}시간 전` : false
    const days = Number(cur / DAY) >= 1 ? `${cur / DAY}일 전` : false
    const weeks = Number(cur / WEEK) >= 1 ? `${cur / WEEK}주 전` : false
    const months = Number(cur / MON) >= 1 ? `${cur / MON}달 전` : false
    return months || weeks || days || hours || minutes || seconds
  }
  // 댓글작성
  const submitComment = () => {
    const randomId = Math.random()
    const randomname = Math.random().toString(36).substring(2, 11)
    // create시간입니다.
    const createdAt = Date.now()
    // n초천으로 시간을 띄웁니다.
    const create_time = getTime(createdAt)
    const userImgUrl = 'https://source.unsplash.com/random'
    const newComment = {
      commentId: randomId,
      content: newcomment,
      nickname: randomname,
      thumbImg: userImgUrl,
      updatedDate: create_time,
    }
    if (newComment.content === '') {
      alert('댓글을 입력해 주세요!')
    } else {
      setComments([...comments, newComment])
      setNewComment('')
    }
  }
  // 엔터키로 전송
  const handleKeyPress = (e: KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      submitComment()
    }
  }

  return (
    <div className=" flex items-center">
      <atoms.ImageUser
        alt={'user'}
        src={'https://source.unsplash.com/random'}
      />
      <div
        className="w-[48.8rem] h-[3rem] ml-[1rem]  
       rounded-[.5rem] border-[.1rem] border-black 
       bg-white "
      >
        <form className="flex pl-2 mt-[.7rem]" onSubmit={handleSubmit}>
          <input
            className=" w-[45rem] text-title3  bg-transparent	 focus:outline-0"
            placeholder="따뜻한 댓글은 작성자에게 큰 힘이 됩니다 :D"
            type="text"
            value={newcomment}
            onChange={onChange}
            // 엔터키로 입력 가능
            onKeyPress={handleKeyPress}
          />
          <button
            className="w-[9rem] ml-[2rem]"
            type="button"
            onClick={() => submitComment()}
          >
            <span className="text-red text-title3-bold "> 입력 </span>
          </button>
        </form>
      </div>
    </div>
  )
}

export default Comment
