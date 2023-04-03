// 회원
interface Member {
  memberId: number
  email?: string
  nickname: string
  follows?: number
  follower?: number
  isFollow?: boolean
  thumbnail?: {
    thumbnailId?: number
    mainImg?: string
    thumbImg: string
  }
}
