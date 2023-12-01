package com.bcit.miniapp.data

enum class ApiEndPoints(
    val url:String
) {
    FACT_URL("https://meowfacts.herokuapp.com/"),
    IMAGE_BASE_URL("https://api.thecatapi.com/v1/images/search"),
}