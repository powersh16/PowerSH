package com.esi.sba.powersh.model

import androidx.compose.runtime.mutableStateListOf
import com.esi.sba.powersh.R

object DataProvider {

    val productList = listOf(
        Product(
            id = 0,
            title = "Basket",
            price = 7000,
            ImageId = R.drawable.basket
        ),
        Product(
            id = 1,
            title = "Running",
            price = 6000,
            ImageId = R.drawable.running2
        ),
        Product(
            id = 2,
            title = "Swazilla",
            price = 8000,
            ImageId = R.drawable.swazila
        ),
        Product(
            id = 3,
            title = "versac",
            price = 4000,
            ImageId = R.drawable.versac2
        ),
        Product(
            id = 4,
            title = "Weird",
            price = 3000,
            ImageId = R.drawable.weird2
        ),
      /*  product(
            id = 6,
            title = "Lucy",
            description = "Picture yourself in a boat on a river, Lucy is a pup with kaleidoscope eyes.",
            puppyImageId = R.drawable.p6
        ),
        product(
            id = 7,
            title = "Astro",
            description = "Is it a bird? A plane? No, it's Astro blasting off into your heart!",
            puppyImageId = R.drawable.p7
        ),
        product(
            id = 8,
            title = "Boo",
            description = "Boo is just a teddy bear in disguise. What he lacks in grace, he makes up in charm.",
            puppyImageId = R.drawable.p8
        ),
        product(
            id = 9,
            title = "Pippa",
            description = "Pippa likes to look out the window and write pup-poetry.",
            puppyImageId = R.drawable.p9
        ),
        product(
            id = 10,
            title = "Coco",
            description = "Coco enjoys getting pampered at the local puppy spa.",
            puppyImageId = R.drawable.p10
        ),
        product(
            id = 11,
            title = "Brody",
            description = "Brody is a good boy, waiting for your next command.",
            puppyImageId = R.drawable.p11
        ),
        product(
            id = 12,
            title = "Stella",
            description = "Stella! Calm and always up for a challenge, she's the perfect companion.",
            puppyImageId = R.drawable.p12
        ),*/
    )




    val cartList = mutableStateListOf<CardItem>(

    CardItem(
        quantity = 1,
        color = "White",
        size = 38,
            id = 1,
            title = "Basket",
            price = 7000,
            ImageId = R.drawable.basket
        ),
        CardItem(
            quantity = 2,
            color = "Blue",
            size = 37,
            id = 2,
            title = "Running",
            price = 6000,
            ImageId = R.drawable.running2
        ),
        CardItem(
            quantity = 2,
            color = "Black",
            size = 41,
            id = 3,
            title = "Swazilla",
            price = 8000,
            ImageId = R.drawable.swazila
        ),
        CardItem(
            quantity = 1,
            color = "Blue",
            size = 39,
            id = 4,
            title = "versac",
            price = 4000,
            ImageId = R.drawable.versac2
        ),
        CardItem(
            quantity = 1,
            color = "Brown",
            size = 42,
            id = 5,
            title = "Weird",
            price = 3000,
            ImageId = R.drawable.weird2
        ),
    )

}