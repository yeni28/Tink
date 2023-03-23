import React from 'react'

import { useLocation, useNavigate } from 'react-router-dom'

import { HEADER_DATA } from '@/components/organisms/header/HeaderData'

export default function Header() {
  const location = useLocation()
  const navigate = useNavigate()

  const setPageTitle = () => {
    let pageTitle
    let pageSubtitle
    HEADER_DATA.forEach((el) => {
      if (el.route === location.pathname) {
        pageTitle = el.title
        pageSubtitle = el.subtitle
      }
    })
    return { pageTitle, pageSubtitle }
  }

  const showHeader = () => {
    let result = true
    if (location.pathname === '/' || location.pathname === '/tutorial')
      result = false
    return result
  }

  const getPageTitle = setPageTitle()
  return (
    <>
      {showHeader() && (
        <header
          className="mt-[2.63rem] mb-16 flex flex-col
        items-center"
        >
          <p className="mb-5 font-pop text-supertitle-bold">
            {getPageTitle.pageTitle}
          </p>
          <p className="text-title3">{getPageTitle.pageSubtitle}</p>
        </header>
      )}
    </>
  )
}
