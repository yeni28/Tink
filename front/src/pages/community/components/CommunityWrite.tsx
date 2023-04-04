import React, { useMemo, useState } from 'react'
import ReactQuill, { Quill } from 'react-quill'

import ImageResize from '@looop/quill-image-resize-module-react'

import 'react-quill/dist/quill.snow.css'
import { RangeStatic } from 'quill'

import { axBase } from '@/apis/axiosInstance'
import StraitLine from '@/assets/drawings/straitline.png'

Quill.register('modules/ImageResize', ImageResize)

function CommunityWrite() {
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

  const modules = useMemo(
    () => ({
      toolbar: {
        container: [
          ['bold', 'blockquote'],
          [{ list: 'ordered' }, { list: 'bullet' }],
          ['link', 'image'],
        ],
        handlers: {
          image: imageHandler,
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
      <img alt="line" className="mb-2" src={StraitLine} />
      <ReactQuill
        ref={quillRef}
        modules={modules}
        placeholder="내용을 입력해주세요."
        style={{ height: '45rem' }}
        theme="snow"
      />
    </div>
  )
}

export default CommunityWrite
