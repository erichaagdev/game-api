package com.gorlah.game.api.game

enum class GameCategory(val value: Int) {

    MAIN_GAME(0),
    DLC_ADDON(1),
    EXPANSION(2),
    BUNDLE(3),
    STANDALONE_EXPANSION(4),
    MOD(5),
    EPISODE(6),
    SEASON(7),
    REMAKE(8),
    REMASTER(9),
    EXPANDED_GAME(10),
    PORT(11),
    FORK(12);

    companion object {
        private val gameCategoriesByValue = values().associateBy { it.value }

        fun fromValue(value: Int?): GameCategory? {
            value ?: return null
            return gameCategoriesByValue[value]
        }
    }
}