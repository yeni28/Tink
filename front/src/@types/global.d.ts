declare module '*.css' {
  const styleObject: { [key: string]: string }
  export default styleObject
}

interface imgProps {
  src: string
  alt?: string
  width?: number
  height?: number
}
