const pattern_dummy = [
  {
    id: 1,
    src: 'https://images4-f.ravelrycache.com/uploads/crisdvic/914958056/R5b_small2.JPEG',
    title: 'Calablanca Top',
    userImgUrl:
      'https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1143&q=80',
    userName: 'Cris Dvic',
  },
  {
    id: 2,
    src: 'https://images4-g.ravelrycache.com/uploads/Moonstruckmermaid/914451659/Heroe1_crop_small_best_fit.jpg',
    title: 'Joni',
    userImgUrl:
      'https://images.unsplash.com/photo-1573865526739-10659fec78a5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=715&q=80',
    userName: 'Natasja Hornby',
  },
  {
    id: 3,
    src: 'https://images4-g.ravelrycache.com/uploads/MidoriHirose/475388370/Blob_small_best_fit',
    title: 'Ranunculus',
    userImgUrl:
      'https://images.unsplash.com/photo-1495360010541-f48722b34f7d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=736&q=80',
    userName: 'Midori Hirose',
  },
  {
    id: 4,
    src: 'https://images4-g.ravelrycache.com/uploads/KelbourneWoolens/914306504/Spring_Sweater_1-2_sm_small_best_fit.jpg',
    title: 'Spring Sweater',
    userImgUrl:
      'https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80',
    userName: 'Kelbourne Woolens Scout',
  },
  {
    id: 5,
    src: 'https://images4-g.ravelrycache.com/uploads/originallylovely/787571731/webp/Knit_Tshirt-5085_small_best_fit.webp#jpg',
    title: 'Perfect Knit T-Shirt',
    userImgUrl:
      'https://images.unsplash.com/photo-1519052537078-e6302a4968d4?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80',
    userName: 'Kaitlin Barthold',
  },
  {
    id: 6,
    src: 'https://images4-g.ravelrycache.com/uploads/FleeceLoveHappy/915013498/temp1_small.jpg',
    title: 'Temptation Mittens',
    userImgUrl:
      'https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1143&q=80',
    userName: 'Traci Scott',
  },
  {
    id: 7,
    src: 'https://images4-g.ravelrycache.com/uploads/dreareneeknits/914059244/5J6A5237_small_best_fit.jpg',
    title: 'DRK Everyday Cowl',
    userImgUrl:
      'https://images.unsplash.com/photo-1529778873920-4da4926a72c2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=736&q=80',
    userName: 'Andrea Mowry',
  },
  {
    id: 8,
    src: 'https://images4-f.ravelrycache.com/uploads/jaaladay/915001939/20230325_090135_small.jpg',
    title: 'Bristol Fashion',
    userImgUrl:
      'https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1143&q=80',
    userName: 'Jaala Spiro',
  },
  {
    id: 9,
    src: 'https://images4-g.ravelrycache.com/uploads/rililie/914958827/RebootTop_worn79-261080_small2.jpg',
    title: 'RebootTOP',
    userImgUrl:
      'https://images.unsplash.com/photo-1606214174585-fe31582dc6ee?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80',
    userName: 'La Maison Rililie',
  },
  {
    id: 10,
    src: 'https://images4-f.ravelrycache.com/uploads/sosu/914807590/SOSU-BaselineSweater-37_small_best_fit.jpg',
    title: 'Baseline',
    userImgUrl:
      'https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1143&q=80',
    userName: 'Susanne Sommer',
  },
  {
    id: 11,
    src: 'https://images4-g.ravelrycache.com/uploads/PetiteKnitDK/874227252/sophie_scarf_lang_brun3_small_best_fit.JPG',
    title: 'Sophie Scarf',
    userImgUrl:
      'https://images.unsplash.com/photo-1513360371669-4adf3dd7dff8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80',
    userName: 'PetiteKnit',
  },
  {
    id: 12,
    src: 'https://images4-g.ravelrycache.com/uploads/Kellyg232/759204633/webp/IMG_3119_small_best_fit.webp#jpeg',
    title: 'Daisy Maze Hat',
    userImgUrl:
      'https://images.unsplash.com/photo-1596854372407-baba7fef6e51?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880&q=80',
    userName: 'Kelly Gaffney',
  },
]
export { pattern_dummy }
