package com.nanaten.try_livedata_builder.entity

import com.squareup.moshi.Json

data class Repos(
    val id: Int,
    val name: String,
    @Json(name = "html_url") val htmlUrl: String,
    val owner: Owner
)

data class Owner(
    val id: Int,
    val login: String
)