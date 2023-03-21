declare module '*.css' {
  const styleObject: { [key: string]: string }
  export default styleObject
}

interface imageProps {
  src: string
  alt?: string
  width?: number
  height?: number
}

interface buttonProps {
  bgColor: string
  textColor: string
  innerValue: string
  onClick: () => void
}
