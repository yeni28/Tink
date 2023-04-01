export const setReviewPostData = (data: ReviewPost, titleImage: File) => {
  const formData = new FormData()
  formData.append('memberEmail', data.memberEmail)
  formData.append('title', data.title)
  formData.append('content', data.content)
  formData.append('boardCategory', data.boardCategory)
  if (data.yarnName) formData.append('yarnName', data.yarnName)
  if (data.yarnWeight) formData.append('yarnWeight', String(data.yarnWeight))
  if (data.yarnLength) formData.append('yarnLength', String(data.yarnLength))
  if (data.needle) formData.append('needle', data.needle)
  if (data.time) formData.append('time', data.time)
  if (data.patternId) formData.append('patternId', String(data.patternId))

  // 대표 사진
  const file = titleImage
  formData.append('multipartFile', file)

  return formData
}

export const setReviewPutData = (data: ReviewPut, titleImage: File) => {
  const formData = new FormData()
  formData.append('boardId', String(data.boardId))
  formData.append('memberEmail', data.memberEmail)
  formData.append('title', data.title)
  formData.append('content', data.content)
  formData.append('boardCategory', data.boardCategory)
  if (data.yarnName) formData.append('yarnName', data.yarnName)
  if (data.yarnWeight) formData.append('yarnWeight', String(data.yarnWeight))
  if (data.yarnLength) formData.append('yarnLength', String(data.yarnLength))
  if (data.needle) formData.append('needle', data.needle)
  if (data.time) formData.append('time', data.time)
  if (data.patternId) formData.append('patternId', String(data.patternId))

  // 대표 사진
  const file = titleImage
  formData.append('multipartFile', file)

  return formData
}
