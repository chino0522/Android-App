package com.bcit.miniapp.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.bcit.miniapp.data.*
import com.bcit.miniapp.data.CatRepository

class CatState (
    private val catRepository: CatRepository
) {

    var fact: MutableState<List<String>> = mutableStateOf(listOf(""))
    var image: MutableState<String> = mutableStateOf("")

    suspend fun getCatFact() {
        fact.value = catRepository.getFact().factList
    }

    suspend fun getCatImage() {
        image.value = catRepository.getImageURL().url
    }

    suspend fun refresh() {
        getCatImage()
        getCatFact()
    }
}