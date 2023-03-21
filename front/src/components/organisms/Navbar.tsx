import React from 'react'
import { Link } from 'react-router-dom'

function Navbar() {
  const menus = [
    { id: 'Recommend', link: '/recommend' },
    { id: 'Tutorial', link: '/tutorial' },
    { id: 'Community', link: '/community' },
    { id: 'Campaign', link: '/campaign' },
    { id: 'MyPage', link: '/mypage' },
  ]

  return (
    <nav className="h-[6.25rem] flex justify-between">
      {/* Logo 영역 */}
      <div>
        <Link to="/" />
      </div>

      {/* Menu, Link 영역 */}
      <div>
        {/* map을 이용해서 router link 연결 */}
        <ul className="list-none">
          <li></li>
        </ul>
        {/* 추후 알림 창이 여기 들어가면 될 것 같습니다. */}
      </div>
    </nav>
  )
}

export default Navbar
