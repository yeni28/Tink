import React, { useEffect, useState } from 'react'
import { useForm, SubmitHandler } from 'react-hook-form'

import ReviewWrite from './components/ReviewWrite'
import Search from './components/Search'

interface Pattern {
  patternId: number | null
  patternName: string
}

function WriteReviewCommunity() {
  const [titleImage, setTitleImage] = useState<any>()
  const [imgPreview, setImgPreview] = useState<any>()
  const { register, handleSubmit, watch, setValue } = useForm<ReviewPost>()

  // 대표 사진 파일 삽입
  const onChangeFile = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTitleImage(e.target.files)
  }

  // 대표 사진 미리보기 임시 url 설정
  useEffect(() => {
    if (titleImage && titleImage.length > 0) {
      const file = titleImage[0]
      setImgPreview(URL.createObjectURL(file))
    }
  }, [titleImage])

  // 검색한 도안
  const [pattern, setPattern] = useState<Pattern>({
    patternId: null,
    patternName: '',
  })

  const onSubmit: SubmitHandler<ReviewPost> = (data) => console.log(data)

  return (
    <div className="pt-[25.56rem]">
      <form onSubmit={handleSubmit(onSubmit)}>
        <button type="submit">제출</button>
        <div id="title-image">
          <div className="h-[30rem] absolute left-0 top-0 bg-white opacity-70 z-20" />
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
              onChange={onChangeFile}
            />
            <label className="z-20" htmlFor="picture">
              <p className="text-title3-bold">자랑글에 보여지는 첫 화면!</p>
              <p className="text-title3-bold mb-6">
                대표 사진을 업로드해 주세요.
              </p>
              <div className="rounded-[0.63rem] w-[13.13rem] h-12 text-title3-bold bg-grey text-white cursor-pointer flex justify-center items-center">
                대표 사진 추가 하기
              </div>
            </label>
          </div>
        </div>
        <div className="flex flex-col items-center">
          <p className="text-headline text-grey">
            뜨개 작품을 이해하는 데 많은 도움이 되는 정보예요! 꼼꼼히 입력해주실
            수록 더 좋아요.
          </p>
          <div
            className="flex justify-between w-[60rem] h-[15rem] px-14 py-5"
            id="material"
          >
            <div className="flex flex-col gap-5">
              <div className="flex w-80 justify-between">
                <label className="text-headline" htmlFor="yarnName">
                  실 이름
                </label>
                <input
                  {...register('yarnName')}
                  className="focus: outline-none"
                  id="yarnName"
                  type="text"
                />
              </div>
              <div className="flex w-80 justify-between">
                <label className="text-headline" htmlFor="yarnLength">
                  실 길이
                </label>
                <input
                  {...register('yarnLength')}
                  className="focus: outline-none"
                  id="yarnLength"
                  type="number"
                />
              </div>
              <div className="flex w-80 justify-between">
                <label className="text-headline" htmlFor="yarnWeight">
                  실 무게
                </label>
                <input
                  {...register('yarnWeight')}
                  className="focus: outline-none"
                  id="yarnWeight"
                  type="number"
                />
              </div>
            </div>
            <div className="flex flex-col gap-5">
              <div className="flex w-80 justify-between">
                <label className="text-headline" htmlFor="needle">
                  사용한 바늘
                </label>
                <input
                  {...register('needle')}
                  className="focus: outline-none"
                  id="needle"
                  type="text"
                />
              </div>
              <div className="flex w-80 justify-between">
                <label className="text-headline" htmlFor="time">
                  소요 기간
                </label>
                <input
                  {...register('time')}
                  className="focus: outline-none"
                  id="time"
                  type="text"
                />
              </div>
              <div>
                <div className="flex w-80 justify-between mb-3">
                  <label className="text-headline" htmlFor="pattern">
                    도안
                  </label>
                  <input
                    {...register('patternId')}
                    readOnly
                    className="focus: outline-none cursor-pointer"
                    id="pattern"
                    type="text"
                    value={pattern.patternName}
                    onClick={() => console.log}
                  />
                </div>
                <Search
                  setPattern={(pattern: Pattern) => setPattern(pattern)}
                />
              </div>
            </div>
          </div>
          <div>
            <ReviewWrite
              register={register}
              setValue={setValue}
              watch={watch}
            />
          </div>
        </div>
      </form>
    </div>
  )
}

export default WriteReviewCommunity
