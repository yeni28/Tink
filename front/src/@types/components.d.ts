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
  bgColor: 'red' | 'black'
  textColor: 'white' | 'black'
  innerValue: string
  onClick?: () => void
}

interface CardMdProps {
  author: string
  isFollow: boolean
  titleImgUrl: string
  title: string
  views: number
  likes: number
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

// comment
interface CommentProps {
  commentId: number
  updatedDate: string
  content: string
  nickname: string
  thumbImg: string
}
