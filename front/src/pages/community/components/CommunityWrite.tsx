import React, { useMemo, useState } from 'react'
import ReactQuill from 'react-quill'

import 'react-quill/dist/quill.snow.css'
import '@/styles/quill.css'
import StraitLine from '@/assets/drawings/straitline.png'
function CommunityWrite() {
  const [commentCount, setCommentCount] = useState<string>('')
  const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value.trim()

    setCommentCount(event.target.value)
  }

  const modules = useMemo(
    () => ({
      toolbar: {
        container: [
          ['bold'],
          [{ header: [1, 2, 3, 4, false] }],
          [
            { list: 'ordered' },
            { list: 'bullet' },
            { indent: '-1' },
            { indent: '+1' },
            { align: [] },
          ],
          ['image'],
        ],
        handlers: {
          // image: imageHandler,
        },
      },
    }),
    []
  )
  return (
    <div>
      <div className="flex">
        <input
          maxLength={25}
          placeholder="제목을 입력해주세요."
          type="text"
          className="w-[36rem] h-[2rem] mr-[2rem] bg-transparent text-title1-bold placeholder:text-title1-bold focus: outline-none
        text-title1"
          onChange={onChange}
        />
        <span className="text-body-bold text-stone-300">
          {commentCount.replace(/<br\s*\/?>/gm, '\n').length} / 25
        </span>
      </div>
      <img alt="line" className="mb-2" src={StraitLine} />
      <ReactQuill
        modules={modules}
        placeholder="내용을 입력해주세요."
        theme="snow"
      />
    </div>
  )
}

export default CommunityWrite
