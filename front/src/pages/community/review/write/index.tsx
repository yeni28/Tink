import React, { useEffect, useState } from 'react'
import { SubmitErrorHandler, SubmitHandler, useForm } from 'react-hook-form'

import { useNavigate } from 'react-router-dom'

import Material from './components/Material'
import ReviewWrite from './components/ReviewWrite'

import { setReviewPostData } from '../../apis/SetFormData'

import { axAuth } from '@/apis/axiosInstance'
import atoms from '@/components/atoms'

interface Pattern {
  patternId: number | null
  name: string
}

function WriteReviewCommunity() {
  const [titleImage, setTitleImage] = useState<File | null>(null)
  const [imgPreview, setImgPreview] = useState<any>()
  const {
    register,
    handleSubmit,
    watch,
    setValue,
    formState: { errors },
  } = useForm<ReviewPost>()

  // 대표 사진 파일 삽입
  const onChangeFile = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      const file = e.target.files[0]
      setTitleImage(file)
    }
  }

  // 대표 사진 미리보기 임시 url 설정
  useEffect(() => {
    if (titleImage) {
      const file = titleImage
      setImgPreview(URL.createObjectURL(file))
    }
  }, [titleImage])

  // 검색한 도안
  const [pattern, setPattern] = useState<Pattern>({
    patternId: null,
    name: '',
  })

  // 백 연결
  const navigate = useNavigate()
  const submitHandler: SubmitHandler<ReviewPost> = (data) => {
    data.boardCategory = 'review'
    if (pattern.patternId !== null) data.patternId = pattern.patternId

    if (!titleImage) return

    const formData = setReviewPostData(data, titleImage)
    axAuth({
      method: 'post',
      url: '/review',
      data: formData,
    }).then((res) => {
      const boardId = res.data.result.boardId
      navigate(`/community/review/detail/${boardId}`)
    })
  }

  const errorHandler: SubmitErrorHandler<ReviewPost> = (errors) => {
    if (errors.multipartFile) alert(`${errors.multipartFile.message}`)
    if (!errors.multipartFile && errors.title) alert(`${errors.title.message}`)
    if (!errors.multipartFile && !errors.title && errors.content)
      alert(`${errors.content.message}`)
  }

  return (
    <div className="pt-[25.56rem]">
      <form
        onSubmit={handleSubmit(submitHandler, errorHandler)}
        onKeyDown={(e) => {
          if (e.key === 'Enter') e.preventDefault()
        }}
      >
        <div id="title-image">
          {/* 대표 사진 위 흰색 레이어 */}
          <div className="w-full h-[30rem] absolute left-0 top-0 bg-white opacity-70 z-20" />
          <div
            className="w-full h-[30rem] pt-[100px] overflow-hidden absolute left-0 top-0 bg-no-repeat bg-cover bg-center flex flex-col justify-center items-center"
            id="title-image"
            style={{ background: imgPreview ? `url(${imgPreview})` : 'white' }}
          >
            <input
              accept="image/*"
              className="hidden"
              id="picture"
              type="file"
              {...register('multipartFile', {
                required: '대표사진을 등록해주세요.',
              })}
              onChange={onChangeFile}
            />
            <label className="z-20" htmlFor="picture">
              <div className="flex flex-col items-center">
                <p className="text-title3-bold">자랑글에 보여지는 첫 화면!</p>
                <p className="text-title3-bold mb-6">
                  대표 사진을 업로드해 주세요.
                </p>
              </div>
              <div className="rounded-[0.63rem] w-[13.13rem] h-12 text-title3-bold bg-grey text-white cursor-pointer flex justify-center items-center">
                대표 사진 추가 하기
              </div>
            </label>
          </div>
        </div>
        <div className="flex flex-col items-center">
          <p className="text-headline text-grey">
            뜨개 작품을 이해하는 데 많은 도움이 되는 정보예요! 꼼꼼히 입력해
            주실수록 더 좋아요.
          </p>
          <Material
            pattern={pattern}
            register={register}
            setPattern={setPattern}
          />
          <div>
            <ReviewWrite
              errors={errors}
              register={register}
              setValue={setValue}
              watch={watch}
            />
          </div>
        </div>
        <div className="flex justify-end mt-5 my-64">
          <atoms.ButtonDoodle innerValue="올리기" type="submit" />
        </div>
      </form>
    </div>
  )
}

export default WriteReviewCommunity
