package com.gorlah.game.api.configuration

import com.gorlah.game.api.GameApiProperties
import org.springframework.context.annotation.Bean
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authorization.AuthorizationContext
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@EnableWebFluxSecurity
class SecurityConfiguration {

    @Bean
    fun securityFilterChain(http: ServerHttpSecurity, properties: GameApiProperties): SecurityWebFilterChain {
        return http {
            authorizeExchange {
                authorize("/actions/**") { _, context ->
                    checkSecretHeader(context, properties.igdb.webhookSecret!!)
                }
                authorize(anyExchange, permitAll)
            }
            csrf {
                disable()
            }
        }
    }

    fun checkSecretHeader(context: AuthorizationContext, webhookSecret: String): Mono<AuthorizationDecision> {
        val secretHeader = context.exchange.request.headers["X-Secret"]
        return if (listOf(webhookSecret) == secretHeader) {
            AuthorizationDecision(true).toMono()
        } else {
            AuthorizationDecision(false).toMono()
        }
    }
}