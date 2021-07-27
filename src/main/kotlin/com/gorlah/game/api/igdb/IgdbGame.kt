package com.gorlah.game.api.igdb

data class IgdbGame (
    val id: String,
    val name: String,
    val category: Int?,
    val status: Int?,
    val summary: String,
)