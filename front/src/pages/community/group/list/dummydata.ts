interface Props extends CardTextProps {
  boardId: number
}

const group_list_dummy: Props[] = [
  {
    boardId: 1,
    onClick: () => console.log('디테일 들어가보자'),
    hit: 156,
    createDate: '2023-03-29',
    commentCnt: 10,
    title: '장덕동 뜨개원 구해요!😊',
    content:
      '<p>장덕동에서 뜨개구리를 함께 뜨는 뜨개구리 모임 입니다.</p> <br></br> <p>뜨개질을 마친 후엔 장인 족발집에서 회식을 할 예정입니다.</p> <br></br><p>뜨개구리의 배가 먼저 완성 될 지 저희의 뱃살이 먼저 완성될 지는 미지수 입니다.</p><br></br><p>맛과 뜨개, 두 가지를 모두 잡고 싶은 분이 계신다면 장덕동 뜨개뜨개로 주저 없이 조인해주세요.</p>',
    liked: 20,
    member: {
      memberId: 1,
      nickname: '개굴조아',
    },
  },
]

export default group_list_dummy
