// 회원
interface Member {
  memberId: number
  email?: string
  nickname: string
  follows?: boolean
  follower?: string
  thumbnail?: {
    thumbnailId?: number
    mainImg?: string
    thumbImg: string
  }
}
