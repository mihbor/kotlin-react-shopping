package shopping

import shopping.model.Collection
import shopping.model.Item
import shopping.model.Section

val sectionsData = listOf(
  Section(1, "hats", "/static/images/hats.jpeg"),
  Section(2, "jackets", "/static/images/jackets.jpeg"),
  Section(3, "sneakers", "/static/images/sneakers.jpeg"),
  Section(4, "womens", "/static/images/womens.jpeg", true),
  Section(5, "mens", "/static/images/mens.jpeg", true)
)

val collectionsData = listOf(
  Collection(
    id= 1,
    title= "Hats",
    routeName= "hats",
    items= listOf(
      Item(
        id = 1,
        name = "Brown Brim",
        imageUrl = "https://i.ibb.co/ZYW3VTp/brown-brim.png",
        price = 2500
      ),
      Item(
        id = 2,
        name = "Blue Beanie",
        imageUrl = "https://i.ibb.co/ypkgK0X/blue-beanie.png",
        price = 1800
      ),
      Item(
        id = 3,
        name = "Brown Cowboy",
        imageUrl = "https://i.ibb.co/QdJwgmp/brown-cowboy.png",
        price = 3500
      ),
      Item(
        id = 4,
        name = "Grey Brim",
        imageUrl = "https://i.ibb.co/RjBLWxB/grey-brim.png",
        price = 2500
      ),
      Item(
        id = 5,
        name = "Green Beanie",
        imageUrl = "https://i.ibb.co/YTjW3vF/green-beanie.png",
        price = 1800
      ),
      Item(
        id = 6,
        name = "Palm Tree Cap",
        imageUrl = "https://i.ibb.co/rKBDvJX/palm-tree-cap.png",
        price = 1400
      ),
      Item(
        id = 7,
        name = "Red Beanie",
        imageUrl = "https://i.ibb.co/bLB646Z/red-beanie.png",
        price = 1800
      ),
      Item(
        id = 8,
        name = "Wolf Cap",
        imageUrl = "https://i.ibb.co/1f2nWMM/wolf-cap.png",
        price = 1400
      ),
      Item(
        id = 9,
        name = "Blue Snapback",
        imageUrl = "https://i.ibb.co/X2VJP2W/blue-snapback.png",
        price = 1600
      )
    )
  ),
  Collection(
    id = 2,
    title = "Sneakers",
    routeName = "sneakers",
    items = listOf(
      Item(
        id = 1,
        name = "Adidas NMD",
        imageUrl = "https://i.ibb.co/0s3pdnc/adidas-nmd.png",
        price = 22000
      ),
      Item(
        id = 2,
        name = "Adidas Yeezy",
        imageUrl = "https://i.ibb.co/dJbG1cT/yeezy.png",
        price = 28000
      ),
      Item(
        id = 3,
        name = "Black Converse",
        imageUrl = "https://i.ibb.co/bPmVXyP/black-converse.png",
        price = 11000
      ),
      Item(
        id = 4,
        name = "Nike White AirForce",
        imageUrl = "https://i.ibb.co/1RcFPk0/white-nike-high-tops.png",
        price = 16000
      ),
      Item(
        id = 5,
        name = "Nike Red High Tops",
        imageUrl = "https://i.ibb.co/QcvzydB/nikes-red.png",
        price = 16000
      ),
      Item(
        id = 6,
        name = "Nike Brown High Tops",
        imageUrl = "https://i.ibb.co/fMTV342/nike-brown.png",
        price = 16000
      ),
      Item(
        id = 7,
        name = "Air Jordan Limited",
        imageUrl = "https://i.ibb.co/w4k6Ws9/nike-funky.png",
        price = 19000
      ),
      Item(
        id = 8,
        name = "Timberlands",
        imageUrl = "https://i.ibb.co/Mhh6wBg/timberlands.png",
        price = 20000
      )
    )
  ),
  Collection(
    id = 3,
    title = "Jackets",
    routeName = "jackets",
    items = listOf(
      Item(
        id = 1,
        name = "Black Jean Shearling",
        imageUrl = "https://i.ibb.co/XzcwL5s/black-shearling.png",
        price = 12500
      ),
      Item(
        id = 2,
        name = "Blue Jean Jacket",
        imageUrl = "https://i.ibb.co/mJS6vz0/blue-jean-jacket.png",
        price = 9000
      ),
      Item(
        id = 3,
        name = "Grey Jean Jacket",
        imageUrl = "https://i.ibb.co/N71k1ML/grey-jean-jacket.png",
        price = 9000
      ),
      Item(
        id = 4,
        name = "Brown Shearling",
        imageUrl = "https://i.ibb.co/s96FpdP/brown-shearling.png",
        price = 16500
      ),
      Item(
        id = 5,
        name = "Tan Trench",
        imageUrl = "https://i.ibb.co/M6hHc3F/brown-trench.png",
        price = 18500
      )
    )
  ),
  Collection(
    id = 4,
    title = "Womens",
    routeName = "womens",
    items = listOf(
      Item(
        id = 1,
        name = "Blue Tanktop",
        imageUrl = "https://i.ibb.co/7CQVJNm/blue-tank.png",
        price = 2500
      ),
      Item(
        id = 2,
        name = "Floral Blouse",
        imageUrl = "https://i.ibb.co/4W2DGKm/floral-blouse.png",
        price = 2000
      ),
      Item(
        id = 3,
        name = "Floral Dress",
        imageUrl = "https://i.ibb.co/KV18Ysr/floral-skirt.png",
        price = 8000
      ),
      Item(
        id = 4,
        name = "Red Dots Dress",
        imageUrl = "https://i.ibb.co/N3BN1bh/red-polka-dot-dress.png",
        price = 8000
      ),
      Item(
        id = 5,
        name = "Striped Sweater",
        imageUrl = "https://i.ibb.co/KmSkMbH/striped-sweater.png",
        price = 4500
      ),
      Item(
        id = 6,
        name = "Yellow Track Suit",
        imageUrl = "https://i.ibb.co/v1cvwNf/yellow-track-suit.png",
        price = 13500
      ),
      Item(
        id = 7,
        name = "White Blouse",
        imageUrl = "https://i.ibb.co/qBcrsJg/white-vest.png",
        price = 2000
      )
    )
  ),
  Collection(
    id = 5,
    title = "Mens",
    routeName = "mens",
    items = listOf(
      Item(
        id = 1,
        name = "Camo Down Vest",
        imageUrl = "https://i.ibb.co/xJS0T3Y/camo-vest.png",
        price = 32500
      ),
      Item(
        id = 2,
        name = "Floral T-shirt",
        imageUrl = "https://i.ibb.co/qMQ75QZ/floral-shirt.png",
        price = 2000
      ),
      Item(
        id = 3,
        name = "Black & White Longsleeve",
        imageUrl = "https://i.ibb.co/55z32tw/long-sleeve.png",
        price = 2500
      ),
      Item(
        id = 4,
        name = "Pink T-shirt",
        imageUrl = "https://i.ibb.co/RvwnBL8/pink-shirt.png",
        price = 2500
      ),
      Item(
        id = 5,
        name = "Jean Long Sleeve",
        imageUrl = "https://i.ibb.co/VpW4x5t/roll-up-jean-shirt.png",
        price = 4000
      ),
      Item(
        id = 6,
        name = "Burgundy T-shirt",
        imageUrl = "https://i.ibb.co/mh3VM1f/polka-dot-shirt.png",
        price = 2500
      )
    )
  )
)