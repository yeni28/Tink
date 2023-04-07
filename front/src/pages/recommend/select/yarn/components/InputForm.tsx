import React from 'react'
import { useForm, SubmitHandler } from 'react-hook-form'

import { useNavigate } from 'react-router-dom'

import atoms from '@/components/atoms'

function InputForm() {
  const { register, handleSubmit } = useForm<YarnInfo>()
  const navigate = useNavigate()
  const onSubmit = (data: YarnInfo) =>
    navigate('/pattern/list', { state: { yarnInfo: data } })

  return (
    <div>
      <p className="text-title2-bold pl-32">
        보유하신 실의 정보를 입력해주세요
      </p>
      <div className="mt-16 ">
        <form onSubmit={handleSubmit(onSubmit)}>
          <div className="flex flex-col items-start gap-8">
            <div className="flex justify-between w-80">
              <label className="text-title3-bold"> 코 </label>
              <input type="number" {...register('gauge')} />
            </div>
            <div className="flex justify-between w-80">
              <label className="text-title3-bold"> 1인치 당 코 </label>
              <input type="number" {...register('gaugeDivisor')} />
            </div>
            <div className="flex justify-between w-80">
              <label className="text-title3-bold"> 단 </label>
              <input type="number" {...register('rowGauge')} />
            </div>
            <div className="flex justify-between w-80">
              <label className="text-title3-bold"> 실 길이 </label>
              <input type="number" {...register('yardage')} />
            </div>
            <div className="flex justify-between w-80">
              <label className="text-title3-bold"> 최대 실 길이 </label>
              <input type="number" {...register('yardageMax')} />
            </div>
          </div>
          <div className="absolute -right-52 -bottom-44">
            <atoms.ButtonDoodle innerValue="다음" type="submit" />
          </div>
        </form>
      </div>
    </div>
  )
}

export default InputForm
