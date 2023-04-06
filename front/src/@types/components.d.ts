interface ImageProps {
  src: string
  alt?: string
  width?: number
  height?: number
}

interface ImageLg extends ImageProps {
  src: string
  mainValue?: string
  subValue?: string
  onClick: () => void
  bgColor?: string
}

interface ButtonProps {
  bgColor: 'red' | 'black' | 'white'
  textColor: 'white' | 'black'
  innerValue: string
  onClick?: () => void
}

interface CardMdProps {
  nickname: string
  patternThumnail: string
  title: string
  hit: number
  liked: number
  onClick: () => void
}

interface CardTextProps {
  boardId: number
  title: string
  content: string
  hit: number
  commentCnt: number
  nickname: string
}

interface CardCampainProps {
  boardId: number
  title: string
  content: string
  period: string
  organizer: stirng
  ImgUrl: string
  onClick: () => void
}

// comment
interface CommentProps {
  commentId: number
  updatedDate: string
  content: string
  nickname: string
  thumbImg: string
}
