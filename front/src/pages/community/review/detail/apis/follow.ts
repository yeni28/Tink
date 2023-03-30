import React from 'react'

function switchFollow(
  follow: boolean,
  setFollow: React.Dispatch<React.SetStateAction<boolean>>
) {
  setFollow(!follow)
}

export { switchFollow }
