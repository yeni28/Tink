// 마이페이지 좌측 프로필
interface Mypage {
  memberId: number
  email: string
  follows: number
  follower: number
  follow: boolean
  nickname: string
  thumbnailInfoDsl?: {
    thumbnailId?: number
    mainImg?: string
    thumbImg?: string
  }
}

// 마이페이지 전체보기
interface MypageAll {
  boardAndPatternDsls: {
    memberId: number
    // community: null
    // 소모임 목록 + 질문글 목록
    board: {
      boardId: number
      boardCategory: boardCategory
      memberId: number
      title: string
      content: string
      createdDate: number
      updatedDate: number
    }[]
    // 도안 목록
    pattern: {
      patternId: number
      name: string
      patternThumbnails?: string
    }[]
  }
  // 자랑글 목록
  communityBoardInfoDsls: {
    boardId: number
    boardCategory: boardCategory
    memberId: number
    createdDate: number
    updatedDate: number
    title: string
    content: string
    thumbnail: {
      thumbnailId: number
      mainImg?: string
      thumbImg?: string
    }
    // material: null
  }[]
}
