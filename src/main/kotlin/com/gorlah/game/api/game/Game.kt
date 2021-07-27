package com.gorlah.game.api.game

data class Game (
    val id: String,
    val name: String,
    val category: GameCategory?,
    val status: GameStatus?,
    val summary: String,
)