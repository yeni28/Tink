// 회원
interface Member {
  memberId: number
  email?: string
  nickname: string
  follows?: number
  follower?: number
  thumbnail?: {
    thumbnailId?: number
    mainImg?: string
    thumbImg: string
  }
}
