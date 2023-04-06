// 도안 목록
interface PatternDetail {
  needles: {
    prettyMetric: string // 2.75mm
    us: string // US 2
    knitting: boolean | null
    crochet: boolean | null
  }[]
  category: {
    id: number
    categoryName: string
    depth: number
    parentCategory: string | null
  }
  thumbnails: {
    mainImg: stirng
    thumbImg: stirng
  }[]
  id: number // 도안 아이디
  name: string // 도안 이름
  gauge: number // 코
  rowGauge: number // 단
  gaugeDivisor: number // 인치 게이지
  gaugePattern: string // '8 rows and 8 sts = 1 inch in garter stitch'
  sizesAvailable: string // 'XS, S, M' 가능한 사이즈
  yardage: number // 실 길이
  yardageDescription: string // 실 종류
  yardageMax: number // 실 최대 길이
  yarnWeightDescription: string // 실 굵기 'Aran'
  downloadUrl: string // 'https://www.ravelry.com/patterns/library/example-pattern-2'
  difficultyCnt: number // 난이도 투표수
  difficultySum: number // 난이도 합산
  difficultyAvg: number // 난이도 평균 최대 10
  patternLikeCheck: number //현재 로그인 한 상태라면 그 회원의 패턴 좋아요 여부(1이면 좋아요 누름,0이면 X)
  patternLikesCount: number //현재 도안의 좋아요 개수
}

// 주간 베스트
interface BestPattern {
  needles: {
    prettyMetric: string // 2.75mm
    us: number // US 2
    knitting: boolean | null
    crochet: boolean | null
  }
  category: {
    id: number
    categoryName: string
    depth: number
    parentCategory: string | null
  }
  thumbnails: [
    {
      mainImg: string
      thumbImg: string
      patternId: number
    },
    {
      mainImg: string
      thumbImg: string
      patternId: number
    }
  ]
  id: number
  name: string
  gauge: number
  gaugeDivisor: number
  gaugePattern: string
  sizesAvailable: string
  yardage: number
  yardageDescription: string
  yardageMax: number
  yarnWeightDescription: string
  downloadUrl: string
  notesHtml: string
  difficultyCnt: number
  difficultySum: number
  difficultyAvg: number
}

// 뜨개질 선호도 조회
interface LikePattern {
  patternId: int
  name: string
  patternThumbnails: [
    {
      patternThumbnailId: int
      mainImg: null | string
      thumbImg: null | string
    }
  ]
}
