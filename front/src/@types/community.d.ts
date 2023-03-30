// 커뮤니티종류
type boardCategory = 'review' | 'question' | 'group'

// 자랑글
// request data
interface ReviewPost {
  memberEmail: string
  title: string
  content: string
  boardCategory: boardCategory
  // 재료 정보 (옵션)
  yarnName?: string
  yarnWeight?: float
  yarnLength?: float
  needle?: string
  time?: string // 소요기간
  patternId?: number
  multipartFile: Blob // 대표사진
}

interface ReviewPut extends ReviewPost {
  boardId: number
}

// response data
interface ReviewDetail {
  boardId: number
  title: string
  isLike: boolean
  content: string
  liked: number // 좋아요 수
  hit: number // 조회수
  member: Member // 작성자
  boardCategory: boardCategory
  material: {
    yarnName: string
    yarnWeight: float
    yarnLength: float
    needle: string
    time: string // 소요기간
  }
  commentCnt: number // 댓글 총 개수
  comments: CommentProps[]
  patternId: number
  patternThumbImg: string
  isfollowed: boolean // 로그인한 사용자의 팔로우 유무
  isLiked: boolean // 로그인한 사용자의 좋아요 유무
}

interface ReviewList {
  boardId: string
  title: string
  liked: number // 좋아요 수
  hit: number // 조회수
  patternId: number // 도안 상세보기 위해
  patternThumbImg: string // 도안 이미지
  isfollowed: boolean // 로그인한 사용자의 팔로우유무  (boolean)
}

// 소모임
// request data
interface GroupPost {
  title: string
  content: string
  boardCategory: boardCategory
  multipartFile: Blob
}

interface GroupPut extends GroupPost {
  boardId: number
}

// response data
interface GroupDetail {
  boardId: number
  title: string
  content: string
  createdDate: string
  updatedDate: string
  liked: number // 좋아요수
  hit: number // 조회수
  member: Member
  commentCnt: number // 댓글 총 개수
  comments: CommentProps[]
}

type QuestionList = CardTextProps

// 질문
// request data
interface QuestionPost {
  title: string
  content: string
  boardCategory: boardCategory
  multipartFile: Blob
}

interface QuestionPut extends QuestionPost {
  boardId: number
}

// response data
interface QuestionDetail {
  boardId: number
  title: string
  content: string
  createdDate: string
  updatedDate: string
  liked: number // 좋아요수
  hit: number // 조회수
  member: Member
  commentCnt: number // 댓글 총 개수
  comments: CommentProps[]
}

type QuestionList = CardTextProps
