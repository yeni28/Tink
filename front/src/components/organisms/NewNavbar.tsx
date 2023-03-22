import React, { useState, useEffect } from "react";
import { MenuData } from "@/components/organisms/NavbarMenuData";
import { NavLink, useLocation } from "react-router-dom";
import { GiHamburgerMenu } from "react-icons/gi";
import { GrClose } from "react-icons/gr";

function NewNavbar() {
  // 작은 화면에서 menu 열고 닫는 부분
  const [openMenu, setOpenMenu] = useState(false);
  const onClickHandler = () => {
    setOpenMenu(!openMenu);
  };

  // router가 변하면 navbar도 닫히게 설정하는 부분
  let location = useLocation();
  useEffect(() => {
    setOpenMenu(false);
  }, [location]);

  // 스크롤 위치에 따라서 navbar 보이게 설정하는 부분
  const [prevScrollPos, setPrevScrollPos] = useState(0);
  const [visible, setVisible] = useState(true);
  useEffect(() => {
    const handleScroll = () => {
      const currentScrollPos = window.pageYOffset;
      setVisible(
        prevScrollPos > currentScrollPos ||
          prevScrollPos - currentScrollPos > 100 ||
          currentScrollPos < 10
      );
      // 메뉴가 열린상태로 스크롤 내리면 메뉴바 닫히게 설정
      if (openMenu && prevScrollPos < currentScrollPos) {
        setOpenMenu(false);
      }

      setPrevScrollPos(currentScrollPos);
    };
    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, [prevScrollPos, visible]);

  return (
    <nav
      className="flex justify-between items-center h-[6.25rem] bg-beige px-8 w-full fixed
      z-50 font-pop text-title3-bold"
      style={{ top: visible ? "0" : "-6.25rem", transition: "top 0.6s" }}
    >
      {/* Logo가 담기는 영역 */}
      <div className="text-red cursor-pointer justify-self-start">
        <NavLink to="/">
          <div className="">Tink</div>
        </NavLink>
      </div>
      {/* 햄버거 모양 아이콘 */}
      <div onClick={onClickHandler} className="ml:block hidden">
        {openMenu ? <GrClose /> : <GiHamburgerMenu />}
      </div>

      <ul
        className={
          (openMenu ? "" : "ml:hidden ") +
          " ml:bg-beige ml:absolute ml:top-0 ml:left-1/2 ml:pt-24 ml:flex ml:flex-col ml:justify-start grid grid-cols-5 gap-5 list-none items-center text-center justify-end"
        }
      >
        {MenuData.map((item, index: number) => {
          return (
            <NavLink to={item.url} key={index}>
              <li className="whitespace-nowrap ">{item.title}</li>
            </NavLink>
          );
        })}
      </ul>
    </nav>
  );
}

export default NewNavbar;
