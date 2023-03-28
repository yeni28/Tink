import React from 'react'
import { Outlet } from 'react-router-dom'

import CommunityToggle from '@/pages/community/components/CommunityToggle'

function Community() {
  return (
    <div>
      <CommunityToggle />
      <Outlet />
    </div>
  )
}
export default Community
