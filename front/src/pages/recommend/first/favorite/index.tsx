import React from 'react'

import atoms from '@/components/atoms'
import organisms from '@/components/organisms'

function FavoriteFirstRecommend() {
  return (
    <div>
      <div className="bg-white px-4 pt-7">
        <organisms.Patterns />
        <div className="mt-[4.65rem] flex justify-center pb-12 mb-16">
          <atoms.ButtonTag
            bgColor="red"
            innerValue="다른 도안"
            textColor="white"
            onClick={() => console.log('refresh')}
          />
        </div>
      </div>
    </div>
  )
}

export default FavoriteFirstRecommend
