package com.gorlah.game.api.configuration

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction

@Configuration
class WebClientConfiguration {

    @Bean
    fun authorizedClientManager(
        clientRegistrationRepository: ReactiveClientRegistrationRepository,
        authorizedClientService: ReactiveOAuth2AuthorizedClientService,
    ): ReactiveOAuth2AuthorizedClientManager {
        return AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
            clientRegistrationRepository, authorizedClientService)
    }

    @Bean
    fun oAuth2WebClientCustomizer(
        authorizedClientManager: ReactiveOAuth2AuthorizedClientManager,
    ) = WebClientCustomizer {
        val oAuth2Filter = ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager)
        it.filter(oAuth2Filter)
    }
}