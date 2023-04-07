const question_detail_dummy: QuestionDetail = {
  boardId: 1,
  title: '목도리 뜨는 데 실 몇 볼이 필요할까요?',
  content:
    '<p>이번 겨울 옆구리는 시려도 목은 시리고 싶지 않아 목도리를 뜨려 합니다.</p> <p>뜨개질을 시작하는 데 목도리가 좋다고 하더라구요?</p><p>마음에 드는 색상이나 실은 있는데 실이 몇 볼 소요될지 감이 잘 안와서 여쭤봅니다!</p><p>뜨개 고수님들 알려주세요😆</p>',
  createdDate: '2023-03-30',
  updatedDate: '2023-03-31',
  liked: 15, // 좋아요수
  hit: 120, // 조회수
  member: {
    memberId: 1,
    nickname: '목도리 도마뱀',
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

export default question_detail_dummy
