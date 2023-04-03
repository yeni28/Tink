import React, { useMemo, useState } from 'react'
import ReactQuill, { Quill } from 'react-quill'

import ImageResize from '@looop/quill-image-resize-module-react'

import 'react-quill/dist/quill.snow.css'
import StraitLine from '@/assets/drawings/straitline.png'
Quill.register('modules/ImageResize', ImageResize)

function CommunityWrite() {
  const [textCount, settextCount] = useState<string>('')

  const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value
    settextCount(inputValue)
  }
  const modules = useMemo(
    () => ({
      toolbar: {
        container: [
          ['bold', 'blockquote'],
          [{ list: 'ordered' }, { list: 'bullet' }],
          ['link', 'image'],
        ],
        handlers: {
          // image: imageHandler,
        },
      },
      ImageResize: { modules: ['Resize'] },
    }),
    []
  )
  return (
    <div>
      <div className="flex">
        <input
          className="w-[36rem] h-[2rem] mr-[2rem] bg-transparent text-title1-bold placeholder:text-title1-bold focus: outline-none"
          maxLength={25}
          placeholder="제목을 입력해주세요."
          type="text"
          onChange={onChange}
        />
        <span className="text-body-bold text-stone-300">
          {textCount.replace(/<br\s*\/?>/gm, '\n').length} / 25
        </span>
      </div>
      <img
        alt="line"
        className="mb-2"
        referrerPolicy="no-referrer"
        src={StraitLine}
      />
      <ReactQuill
        modules={modules}
        placeholder="내용을 입력해주세요."
        style={{ height: '45rem' }}
        theme="snow"
      />
    </div>
  )
}

export default CommunityWrite
