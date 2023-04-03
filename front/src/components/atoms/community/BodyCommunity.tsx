import React from 'react'

import Parser from 'html-react-parser'

function BodyCommunity({ content }: { content: string }) {
  return (
    <div className="text-body">
      {Parser(content.length < 100 ? content : content.slice(0, 70) + '...')}
    </div>
  )
}

export default BodyCommunity
