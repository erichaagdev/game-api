package com.gorlah.game.api

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface GameRepository : ReactiveMongoRepository<Game, String>