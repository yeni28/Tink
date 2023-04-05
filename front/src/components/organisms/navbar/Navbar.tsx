import React, { useState, useEffect } from 'react'

import { GiHamburgerMenu } from 'react-icons/gi'
import { GrClose } from 'react-icons/gr'
import { NavLink, useLocation } from 'react-router-dom'

import { GOOGLE_URL } from '@/apis/login/OAuth'

import { LogoutMenuData } from '@/components/organisms/navbar/LogoutNavbarMenuData'
import { MenuData } from '@/components/organisms/navbar/NavbarMenuData'

function NewNavbar() {
  // 라우터 이름 확인
  const routerName = window.location.pathname
  // 구글 로그인 창 열기
  function GoogleLogin() {
    window.location.href = GOOGLE_URL
  }

  // 로그인 여부 판단하기
  const [isLogin, setIsLogin] = useState(false)
  useEffect(() => {
    const token = localStorage.getItem('accessToken')
    if (token) setIsLogin(true)
  })

  // 작은 화면에서 menu 열고 닫는 부분
  const [openMenu, setOpenMenu] = useState(false)
  const onClickHandler = () => {
    setOpenMenu(!openMenu)
  }

  // router가 변하면 navbar도 닫히게 설정하는 부분
  const location = useLocation()
  useEffect(() => {
    setOpenMenu(false)
  }, [location])

  // 스크롤 위치에 따라서 navbar 보이게 설정하는 부분
  const [prevScrollPos, setPrevScrollPos] = useState(0)
  const [visible, setVisible] = useState(true)
  useEffect(() => {
    const handleScroll = () => {
      const currentScrollPos = window.pageYOffset
      setVisible(
        prevScrollPos > currentScrollPos ||
          prevScrollPos - currentScrollPos > 100 ||
          currentScrollPos < 10
      )
      // 메뉴가 열린상태로 스크롤 내리면 메뉴바 닫히게 설정
      if (openMenu && prevScrollPos < currentScrollPos) {
        setOpenMenu(false)
      }

      setPrevScrollPos(currentScrollPos)
    }
    window.addEventListener('scroll', handleScroll)
    return () => window.removeEventListener('scroll', handleScroll)
  }, [prevScrollPos, visible])

  const MenuDataNods = () => {
    return MenuData.map((item, index: number) => {
      return (
        <NavLink key={index} to={item.url}>
          <li className="whitespace-nowrap ">{item.title}</li>
        </NavLink>
      )
    })
  }
  const LogoutMenuDatas = () => {
    return (
      <>
        {LogoutMenuData.map((item, index: number) => {
          return (
            <NavLink key={index} to={item.url}>
              <li className="whitespace-nowrap ">{item.title}</li>
            </NavLink>
          )
        })}
        <li className="cursor-pointer" onClick={GoogleLogin}>
          login
        </li>
      </>
    )
  }

  return (
    <nav
      style={{ top: visible ? '0' : '-6.25rem', transition: 'top 0.6s' }}
      className={`flex justify-between items-center h-[6.25rem] ${
        routerName === '/'
          ? 'bg-gradient-to-l from-black text-white'
          : 'bg-beige'
      } px-8 w-full fixed
      z-50 font-pop text-title3-bold`}
    >
      {/* Logo가 담기는 영역 */}
      <div className="text-black cursor-pointer justify-self-start">
        <NavLink to="/">
          <div className="">Tink</div>
        </NavLink>
      </div>
      {/* 햄버거 모양 아이콘 */}
      <div className="ml:block hidden" onClick={onClickHandler}>
        {openMenu ? <GrClose /> : <GiHamburgerMenu />}
      </div>

      <ul
        className={
          (openMenu ? '' : 'ml:hidden ') +
          ' ml:bg-beige ml:absolute ml:top-0 ml:left-1/2 ml:pt-24 ml:flex ml:flex-col ml:justify-start grid grid-cols-5 gap-5 list-none items-center text-center justify-end'
        }
      >
        {isLogin ? MenuDataNods() : LogoutMenuDatas()}
      </ul>
    </nav>
  )
}

export default NewNavbar
