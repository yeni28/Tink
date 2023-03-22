interface Props {
  [key: string]: unknown
}

function Detail(props: Props): JSX.Element {
  return <div id="detail" {...props} className="pt-[6.25rem] ]" />
}

export default Detail
