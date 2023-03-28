import React, { useMemo } from 'react'
import ReactQuill from 'react-quill'

import 'react-quill/dist/quill.snow.css'
import StraitLine from '@/assets/drawings/straitline.png'
function CommunityWrite() {
  const modules = useMemo(
    () => ({
      toolbar: {
        container: [
          ['bold'],
          [{ size: ['small', false, 'large', 'huge'] }, { color: [] }],
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
      <input
        placeholder="제목을 입력해주세요."
        type="text"
        className="w-full h-[2rem] bg-transparent placeholder:text-title1 focus: outline-none
        text-title1"
      />
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
