package com.gorlah.game.api.game

import com.fasterxml.jackson.annotation.JsonCreator

enum class GameStatus(val value: Int) {

    RELEASED(0),
    ALPHA(2),
    BETA(3),
    EARLY_ACCESS(4),
    OFFLINE(5),
    CANCELLED(6),
    RUMORED(7);

    companion object {
        private val gameStatusesByValue = values().associateBy { it.value }

        @JsonCreator
        fun fromValue(value: Int?): GameStatus? {
            value ?: return null
            return gameStatusesByValue[value]
        }
    }
}