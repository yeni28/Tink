import React, { useEffect, useState } from 'react'

import { useParams } from 'react-router-dom'

import { axAuth } from '@/apis/axiosInstance'

function MyAll() {
  const [datas, setDatas] = useState<MypageAll>()
  const params = useParams()
  const memberId = params.id

  useEffect(() => {
    let url
    if (memberId === 'user') url = '/members/mypage/'
    else url = `/members/${memberId}`

    axAuth({ url, data: { id: memberId } })
      .then((res) => {
        setDatas(res.data.result)
        console.log(res.data.result)
      })
      .catch((err) => console.log(err))
  }, [])

  return <div>MyAll</div>
}

export default MyAll
