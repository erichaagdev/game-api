package com.gorlah.game.api.igdb

import com.gorlah.game.api.GameApiProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class IgdbConfiguration {

    @Bean
    fun igdbWebClient(
        webClientBuilder: WebClient.Builder,
        clientRegistrationRepository: ReactiveClientRegistrationRepository,
        properties: GameApiProperties,
    ): WebClient {
        return webClientBuilder
            .baseUrl("https://api.igdb.com/v4")
            .defaultHeaders {
                it.contentType = MediaType.APPLICATION_JSON
                it["Client-ID"] = properties.twitch.clientId
            }
            .defaultRequest { it.attributes(clientRegistrationId("twitch")) }
            .build()
    }
}