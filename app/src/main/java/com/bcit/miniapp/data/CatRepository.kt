package com.bcit.miniapp.data

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CatRepository(
    private val client: HttpClient
) {
    suspend fun getFact() : Fact {
        val factResponse = client.get(ApiEndPoints.FACT_URL.url)
        val factJson = factResponse.body<JsonObject>().toString()

        println(factJson)

        return deserializeFactJson(factJson)
    }

    suspend fun getImageURL() : Image {
        val imageResponse = client.get(ApiEndPoints.IMAGE_BASE_URL.url)
        val imageJson = imageResponse.body<JsonArray>()[0].toString()

        return deserializeImageJson(imageJson)
    }

    private fun deserializeImageJson(json:String) : Image {
        return Gson().fromJson(json, Image::class.java)
    }

    private fun deserializeFactJson(json:String) : Fact {
        return Gson().fromJson(json, Fact::class.java)
    }
}