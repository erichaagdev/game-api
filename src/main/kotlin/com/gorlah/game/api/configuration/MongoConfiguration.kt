package com.gorlah.game.api.configuration

import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter

@Configuration
class MongoConfiguration {

    @Bean
    fun mongoConfigurer(
        mappingMongoConverter: MappingMongoConverter
    ) = ApplicationListener<ContextRefreshedEvent> {
        mappingMongoConverter.setTypeMapper(DefaultMongoTypeMapper(null))
    }
}