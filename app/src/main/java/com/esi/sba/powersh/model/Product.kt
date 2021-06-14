package com.esi.sba.powersh.model

data class Product(
    val id: Int,
    val title: String,
    val price:Int,
    val ImageId: Int = 0
)



data class CardItem(
    val id: Int,
    val title: String,
    val price:Int,
    val quantity:Int,
    val color:String,
    val size:Int,
    val ImageId: Int = 0
)

