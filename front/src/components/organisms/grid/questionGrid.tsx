import React from 'react'

import { useNavigate } from 'react-router-dom'

import molecules from '@/components/molecules'

interface Props extends Omit<CardTextProps, 'onClick'> {
  boardId: number
}

function QuestionGrid({ items }: { items: Props[] }) {
  const navigate = useNavigate()

  return (
    <div className="grid grid-cols-2 gap-x-8 gap-y-10 justify-items-center ">
      {items.map((item) => {
        return (
          <molecules.CardText
            key={item.boardId}
            commentCnt={item.commentCnt}
            content={item.content}
            hit={item.hit}
            nickname={item.nickname}
            title={item.title}
            onClick={() =>
              navigate(`/community/question/detail/${item.boardId}`)
            }
          />
        )
      })}
    </div>
  )
}

export default QuestionGrid
