import React, { useState, useEffect } from 'react'
import { Link, NavLink } from 'react-router-dom'

import { ReactComponent as yarn } from '@/assets/svg/yarn.svg'
import navStyle from '@/styles/navbar.module.css'

function Navbar() {
  const [prevScrollPos, setPrevScrollPos] = useState(0)
  const [visible, setVisible] = useState(true)

  const handleScroll = () => {
    // find current scroll position
    const currentScrollPos = window.pageYOffset
    // set state based on location info (explained in more detail below)
    setVisible(
      prevScrollPos > currentScrollPos ||
        prevScrollPos - currentScrollPos > 70 ||
        currentScrollPos < 10
    )
    // set state to new scroll position
    setPrevScrollPos(currentScrollPos)
  }

  // new useEffect:
  useEffect(() => {
    window.addEventListener('scroll', handleScroll)
    return () => window.removeEventListener('scroll', handleScroll)
  }, [prevScrollPos, visible, handleScroll])

  return (
    <nav
      className={navStyle.navBack}
      style={{ top: visible ? '0' : '-6.25rem' }}
    >
      {/* Logo 영역 */}
      <div>
        <NavLink to="/">
          <div className="w-[200px] h-full bg-grey">Logo</div>
        </NavLink>
      </div>

      {/* Menu, Link 영역 */}
      <div>
        {/* map을 이용해서 router link 연결 */}
        <div>
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
        {/* point용 실타래가 들어가는 곳 */}
      </div>
    </nav>
  )
}

export default Navbar
