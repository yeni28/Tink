const review_detail_dummy: ReviewDetail = {
  isLike: true,
  title: '우리집 강아지 뜨개를 좋아해',
  content:
    '<p>우리집 강아지가 뜨개질 된 니트를 좋아합니다.</p><p>그래서 제가 뜨개질을 해서 니트를 짜줬더니 정말 좋아해요</p>',
  boardCategory: 'review',
  material: {
    yarnName: '짱좋은 털',
    yarnWeight: 30,
    yarnLength: 200,
    needle: '대바늘 2호',
    time: '일주일',
  },
  boardId: 1,
  commentCnt: 1,
  comments: [
    {
      commentId: 1,
      updatedDate: '2023-03-29',
      content: '강아지가 너무 귀여워요',
      nickname: '멍멍좋아',
      thumbImg:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8cHJvZmlsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60',
    },
  ],
  hit: 10,
  isfollowed: false,
  isLiked: false,
  liked: 20,
  patternId: 123,
  patternThumbImg: 'https://source.unsplash.com/random',
  member: {
    memberId: 1,
    nickname: '뜨개멍이',
    thumbnail: {
      thumbImg: 'https://source.unsplash.com/random',
    },
  },
}

export default review_detail_dummy
