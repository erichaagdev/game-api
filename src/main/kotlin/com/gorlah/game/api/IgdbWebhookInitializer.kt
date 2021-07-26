package com.gorlah.game.api

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Profile
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.BodyInserters.fromFormData
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Component
@Profile("!test")
class IgdbWebhookInitializer(
    private val igdbWebClient: WebClient,
    private val properties: GameApiProperties,
) : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        Mono.just(event)
            .flatMap { registerWebhook("/games", "create") }
            .flatMap { registerWebhook("/games", "update") }
            .flatMap { registerWebhook("/games", "delete") }
            .subscribe()
    }

    private fun registerWebhook(endpoint: String, method: String): Mono<JsonNode> {
        return igdbWebClient
            .post()
            .uri("$endpoint/webhooks")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(fromFormData(buildWebhookRegistration(endpoint, method)))
            .retrieve()
            .bodyToMono()
    }

    private fun buildWebhookRegistration(endpoint: String, method: String): MultiValueMap<String, String> {
        return LinkedMultiValueMap(mapOf(
            "url" to listOf(properties.igdb.webhookCallbackUrl + "/actions$endpoint/$method"),
            "method" to listOf(method),
            "secret" to listOf(properties.igdb.webhookSecret),
        ))
    }
}