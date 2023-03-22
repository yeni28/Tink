import React, { useState, useEffect } from 'react'
import { GiHamburgerMenu } from 'react-icons/gi'
import { NavLink } from 'react-router-dom'

import { ReactComponent as yarn } from '@/assets/svg/yarn.svg'
import navStyle from '@/styles/navbar.module.css'

function Navbar() {
  const [prevScrollPos, setPrevScrollPos] = useState(0)
  const [visible, setVisible] = useState(true)
  const [clickHamburger, setClickHamburder] = useState(false)
  const onClickHandler = () => {
    setClickHamburder(!clickHamburger)
  }

  // new useEffect:
  useEffect(() => {
    const handleScroll = () => {
      // find current scroll position
      const currentScrollPos = window.pageYOffset
      setVisible(
        prevScrollPos > currentScrollPos ||
          prevScrollPos - currentScrollPos > 70 ||
          currentScrollPos < 10
      )
      setPrevScrollPos(currentScrollPos)
    }
    window.addEventListener('scroll', handleScroll)
    return () => window.removeEventListener('scroll', handleScroll)
  }, [prevScrollPos, visible])

  return (
    <header>
      <nav
        className={navStyle.navBack}
        style={{ top: visible ? '0' : '-6.25rem' }}
      >
        {/* Logo 영역 */}
        <div>
          <NavLink to="/" onClick={onClickHandler}>
            <div className="w-[200px] h-full bg-grey">Logo</div>
          </NavLink>
        </div>

        {/* Menu, Link 영역 */}

        {/* responsive web을 위한 Hamburger button */}
        <div
          className="xl:hidden block cursor-pointer"
          onClick={onClickHandler}
        >
          <GiHamburgerMenu />
        </div>

        {/* xl */}
        <div
          className={`${
            clickHamburger ? 'block' : 'hidden '
          } navStyle.menuList`}
        >
          <ul className={navStyle.menuList}>
            {/* <ul
            className={`navStyle.menuList ${
              'max-md' && clickHamburger ? navStyle.smallMenuList : 'hidden'
            } `}
          > */}
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
      </nav>
    </header>
  )
}

export default Navbar
