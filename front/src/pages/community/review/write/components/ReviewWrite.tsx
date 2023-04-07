import React, { useMemo, useState, useEffect } from 'react'
import {
  FieldErrors,
  UseFormRegister,
  UseFormSetValue,
  UseFormWatch,
} from 'react-hook-form'
import ReactQuill, { Quill } from 'react-quill'

import ImageResize from '@looop/quill-image-resize-module-react'
import { RangeStatic } from 'quill'

import 'react-quill/dist/quill.snow.css'
import { axBase } from '@/apis/axiosInstance'
import StraitLine from '@/assets/drawings/straitline.png'

Quill.register('modules/imageResize', ImageResize)

interface Props {
  register: UseFormRegister<ReviewPost>
  watch: UseFormWatch<ReviewPost>
  setValue: UseFormSetValue<ReviewPost>
  errors: FieldErrors<ReviewPost>
}

function ReviewWrite({ register, watch, setValue, errors }: Props) {
  const [textCount, settextCount] = useState<string>('')

  const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value
    settextCount(inputValue)
  }

  const quillRef = React.useRef<ReactQuill>(null)

  // 이미지 업로드 핸들러, modules 설정보다 위에 있어야 정상 적용
  const imageHandler = () => {
    // file input 임의 생성
    const input = document.createElement('input')
    input.setAttribute('type', 'file')
    input.click()

    input.onchange = async () => {
      const file = input.files
      const formData = new FormData()

      if (file) {
        formData.append('multipartFiles', file[0])
      }

      // file 데이터 담아서 서버에 전달하여 이미지 업로드
      await axBase({
        method: 'post',
        url: '/img/uploadImage',
        data: {
          multipartFiles: formData,
        },
      })
        .then((res) => {
          if (quillRef.current) {
            // 현재 Editor 커서 위치에 서버로부터 전달받은 이미지 불러오는 url을 이용하여 이미지 태그 추가
            const index = (
              quillRef.current.getEditor().getSelection() as RangeStatic
            ).index

            const quillEditor = quillRef.current.getEditor()
            quillEditor.setSelection(index, 1)

            quillEditor.clipboard.dangerouslyPasteHTML(
              index,
              `<img src=${res.data.result} alt='innerImg' />`
            )
          }
        })
        .catch((err) => console.log(err))
    }
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

  const modules = useMemo(
    () => ({
      toolbar: {
        container: toolbarOptions,
        handlers: {
          image: imageHandler,
        },
      },
      imageResize: { modules: ['Resize'] },
    }),
    []
  )

  useEffect(() => {
    register('content', { required: '본문을 입력해주세요.' })
  }, [register])

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
          {...register('title', { required: '제목을 입력해주세요.' })}
          onChange={onChange}
        />
        <span className="w-32 text-body-bold text-stone-300">
          {textCount.replace(/<br\s*\/?>/gm, '\n').length} / 25
        </span>
      </div>
      <img alt="line" className="mb-2" src={StraitLine} />
      <ReactQuill
        ref={quillRef}
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
