const group_detail_dummy: GroupDetail = {
  boardId: 1,
  title: '장덕동 뜨개원 구해요!',
  content:
    '<p>장덕동에서 뜨개구리를 함께 뜨는 뜨개구리 모임 입니다.</p>  <p>뜨개질을 마친 후엔 장인 족발집에서 회식을 할 예정입니다.</p> <p>뜨개구리의 배가 먼저 완성 될 지 저희의 뱃살이 먼저 완성될 지는 미지수 입니다.</p><p>맛과 뜨개, 두 가지를 모두 잡고 싶은 분이 계신다면 장덕동 뜨개뜨개로 주저 없이 조인해주세요.</p>',
  createdDate: '2023-03-30',
  updatedDate: '2023-03-31',
  liked: 15, // 좋아요수
  hit: 120, // 조회수
  member: {
    memberId: 1,
    nickname: '장덕좋아',
    thumbnail: {
      thumbnailId: 2,
      thumbImg: 'https://images.unsplash.com/random',
    },
  },
  commentCnt: 1, // 댓글 총 개수
  comments: [
    {
      commentId: 1,
      updatedDate: '2023-03-31',
      content:
        '장덕동에 더 맛있는 집을 제가 많이 알 고 있는데 감히 추천드려도 괜찮을까요?',
      nickname: '김앨리스',
      thumbImg:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8cHJvZmlsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60',
    },
  ],
}

export default group_detail_dummy
