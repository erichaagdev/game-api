package com.gorlah.game.api.game

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface GameRepository : ReactiveMongoRepository<Game, String>