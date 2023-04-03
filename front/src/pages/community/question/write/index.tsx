import React from 'react'

import { SubmitErrorHandler, SubmitHandler, useForm } from 'react-hook-form'

import Write from '../../components/CommunityWrite'

import InputBox from '@/assets/drawings/inputlinebox.png'
import atoms from '@/components/atoms'

function WriteQuestionCommunity() {
  const {
    register,
    handleSubmit,
    watch,
    setValue,
    formState: { errors },
  } = useForm<QuestionPost>()

  // const submitHandler: SubmitHandler<QuestionPost> = (data) => {
  //   data.boardCategory = 'review'
  //   data.memberEmail = '이메일 작업 필요'

  //   if (!titleImage) return

  //   const formData = setReviewPostData(data, titleImage)

  // formData 확인용
  // const entries = formData.entries()
  // for (const pair of entries) {
  //   console.log(pair[0] + ', ' + pair[1])
  // }
  // }

  // const errorHandler: SubmitErrorHandler<QuestionPost> = (errors) => {
  //   if (errors.multipartFile) alert(`${errors.multipartFile.message}`)
  //   if (!errors.multipartFile && errors.title) alert(`${errors.title.message}`)
  //   if (!errors.multipartFile && !errors.title && errors.content)
  //     alert(`${errors.content.message}`)
  // }
  return (
    <div>
      <form
        // onSubmit={handleSubmit(submitHandler, errorHandler)}
        onKeyDown={(e) => {
          if (e.key === 'Enter') e.preventDefault()
        }}
      >
        <div className="flex flex-col items-center">
          <img alt="border" className="relative" src={InputBox} width="75%" />
          <div className="mt-[6rem] flex justify-center absolute w-[42rem]">
            <Write />
          </div>
        </div>
        <div className="fixed top-[24rem] right-[6rem]">
          <atoms.ButtonDoodle
            innerValue="올리기"
            type="submit"
            onClick={() => console.log('ButtonDoodle')}
          />
        </div>
      </form>
    </div>
  )
}

export default WriteQuestionCommunity
