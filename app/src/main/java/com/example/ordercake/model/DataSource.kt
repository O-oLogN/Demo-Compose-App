package com.example.ordercake.model

import com.example.ordercake.R

object DataSource {
    val cakeList: List<Cake> = listOf(
        Cake(cakeName = "Cupcake", cakeImageId = R.drawable.cupcake, cakePrice = 10),
        Cake(cakeName = "Donut", cakeImageId = R.drawable.donut, cakePrice = 20),
        Cake(cakeName = "Eclair", cakeImageId = R.drawable.eclair, cakePrice = 15),
        Cake(cakeName = "Honeycomb", cakeImageId = R.drawable.honeycomb, cakePrice = 25),
        Cake(cakeName = "Kitkat", cakeImageId = R.drawable.kitkat, cakePrice = 10)
    )

    val flavorOptionList: List<Option> = listOf(
        Option(
            optionInformation = "Vanilla",
            optionPrice = 10,
        ),
        Option(
            optionInformation = "Chocolate",
            optionPrice = 20,
        ),
        Option(
            optionInformation = "Red Velvet",
            optionPrice = 15,
        ),
        Option(
            optionInformation = "Salted Caramel",
            optionPrice = 25,
        ),
        Option(
            optionInformation = "Coffee",
            optionPrice = 10,
        ),
    )

    val dateOptionList: List<Option> = listOf(
        Option(optionInformation = "Mon 17 July"),
        Option(optionInformation = "Tue 18 July"),
        Option(optionInformation = "Thu 20 July"),
        Option(optionInformation = "Sat 22 July"),
        Option(optionInformation = "Sun 23 July"),
    )
    enum class Screen {
        Start,
        Flavor,
        Date,
        Summary,
    }
}