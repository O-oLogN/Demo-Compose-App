package com.example.ordercake.model

import androidx.annotation.DrawableRes

data class Cake(
    val cakeName: String = "",
    @DrawableRes val cakeImageId: Int = 0,
    val cakePrice: Int = 0,
)
/**
 *    [optionPrice] only applies for Flavor
 */
data class Option(
    val optionInformation: String = "",
    val optionPrice: Int = 0,
)
data class Product(
    val name: String = "",
    val quantity: Int = 0,
    val flavor: Option = Option(),
    val date: Option = Option(),
    val subtotal: Int = 0
)