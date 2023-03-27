import React, { useEffect, useState } from 'react'

import FileBox from '@/assets/drawings/FileLineBox.png'
import FileCat from '@/assets/drawings/파일냥이.png'

function ColorSelectRecommend() {
  const [imgPreview, setImgPreview] = useState<any>()
  const [image, setImage] = useState<any>()

  const onChangeFile = (e: React.ChangeEvent<HTMLInputElement>) => {
    setImage(e.target.files)
  }

  useEffect(() => {
    if (image && image.length > 0) {
      const file = image[0]
      console.log(file)
      setImgPreview(URL.createObjectURL(file))
    }
  }, [image])

  return (
    <div className="flex justify-center items-center pt-16">
      <div className="relative flex justify-center">
        <img alt="border" src={FileBox} width="50%" />
        <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">
          <input
            accept="image/*"
            className="hidden"
            id="picture"
            type="file"
            onChange={onChangeFile}
          />
          <label htmlFor="picture">
            {(imgPreview && (
              <img
                alt="preview"
                className="object-cover object-cover w-full cursor-pointer"
                src={imgPreview}
              />
            )) || (
              <img
                alt="preview"
                className="object-cover cursor-pointer w-[196px] h-[209px]"
                src={FileCat}
              />
            )}
          </label>
        </div>
      </div>
    </div>
  )
}

export default ColorSelectRecommend
