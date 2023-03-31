import React, { useMemo, useState } from 'react'
import { UseFormRegister, UseFormSetValue, UseFormWatch } from 'react-hook-form'
import ReactQuill, { Quill } from 'react-quill'

import ImageResize from '@looop/quill-image-resize-module-react'

import 'react-quill/dist/quill.snow.css'
import StraitLine from '@/assets/drawings/straitline.png'

function ReviewWrite({
  register,
  watch,
  setValue,
}: {
  register: UseFormRegister<ReviewPost>
  watch: UseFormWatch<ReviewPost>
  setValue: UseFormSetValue<ReviewPost>
}) {
  const [textCount, settextCount] = useState<string>('')
  const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value
    settextCount(inputValue)
  }

  const toolbarOptions = [
    ['bold', 'italic', 'underline', 'strike'], // toggled buttons
    ['blockquote', 'code-block'],
    ['link', 'image'],
    [{ header: 1 }, { header: 2 }], // custom button values
    [{ list: 'ordered' }, { list: 'bullet' }],
    [{ script: 'sub' }, { script: 'super' }], // superscript/subscript
    [{ indent: '-1' }, { indent: '+1' }], // outdent/indent
    [{ direction: 'rtl' }], // text direction

    [{ size: ['small', false, 'large', 'huge'] }], // custom dropdown
    [{ header: [1, 2, 3, 4, 5, 6, false] }],

    [{ color: [] }, { background: [] }], // dropdown with defaults from theme
    [{ font: [] }],
    [{ align: [] }],

    ['clean'], // remove formatting button
  ]

  Quill.register('modules/imageResize', ImageResize)
  const modules = useMemo(
    () => ({
      toolbar: toolbarOptions,
      imageResize: { modules: ['Resize'] },
    }),
    []
  )

  const onEditorStateChange = (value: string) => {
    setValue('content', value)
  }

  const editorContent = watch('content')
  return (
    <div>
      <div className="flex">
        <input
          className="w-full h-[2rem] mr-[2rem] bg-transparent text-title1-bold placeholder:text-title1-bold focus: outline-none"
          maxLength={25}
          placeholder="제목을 입력해주세요."
          type="text"
          {...register('title')}
          onChange={onChange}
        />
        <span className="w-32 text-body-bold text-stone-300">
          {textCount.replace(/<br\s*\/?>/gm, '\n').length} / 25
        </span>
      </div>
      <img alt="line" className="mb-2" src={StraitLine} />
      <ReactQuill
        modules={modules}
        placeholder="내용을 입력해주세요."
        style={{ minHeight: '45rem' }}
        theme="snow"
        value={editorContent}
        onChange={onEditorStateChange}
      />
    </div>
  )
}

export default ReviewWrite
