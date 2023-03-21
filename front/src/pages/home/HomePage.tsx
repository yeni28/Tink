import atoms from '@/components/atoms'
import molecules from '@/components/molecules'

function HomePage() {
  return (
    <div className="HomePage">
      <atoms.ImageMd src="https://images.unsplash.com/photo-1679214523091-a9f9697ed10c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80" />
      <atoms.ImageLg
        alt="imageLg"
        src="https://images.unsplash.com/photo-1679214523091-a9f9697ed10c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80"
      />
      <molecules.CardSm
        isLiked={false}
        title={'White Mountains Light Even Longer Hello World!'}
        userName={'Midori Hirose'}
        userImgUrl={
          'https://i.natgeofe.com/k/ad9b542e-c4a0-4d0b-9147-da17121b4c98/MOmeow1_4x3.png'
        }
      />
      <molecules.CardMd
        author="뜨개왕 김뜨개"
        isFollow={false}
        likes={73}
        title="중요한건 귀여운 고양이"
        titleImgUrl="https://www.sisain.co.kr/news/photo/202110/45791_82634_4851.jpg"
        views={162}
      />
      <atoms.ButtonSquareRound
        bgColor="red"
        btnType="squareSm"
        innerValue="+ 팔로우"
        textColor="white"
        onClick={() => console.log('test')}
      />
      <atoms.ButtonSquareRound
        bgColor="red"
        btnType="squareMd1"
        innerValue="팔로우"
        textColor="white"
        onClick={() => console.log('test')}
      />
      <atoms.ButtonSquareRound
        bgColor="black"
        btnType="squareMd2"
        innerValue="난이도 평가"
        textColor="white"
        onClick={() => console.log('test')}
      />
      <atoms.ButtonSquareRound
        bgColor="black"
        btnType="roundSm"
        innerValue="색추천"
        textColor="white"
        onClick={() => console.log('test')}
      />
      <atoms.ButtonSquareRound
        bgColor="red"
        btnType="roundLg"
        innerValue="신청하러 가기"
        textColor="white"
        onClick={() => console.log('test')}
      />
    </div>
  )
}

export default HomePage
