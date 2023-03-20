import atoms from '@/components/atoms'
import mocules from '@/components/mocules'

function HomePage() {
  return (
    <div className="HomePage">
      <atoms.ImageMd src="https://cdn.yarn.com/media/W1siZiIsIjIwMjIvMDkvMjIvMTIvNTEvMjEvNDU0OWE5YzAtZGY2Ni00OGU3LTk0MjEtYmM3NzJlZGJmMzYxL2ZpbGUuanBnIl1d/The%20Fibre%20Co%20%26Make%20Super%20Bulky.jpg?sha=0df30f20f78acb2f" />
      <mocules.CardMd />
    </div>
  )
}

export default HomePage
