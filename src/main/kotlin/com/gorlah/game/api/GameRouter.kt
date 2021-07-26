package com.gorlah.game.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router

@Configuration
class GameRouter {

    @Bean
    fun gameRoute(gameHandler: GameHandler): RouterFunction<ServerResponse> {
        return router {
            POST("/actions/games/create") { gameHandler.createGame(it) }
            POST("/actions/games/update") { gameHandler.updateGame(it) }
            POST("/actions/games/delete") { gameHandler.deleteGame(it) }
            GET("/games") {
                ServerResponse.ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .body(gameHandler.getGames())
            }
        }
    }
}