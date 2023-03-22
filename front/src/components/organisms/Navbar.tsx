import React from 'react'
import { Link, NavLink } from 'react-router-dom'

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
      <NavLink to="/">
        <div className="w-[200px] h-full bg-grey">Logo</div>
      </NavLink>

      {/* Menu, Link 영역 */}
      <div>
        {/* map을 이용해서 router link 연결 */}
        <ul className={navStyle.menuList}>
          <NavLink to="/recommend">
            <li className={navStyle.menuItem}>Recommend</li>
          </NavLink>
          <NavLink to="/tutorial">
            <li className={navStyle.menuItem}>Tutorial</li>
          </NavLink>
          <NavLink to="/community">
            <li className={navStyle.menuItem}>Community</li>
          </NavLink>
          <NavLink to="/campaign">
            <li className={navStyle.menuItem}>Campaign</li>
          </NavLink>
          <NavLink to="/mypage">
            <li className={navStyle.menuItem}>My Page</li>
          </NavLink>
        </ul>
        {/* 추후 알림 창이 여기 들어가면 될 것 같습니다. */}
      </div>
    </nav>
  )
}

export default Navbar
