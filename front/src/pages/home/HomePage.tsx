import { useState, useRef } from 'react'

import atoms from '@/components/atoms'
import molecules from '@/components/molecules'
import organisms from '@/components/organisms'

function HomePage() {
  // 댓글 Props
  const [comments, setComments] = useState<CommentProps[]>([])
  // 댓글 삭제
  const deleteComment = (id: number) => {
    setComments(
      comments.filter((comment) => {
        return comment.id !== id
      })
    )
  }
  return (
    <div className="HomePage">
      {/* <organisms.TitleBlock
        subtitle="키워드를 선택하면 맞춤 도안을 추천해드려요!"
        title="Recommend"
      /> */}
      <atoms.ImageMd src="https://images.unsplash.com/photo-1679214523091-a9f9697ed10c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80" />
      <atoms.ImageLg
        alt="imageLg"
        mainValue="도안"
        src="https://images.unsplash.com/photo-1679214523091-a9f9697ed10c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80"
        subValue="(기본적인 뜨개 방법을 알고 있다)"
        onClick={() => console.log('카드 눌러봄')}
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
        onClick={() => console.log('CardMd')}
      />
      <atoms.ButtonSquareSm
        bgColor="red"
        innerValue="+ 팔로우"
        textColor="white"
        onClick={() => console.log('ButtonSquareSm')}
      />
      <atoms.ButtonSquareMd1
        bgColor="red"
        innerValue="팔로우"
        textColor="white"
        onClick={() => console.log('ButtonSquareMd1')}
      />
      <atoms.ButtonSquareMd2
        bgColor="black"
        innerValue="난이도 평가"
        textColor="white"
        onClick={() => console.log('ButtonSquareMd2')}
      />
      <atoms.ButtonRoundSm
        bgColor="black"
        innerValue="색추천"
        textColor="white"
        onClick={() => console.log('ButtonRoundSm')}
      />
      <atoms.ButtonRoundLg
        bgColor="red"
        innerValue="신청하러 가기"
        textColor="white"
        onClick={() => console.log('ButtonRoundLg')}
      />
      <atoms.ButtonDoodle
        innerValue="다음"
        onClick={() => console.log('ButtonDoodle')}
      />
      <atoms.ButtonTag
        bgColor="red"
        innerValue="상의"
        textColor="white"
        onClick={() => console.log('ButtonTag')}
      />
      <organisms.ariThreeCard
        first={{
          alt: 'imageLg',
          mainValue: '도안',
          src: 'https://images.unsplash.com/photo-1677274207822-e726bbba46e3?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY3OTQ3MDE3Mg&ixlib=rb-4.0.3&q=80&w=1080',
          subValue: '(기본적인 뜨개 방법을 알고 있다)',
          onClick: () => console.log('카드를 눌러봤다'),
        }}
        second={{
          alt: 'imageLg',
          mainValue: '도안',
          src: 'https://images.unsplash.com/photo-1677796503531-8bc3388d987e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY3OTQ3MDE1NA&ixlib=rb-4.0.3&q=80&w=1080',
          subValue: '(기본적인 뜨개 방법을 알고 있다)',
          onClick: () => console.log('카드를 눌러봤다'),
        }}
        third={{
          alt: 'imageLg',
          mainValue: '도안',
          src: 'https://source.unsplash.com/random',
          subValue: '(기본적인 뜨개 방법을 알고 있다)',
          onClick: () => console.log('카드를 눌러봤다'),
        }}
      />
      <molecules.CardText onClick={() => console.log('디테일로 이동')} />
      <molecules.CommentInput
        comments={comments}
        setComments={(comment) => setComments(comment)}
      />
      <molecules.CommentList
        comments={comments}
        deleteComment={deleteComment}
      />
    </div>
  )
}

export default HomePage
