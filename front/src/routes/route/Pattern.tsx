import DetailPattern from '@/pages/pattern/detail'
import ListPattern from '@/pages/pattern/list'

const Pattern = [
  {
    path: '/pattern/list',
    element: <ListPattern />,
  },
  {
    path: '/pattern/:id',
    element: <DetailPattern />,
  },
]

export default Pattern
