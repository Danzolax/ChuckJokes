package com.zolax.chuckjokes.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Root(
    @Json(name = "type")
    var type: String? = null,
    @Json(name = "value")
    var value: List<Joke>? = null
)