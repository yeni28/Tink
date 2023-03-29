import React from 'react'

import CommunityMainPage from '@/pages/community'
import GroupCommunity from '@/pages/community/group'

import DetailGroupCommunity from '@/pages/community/group/detail'
import ListGroupCommunity from '@/pages/community/group/list'
import WriteGroupCommunity from '@/pages/community/group/write'
import QuestionCommunity from '@/pages/community/question'
import DetailQuestionCommunity from '@/pages/community/question/detail'
import ListQuestionCommunity from '@/pages/community/question/list'
import WriteQuestionCommunity from '@/pages/community/question/write'
import ReviewCommunity from '@/pages/community/review'
import DetailReviewCommunity from '@/pages/community/review/detail'
import ListReviewCommunity from '@/pages/community/review/list'
import WriteReviewCommunity from '@/pages/community/review/write'

const Community = [
  {
    path: '/community',
    element: <CommunityMainPage />,
    children: [
      {
        path: 'group',
        element: <GroupCommunity />,
        children: [{ path: 'list', element: <ListGroupCommunity /> }],
      },
      {
        path: 'question',
        element: <QuestionCommunity />,
        children: [{ path: 'list', element: <ListQuestionCommunity /> }],
      },
      {
        path: 'review',
        element: <ReviewCommunity />,
        children: [{ path: 'list', element: <ListReviewCommunity /> }],
      },
    ],
  },
  { path: 'community/question/write', element: <WriteQuestionCommunity /> },
  {
    path: 'community/question/detail/:id',
    element: <DetailQuestionCommunity />,
  },
  { path: 'community/group/write', element: <WriteGroupCommunity /> },
  { path: 'community/group/detail/:id', element: <DetailGroupCommunity /> },
  { path: 'community/review/detail/:id', element: <DetailReviewCommunity /> },
  { path: 'community/review/write', element: <WriteReviewCommunity /> },
]

export default Community
