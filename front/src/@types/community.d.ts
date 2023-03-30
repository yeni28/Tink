// 커뮤니티종류
type boardCategory = 'review' | 'question' | 'group'

// 자랑글

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

// 소모임

// 질문
