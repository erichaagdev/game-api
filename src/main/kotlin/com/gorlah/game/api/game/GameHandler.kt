package com.gorlah.game.api.game

import com.gorlah.game.api.igdb.IgdbGame
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono

@Component
class GameHandler(
    private val gameRepository: GameRepository,
) {

    private val log = LoggerFactory.getLogger(GameHandler::class.java)

    fun saveGame(request: ServerRequest): Mono<ServerResponse> {
        return request
            .bodyToMono<IgdbGame>()
            .map {
                Game(
                    id = it.id,
                    name = it.name,
                    category = GameCategory.fromValue(it.category),
                    status = GameStatus.fromValue(it.status),
                    summary = it.summary,
                )
            }
            .flatMap { gameRepository.save(it) }
            .doOnNext { log.info("Saved: $it") }
            .doOnError { log.error(it.message, it) }
            .flatMap { ok().build() }
    }
}