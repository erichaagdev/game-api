package com.gorlah.game.api

import com.fasterxml.jackson.databind.JsonNode
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class GameHandler(
    private val gameRepository: GameRepository,
) {

    private val logger = LoggerFactory.getLogger(GameHandler::class.java)

    fun getGames(): Flux<Game> {
        return gameRepository.findAll()
    }

    fun createGame(request: ServerRequest): Mono<ServerResponse> {
        return request
            .bodyToMono<JsonNode>()
            .doOnNext { logger.info("Create: $it") }
            .map {
                Game(
                    id = it["id"].asText(),
                    name = it["name"].asText(),
                )
            }
            .flatMap { gameRepository.save(it) }
            .flatMap { ok().build() }
    }

    fun updateGame(request: ServerRequest): Mono<ServerResponse> {
        return request
            .bodyToMono<JsonNode>()
            .doOnNext { logger.info("Update: $it") }
            .map {
                Game(
                    id = it["id"].asText(),
                    name = it["name"].asText(),
                )
            }
            .flatMap { gameRepository.save(it).toMono() }
            .flatMap { ok().build() }
    }

    fun deleteGame(request: ServerRequest): Mono<ServerResponse> {
        return request
            .bodyToMono<JsonNode>()
            .doOnNext { logger.info("Delete: $it") }
            .flatMap { ok().build() }
    }
}