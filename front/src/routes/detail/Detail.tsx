interface Props {
  [key: string]: unknown;
}

function Detail(props: Props): JSX.Element {
  return <div id="detail" {...props} />;
}

export default Detail