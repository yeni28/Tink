const attributeData = [
  { id: 'Age or Size', name: '나이', key: 1 },
  { id: 'Fit', name: '핏', key: 2 },
  { id: 'Gender', name: '성별', key: 3 },
  { id: 'Neck', name: '넥라인', key: 4 },
  { id: 'Sleeve', name: '소매', key: 5 },
  { id: 'Pattern', name: '무늬', key: 6 },
  { id: 'Construction', name: '구조', key: 7 },
  { id: 'Other', name: '기타', key: 8 },
]

const ageData = [
  { id: 'preemie', name: '신생아', key: 9 },
  { id: 'baby', name: '아기', key: 10 },
  { id: 'child', name: '유아', key: 11 },
  { id: 'teen', name: '청소년', key: 12 },
  { id: 'adult', name: '성인', key: 13 },
]

const genderData = [
  { id: 'male', name: '남성', key: 14 },
  { id: 'female', name: '여성', key: 15 },
  { id: 'unisex', name: '유니섹스', key: 16 },
]

const fitData = [{ id: 'fitted', name: '핏', key: 17 }]

const neckData = [
  { id: 'boat-neck', name: '보트넥', key: 18 },
  { id: 'collar', name: '카라', key: 19 },
  { id: 'crew-neck', name: '크루넥', key: 20 },
  { id: 'scoop-neck', name: '스쿱넥', key: 21 },
  { id: 'square-neck', name: '스퀘어넥', key: 22 },
  { id: 'v-neck', name: '브이넥', key: 23 },
]

const patternData = [
  { id: 'stripes', name: '줄무늬', key: 24 },
  { id: 'stranded', name: '가로배색', key: 25 },
  { id: 'Intarsia', name: '세로배색', key: 26 },
]

const sleeveData = [{ id: 'sleeveless', name: '민소매', key: 27 }]

const constructionData = [
  { id: 'top-down', name: '위에서부터 뜨는', key: 28 },
  { id: 'bottom-up', name: '아래에서부터 뜨는', key: 29 },
  { id: 'short-rows', name: '입체적으로 뜨는', key: 30 },
  { id: 'worked-flat', name: '평면으로 뜨는', key: 31 },
]

const otherData = [
  { id: 'seamed', name: '이음매 있는', key: 32 },
  { id: 'seamless', name: '이음매 없는', key: 33 },
  { id: 'chart', name: '차트식 도안', key: 34 },
  { id: 'written-pattern', name: '서술식 도안', key: 35 },
  { id: 'video-tutorial', name: '영상 설명', key: 36 },
]

export {
  ageData,
  attributeData,
  fitData,
  genderData,
  neckData,
  otherData,
  patternData,
  sleeveData,
  constructionData,
}
