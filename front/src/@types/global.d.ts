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

interface ButtonProps {
  bgColor: 'red' | 'black'
  textColor: 'white' | 'black'
  innerValue: string
  onClick: () => void
}

interface CommunityProps {
  title: string
  body: string
  username: string
  view: number
  comment: number
}
