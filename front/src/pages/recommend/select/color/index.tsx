import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

import FileBox from '@/assets/drawings/fileLineBox.png'
import yarn from '@/assets/drawings/yarn.png'
import FileCat from '@/assets/drawings/파일냥이.png'
import atoms from '@/components/atoms'

function ColorSelectRecommend() {
  const [image, setImage] = useState<any>()
  const [imgPreview, setImgPreview] = useState<any>()
  const navigate = useNavigate()

  const onChangeFile = (e: React.ChangeEvent<HTMLInputElement>) => {
    setImage(e.target.files)
  }

  useEffect(() => {
    if (image && image.length > 0) {
      const file = image[0]
      setImgPreview(URL.createObjectURL(file))
    }
  }, [image])

  return (
    <div className="flex justify-center items-center pt-16 relative">
      <div className="relative flex justify-center">
        <img alt="border" src={FileBox} width="50%" />
        <div className="w-[34rem] h-[23rem] absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">
          <input
            accept="image/*"
            className="hidden"
            id="picture"
            type="file"
            onChange={onChangeFile}
          />
          <label
            className="w-full h-full flex flex-col justify-center items-center"
            htmlFor="picture"
          >
            {(imgPreview && (
              <img
                alt="preview"
                className="w-full h-full cursor-pointer object-contain"
                src={imgPreview}
              />
            )) || (
              <>
                <img
                  alt="preview"
                  className="object-contain cursor-pointer w-[196px] h-[209px] mb-5"
                  src={FileCat}
                />
                <p className="font-cha text-title2">사진을 올려주라냥</p>
              </>
            )}
          </label>
        </div>
      </div>
      <div className="absolute right-5 bottom-0">
        <atoms.ButtonDoodle
          innerValue="다음"
          onClick={() => {
            image
              ? navigate('/recommend/select/color/result', {
                  state: { propImage: image },
                })
              : alert('사진을 올려주세요!')
          }}
        />
      </div>
      <div className="absolute -top-4 -right-[16.5rem] w-[12.75rem]">
        <img src={yarn} />
      </div>
    </div>
  )
}

export default ColorSelectRecommend
