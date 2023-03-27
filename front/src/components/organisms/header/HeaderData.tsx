import React from 'react'

import {
  HEADER_TITLE,
  HEARDER_SUBTITLE,
} from '@/components/organisms/header/HeaderConstants'

const HEADER_DATA = [
  {
    route: '/recommend',
    title: HEADER_TITLE.RECOMMEND,
    subtitle: HEARDER_SUBTITLE.RECOMMEND.MAIN,
  },
  {
    route: '/recommend/first',
    title: HEADER_TITLE.PICK_SKILL_LEVEL,
    subtitle: HEARDER_SUBTITLE.RECOMMEND.FIRST,
  },
  {
    route: '/recommend/select/pattern',
    title: HEADER_TITLE.RECOMMEND,
    subtitle: HEARDER_SUBTITLE.RECOMMEND.PATTERN,
  },
  {
    route: '/recommend/select/yarn',
    title: HEADER_TITLE.RECOMMEND,
    subtitle: HEARDER_SUBTITLE.RECOMMEND.YARN,
  },
  {
    route: '/recommend/select/color',
    title: HEADER_TITLE.RECOMMEND,
    subtitle: HEARDER_SUBTITLE.RECOMMEND.COLOR,
  },
  {
    route: '/recommend/result',
    title: HEADER_TITLE.RECOMMEND,
    subtitle: HEARDER_SUBTITLE.RECOMMEND.USER_PATTERN,
  },
  {
    route: '/recommend/color/result',
    title: HEADER_TITLE.RECOMMEND,
    subtitle: HEARDER_SUBTITLE.RECOMMEND.USER_COLOR,
  },
  {
    route: '/tutorial/knit',
    title: HEADER_TITLE.TUTORIAL,
    subtitle: HEARDER_SUBTITLE.TUTORIAL.KNIT,
  },
  {
    route: '/tutorial/purl',
    title: HEADER_TITLE.TUTORIAL,
    subtitle: HEARDER_SUBTITLE.TUTORIAL.PURL,
  },
  {
    route: '/tutorial/caston',
    title: HEADER_TITLE.TUTORIAL,
    subtitle: HEARDER_SUBTITLE.TUTORIAL.CAST_ON,
  },
  {
    route: '/community/review',
    title: HEADER_TITLE.COMMUNITY,
    subtitle: HEARDER_SUBTITLE.COMMUNITY.REVIEW,
  },
  {
    route: '/campaign',
    title: HEADER_TITLE.CAMPAIGN,
    subtitle: HEARDER_SUBTITLE.CAMPAIGN,
  },
  {
    route: '/mypage',
    title: HEADER_TITLE.MY_PAGE,
    subtitle: HEARDER_SUBTITLE.MY_PAGE.USER_INFO,
  },
]

export { HEADER_DATA }
