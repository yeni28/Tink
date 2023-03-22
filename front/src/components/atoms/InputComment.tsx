import React, { useState, Dispatch, SetStateAction } from 'react'

function InputComment() {
  const [comment, setComment] = useState('')

  const onChange = (event: React.ChangeEvent<HTMLInputElement>) =>
    setComment(event.target.value)

  const onSubmit = (e: React.FormEvent<HTMLFormElement>): void => {
    e.preventDefault()
    this.setState(({ comments, input }) => ({
      input: '',
      comments: comments.concat({
        id: this.id++,
        value: input,
      }),
    }))
  }

  return (
    <div className="w-[48.8rem] h-[3rem] ml-[1rem]  pl-2 rounded-[.5rem] border-[.1rem] border-black flex bg-white items-center ">
      <form>
        <input
          className=" w-[43rem] text-title3  bg-transparent	 focus:outline-0"
          placeholder="따뜻한 댓글은 작성자에게 큰 힘이 됩니다 :D"
          type="text"
          value={comment}
          onChange={onChange}
        />
      </form>
      <button className=" w-[9rem] ml-[2rem] ">
        <span className="text-red text-title3-bold "> 입력 </span>
      </button>
    </div>
  )
}

export default InputComment
