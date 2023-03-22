import React, { useState } from 'react'

function InputComment() {
  const [comment, setComment] = useState('')
  const [commentArray, setCommentArray] = useState([''])

  const onSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault()
    if (comment === '') {
      return
    }
    setCommentArray([...commentArray, comment])
    setComment('')
  }

  const onChange = (event: React.ChangeEvent<HTMLInputElement>) =>
    setComment(event.target.value)

  return (
    <div
      className="w-[48.8rem] h-[3rem] ml-[1rem]  pl-2 rounded-[.5rem] border-[.1rem] border-black flex items-center "
      onSubmit={() => onSubmit}
    >
      <form>
        <input
          className=" w-[40rem] text-title3 focus:outline-0"
          placeholder="따뜻한 댓글은 작성자에게 큰 힘이 됩니다 :D"
          type="text"
          value={comment}
          onChange={onChange}
        />
      </form>
      <button className="text-red text-title3-bold ml-[5rem]"> 입력 </button>
    </div>
  )
}

export default InputComment
