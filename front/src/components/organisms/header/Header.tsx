import React from 'react'

import { useLocation, useNavigate, useParams } from 'react-router-dom'

import { HEADER_DATA } from '@/components/organisms/header/HeaderData'

export default function Header() {
  const location = useLocation()
  const navigate = useNavigate()
  const params = useParams()

  const setPageTitle = () => {
    let pageTitle
    let pageSubtitle

    HEADER_DATA.forEach((el) => {
      if (
        location.pathname === `${el.route}${params.id}` ||
        location.pathname === el.route
      ) {
        pageTitle = el.title
        pageSubtitle = el.subtitle
      }
    })
    return { pageTitle, pageSubtitle }
  }

  const showHeader = () => {
    let result = false

    for (const data of HEADER_DATA) {
      if (
        location.pathname === `${data.route}${params.id}` ||
        location.pathname === data.route
      ) {
        result = true
        break
      }
    }
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
