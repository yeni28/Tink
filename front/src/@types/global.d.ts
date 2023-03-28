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
