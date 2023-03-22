import React, { useState, useEffect } from "react";
import { MenuData } from "@/components/organisms/NavbarMenuData";
import { NavLink } from "react-router-dom";
import { GiHamburgerMenu } from "react-icons/gi";
import { GrClose } from "react-icons/gr";
import { GrFormClose } from "react-icons/gr";
import "@/styles/newnavbar.css";

function NewNavbar() {
  const [openMenu, setOpenMenu] = useState(false);
  const onClickHandler = () => {
    setOpenMenu(!openMenu);
  };

  const [prevScrollPos, setPrevScrollPos] = useState(0);
  const [visible, setVisible] = useState(true);
  useEffect(() => {
    const handleScroll = () => {
      const currentScrollPos = window.pageYOffset;
      setVisible(
        prevScrollPos > currentScrollPos ||
          prevScrollPos - currentScrollPos > 70 ||
          currentScrollPos < 10
      );
      setPrevScrollPos(currentScrollPos);
    };
    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, [prevScrollPos, visible]);

  return (
    <nav
      className="flex justify-between items-center h-[6.25rem] bg-beige px-8 w-full fixed
      z-50"
      style={{ top: visible ? "0" : "-6.25rem" }}
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
          "ml:bg-beige ml:absolute ml:top-20 ml:left-1/2  ml:flex ml:flex-col ml:justify-start grid grid-cols-5 gap-5 list-none items-center text-center justify-end"
        }
      >
        {MenuData.map((item, index: number) => {
          return (
            <NavLink to={item.url} key={index}>
              <li className={item.cName}>{item.title}</li>
            </NavLink>
          );
        })}
      </ul>
    </nav>
  );
}

export default NewNavbar;
