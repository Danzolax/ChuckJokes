package com.zolax.chuckjokes.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Joke(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "joke")
    var joke: String? = null,
    @Json(name = "categories")
    var categories: List<String>? = null
)
