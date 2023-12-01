package com.bcit.miniapp

import android.app.Application
import com.bcit.miniapp.data.CatRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

class MyApp : Application() {
    private val client by lazy {
        HttpClient {
            install(ContentNegotiation) {
                gson()
            }
        }
    }

    val catRepository by lazy {
        CatRepository(client)
    }
}