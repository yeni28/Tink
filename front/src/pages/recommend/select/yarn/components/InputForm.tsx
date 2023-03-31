import React from 'react'
import { useForm, SubmitHandler } from 'react-hook-form'

import atoms from '@/components/atoms'

type YarnInfo = {
  yarnWeight: string
  yarnLength: string
  gauge: string
}

function InputForm() {
  const { register, handleSubmit } = useForm<YarnInfo>()
  const onSubmit: SubmitHandler<YarnInfo> = (data) => console.log(data)

  const onClick = () => {
    handleSubmit(onSubmit)()
  }
  return (
    <div>
      <p className="text-title2-bold pl-32">
        보유하신 실의 정보를 입력해주세요
      </p>
      <div className="mt-16 ">
        <form
          className="flex flex-col items-start gap-14"
          onSubmit={handleSubmit(onSubmit)}
        >
          <div className="flex justify-between w-72">
            <label className="text-title2-bold"> 무게 </label>
            <input {...register('yarnWeight')} />
          </div>
          <div className="flex justify-between w-72">
            <label className="text-title2-bold"> 길이 </label>
            <input {...register('yarnLength')} />
          </div>

          <div className="flex justify-between w-72">
            <label className="text-title2-bold"> 게이지 </label>
            <input {...register('gauge')} />
          </div>
        </form>

        <div className="absolute -right-40">
          <atoms.ButtonDoodle innerValue={'다음'} onClick={onClick} />
        </div>
      </div>
    </div>
  )
}

export default InputForm
