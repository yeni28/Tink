const HEADER_TITLE = {
  RECOMMEND: 'Recommend',
  UPLOAD: 'Upload',
  TUTORIAL: 'Tutorial',
  COMMUNITY: 'Community',
  MY_PAGE: 'My Page',
  CAMPAIGN: 'Campaign',
  PICK_SKILL_LEVEL: '숙련도를 선택해주세요!',
  PICK_LIKE_PATTERN: '관심있는 도안을 선택해주세요!',
} as const

const HEARDER_SUBTITLE = {
  PICK_SKILL_LEVEL: {
    PICK_SKILL_LEVEL: '마스터 샐리이신가요?',
    PICK_LIKE_PATTERN: '다섯 개 이상을 선택해주세요',
  },
  TUTORIAL: {
    KNIT: '겉뜨기에 대해 알아보아요!',
    PURL: '안뜨기에 대해 알아보아요!',
    CAST_ON: '코잡기에 대해 알아보아요!',
  },
  RECOMMEND: {
    MAIN: '키워드를 선택하면 맞춤 도안을 추천해드려요!',
    PATTERN: '키워드를 선택하면 맞춤 도안을 추천해드려요!',
    YARN: '실 정보에 따른 맞춤 도안을 추천해드려요!',
    COLOR: '색상 정보에 따른 맞춤 색 조합을 추천해드려요!',
    USER_PATTERN: 'Brad 님이 찾고있던 도안, 여기 있네요!',
    USER_COLOR: 'Brad 님이 찾고있던 색조합, 여기 있네요!',
    FIRST: '마스터 샐리이신가요?',
    PICK: '다섯 개 이상을 선택해주세요',
  },
  PATTERN: {
    DESIGNER_NAME: 'by Espace Tricot',
    USER_UPLOAD: 'Brad님의 새로운 도안은 어떤 도안일까요?',
  },
  COMMUNITY: {
    REVIEW: '내가 만든 뜨개 너를 위해 짜왔지',
    MEET: '함께 하면 더 즐거운 뜨개',
    QNA: '모르는 것을 묻고, 아는 것을 나눠요',
  },
  MY_PAGE: {
    USER_INFO: '렛미두잇뽀유~',
    USER_REIVEW: '소가 모이면? 그곳은 축사입니다.',
  },
  CAMPAIGN: '뜨개로 마음을 나눠요',
} as const

export { HEADER_TITLE, HEARDER_SUBTITLE }
