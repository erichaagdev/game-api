package com.gorlah.game.api.game

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class GameRouter {

    @Bean
    fun gameRoute(gameHandler: GameHandler): RouterFunction<ServerResponse> {
        return router {
            POST("/actions/games/create") { gameHandler.saveGame(it) }
            POST("/actions/games/update") { gameHandler.saveGame(it) }
        }
    }
}