interface Props extends Omit<CardMdProps, 'onClick'> {
  id: number
}

const review_list_dummy: Props[] = [
  {
    id: 1,
    titleImgUrl: 'https://source.unsplash.com/random',
    views: 156,
    likes: 7456,
    isFollow: false,
    title: 'Calablanca Top',
    author: 'Cris Dvic',
  },
  {
    id: 2,
    titleImgUrl: 'https://source.unsplash.com/random',
    views: 156,
    likes: 7456,
    isFollow: true,
    title: 'Joni',
    author: 'Natasja Hornby',
  },
  {
    id: 3,
    titleImgUrl: 'https://source.unsplash.com/random',
    views: 156,
    likes: 7456,
    isFollow: true,
    title: 'Ranunculus',
    author: 'Midori Hirose',
  },
  {
    id: 4,
    titleImgUrl: 'https://source.unsplash.com/random',
    views: 156,
    likes: 7456,
    isFollow: false,
    title: 'Spring Sweater',
    author: 'Kelbourne Woolens Scout',
  },
  {
    id: 5,
    titleImgUrl: 'https://source.unsplash.com/random',
    views: 156,
    likes: 7456,
    isFollow: true,
    title: 'Perfect Knit T-Shirt',
    author: 'Kaitlin Barthold',
  },
  {
    id: 6,
    titleImgUrl: 'https://source.unsplash.com/random',
    views: 156,
    likes: 7456,
    isFollow: false,
    title: 'Temptation Mittens',
    author: 'Traci Scott',
  },
  {
    id: 7,
    titleImgUrl: 'https://source.unsplash.com/random',
    views: 156,
    likes: 7456,
    isFollow: false,
    title: 'DRK Everyday Cowl',
    author: 'Andrea Mowry',
  },
  {
    id: 8,
    titleImgUrl: 'https://source.unsplash.com/random',
    views: 156,
    likes: 7456,
    isFollow: true,
    title: 'Bristol Fashion',
    author: 'Jaala Spiro',
  },
  {
    id: 9,
    titleImgUrl: 'https://source.unsplash.com/random',
    views: 156,
    likes: 7456,
    isFollow: false,
    title: 'RebootTOP',
    author: 'La Maison Rililie',
  },
  {
    id: 10,
    titleImgUrl: 'https://source.unsplash.com/random',
    views: 156,
    likes: 7456,
    isFollow: true,
    title: 'Baseline',
    author: 'Susanne Sommer',
  },
  {
    id: 11,
    titleImgUrl: 'https://source.unsplash.com/random',
    views: 156,
    likes: 7456,
    isFollow: false,
    title: 'Sophie Scarf',
    author: 'PetiteKnit',
  },
  {
    id: 12,
    titleImgUrl: 'https://source.unsplash.com/random',
    views: 156,
    likes: 7456,
    isFollow: true,
    title: 'Daisy Maze Hat',
    author: 'Kelly Gaffney',
  },
]
export default review_list_dummy
