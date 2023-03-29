declare module '*.css' {
  const styleObject: { [key: string]: string }
  export default styleObject
}

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

interface CommunityProps {
  title?: string
  body?: string
  username?: string
  view?: number
  comment?: number
}

interface CommentProps {
  id: number
  value: string
  username: string
  create_time: string
  userImage: string
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
