declare module '*.css' {
  const styleObject: { [key: string]: string }
  export default styleObject
}

type CommunityFilter = '최신순' | '인기순'
type PatternFilter = '최신순' | '인기순' | '이름순'

interface Alarm {
  commentCnt: number // 알림 총개수
  comments: {
    notificationId: number
    boardCategory: boardCategory
    title: string // 질문, 커뮤니티 글의 제목
    commentId: number
    createdDate: string
    readStatus: 0 | 1 // (0:안읽음,   1: 읽음)
  }[]
}

type boardCategory = 'review' | 'question' | 'group'

interface ReviewDetail {
  isLike: boolean
  boardId: number
  memberEmail: string
  title: string
  conetent: string
  boardCategory: boardCategory
  yarnName: string
  yarnWeight: float
  yarnLength: float
  needle: string
  time: string
  user: {
    userId: number
    profileImage: string
    nickname: string
    isFollow: boolean
  }
  pattern: {
    patternId: number
    patternImage: string
  }
}
