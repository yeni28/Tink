import React from 'react'
import { Link } from 'react-router-dom'

import navStyle from '@/styles/navbar.module.css'

function Navbar() {
  const menus = [
    { id: 'Recommend', link: '/recommend' },
    { id: 'Tutorial', link: '/tutorial' },
    { id: 'Community', link: '/community' },
    { id: 'Campaign', link: '/campaign' },
    { id: 'MyPage', link: '/mypage' },
  ]

  return (
    <nav className={navStyle.navBack}>
      {/* Logo 영역 */}
      <div className="w-[200px] bg-grey">
        Logo
        <Link to="/" />
      </div>

      {/* Menu, Link 영역 */}
      <div>
        {/* map을 이용해서 router link 연결 */}
        <ul className={navStyle.menuList}>
          <li className={navStyle.menuItem}>Recommend</li>
          <li className={navStyle.menuItem}>Tutorial</li>
          <li className={navStyle.menuItem}>Communtiy</li>
          <li className={navStyle.menuItem}>Campaign</li>
          <li className={navStyle.menuItem}>My Page</li>
        </ul>
        {/* 추후 알림 창이 여기 들어가면 될 것 같습니다. */}
      </div>
    </nav>
  )
}

export default Navbar
